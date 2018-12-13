var index=0;
var allMFCountTable={};
var allMFCount={
    "data":[
            {
                "mfName": "你",
                "mfOrgNo": "006",
                "total": 2,
                "onlineCount": 1,
                "offlineCount": 1,
            },
            {
                "mfName": "你好",
                "mfOrgNo": "",
                "total": 1,
                "onlineCount": 0,
                "offlineCount": 1,
            }
    ],
     "meta": {
          "success":true,
           "code":"1002", 
           "message":"参数有误",
     },
};

//单个厂商统计信息添加
function addMFCount(arr){
    index++;
    var str='<tr><td>'+index+'</td>';
    for(var key in arr){
        str+='<td class="'+key+'">'+arr[key]+'</td>';

    }
    return str;
}

//循环添加所有厂商统计信息
function addAllMFCount(arr){
    var str='';
    var dataArr=arr['data'];
    var meta=arr['meta'];
    if(!meta['success']){
        return -1;
    }
    else{
        var len=dataArr.length;
        for(var i=0;i<len;i++){
            str+=addMFCount(dataArr[i]);
        }
        return str;
    }
}


//封装ajax请求的过程，用于获取所有的厂商统计信息
function getResources(){
    var request=new XMLHttpRequest();
    var result;
    request.onreadystatechange=function(){
        if(request.readyState===4){
            if(request.status>=200&&request.status || request.status == 304){
                console.log('请求成功');
                var rspText=request.responseText;
                console.log('后端返回了数据：'+rspText);
                result=JSON.parse(rspText);
                allMFCountTable=result;
                 //在执行之前先删除表格中的所有元素，重新进行动态添加
                var tby=$('#dynamic-table tbody').eq(0);
                var trs=tby.children('tr');
                var str=addAllMFCount(allMFCountTable);
                var rows=0;
                if(str==-1){
                    alert('数据请求失败！');
                }
                else{
                    console.log('数据请求成功，正在更新表格！');
                    rows=allMFCount['data'].length;
                    tby.empty();
                    tby.html(str); 
                } 
            }
            else{
                console.log('请求失败，状态码为：'+request.status);
            }
        }
        else{
            console.log('请求还在继续...');
        }
    }
    //拼接参数：获取所有的厂商统计不需要传递参数
    var params='';
    var URL='/manufacturerCount/getManufacturerCountByMfName';    //对应到接口的位置
    request.open('GET',URL);
    request.send();      //发送请求
}

//使用jQuery的ajax请求，用于获取所有的厂商统计信息
function getResourcesjQuery(){
    //拼接参数：获取所有的厂商统计不需要传递参数
    var params='';
    var URL='/manufacturerCount/getManufacturerCountByMfName';    //对应到接口的位置
    $.ajax({
        url:URL,
        type:'GET',
        dataType:'jsonp',       //指定服务器返回的数据类型
        jsonp:'callbackFunc',   //指定回调函数的参数名
        jsonpCallback:'updateTable',
        success:function(data){
            console.log('成功调用！');
        },
        error:function(err){
            console.log('Exception:'+err);
        },
    });          
}

//获取到数据之后执行的回调函数
function updateTable(data){
    var result=JSON.parse(data);
    allMFCountTable=result;
    //在执行之前先删除表格中的所有元素，重新进行动态添加
   var tby=$('#dynamic-table tbody').eq(0);
   var str=addAllMFCount(result);
   if(str==-1){
       alert('数据请求失败！');
   }
   else{
       console.log('数据请求成功，正在更新表格！');
       tby.empty();
       tby.html(str);  
   } 
}



$(function(){
    //jQuery的入口函数
    //跨域请求服务端的数据，等待数据返回，更新页面的表格数据
    //1. 第一种方法：原生的ajax请求
    //getResources();
    //2. 使用jQuery的ajax跨域方式
    //getResourcesjQuery();

    var tby=$('#dynamic-table tbody').eq(0);
    $.ajax({
        type: "get",
        url:"/manufacturerCount/getManufacturerCountByMfName",
        data: {
        },
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                console.log('后端返回了数据：'+mfData);
                var len=mfData.length;
                var strr='';
                for(var i=0;i<len;i++){
                    strr+=addMFCount(mfData[i]);
                }
                tby.empty();
                tby.html(strr);
            }
        }
    });




    // //在执行之前先删除表格中的所有元素，重新进行动态添加
    // var tby=$('#dynamic-table tbody').eq(0);
    // var trs=tby.children('tr');
    // var str=addAllMFCount(allMFCount);
    // allMFCountTable=allMFCount;
    // var rows=0;
    // console.log(allMFCountTable);
    // if(str==-1){
    //     alert('数据请求失败！');
    // }
    // else{
    //     console.log('数据请求成功，正在更新表格！');
    //     rows=allMFCount['data'].length;
    //     tby.empty();
    //     tby.html(str);
    // }
    // // //所有厂商统计添加完成
    // // //修改动态表下的描述信息:Showing 1 to 3
    // var infoTbl=$('.dataTables_info');
    // var info='展示 1 to '+rows+' of '+rows+' 记录';
    // infoTbl.text(info);

    // //修改动态表下的分页：Previous和Next
    // var prev=$('li.previous a').eq(0);
    // var nxt=$('li.next a').eq(0);
    // console.log(prev.text());
    // console.log(nxt.text());
    // prev.text('上一页');
    // nxt.text('下一页');

});

//事件绑定
//点击查询按钮
$('#getRow').click(function (e) {
    index=0;
    var name = $("#search").val();
    console.log(name);  
    var tby=$('#dynamic-table tbody').eq(0);
    $.ajax({
        type: "get",
        url:"/manufacturerCount/getManufacturerCountByMfName",
        data: {
            mfName: name
        },
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                console.log('后端返回了数据：'+mfData);
                var len=mfData.length;
                var strr='';
                for(var i=0;i<len;i++){
                    strr+=addMFCount(mfData[i]);
                }
                tby.empty();
                tby.html(strr);
            }
        }
    });
})

