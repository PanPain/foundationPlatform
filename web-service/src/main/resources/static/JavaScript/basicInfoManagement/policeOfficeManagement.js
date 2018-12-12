var index=0;

function addPoliceOffice(arr){
    index++;
    var str='<tr><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">'+
    '<input type="checkbox" class="ace" />'+'<span class="lbl"></span></label></td><td>'+index+'</td>';
    // for(var key in arr){
        str+='<td class="'+policeOfficeNo+'">'+arr.policeOfficeNo+'</td>'+'<td class="'+policeOfficeName+'">'+arr.policeOfficeName+'</td>'+'<td class="'+districtNo+'">'+arr.districtNo+'</td>'+'<td class="'+districtName+'">'+arr.districtName+'</td>';
    // }
    str+='<td class="editTbl"><div class="hidden-sm hidden-xs action-buttons"><a class="blue detailRow" data-toggle="modal" data-id="'+arr.policeOfficeId+'" data-no="'+arr.policeOfficeNo+'" data-na="'+arr.policeOfficeName+'" data-arn="'+arr.districtNo+'" data-arm="'+arr.districtName+'" data-adr="'+arr.address+'" data-rem="'+arr.remark+'" >'+
        '<i class="ace-icon glyphicon glyphicon-th bigger-130"></i></a><a class="green editRow"   data-id="'+arr.policeOfficeId+'" data-no="'+arr.policeOfficeNo+'" data-na="'+arr.policeOfficeName+'" data-arn="'+arr.districtNo+'" data-arm="'+arr.districtName+'" data-adr="'+arr.address+'" data-rem="'+arr.remark+'" data-toggle="modal">'+
        '<i class="ace-icon fa fa-pencil bigger-130"></i></a><a class="red delRow"  data-id="'+arr.policeOfficeId+'" data-toggle="modal">'+
        '<i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
    str+='<div class="hidden-md hidden-lg"><div class="inline pos-rel"><button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">'+
        '<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">'+
        '<li><a class="tooltip-info" data-rel="tooltip" title="View" data-toggle="modal"><span class="blue detailRow" data-id="'+arr.policeOfficeId+'" data-no="'+arr.policeOfficeNo+'" data-na="'+arr.policeOfficeName+'" data-arn="'+arr.districtNo+'" data-arm="'+arr.districtName+'" data-adr="'+arr.address+'" data-rem="'+arr.remark+'" >'+
        '<i class="ace-icon fa fa-search-plus bigger-120"></i></span> </a></li><li>'+
        '<a data-rel="tooltip" title="Edit" data-toggle="modal"><span class="green editRow" data-id="'+arr.policeOfficeId+'" data-no="'+arr.policeOfficeNo+'" data-na="'+arr.policeOfficeName+'" data-arn="'+arr.districtNo+'" data-arm="'+arr.districtName+'" data-adr="'+arr.address+'" data-rem="'+arr.remark+'">'+
        '<i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a  class="tooltip-error" data-rel="tooltip" title="Delete" data-toggle="modal">'+
        '<span class="red delRow" data-id="'+arr.policeOfficeId+'" data-no="'+arr.policeOfficeNo+'" data-na="'+arr.policeOfficeName+'" data-arn="'+arr.districtNo+'" data-arm="'+arr.districtName+'" data-adr="'+arr.address+'" data-rem="'+arr.remark+'"><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div></div></td>';

    return str;
}

//循环添加所有人员
function addAllPoliceOffice(arr){
    var str='';
    var dataArr=arr['data'];
    var meta=arr['meta'];
    if(!meta['success']){
        return -1;
    }
    else{
        var len=dataArr.length;
        for(var i=0;i<len;i++){
            str+=addPoliceOffice(dataArr[i]);
        }
        return str;
    }
}
//获取到数据之后执行的回调函数
function updateTable(data){
    var result=JSON.parse(data);
    allPeopleTable=result;
    //在执行之前先删除表格中的所有元素，重新进行动态添加
   var tby=$('#dynamic-table tbody').eq(0);
   var str=addAllPoliceOffice(result);
   if(str==-1){
       alert('数据请求失败！');
   }
   else{
       console.log('数据请求成功，正在更新表格！');
       tby.empty();
       tby.html(str);  
   } 
}
//使用jQuery的ajax请求，用于获取所有的人员信息
//function getResourcesjQuery(){
$(function(){
    index=0;
    var tby=$('#dynamic-table tbody').eq(0);
    var queryAllPoliceOfficeObject={ 
        "dat" :[{

        }],
    }
    var queryAllPoliceOffice=queryAllPoliceOfficeObject['dat'][0];
    // alert(queryAllPoliceOffice);
    console.log(queryAllPoliceOffice);
    //拼接参数：获取所有的人员不需要传递参数
   //对应到接口的位置
    $.ajax({
        type:'post',
        url:"http:192.168.0.1:8080/policeOffice/getPoliceOfficeInfo",
        data: JSON.stringify(queryAllPoliceOffice),
        contentType: "application/json;charset=UTF-8",
        // dataType:'jsonp',       //指定服务器返回的数据类型
        // jsonp:'callbackFunc',   //指定回调函数的参数名
        // jsonpCallback:'updateTable',
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                //console.log('后端返回了数据：'+mfData);
                var len=mfData.length;
                var strr='';
                for(var i=0;i<len;i++){
                    strr+=addPoliceOffice(mfData[i]);
                }
                tby.empty();
                tby.html(strr);
            }
    }
    });          
})
//点击查询按钮
$('#search').click(function (e) {
    index=0;
    var name = $("#queryName").val();
    var no = $("#queryNo").val();
    var dis = $("#queryDis").val();
    
    var data1={};
    if(name != "" && name != null){
    data1["policeOfficeName"]=name;
    }
    if(no!= "" && no != null){
    data1["policeOfficeNo"]=no;
    }
    if(dis!= "" && dis != null){
    data1["districtName"]=dis;
    }
    var tby=$('#dynamic-table tbody').eq(0);
//向后端传送数据保存     
    $.ajax({
        type: "post",
        url:"http:192.168.0.1:8080/policeOffice/getPoliceOfficeInfo",
        data: JSON.stringify(data1),
        contentType: "application/json;charset=UTF-8",
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                var len=mfData.length;
                var strr='';
                for(var i=0;i<len;i++){
                    strr+=addPoliceOffice(mfData[i]);
                }
                tby.empty();
                tby.html(strr);
            }
        }
    });
})


// 111$(function(){
//     //jQuery的入口函数
//     //跨域请求服务端的数据，等待数据返回，更新页面的表格数据
//     //1. 第一种方法：原生的ajax请求
//     //getResources();
//     //2. 使用jQuery的ajax跨域方式
//     //getResourcesjQuery();

//     //在执行之前先删除表格中的所有元素，重新进行动态添加
//     var tby=$('#dynamic-table tbody').eq(0);
//     var trs=tby.children('tr');
//     var str=addAllPoliceOffice(allPeople);
//     allPeopleTable=allPeople;
//     var rows=0;
//     console.log(allPeopleTable);
//     if(str==-1){
//         alert('数据请求失败！');
//     }
//     else{
//         console.log('数据请求成功，正在更新表格！');
//         rows=allPeople['data'].length;
//         tby.empty();
//         tby.html(str);  
//     }
    // //所有人员添加完成
    // //修改动态表下的描述信息:Showing 1 to 3 
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
// });

//事件绑定
//点击新增按钮
$('#addRow').click(function(e){
    var addR=$('#modal-table2');
    console.log(addR);
    addR.css('display','block');
    addR.attr('class','modal fade in');
});
//点击明细按钮
$('#dynamic-table').on('click','.detailRow',function(e){
    var addR=$('#modal-table3');
    console.log(addR);
    addR.css('display','block');
    addR.attr('class','modal fade in');
    var editId= $(this).attr('data-id');
    var editNo= $(this).attr('data-no'); 
    var editNa= $(this).attr('data-na'); 
    var editArn= $(this).attr('data-arn'); 
    var editArm= $(this).attr('data-arm');
    var editAdr= $(this).attr('data-adr');
    var editRem= $(this).attr('data-rem');
	$("input#policeOfficeNo2").val(editNo);
	$("input#policeOfficeName2").val(editNa);
	$("input#districtNo2").val(editArn);
	$("input#districtName2").val(editArm);
	$("input#address2").val(editAdr);
	$("input#remark2").val(editRem);
    
        })
   
//点击编辑按钮  data-adr="'+arr.address+'" data-arm="'+arr.remark+'" 
$('#dynamic-table').on('click','.editRow',function(e){
    var addR=$('#modal-table');
    console.log(addR);
    addR.css('display','block');
    addR.attr('class','modal fade in');
    editId= $(this).attr('data-id');
    var editNo= $(this).attr('data-no'); 
    var editNa= $(this).attr('data-na'); 
    var editArn= $(this).attr('data-arn'); 
    var editArm= $(this).attr('data-arm');
    var editAdr= $(this).attr('data-adr');
    var editRem= $(this).attr('data-rem');
    var editNo= $(this).attr('data-no'); 
    var editNa= $(this).attr('data-na'); 
    var editArn= $(this).attr('data-arn'); 
    var editArm= $(this).attr('data-arm');
    var editAdr= $(this).attr('data-adr');
    var editRem= $(this).attr('data-rem');
	$("input#policeOfficeNo1").val(editNo);
	$("input#policeOfficeName1").val(editNa);
	$("select#districtNo1").val(editArn);
	$("select#districtName1").val(editArm);
	$("input#address1").val(editAdr);
	$("input#remark1").val(editRem);
    })
//点击编辑按钮中的保存按钮
$('#saveEdit').click(function(e){
    e.stopPropagation();
   
    if(!($("input#policeOfficeNo1").val())||(!$("input#policeOfficeName1").val())||(!$("select#districtNo1").val())||(!$("select#districtName1").val()))
// if(!($("#NewNumber").val()))
{
alert("除备注和单位地址外，其他各项为必填项");
}else{
var editPoliceOfficeObject={ 
    "dat" :[{
        "policeOfficeId":editId,
        "policeOfficeNo":$("input#policeOfficeNo1").val(),
        "policeOfficeName":$("input#policeOfficeName1").val(),
        "districtNo":$("select#districtNo1").val(),
        "districtName":$("select#districtName1").val(),
        "address":$("input#address1").val(),
        "remark":$("input#remark1").val(),
    }],
}
  var editPoliceOffice1=editPoliceOfficeObject['dat'][0];

  console.log(editPoliceOffice1);
//向后端传送数据保存
      $.ajax({
      type: "POST",
      url: "http:192.168.0.1:8080/policeOffice/updatePoliceOfficeInfo",
      data: JSON.stringify(editPoliceOffice1),
      contentType: "application/json;charset=UTF-8",
      success: function(response){
       if(response.meta.success){
           alert(response.meta.message);
           location.reload();
        }else{
        alert(response.meta.message);
        }
      },
    error: function(XmlHttpRequest,textStatus,errThrown){
    console.log(XmlHttpRequest.status);
    }
    });
 }
});

//点击删除按钮
$('#dynamic-table').on('click','.delRow',function(e){
    //alert(this);
    e.stopPropagation();
    var rsu=confirm('确定要删除该单位吗？');
    if(rsu){
        var delId= $(this).attr('data-id');
        var deleteList =[]; 
        deleteList[0] = delId;  
       
        $.ajax({
            type:"post",
            url:"http:192.168.0.1:8080/policeOffice/deletePoliceOfficeInfo",      
            data:JSON.stringify([deleteList[0]]),
            contentType: "application/json;charset=UTF-8",
        success: function(response){
            if(response.meta.success){
                alert('删除成功');
                //alert(this);
                $($(this).parent().parent().parent()).remove();
                window.location.reload();
            }
        }
    });
     //   alert('删除成功！');
    }
    else{
        alert('撤销操作！');
    }
});
   

//点击新增弹框中的取消按钮和×按钮，关闭弹窗
$('.delAdd').click(function(e){
    var addR=$('#modal-table2');
    console.log(addR);
    addR.css('display','none');
    addR.attr('class','modal fade');
});
//点击编辑弹框中的取消按钮和×按钮，关闭弹窗
$('.delEdit').click(function(e){
    var addR=$('#modal-table');
    console.log(addR);
    addR.css('display','none');
    addR.attr('class','modal fade');
});
//点击明细弹框中的取消按钮和×按钮，关闭弹窗
$('.delDet').click(function(e){
    var addR=$('#modal-table3');
    console.log(addR);
    addR.css('display','none');
    addR.attr('class','modal fade');
});
//点击新增按钮中的保存按钮
$('.page-content').on('click','#saveNew',function(e){
    e.stopPropagation();
    if(!($("input#policeOfficeNo").val())||(!$("input#policeOfficeName").val())||(!$("select#districtNo").val())||(!$("select#districtName").val()))

{
alert("除备注和单位地址外，其他各项为必填项");
}else{
var newPoliceOfficeObject={ 
    "dat" :[{
        "policeOfficeId":new Date().getTime(),
        "policeOfficeNo":$("input#policeOfficeNo").val(),
        "policeOfficeName":$("input#policeOfficeName").val(),
        "districtNo":$("select#districtNo").val(),
        "districtName":$("select#districtName").val(),
        "address":$("input#address").val(),
        "remark":$("input#remark").val(),
    }],
}
  var newPoliceOffice=newPoliceOfficeObject['dat'][0];
  console.log(newPoliceOffice);
//向后端传送数据保存
      $.ajax({
      type: "POST",
      url: "http:192.168.0.1:8080/policeOffice/addPoliceOfficeInfo",
      data: JSON.stringify(newPoliceOffice),
      contentType: "application/json;charset=UTF-8",
      success: function(response){
       if(response.meta.success){
           alert(response.meta.message);
           location.reload();
        }else{
        alert(response.meta.message);
        }
      },
    error: function(XmlHttpRequest,textStatus,errThrown){
    console.log(XmlHttpRequest.status);
    }
    });
 }
});
//点击选择列按钮，阻止默认事件的冒泡
$('.lbl').click(function(e){
    e.stopPropagation();
    var target=$(e.currentTarget);
    var key=target.attr('id');
    if(key!='undefined'){
        var goals1=$('td.'+key);
        var goals2=$('th.'+key);
        goals1.toggle();
        goals2.toggle();
    }   
});

//点击全选/反选按钮，更改复选框的状态:因为上述点击事件阻止冒泡行为，所以此处略微复杂
$('input.all').click(function(e){
    var all=$(e.currentTarget).get(0);
    var items=$('input.items');
    var uncheckItems=$('input.items').not('input:checked');
    console.log(all.checked);
    console.log('所有子项：',items.length);
    console.log('未被选中的子项：',uncheckItems.length);
    var allLbl=$('span.tblItems');
    var length=allLbl.length;
    console.log('子项的数目：',allLbl.length);
    if(all.checked){
        uncheckItems.click();
        //全选
        for(var i=0;i<length;i++){
            var target=allLbl.eq(i);
            var key=target.attr('id');
            if(key!='undefined'){
                var goals1=$('td.'+key);
                var goals2=$('th.'+key);
                goals1.show();
                goals2.show();
            } 
        }
    }
    else{
        all.checked=false;
        items.attr('checked',false);
        //全不选
        for(var i=0;i<length;i++){
            var target=allLbl.eq(i);
            var key=target.attr('id');
            if(key!='undefined'){
                var goals1=$('td.'+key);
                var goals2=$('th.'+key);
                goals1.hide();
                goals2.hide();
            } 
        }
    }
});
//批量删除 未写完
// $('#delRow').click(function(e){
//     alert("123");
//   var checks = document.getElementsByName("chk");
//   n = 0;
//   for (i = 0; i < checks.length; i++) {
//       if (checks[i].checked)
//       n++;
//      }
//   if (n < 1) {
//    alert('请选择要删除的数据！');
//    return false;
//    } else {
//     e.stopPropagation();
//     var rsu = confirm('确定要删除该用户吗？');
//     if (rsu) {
//     var delId = $("td :checked").attr('data-id');
//     }}})




