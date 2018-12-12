var index=0;
var allPeopleTable={};
var allPeople={ 
    "data":[
            {
                "username":'xq', 
                "sex":'女',
                "identify-type":'type 1',
                "identify-nmbs":'1610122604', 
                "origin":'xxxx',
            },
            {
                "username":'dp', 
                "sex":'男',
                "identify-type":'type 2',
                "identify-nmbs":'16101xxxxx', 
                "origin":'xxxx',
            },
            {
                "username":'xq', 
                "sex":'女',
                "identify-type":'type 1',
                "identify-nmbs":'1610122604', 
                "origin":'xxxx',
            },
            {
                "username":'dp', 
                "sex":'男',
                "identify-type":'type 2',
                "identify-nmbs":'16101xxxxx', 
                "origin":'xxxx',
            },
            {
                "username":'xq', 
                "sex":'女',
                "identify-type":'type 1',
                "identify-nmbs":'1610122604', 
                "origin":'xxxx',
            },
            {
                "username":'dp', 
                "sex":'男',
                "identify-type":'type 2',
                "identify-nmbs":'16101xxxxx', 
                "origin":'xxxx',
            },
            {
                "username":'xq', 
                "sex":'女',
                "identify-type":'type 1',
                "identify-nmbs":'1610122604', 
                "origin":'xxxx',
            },
            {
                "username":'dp', 
                "sex":'男',
                "identify-type":'type 2',
                "identify-nmbs":'16101xxxxx', 
                "origin":'xxxx',
            },
            {
                "username":'xq', 
                "sex":'女',
                "identify-type":'type 1',
                "identify-nmbs":'1610122604', 
                "origin":'xxxx',
            },
            {
                "username":'dp', 
                "sex":'男',
                "identify-type":'type 2',
                "identify-nmbs":'16101xxxxx', 
                "origin":'xxxx',
            },
            {
                "username":'xq', 
                "sex":'女',
                "identify-type":'type 1',
                "identify-nmbs":'1610122604', 
                "origin":'xxxx',
            },
            {
                "username":'dp', 
                "sex":'男',
                "identify-type":'type 2',
                "identify-nmbs":'16101xxxxx', 
                "origin":'xxxx',
            },
    ],
     "meta": {
          "success":true,
           "code":"1002", 
           "message":"参数有误",
     },
};

//单个人员添加
function addPeople(arr){
    index++;
    var str='<tr><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">'+
    '<input type="checkbox" class="ace checkBoxPeople" />'+'<span class="lbl"></span></label></td><td>'+index+'</td>';
    for(var key in arr){
        if(key=='identify-type'){
            if(arr[key]=='type 1'){
                str+='<td class="'+key+'"><span class="label label-sm label-info arrowed arrowed-righ">'+arr[key]+'</span></td>';
            }
            else if(arr[key]=='type 2'){
                str+='<td class="'+key+'"><span class="label label-sm label-warning">'+arr[key]+'</span></td>';
            }
            else{
                str+='<td class="'+key+'">'+arr[key]+'</td>';
            }
           
        }
        else{
            str+='<td class="'+key+'">'+arr[key]+'</td>';
        }
       
    }
    str+='<td class="editTbl" id="temp'+index+'"><div class="hidden-sm hidden-xs action-buttons"><a class="blue" href="#modal-table2" data-toggle="modal" title="新增">'+
        '<i class="ace-icon fa fa-search-plus bigger-130"></i></a><a class="green rowEdit" data-toggle="modal" title="编辑" onclick="edit('+index+')" >'+
        '<i class="ace-icon fa fa-pencil bigger-130"></i></a><a class="red rowDel" data-toggle="modal" title="删除" onclick="delt('+index+')" >'+
        '<i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
    str+='<div class="hidden-md hidden-lg"><div class="inline pos-rel"><button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">'+
        '<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">'+
        '<li><a href="#modal-table2" class="tooltip-info" data-rel="tooltip" title="新增" data-toggle="modal"><span class="blue">'+
        '<i class="ace-icon fa fa-search-plus bigger-120"></i></span> </a></li><li>'+
        '<a class="tooltip-success rowEdit" data-rel="tooltip" title="编辑" data-toggle="modal" onclick="edit('+index+')" ><span class="green">'+
        '<i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a class="tooltip-error rowDel" onclick="delt('+index+')" data-rel="tooltip" title="删除" data-toggle="modal">'+
        '<span class="red"><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div></div></td>';

    return str;
}

//循环添加所有人员
function addAllPeople(arr){
    var str='';
    var dataArr=arr['data'];
    var meta=arr['meta'];
    if(!meta['success']){
        return -1;
    }
    else{
        var len=dataArr.length;
        for(var i=0;i<len;i++){
            str+=addPeople(dataArr[i]);
        }
        return str;
    }
}


//封装ajax请求的过程，用于获取所有的人员信息
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
                allPeopleTable=result;
                 //在执行之前先删除表格中的所有元素，重新进行动态添加
                var tby=$('#dynamic-table tbody').eq(0);
                var str=addAllPeople(allPeople);
                if(str==-1){
                    alert('数据请求失败！');
                }
                else{
                    console.log('数据请求成功，正在更新表格！');
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
    //拼接参数：获取所有的人员不需要传递参数
    var params='';
    var URL='http:192.168.0.1:8080/xxxx/xxxxxx';    //对应到接口的位置
    request.open('GET',URL);
    request.send();      //发送请求
}

//使用jQuery的ajax请求，用于获取所有的人员信息
function getResourcesjQuery(){
    //拼接参数：获取所有的人员不需要传递参数
    var params='';
    var URL='http:192.168.0.1:8080/xxx/xxxxx';    //对应到接口的位置
    $ajax({
        url:URL,
        type:'GET',
        contentType: "application/json;charset=UTF-8",
        async:false,
        data:'',
        success:function(data){
            console.log('成功调用！');
            updateTable(data);
        },
        error:function(err){
            alert('人员信息获取失败！');
        },
    });          
}

//获取到数据之后执行的回调函数
function updateTable(data){
    var result=JSON.parse(data);
    allPeopleTable=result;
    //在执行之前先删除表格中的所有元素，重新进行动态添加
   var tby=$('#dynamic-table tbody').eq(0);
   var str=addAllPeople(result);
   if(str==-1){
       alert('数据请求失败！');
   }
   else{
       console.log('数据请求成功，正在更新表格！');
       tby.empty();
       tby.html(str);  
   } 
}


//编辑人员
function edit(index){
    console.log('执行编辑操作，第',index,'行');
    var average=$('select[name="dynamic-table_length"] option:selected');
    index=(parseInt(index))%(parseInt(average.val()));
    console.log(index);
    var theRow=$("#dynamic-table tbody tr").eq(parseInt(index)-1);
    var theRowCld=theRow.children();
    var seq=theRowCld.eq(1).text();          //序号
    var username=theRowCld.eq(2).text();     //姓名
    var sex=theRowCld.eq(3).text();          //性别
    var type=theRowCld.eq(4).text();         //证件类型
    var numbs=theRowCld.eq(5).text();        //证件号码
    var origin=theRowCld.eq(6).text();      //采集来源

    //将相应信息填到编辑表单中
	$("#seq").val(seq);
	$("#usrName").val(username);
    $("#usrSex").val(sex);
    $("#usrType").val(type);
	$("#numbs").val(numbs);
    $("#usrOrigin").val(origin);

    //打开编辑表单
    var addR=$('#modal-table');
    addR.css('display','block');
    addR.attr('class','modal fade in'); 
    //点击确认按钮，完成数据的更新
    //点击确定按钮之后，提交数据并强制刷新当前页面
    $('#editButton').click(function(){
        console.log('点击确定按钮');
        var seqMdy=$("#seq").val();
        var usrMdy=$("#usrName").val();
        var sexMdy=$("#usrSex").val();
        var typMdy=$("#usrType").val();
        var numMdy=$("#numbs").val();
        var originMdy=$("#usrOrigin").val();
        console.log(seqMdy,usrMdy,sexMdy,typMdy,numMdy,originMdy);
		$.ajax({
            type:"post",
            contentType: "application/json;charset=UTF-8",
            async:false,
			url:"http://192.168.0.1:8080/xxxx/xxxxxx",
			data:{
				username:usrMdy,
				sex:sexMdy,
				"identify-type":typMdy,
                "identify-nmbs":numMdy,
                origin:originMdy,
                
			},
			success:function(response){
				var txt=  "修改人员成功！";
				alert(txt);
				location.reload();
			},
			error:function(resp){
				var txt=  "修改人员失败！";
                alert(txt);
			}
		});
    });

}
//删除人员
function delt(index){
    console.log('执行删除操作，第',index,'行');
    var average=$('select[name="dynamic-table_length"] option:selected');
    index=(parseInt(index))%(parseInt(average.val()));
    console.log(index);
    var theRow=$("#dynamic-table tbody tr").eq(parseInt(index)-1);
    var theRowCld=theRow.children();
    var seq=theRowCld.eq(1).text();          //序号
    var username=theRowCld.eq(2).text();     //姓名
    var sex=theRowCld.eq(3).text();          //性别
    var type=theRowCld.eq(4).text();         //证件类型
    var numbs=theRowCld.eq(5).text();        //证件号码
    var origin=theRowCld.eq(6).text();      //采集来源

    var rsu=confirm('确定要删除该条记录吗？');
    if(rsu){
        $.ajax({
            type:"post",
            contentType: "application/json;charset=UTF-8",
            async:false,
			url:"http://192.168.0.1:8080/xxxx/xxxxxx",
			data:{
				username:username,
				sex:sex,
				"identify-type":type,
                "identify-nmbs":numbs,
                origin:origin,
                
			},
			success:function(response){
				var txt=  "删除人员成功！";
				alert(txt);
				location.reload();
			},
			error:function(resp){
				var txt=  "删除人员失败！";
                alert(txt);
			}
		});
    }
    else{
        alert('撤销操作！');
    }

}
//添加人员
$("#addPeopleButton").click(function(){  
    
    var addUsr=$("#addUsr").val();
    var addSex=$("#addSex").val();
    var addType=$("#addType").val();
    var addNums=$("#addNums").val();
    var addOrigin= $("#addOrigin").val();

    var txt="";
    if(addUsr=="")
    {
        txt+=  "姓名不能为空！\n";
    }
    if(addSex=="")
    {
        txt+=  "性别不能为空！\n";
    }
    if(addType=="")
    {
        txt+=  "证件类型不能为空！\n";
    }
    if(addNums=="")
    {
        txt+=  "证件号码不能为空！\n";
    }
    if(addOrigin=="")
    {
        txt+=  "采集来源不能为空！\n";
    }
    
    //重新将页面数据清空
    $("#addUsr").val('');
    $("#addSex").val('');
    $("#addType").val('');
    $("#addNums").val('');
    $("#addOrigin").val('');

    if(txt!="")
    {
        alert(txt);
    }
    else
    {
        $.ajax({
            type:"post",
            contentType: "application/json;charset=UTF-8",
            async:false,
			url:"http://192.168.0.1:8080/xxxx/xxxxxx",
            data: {
                username:addUsr,
				sex:addSex,
				"identify-type":addType,
                "identify-nmbs":addNums,
                origin:addOrigin,
                },
            success: function(resp) {
                var txt=  "添加人员成功！";
                alert(txt);
                location.reload();
            },
            error:function(resp){
                var txt=  "添加人员失败！";
                alert(txt);
            }
        });
    }
});




$(function(){
    //jQuery的入口函数
    //跨域请求服务端的数据，等待数据返回，更新页面的表格数据
    //1. 第一种方法：原生的ajax请求
    //getResources();
    //2. 使用jQuery的ajax跨域方式
    //getResourcesjQuery();

    //在执行之前先删除表格中的所有元素，重新进行动态添加
    var tby=$('#dynamic-table tbody').eq(0);
    var trs=tby.children('tr');
    var str=addAllPeople(allPeople);
    allPeopleTable=allPeople;
    var rows=0;
    console.log(allPeopleTable);
    if(str==-1){
        alert('数据请求失败！');
    }
    else{
        console.log('数据请求成功，正在更新表格！');
        rows=allPeople['data'].length;
        tby.empty();
        tby.html(str);  
    }
    //所有人员添加完成





    
});

//事件绑定
//点击新增按钮
$('#addRow').click(function(e){
    var addR=$('#modal-table2');
    console.log(addR);
    addR.css('display','block');
    addR.attr('class','modal fade in');
});
//点击编辑按钮
$('#editRow').click(function(e){
    console.log('点击编辑栏里的编辑按钮');
    var selectedBox=$("input.checkBoxPeople:checked");
    console.log('所有的复选框：',$("input.checkBoxPeople").length);
    var checkedLength=selectedBox.length;
    console.log('被选中的行数：',checkedLength);
    if(checkedLength>0){
        var seletedRow=selectedBox.eq(0);
        var tdSet=seletedRow.parents('td').eq(0).next();
        var idx=tdSet.text();
        edit(idx);
    }
    else{
        alert('还未选择任何人员，请选择！');
    }
   
});
//点击删除按钮
$('#delRow').click(function(e){
    console.log('点击编辑栏里的删除按钮');
    var selectedBox=$("input.checkBoxPeople:checked");
    console.log('所有的复选框：',$("input.checkBoxPeople").length);
    var delLength=selectedBox.length;
    console.log('被选中的行数：',delLength);
    if(delLength>0){
        var seletedRow=selectedBox.eq(0);
        var tdSet=seletedRow.parents('td').eq(0).next();
        var idx=tdSet.text();
        delt(idx);
    }
    else{
        alert('还未选择任何人员，请选择！');
    }
    
});

//点击查询按钮，实现查询的效果
$('#searchBtn').click(function(){
    
    var name=$('#searchName').val();
    var mac=$('#mac').val();
    var nums=$('#searchNums').val();
    var origin=$('#searchOrigin').val();
    var type=$('#searchType option:selected').val();
    console.log(mac,name,nums,origin,type);
    if(name==''&&mac==''&&nums==''&&origin==''&&type==''){
        console.log('重新刷新咯');
        location.reload();
    }
    else{
        $.ajax({
            type:"post",
            url:"updateUserInfo.action",
            data:{
                Mac:mac,
                username:name,
                "identify-type":type,
                "identify-nmbs":nums,
                origin:origin,
            },
            success:function(data){
                updateTable(data);
            },
            error:function(resp){
                var txt=  "获取人员失败！";
                alert(txt);
            }
        });
    }

});

//点击新增弹框中的取消按钮和×按钮，关闭弹窗
$('.delAdd').click(function(e){
    var addR=$('#modal-table2');
    addR.css('display','none');
    addR.attr('class','modal fade');
});
//点击编辑弹框中的取消按钮和×按钮，关闭弹窗
$('.delEdit').click(function(e){
    var addR=$('#modal-table');
    addR.css('display','none');
    addR.attr('class','modal fade');
});

//点击编辑弹框中的取消按钮和×按钮，关闭弹窗
$('.delDel').click(function(e){
    var addR=$('#modal-table3');
    addR.css('display','none');
    addR.attr('class','modal fade');
});
//确定删除
$('.sureDel').click(function(e){
    console.log('确定删除界面');
    alert('删除成功！');
    var addR=$('#modal-table3');
    console.log(addR);
    addR.css('display','none');
    addR.attr('class','modal fade');
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







