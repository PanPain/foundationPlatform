var index=0;
var allPlaceTable={};
var allPlace={
    "data":[
        {
            "placeId":'1',
            "placeno":'123',
            "placename":'a',
            "placeaddress":'b',
            "bdlatitude":'1123',
            "bdlongitude":'3321',
            "placefw-type":'type1',
            "placejy-prop":'p1',
            "placejy-person":'person1',
            "phone-num":'654321',
            "area-num":'007',
            "area-name":'area1',
            "orgCode":'c1',
        },
        {
            "placeId":'2',
            "placeno":'456',
            "placename":'c',
            "placeaddress":'cc',
            "bdlatitude":'23',
            "bdlongitude":'332',
            "placefw-type":'type2',
            "placejy-prop":'p2',
            "placejy-person":'person2',
            "phone-num":'9876',
            "area-num":'008',
            "area-name":'area2',
            "orgCode":'c2',
        },
        {
            "placeId":'3',
            "placeno":'789',
            "placename":'c',
            "placeaddress":'cc',
            "bdlatitude":'23',
            "bdlongitude":'332',
            "placefw-type":'type3',
            "placejy-prop":'p2',
            "placejy-person":'person3',
            "phone-num":'9876',
            "area-num":'008',
            "area-name":'area2',
            "orgCode":'c2',
        }
    ],
    "meta": {
        "success":true,
        "code":"1002",
        "message":"参数有误",
    },
};

//场所服务类型的映射对象
var fwtype = {
    "00":"旅馆宾馆类（住宿服务场所）",
    "01":"图书馆阅览器",
    "02":"电脑培训中心类",
    "03":"娱乐场所类",
    "04":"交通枢纽",
    "05":"公共交通工具",
    "06":"餐饮服务场所",
    "07":"金融服务场所",
    "08":"购物场所",
    "09":"公共服务场所",
    "10":"文化服务场所",
    "11":"公共休闲场所",
    "12":"其他"
}
//单个场所添加
function addPlace(arr){
     
    index++;
    // var str='<tr><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">'+
    //     '<input type="checkbox" class="ace" />'+'<span class="lbl"></span></label></td><td>'+index+'</td>';
    var str='<tr id='+arr["placeId"]+'><td class="center checkboxTbl"><label class="pos-rel" style="display:block;">'+
    '<input type="checkbox" class="ace" />'+'<span class="lbl"></span></label></td><td>'+index+'</td>';
    for(var key in arr){
        if(key == "type"){
            arr["type"] = fwtype[arr["type"]];
            // alert(arr["type"]);
        }
        str+='<td class="'+key+'">'+arr[key]+'</td>';

    }

    // str+='<td class="editTbl"><div class="hidden-sm hidden-xs action-buttons"><a class="blue" href="#modal-table" data-toggle="modal">'+
    // '<i class="ace-icon fa fa-search-plus bigger-130"></i></a><a class="green" href="#modal-table" data-toggle="modal">'+
    // '<i class="ace-icon fa fa-pencil bigger-130"></i></a><a class="red" href="#modal-table" data-toggle="modal">'+
    // '<i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
    // str+='<div class="hidden-md hidden-lg"><div class="inline pos-rel"><button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">'+
    // '<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">'+
    // '<li><a href="#modal-table" class="tooltip-info" data-rel="tooltip" title="View" data-toggle="modal"><span class="blue">'+
    // '<i class="ace-icon fa fa-search-plus bigger-120"></i></span> </a></li><li>'+
    // '<a href="#modal-table" class="tooltip-success" data-rel="tooltip" title="Edit" data-toggle="modal"><span class="green">'+
    // '<i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a href="#modal-table" class="tooltip-error" data-rel="tooltip" title="Delete" data-toggle="modal">'+
    // '<span class="red"><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div></div></td>';

    //修改
    str+='<td class="editTbl"><div class="hidden-sm hidden-xs action-buttons"><a class="editRow blue" href="#modal-table" data-toggle="modal">'+
    // '<i class="ace-icon fa fa-search-plus bigger-130"></i></a><a class="green" href="#modal-table" data-toggle="modal">'+
    '<i class="ace-icon fa fa-pencil bigger-130"></i></a><a class="delRow red"  data-toggle="modal">'+
    '<i class="ace-icon fa fa-trash-o bigger-130"></i></a></div>';
    str+='<div class="hidden-md hidden-lg"><div class="inline pos-rel"><button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">'+
    '<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i></button><ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">'+
    '<li><a href="#modal-table" class="tooltip-info" data-rel="tooltip" title="View" data-toggle="modal"><span class="editRow blue">'+
    // '<i class="ace-icon fa fa-search-plus bigger-120"></i></span> </a></li><li>'+
    // '<a href="#modal-table" class="tooltip-success" data-rel="tooltip" title="Edit" data-toggle="modal"><span class="green">'+
    '<i class="ace-icon fa fa-pencil-square-o bigger-120"></i></span></a></li><li><a href="#modal-table" class="tooltip-error" data-rel="tooltip" title="Delete" data-toggle="modal">'+
    '<span class="delRow red "><i class="ace-icon fa fa-trash-o bigger-120"></i></span></a></li></ul></div></div></td>';

    return str;
}

//循环添加所有场所
function addAllPlace(arr){
    var str='';
    var dataArr=arr['data'];
    var meta=arr['meta'];
    if(!meta['success']){
        return -1;
    }
    else{
        var len=dataArr.length;
        for(var i=0;i<len;i++){
            str+=addPlace(dataArr[i]);
        }
        return str;
    }
}




//获取到数据之后执行的回调函数
function updateTable(data){
    var result=JSON.parse(data);
    allPlaceTable=result;
    //在执行之前先删除表格中的所有元素，重新进行动态添加
    var tby=$('#dynamic-table tbody').eq(0);
    var str=addAllPlace(result);
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

    //在执行之前先删除表格中的所有元素，重新进行动态添加
    // var tby=$('#dynamic-table tbody').eq(0);
    // var trs=tby.children('tr');
    // var str=addAllPlace(allPlace);
    // allPlaceTable=allPlace;
    // var rows=0;
    // console.log(allPlaceTable);
    // if(str==-1){
    //     alert('数据请求失败！');
    // }
    // else{
    //     console.log('数据请求成功，正在更新表格！');
    //     rows=allPlace['data'].length;
    //     tby.empty();
    //     tby.html(str);
    // }
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



    var tby=$('#dynamic-table tbody').eq(0);
    $.ajax({
        type: "get",
      url:"http:192.168.0.1:8080/place/getAllPlaceInfo",

        data: {
        },
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                console.log('后端返回了数据：'+mfData);
                var len=mfData.length;
                var strr='';
                for(var i=0;i<len;i++){
                    strr+=addPlace(mfData[i]);
                }
                var rows=0;
                if(strr==-1){
                    alert('数据请求失败！');
                }
                else{
                    console.log('数据请求成功，正在更新表格！');
                    rows=allPlace['data'].length;
                    tby.empty();
                    tby.html(strr);
                }
            }
        }
    });
    
});


//编辑
$('#simple-table').on('click','.editRow',function(e){
	e.stopPropagation();
	var addR=$('#modal-table');
	addR.css('display','block');
	addR.attr('class','modal fade in');
	//全局变量，为了后面使用
     editId=this.parentNode.parentNode.parentNode.id;


     

     $.ajax({
        type: "get",
        url:"http:192.168.0.217:8080/policeOffice/getAllPoliceOfficeInfo",
        success: function(response){
            if(response.meta.success){
                var bjData = response.data;
                var len=bjData.length;
                var policeofficename = bjData.map(function(v){
                    return v.policeOfficeName;
                });
                var policeofficeid = bjData.map(function(x){
                    return x.policeOfficeId;
                });
              //  alert(policeofficeid[1])
               // alert(policeofficename);
               for(var k=0;k<len;k++){             
                $("#editfkPoliceOfficeId").append('<option value="' + policeofficeid[k] + '">' + policeofficename[k] + '</option>')
            }
            $("#editfkPoliceOfficeId").change(function(){
                //alert(this.tagName)
                $(this).attr("selected",true);
            });
           
            }
        }
    });






     
    
	$.ajax({
       
			type: "get",
			url:"http:192.168.0.1:8080/place/getPlaceInfoById",
            contentType:"application/json;charset=UTF-8",
		data: {
			"placeId": editId,
		},
		success: function(response){
			if(response.meta.success){
				var placeData = response.data;
                //alert(placeData[0].placeName);
                $('#placeId').val(editId);
                $('#placeNo').val(placeData[0].placeNo);
				$('#editplaceName').val(placeData[0].placeName);
				$('#editAdress').val(placeData[0].address);
				$('#editLatitude').val(placeData[0].latitude);
				$('#editLongitude').val(placeData[0].longitude);
				$('#editType').val(placeData[0].type);
				$('#editProp').val(placeData[0].property);
				$('#editlegalPerson').val(placeData[0].legalPerson);
				$('#editTel').val(placeData[0].tel);
				$('#editorgCode').val(placeData[0].orgCode);
				$('#editfkPoliceOfficeId').val(placeData[0].fkPoliceOfficeId);
			}
		}
	});
})

//点击编辑按钮中的保存按钮
$('.page-content').on('click','#editSave',function(e){
    e.stopPropagation();
    // $("#mytext").change();
   // alert($("#editfkPoliceOfficeId").val());
    if(!($("#placeId").val())||(!$("#placeNo").val())||(!$("#editplaceName").val())||(!$("#editAdress").val())||(!$("#editLatitude").val())||(!$("#editLongitude").val())||!($("#editfkPoliceOfficeId").val())||(!$("#editType").val())||(!$("#editProp").val()))
// if(!($("#NewNumber").val()))
{
    alert("场所名称、编号、所属单位、经纬度、类型、性质为必填项");
}else{
var editPlaceObject={ 
    "dat" :[{
        "placeId":editId,
        "placeNo":$("#placeNo").val(),
        "placeName":$("#editplaceName").val(),
        "address":$("#editAdress").val(),
        "type":$("#editType").val(),
        "property":$("#editProp").val(),
        "longitude":$("#editLongitude").val(),
        "latitude":$("#editLatitude").val(),
        "legalPerson":$("#editlegalPerson").val(),
        "orgCode":$("#editorgCode").val(),
        "fkPoliceOfficeId":$("#editfkPoliceOfficeId").val()
    
    }],
}
//alert($("#editfkPoliceOfficeId").val())


var editPlace=editPlaceObject['dat'][0];
console.log(editPlace);
//向后端传送数据保存
$.ajax({
type: "POST",
url: "http:192.168.0.1:8080/place/updatePlaceInfo",
data: JSON.stringify(editPlace),
contentType: "application/json;charset=UTF-8",
success: function(response){
    if(response.meta.success){
        alert(response.meta.message);
        // location.reload();
    }else{
        alert(response.meta.message);
    }
},
error: function(XmlHttpRequest,textStatus,errThrown){
    console.log(XmlHttpRequest.status);
}
});
$("#modal-table").remove();
 window.location.reload();
}
});



//弹窗里取消按钮的绑定
//新增功能中“取消”和“x”的绑定
$('.delAdd').click(function(e){
    var addR=$('#modal-table2');
    console.log(addR);
    addR.css('display','none');
    addR.attr('class','modal fade');
});
//编辑功能中“取消”和“x”的绑定
$('.delEdit').click(function(){
    var addR=$('#modal-table');
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
    console.log('所有子项：'+items.length);
    console.log('未被选中的子项：'+uncheckItems.length);
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

// 查询

$('#search').click(function (e) {
    index=0;
    //alert(this);
    var splaceName = $("#query-placeName").val();
    var splaceNo = $("#query-placeNo").val();
    var saddress = $("#query-address").val();
    var stype = $("#query-type").val();
    var sproperty = $("#query-property").val();
    var sleagalperson = $("#query-legalPerson").val();
    var sorgCode = $("#query-orgCode").val();
    var data1={};
    if(splaceName != "" && splaceName != null){
        data1["placeName"]=splaceName;
    }
    if(splaceNo!= "" && splaceNo != null){
        data1["placeNo"]=splaceNo;
    }
    if(saddress!= "" && saddress != null){
        data1["address"]=saddress;
    }
    if(stype!= "" && stype != null){
        data1["type"]=stype;
    }
    if(sproperty!= "" && sproperty != null){
        data1["property"]=sproperty;
    }
    if(sleagalperson!= "" && sleagalperson != null){
        data1["legalPerson"]=sleagalperson;
    }
    if(sorgCode!= "" && sorgCode != null){
        data1["orgCode"]=sorgCode;
    }

    var tby=$('#dynamic-table tbody').eq(0);
    $.ajax({
        type: "get",
        url:"http:192.168.0.1:8080/place/searchPlaceInfo",
        
        data: data1,
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                //alert('后端返回了数据：'+mfData);
                var len=mfData.length;
                var strr='';
                for(var i=0;i<len;i++){
                    strr+=addPlace(mfData[i]);
                }
                tby.empty();
                tby.html(strr);
            }
        }
    });
})
//新增
$('#simple-table').on('click','#addRow',function(e){
	e.stopPropagation();
	var addR=$('#modal-table2');
	//console.log(addR);
	addR.css('display','block');
    addR.attr('class','modal fade in');



    
    $.ajax({
        type: "get",
        url:"http:192.168.0.217:8080/policeOffice/getAllPoliceOfficeInfo",
        success: function(response){
            if(response.meta.success){
                var mfData = response.data;
                //alert('后端返回了数据：'+mfData);
                var len=mfData.length;
                var policeofficename = mfData.map(function(v){
                    return v.policeOfficeName;
                });
                var policeofficeid = mfData.map(function(x){
                    return x.policeOfficeId;
                });
              //  alert(policeofficeid[1])
               // alert(policeofficename);
               for(var k=0;k<len;k++){             
                $("#newfkPoliceOfficeId").append('<option value="' + policeofficeid[k] + '">' + policeofficename[k] + '</option>')
            }
            $("#newfkPoliceOfficeId").change(function(){
                //alert(this.tagName)
                $(this).attr("selected",true);
            });
           
            }
        }
    });

})


//点击新增框的“确定”按钮
$('#simple-table').on('click','#addSave',function(e){
    e.stopPropagation();
   // area=[$("#newLongitude").val(),$("#newLatitude").val()];
   // alert($("#newfkPoliceOfficeId").val());
   //alert($("#newType").val());
    //alert($('#newfkPoliceOfficeId option:selected').attr('value'))


   if( (!$("#newplaceNo").val()) || (!$("#newplaceName").val()) || (!$("#newAdress").val()) || (!$("#newLatitude").val()) || (!$("#newLongitude").val()) || (!$("#newfkPoliceOfficeId").val()) || (!$("#newType").val()) || (!$("#newProperty").val()))
 
   {
   alert("场所名称、编号、所属单位、经纬度、类型、性质为必填项");
   }else{
    var newPlaceObject={ 
    "dat" :[{
        "placeName":$("#newplaceName").val(),
        "placeNo":$("#newplaceNo").val(),
        "address":$("#newAdress").val(),
        "longitude":$("#newLongitude").val(),
        "latitude":$("#newLatitude").val(),        
        "type":$("#newType").val(),
        "property":$("#newProperty").val(),
        "legalPerson":$("#newlegalPerson").val(),
        "tel":$("#newTel").val(),
        "orgCode":$("#neworgCode").val(),
         // 此处场所所属单位

        "fkPoliceOfficeId":$('#newfkPoliceOfficeId option:selected').attr('value'),
        "certType":$("#certType").val(),
        "certNo":$("#certNo").val(),
        "beginTime":$("#beginTime").val(),
        "endTime":$("#endTime").val(),
        "cheskStatus":$("#cheskStatus").val()
  
    }],
}
var newPlace=newPlaceObject['dat'][0];
// for(var i in newPlace) {
//     alert(newPlace[i]);
// }
//alert(typeof $("#certType").val());
//alert(newPlace.certTypes)
//向后端传送数据保存
$.ajax({
type: "POST",
url: "http:192.168.0.1:8080/place/addPlaceInfo",
data: JSON.stringify(newPlace),
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


//点击每一行的删除图标
$('#simple-table').on('click','.delRow',function(e){
    // $('.delRow').click(function(e){
        e.stopPropagation();
        var rsu=confirm('确定要删除该场所吗？');
        if(rsu){
            var delId=this.parentNode.parentNode.parentNode.id;
            //alert(delId)
           // alert(this.parentNode.parentNode.parentNode.tagName);
            $.ajax({
                type: "get",
                url:"http:192.168.0.1:8080/place/deletePlaceInfo",
            // url:"http:127.0.0.1:8080/district/deleteDistrict",
                data: {
                "placeId": delId
            },
            dataType: 'json',
            success: function(response){
                if(response.meta.success){
                    alert('后台删除成功');
                    $($(this).parent().parent().parent()).remove();
                    window.location.reload();
                }
            }
        });
            alert('删除成功！');
        }
        else{
            alert('撤销操作！');
        }
    });
