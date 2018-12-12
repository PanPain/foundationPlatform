package edu.xd.ridelab.foundationplatform.redisUtils

import java.time.ZoneId
import java.util.Date

import edu.xd.ridelab.foundationplatform.mysql.vo.Rule
import edu.xd.ridelab.foundationplatform.record.TransferRecord
import org.apache.commons.lang.StringUtils

/**
  * 根据监控规则判断记录是否符合条件工具类
  * @author cwz
  * @date 2018/05/08
  * @since 0.0.0
  */
object RulesJudger {
  /**
    * 根据监控规则判断记录是否符合条件
    * @param rule 监控规则
    * @param record 记录
    * @return 是否满足监控条件
    */
  def judge(rule: Rule, record: TransferRecord): Boolean = {
    // 报警规则是否开启
    if(rule.getStatus.equals("00")){
      // 监控是否存在目标mac或者与目标mac是否符合
      if(StringUtils.isEmpty(rule.getTargetMac) || rule.getTargetMac.equals(record.getMac)){
        // 记录时间是否在监控时间范围内
        if(dateBetween(record, rule.getRuleCreateTime, rule.getRuleExpireTime)){
          // 记录机具是否在监控机具范围内
          val result = rule.getRuleArea.split(",").find(_.equals(record.getDevmac))
          if(result.nonEmpty) return true
        }
      }
    }
    return false
  }

  /**
    * 根据mac记录的进入时间和离开时间，监控规则的有效时间，判断一条记录时间是否在监控规则时间范围内
    * @param record mac记录
    * @param ruleEffectiveTime 监控规则生效时间
    * @param ruleExpireTime 监控规则失效时间
    * @return 一条记录时间是否在监控规则时间范围内
    */
  def dateBetween(record: TransferRecord, ruleEffectiveTime: Date, ruleExpireTime: Date): Boolean ={
    val inTime = ruleEffectiveTime.toInstant.atZone(ZoneId.systemDefault()).toLocalDateTime
    val outTime = ruleExpireTime.toInstant.atZone(ZoneId.systemDefault()).toLocalDateTime
    if(record.getFirstTime.compareTo(outTime) > 0 || record.getLastTime.compareTo(inTime) < 0) false else true
  }

}
