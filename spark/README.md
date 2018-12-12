2018-05-21
# 数据类型 
## mac
- StationMac：移动设备记录
- APMac：开启热点的设备记录
- VI：移动设备登入的虚拟身份记录
## 心跳
- Heartbeat：标明机具上下线信息的记录
## 基本信息
- Place： 地点信息
- Manufacturer： 机具提供商信息
- MacMachine： 机具信息
# 职责
## mac记录
- 统计：每个机具接收的每种类型记录的数量，分为合并前和合并后
- 合并：同一机具接收到的同一mac连续记录
- 报警：判断未合并记录是否符合报警规则
## 心跳
- 上线：判断机具是否上线
## 基本信息
- 更新：根据上传的基本信息，插入新记录或者更新对应记录
# 逻辑
## mac记录
- AlarmAndStatistics(统计未合并记录和报警)
1. 从MySql中读取最新监控规则，并开启redis订阅线程，订阅监控规则，实时更新并广播报警规则
2. 从kafka获取记录，反序列化，并按照记录类型，机具，记录日期进行分组
3. 统计每组内的记录条数，更新redis，针对每条记录，如果符合监控规则，发布到redis，并插入到HBase
### 记录格式
表名为日期：YYYYMMDD  
表中key为  
1. 机具编号:uncompress:StationMac(192168243A4FB8D0241FB:uncompress:StationMac)
2. 机具编号:uncompress:ApMac(192168243A4FB8D0241FB:uncompress:ApMac)
3. 机具编号:uncompress:VI:VIType(192168243A4FB8D0241FB:uncompress:VI:tian-ya-lun-tan)  
value为当前日期累计统计量  
报警发布订阅通道为: UserId, 消息为MonitorAlarmRecord的json串 
- CompressAndStatistics（合并记录和统计数量）
### 合并时间间隔：同一mac地址记录连续在同一机具出现的时间间隔 小于 此配置， 则进行合并
### 最长保留时间间隔：同一mac地址记录在接收到后最多保留多长时间后，输出
1. 从kafka获取记录，反序列化，并按照记录类型，设备mac进行分组
2. 判断是否合并
- 判断是否超过合并时间间隔，此mac都没有更新过，超过则插入HBase,更新Redis计数
- 判断是否超过最长保留时间间隔，超过则插入HBase,更新Redis计数
- 根据记录进入时间进行排序，判断相邻两条记录的时间是否不属于同一机具采集到或者时间间隔大于合并时间间隔，如果满足，插入HBase,更新Redis计数
- 如果记录是虚拟身份记录类型，判断相邻两条记录是否属于同一类型虚拟身份，是否属于同一账号，不符合插入HBase,更新Redis计数
- 更新上条记录的离开时间为当前记录的离开时间，返回上条记录
### 记录格式
表名为日期：YYYYMMDD  
表中key为  
1. 机具编号:compress:StationMac(192168243A4FB8D0241FB:compress:StationMac)
2. 机具编号:compress:ApMac(192168243A4FB8D0241FB:compress:ApMac)
3. 机具编号:compress:VI:VIType(192168243A4FB8D0241FB:compress:VI:tian-ya-lun-tan)  
value为当前日期累计统计量
## 基本信息
- BasicUpdate（更新基本信息）
1. 从kafka获取记录，反序列化
2. 按照记录类型插入到Mysql相关表中（因为接收到的信息包含字段小于数据库对应表字段，需要先查询数据库，补齐字段）
## 心跳记录
- Heartbeat（更新机具的最新心跳信息）
1. 从kafka获取记录，反序列化
2. 更新机具的对应时间戳到redis。如果redis中原先没有此机具，则插入一条机具上线日志到mysql
### 记录格式
表名为heartbeat  
key为机具编号  
value为最新心跳时间戳(ms)  
key "192168242A4FB8D034237" value "1526723022058"
# 运行
## 打包
在foundation-platform目录下
```
mvn clean scala:compile compile package -DskipTests
```
## 运行
1. jar包提交到hdfs
```
hadoop fs -put -f spark-1.0-SNAPSHOT.jar /app/jars/
```
2. 修改spark.properties配置文件
3. 提交程序
### 直接提交
```
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.BasicUpdate --properties-file spark-bu.properties spark-1.0-SNAPSHOT.jar
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.AlarmAndStatistics --properties-file spark-aas.properties spark-1.0-SNAPSHOT.jar
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.CompressAndStatistics --properties-file spark-cas.properties spark-1.0-SNAPSHOT.jar
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.Heartbeat --properties-file spark-hb.properties spark-1.0-SNAPSHOT.jar
```
### Driver-HA(目前Driver挂掉无法重启 )
```
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.BasicUpdate --deploy-mode cluster --supervise --properties-file sparkha-bu.properties hdfs://nameservice1/app/jars/spark-1.0-SNAPSHOT.jar
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.AlarmAndStatistics --deploy-mode cluster --supervise --properties-file sparkha-aas.properties hdfs://nameservice1/app/jars/spark-1.0-SNAPSHOT.jar
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.CompressAndStatistics --deploy-mode cluster --supervise --properties-file sparkha-cas.properties hdfs://nameservice1/app/jars/spark-1.0-SNAPSHOT.jar
spark-submit --class edu.xd.ridelab.foundationplatform.sparkstreaming.Heartbeat --deploy-mode cluster --supervise --properties-file sparkha-hb.properties hdfs://nameservice1/app/jars/spark-1.0-SNAPSHOT.jar
```
```
Exception in thread "main" java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.spark.deploy.worker.DriverWrapper$.main(DriverWrapper.scala:58)
	at org.apache.spark.deploy.worker.DriverWrapper.main(DriverWrapper.scala)
Caused by: org.apache.spark.SparkException: Found both spark.executor.extraJavaOptions and SPARK_JAVA_OPTS. Use only the former.
	at org.apache.spark.SparkConf$$anonfun$validateSettings$6$$anonfun$apply$5.apply(SparkConf.scala:470)
	at org.apache.spark.SparkConf$$anonfun$validateSettings$6$$anonfun$apply$5.apply(SparkConf.scala:468)
	at scala.collection.immutable.List.foreach(List.scala:318)
	at org.apache.spark.SparkConf$$anonfun$validateSettings$6.apply(SparkConf.scala:468)
	at org.apache.spark.SparkConf$$anonfun$validateSettings$6.apply(SparkConf.scala:454)
	at scala.Option.foreach(Option.scala:236)
	at org.apache.spark.SparkConf.validateSettings(SparkConf.scala:454)
	at org.apache.spark.SparkContext.<init>(SparkContext.scala:400)
	at org.apache.spark.SparkContext$.getOrCreate(SparkContext.scala:2311)
	at org.apache.spark.streaming.StreamingContext.<init>(StreamingContext.scala:140)
	at org.apache.spark.streaming.StreamingContext$$anonfun$getOrCreate$1.apply(StreamingContext.scala:864)
	at org.apache.spark.streaming.StreamingContext$$anonfun$getOrCreate$1.apply(StreamingContext.scala:864)
	at scala.Option.map(Option.scala:145)
	at org.apache.spark.streaming.StreamingContext$.getOrCreate(StreamingContext.scala:864)
	at edu.xd.ridelab.foundationplatform.sparkstreaming.StreamingTest$.main(StreamingTest.scala:20)
	at edu.xd.ridelab.foundationplatform.sparkstreaming.StreamingTest.main(StreamingTest.scala)
	... 6 more
```
### 查看spark应用状态
```
curl http://iotp-2:6066/v1/submissions/status/driver-20151008145126-0000
```