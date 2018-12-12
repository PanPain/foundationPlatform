//柱状图
function initBarView(values){
    //var len=xArr.length;
    $('#barChart').html("");
    var xArr=[];
    var len=values.length;
    for(var i=0;i<len;i++){
        if(i==0){
            xArr[i]="对河口银子山村-余杭交界";
        }
        else if(i==Math.floor(len*2/3)){
            xArr[i]="安装位置：三墩村-前村路口";
        }
        else{
            xArr[i]="";
        }
    }
    var chart = new twaver.charts.BarChart();
    var box = chart.getDataBox();

    chart.setType("group");
    var data = new twaver.Element();
    data.setStyle("chart.values", values);
    data.setStyle("chart.color", "red");
    

    box.add(data);
    chart.setXScaleTexts(new twaver.List(xArr));
    chart.setLowerLimit(0);
    chart.setYScaleValueGap(500);
    chart = chart.getView();

    var dom=$('#barChart');
    var width=dom.css('width');
    var height=dom.css('height');
    chart.style.width = width;
    chart.style.height = height;
    $("#barChart").append(chart);
}
//线型图
function initLineView(xArr,values){
    var len=xArr.length;
    $('#lineChart').html("");

    var chart = new twaver.charts.LineChart();
    box = chart.getDataBox();

    var data = new twaver.Element();
    data.setStyle("chart.values", values);
    data.setStyle("chart.color", "red");
    box.add(data);
    
    chart.setXScaleTexts(new twaver.List(xArr));
    chart.setBackgroundVisible(false);  //不显示背景
    chart.setLowerLimit(0);
    chart.setYScaleValueGap(500);
    chart = chart.getView();

    var dom=$('#lineChart');
    var width=dom.css('width');
    var height=dom.css('height');
    
    chart.style.width = width;
    chart.style.height = height;
    
   
    $("#lineChart").append(chart);
}
//饼图1
function initPieView(arr,domEle){
    var str="#"+domEle;
    $(str).html("");
    var chart = new twaver.charts.PieChart();
    box = chart.getDataBox();

    createData('微博', arr[0], "#48d1cc");
    createData('QQ', arr[1], "#cd853f");
    createData('微信', arr[2], "gray");
    createData('其他', arr[3], "green");
   
    chart.setType("circle");
    chart.formatValueText = function(value, data){
    return data.getName() + ':'+parseInt(value/chart.getSum()*100) + '%';
    };
   
    var chartDom = chart.getView();

    var dom=$(str);
    var width=dom.css('width');
    var height=dom.css('height');

    chartDom.style.width = width;
    chartDom.style.height = height;
    $(str).append(chartDom);
}
//饼图2
function initPieView2(arr,domEle){
    var str="#"+domEle;
    $(str).html("");
    var chart = new twaver.charts.PieChart();
    box = chart.getDataBox();

    createData('手机号码', arr[0], "red");
    createData('IMEI', arr[1], "#2f4f4f");
    createData('IMSI', arr[2], "#48d1cc");
    createData('QQ', arr[3], "#cd853f");
    createData('微信', arr[4], "gray");
    createData('其他', arr[5], "green");
   
    chart.setType("circle");
    chart.formatValueText = function(value, data){
    return data.getName() + ':'+parseInt(value/chart.getSum()*100) + '%';
    };
   
    var chartDom = chart.getView();

    var dom=$(str);
    var width=dom.css('width');
    var height=dom.css('height');

    chartDom.style.width = width;
    chartDom.style.height = height;
    $(str).append(chartDom);
}
function createData(name, value, color){
    var data = new twaver.Element();
    data.setStyle("chart.value", value);
    data.setStyle("chart.color", color);
    data.setStyle("chart.value.color", "black");
    data.setStyle("chart.value.font", 'heivetica');
    data.setName(name);
    box.add(data);
}

function returnParam(num,para1,para2){
    //num从0到3表示：总数，今日总数，去重总数，昨日去重总数
    //para1表示column,para2表示date
    if(num==0){
        return {
            "column":para1,
            "compression":0,
        };
    }
    else if(num==1){
        return {
            "column":para1,
            "compression":0,
            "date":paraa2,
        };
    }
    else if(num==2){
        return {
            "column":para1,
            "compression":1,
        };
    }
    else if(num==3){
        return {
            "column":para1,
            "compression":1,
            "date":para2,
        };
    }
}
//日期格式转换
function transferTime(curDate){
    var year=curDate.getFullYear();
    var month=parseInt(curDate.getMonth()+1);
    var day=curDate.getDate();
    if(month<10){
        month="0"+month;
    }
    if(day<10){
        day="0"+day;
    }
    return year+'-'+month+'-'+day;
}
//区域设备状态统计
function deviceStatus(areas,online,offline){
    var str='';
    var len=areas.length;
    for(var i=0;i<len;i++){
        var rate=Number(online[i])*100/(Number(online[i])+Number(offline[i])).toFixed(2);
        str+=`<tr>
            <td>${areas[i]} <label class="green"> 在线 ${online[i]}; </label><label class="red"> 离线 ${offline[i]}; </label><label> 在线率 ${rate}%</label></td>
        </tr>`;
    }
    return str;
}

$(function(){
    //初始化终端/热点/虚拟身份的采集数据
    var arr=[];
    var myDate=new Date();
    var today=transferTime(myDate);

    var preDate = new Date(myDate.getTime() - 24*60*60*1000); //前一天
    var yesterday=transferTime(preDate);
    var types=['MAC_COUNT','AP_MAC_COUNT','VI_COUNT'];
    var delOrNot=[0,1,2,3];
    //获取终端/热点/虚拟身份
    for(var h=0;h<3;h++){
        arr[h]=[];
        for(var g=0;g<4;g++){
            var params;
            if(g==0){
                params= {
                    "column":types[h],
                    "compression":0,
                };
            }
            else if(g==1){
                params= {
                    "column":types[h],
                    "compression":0,
                    "date":today,
                };
            }
            else if(g==2){
                params= {
                    "column":types[h],
                    "compression":1,
                };
            }
            else if(g==3){
                params= {
                    "column":types[h],
                    "compression":1,
                    "date":yesterday,
                };
            }
            console.log(params);
            $.ajax({
                type:"get",
                async:false,    //同步
                contentType: "application/json;charset=UTF-8",
                url:"http://192.168.0.1:8080/recordStatistic/getTotalByConds",
                data:params,
                success: function(resp) {
                    console.log(JSON.stringify(resp));
                    if(resp.meta.success){
                        arr[h].push(resp.data);
                   }
                   else{
                       alert('数据获取失败！');
                   }
                },
                error:function(resp){
                   alert('获取信息失败！');
                }
            });
        }  
    }
    console.log(arr);
    $('span#zhongduan').html(` 终端采集总数：${arr[0][0]}
    <br /> 终端今日总数：${arr[0][1]}
    <br /> 终端采集去重总数：${arr[0][2]}
    <br /> 终端昨日去重总数：${arr[0][3]}`);
    $('span#redian').html(` 热点采集总数：${arr[1][0]}
    <br /> 热点今日总数：${arr[1][1]}
    <br /> 热点采集去重总数：${arr[1][2]}
    <br /> 热点昨日去重总数：${arr[1][3]}`);
    $('span#xunishenfen').html(` 虚拟身份采集总数：${arr[2][0]}
    <br /> 虚拟身份今日总数：${arr[2][1]}
    <br /> 虚拟身份采集去重总数：${arr[2][2]}
    <br /> 虚拟身份昨日去重总数：${arr[2][3]}`);
    //设备状态统计
    var offline=0,online=0;
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/machineCount/getMachineStatusCount",
        data:"",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                offline=resp.data.offline;
                online=resp.data.online;
           }
           else{
               alert('数据获取失败！');
           }
        },
        error:function(resp){
           alert('获取信息失败！');
        }
    });
    $('span#shebei').html(` 设备总数：
    <br /> 视频总数：
    <br /> 设备在线数：${online}
    <br /> 设备离线数：${offline}`);
    //end
    //区域设备状态统计
    var arr1=[],arr2=[],arr3=[];
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/machineCount/getMachineStatusCountByDistrict",
        data: "",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                    arr1.push(objs[i]["districtName"]);
                    arr2.push(objs[i]["onlineCount"]);
                    arr3.push(objs[i]["offlineCount"]);
                }
           }
           else{
               alert('区域设备状态数据获取失败！');
           }
        },
        error:function(resp){
           alert('区域设备状态获取信息失败！');
        }
    });
    var result=deviceStatus(arr1,arr2,arr3);
    $('#deviceStatus tbody').html('');
    $('#deviceStatus tbody').append(result);
    //end
    //单位统计
    var unitStatistic='';
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/recordStatistic/getMacStatusInfo",
        data: "",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                    unitStatistic+=`<tr>
                            <td>${objs[i]["policeOfficeName"]}</td>
                            <td>${objs[i]["macCount"]}</td>
                            <td>${objs[i]["offlineMacCount"]}</td>
                            <td>${objs[i]["onlineMacCount"]}</td>
                            <td>${objs[i]["onlineRate"]}</td>
                            <td>${objs[i]["todayCollectCount"]}</td>
                            <td>${objs[i]["yesterdayCollectCount"]}</td>
                            <td>${objs[i]["totalCollectCount"]}</td>
                        </tr>`;
                }
           }
           else{
               alert('单位统计数据获取失败！');
           }
        },
        error:function(resp){
           alert('单位统计获取信息失败！');
        }
    });
    $('#Grid-cell-table3 tbody').html('');
    $('#Grid-cell-table3 tbody').append(unitStatistic);
    //end
    //线型图数据：终端设备采集量统计
    var times=[],count=[];
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/recordStatistic/getTotalByDates",
        data: {
            "startDate":'2018-05-21',
            "endDate":'2018-05-23',
        },
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                   times.push(objs[i]["date"].replace('-',''));
                   count.push(objs[i]["count"]);
                }
           }
           else{
               alert('终端设备采集量统计数据获取失败！');
           }
        },
        error:function(resp){
           alert('终端设备采集量统计获取信息失败！');
        }
    });
    //柱状图：终端信息采集量偏少设备
    var counts=[];
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/recordStatistic/getLeastCollectMachine",
        data: {
            "num":20,
        },
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                   counts.push(objs[i]["count"]);
                }
           }
           else{
               alert('终端信息采集量偏少设备数据获取失败！');
           }
        },
        error:function(resp){
           alert('终端信息采集量偏少设备获取信息失败！');
        }
    });
    //end
    //获取饼图的信息
    var pieArr=[];
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/recordStatistic/countViModel",
        data:"",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                pieArr.push(objs["sinaWeiBo"]);
                pieArr.push(objs["qqChat"]);
                pieArr.push(objs["weiXinMobile"]);
                pieArr.push(objs["others"]);
           }
           else{
               alert('终端信息采集量偏少设备数据获取失败！');
           }
        },
        error:function(resp){
           alert('终端信息采集量偏少设备获取信息失败！');
        }
    });



    
    //初始化线型图和柱状图
    //var xArr=['180323','180324','180325','180326','180327','180328','180329'];
    //var lineValues=[40000000,35000000,40000000,30000000,20000000,30000000,35000000];
    var barValues=[1,3,20,20,40,40,20,30,50,100,90,90,80,100,120,100,100,80,80];
    initLineView(times,count);
    initBarView(counts);
    //更新饼图
    //var arr1=[10,20,30,40,0,0];
    var arr2=[10,10,20,20,30,30,40,40];
    initPieView(pieArr,'pieChart1');
    initPieView2(arr2,'pieChart2');
    //更新布控告警列表
    $('#Grid-cell-table2 tbody').html('');
    var tableStr='';
    var areas=[],ruleName=[],targetMac=[],time=[];
    var len=0,i;
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/rule/getAllRules",
        data: "",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                len=objs.length;
                for(var i=0;i<len;i++){
                    areas.push(objs[i]["ruleArea"]);
                    ruleName.push(objs[i]["ruleName"]);
                    targetMac.push(objs[i]["targetMac"]);
                    time.push(objs[i]["ruleExpireTime"]);
                }
           }
           else{
               alert('布控数据获取失败！');
           }
        },
        error:function(resp){
           alert('获取布控信息失败！');
        }
    });
    for(i=0;i<len;i++){
        tableStr+=`<tr>
            <td>${areas[i]}</td>
            <td>${ruleName[i]}</td>
            <td>${targetMac[i]}</td>
            <td>${time[i]}</td>
        </tr>`;
    }
    $('#Grid-cell-table2 tbody').append(tableStr);
    //布控告警列表更新完成

})
//选择日月年或者日周月的时候，更改当前被触发的按钮
$('.btnClk').click(function(e){
    var target=$(e.currentTarget);
    var className=target.attr('class');
    var index=className.indexOf('active');
    if(index==-1){
        className+=" active";
        target.attr('class',className);
        console.log();
        var siblings=target.siblings();
        var len=siblings.length;
        for(var i=0;i<len;i++){
            var tem=siblings.eq(i);
            var temClassName=tem.attr('class');
            temClassName=temClassName.replace('active','');
            temClassName=$.trim(temClassName);
            tem.attr('class',temClassName);
        }
    }
    
});