var theUrl="http:192.168.0.1:8080";
var allDistrict={ 
	"data":[
	{

		"districtId":1,
		"districtNo":"123",
		"districtName":"xian",
		"districtLevel":"1",
		"districtArea":"22，23",
		"districtRemark":"123124",

	},
	{
		"districtId":2,
		"districtNo":"1025",
		"districtName":"雁塔区",
		"districtLevel":"2",
		"districtArea":"25，56",
		"districtRemark":"这里是雁塔区",
	}
	],
	"meta": {
		"success":true,
		"code":"1002", 
		"message":"查询区域信息成功",
	},
};

//单个地区添加
function addTheDistrict(arr){
	// arr["districtName"]属性要加引号！！！
	var str=' <li class="list-group-item" id='+arr["districtId"]+'> <i class="iconfont icon-fuwuquyu"></i>'+arr["districtName"]+
	' <button class="btn btn-warning pull-right dropdown-toggle" type="button" data-toggle="dropdown">'+'操作'+'<span class="caret"></span></button>'+
	'<ul class="dropdown-menu pull-right"><li><a href="#" class="addNew"> <i class="iconfont icon-jiahao"></i>'+'新增'+
	'</a></li><li><a href="#" class="editNew"> <i class="iconfont icon-bianji"></i>'+'编辑'+
	'</a></li><li><a href="#" class="delRow"><i class="iconfont icon-shanchu"></i>'+'删除'+'</a></li></ul></li>';
	return str;
}

//显示区域明细
function districtDetail(arr){
	$('#form-field-number').val(arr["districtNo"]);
	$('#form-field-number').val(arr["districtNo"]);
	$('#form-field-number').val(arr["districtNo"]);
	$('#form-field-number').val(arr["districtNo"]);
	$('#form-field-number').val(arr["districtNo"]);
}
$(function(){
    //jQuery的入口函数
    //跨域请求服务端的数据，等待数据返回，更新页面数据
    var disul=$('#distriList');
    $.ajax({
    	type: "get",
    	url:theUrl+"/district/getAllDistrict",
    	data: {
    	},
    	success: function(response){
    		if(response.meta.success){
    			var districtData = response.data;
				// console.log(districtData);
				var len=districtData.length;
				var strr='';
				for(var i=0;i<len;i++){             
					strr+=addTheDistrict(districtData[i]);
				}
				disul.empty();
				disul.html(strr);  
			}
		}
	});
       // 百度地图API功能
       var map = new BMap.Map("map");
       map.centerAndZoom(new BMap.Point(108.949915,34.350607),12);
       map.enableScrollWheelZoom(true);
   })
//点击搜索按钮
$('.page-cont').on('click','#searchDis',function(e){
	var disul=$('#distriList');
	var value=$("#searchInput").val();
	$.ajax({
		type: "get",
		url:theUrl+"/district/searchDistrict",
		data: {
			"districtName": value,
		},
		success: function(response){
			if(response.meta.success){
				var districtData = response.data;
				console.log(districtData);
				var len=districtData.length;
				var strr='';
				for(var i=0;i<len;i++){             
					strr+=addTheDistrict(districtData[i]);
				}
				disul.empty();
				disul.html(strr);  
			}
		}
	});

})

  //事件绑定
//点击左侧区域，右侧显示相应区域明细
$('#distriList').on('click','.list-group-item',function(e){
	theli=$(this);
	theliId= theli.attr("id");
	$.ajax({
		type: "get",
		url:theUrl+"/district/getDistrictDetailInfo",
		data: {
			"districtId": theliId
		},
		success: function(response){
			if(response.meta.success){
				var districtData = response.data;
				// console.log(response);
				var LongitLatitude=districtData.districtArea;

				// console.log(districtData);
				$('#form-field-number').val(districtData.districtNo);
				$('#form-field-name').val(districtData.districtName);
				$('#form-field-level').val(districtData.districtLevel);
				$('#form-field-note').val(districtData.districtRemark);
				if(LongitLatitude){
                     $('#form-field-longitude').val(LongitLatitude.split(',')[0]);
				     $('#form-field-latitude').val(LongitLatitude.split(',')[1]);
				}
			
			}
		}
	});
});

//点击页面最上方新增按钮
$('.addNew').click(function(e){
	e.stopPropagation();
	//console.log("clicked")
	var addR=$('#modal-new');
	//console.log(addR);
	addR.css('display','block');
	addR.attr('class','modal fade in');
});

//点击区域下拉列表中的新增按钮
$('#distriList').on('click','.addNew',function(e){
	e.stopPropagation();
	//console.log("clicked")
	var addR=$('#modal-new');
	//console.log(addR);
	addR.css('display','block');
	addR.attr('class','modal fade in');
})
//点击区域下拉列表中的编辑按钮
$('#distriList').on('click','.editNew',function(e){
	e.stopPropagation();
	//console.log("clicked")
	var addR=$('#modal-edit');
	//console.log(addR);
	addR.css('display','block');
	addR.attr('class','modal fade in');
	//全局变量，为了后面使用
	editId=this.parentNode.parentNode.parentNode.id;
	$.ajax({
		type: "get",
		url:theUrl+"/district/getDistrictDetailInfo",
		data: {
			"districtId": editId
		},
		success: function(response){
			if(response.meta.success){
				var districtData = response.data;
				var LongitLatitude=districtData.districtArea;
				$('#editNumber').val(districtData.districtNo);
				$('#editName').val(districtData.districtName);
				$('#editLevel').val(districtData.districtLevel);
				$('#editNote').val(districtData.districtRemark);
				$('#editLongitude').val(LongitLatitude.split(',')[0]);
				$('#editLatitude').val(LongitLatitude.split(',')[1]);
			}
		}
	});
})


//点击新增弹框中的取消按钮和×按钮，关闭弹窗
$('.page-cont').on('click','.delAdd',function(e){
// $('#modal-edit .delAdd').click(function(e){
	e.stopPropagation();
	var addR=$('#modal-new');
	addR.css('display','none');
	addR.attr('class','modal fade');
});
 //点击编辑弹框中的取消按钮和×按钮，关闭弹窗
 $('.page-cont').on('click','.delEdit',function(e){
// $('#modal-edit .delAdd').click(function(e){
	e.stopPropagation();
	var addR=$('#modal-edit');
	addR.css('display','none');
	addR.attr('class','modal fade');
});

//点击新增按钮中的保存按钮
$('.page-cont').on('click','#saveNew',function(e){
	e.stopPropagation();
	if(!($("#NewNumber").val())||(!$("#NewName").val())||(!$("#NewLevel").val())||(!$("#NewLongitude").val())||(!$("#NewLatitude").val()))
	// if(!($("#NewNumber").val()))
{
	alert("除备注外，其他各项为必填项");
}else{
	area=[$("#NewLongitude").val(),$("#NewLatitude").val()];
	var newDistrictObject={ 
		"dat" :[{
			// "districtId":new Date().getTime(),
			"districtNo":$("#NewNumber").val(),
			"districtName":$("#NewName").val(),
			"districtLevel":$("#NewLevel").val(),
			"districtArea":area.toString(),
			"districtRemark":$("#NewNote").val(),
		}],
	}
	var newDistrict=newDistrictObject['dat'][0];
	//console.log(newDistrict);
//向后端传送数据保存
$.ajax({
	type: "POST",
	url: theUrl+"/district/addDistrict",
	data: JSON.stringify(newDistrict),
	contentType: "application/json;charset=UTF-8",
	success: function(response){
		//console.log(JSON.stringify(response));
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

//点击编辑按钮中的保存按钮
$('.page-cont').on('click','#saveEdit',function(e){
	e.stopPropagation();
		// $("#mytext").change();
		if(!($("#editNumber").val())||(!$("#editName").val())||(!$("#editLevel").val())||(!$("#editLongitude").val())||(!$("#editLatitude").val()))
	// if(!($("#NewNumber").val()))
{
	alert("除备注外，其他各项为必填项");
}else{
	area=[$("#editLongitude").val(),$("#editLatitude").val()];
	var editDistrictObject={ 
		"dat" :[{
			"districtId":editId,
			"districtNo":$("#editNumber").val(),
			"districtName":$("#editName").val(),
			"districtLevel":$("#editLevel").val(),
			"districtArea":area.toString(),
			"districtRemark":$("#editNote").val(),
		}],
	}
	var editDistrict=editDistrictObject['dat'][0];
	//console.log(editDistrict);
//向后端传送数据保存
$.ajax({
	type: "POST",
	url: theUrl+"/district/updateDistrict",
	data: JSON.stringify(editDistrict),
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

//点击操作下拉菜单中的删除按钮
$('#distriList').on('click','.delRow',function(e){
// $('.delRow').click(function(e){
	e.stopPropagation();
	var rsu=confirm('确定要删除该地区吗？');
	if(rsu){
		var delId=this.parentNode.parentNode.parentNode.id;
		var delELe=$(this).parent().parent().parent();
		$.ajax({
			type: "delete",
			url:theUrl+"/district/deleteDistrict?districtId="+delId,
		//     data: JSON.stringify({
		// 	"districtId": delId
		// }),
		dataType: 'json',
		success: function(response){
			if(response.meta.success){
				$(delELe).remove();
				alert('删除成功');
			}
		}
	});
	}
	else{
		alert('撤销操作！');
	}
});
$('.page-cont').on('click','.btn-map',function(e){
     // 百度地图API功能
     var map = new BMap.Map("map");
     map.centerAndZoom(new BMap.Point(108.93,34.27),12);
     map.enableScrollWheelZoom(true);
	// 用经纬度设置地图中心点
	if($("#form-field-longitude").val()!= "" && ($("#form-field-latitude").val() != "")){
		var new_point = new BMap.Point($("#form-field-longitude").val(),$("#form-field-latitude").val());
		map.centerAndZoom(new_point, 12);
			var marker = new BMap.Marker(new_point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中    
		}
	})
// var map = new BMap.Map("allmap");
// 	var point = new BMap.Point(108.93,34.27);
// 	map.centerAndZoom(point, 12);
// 	var marker = new BMap.Marker(point);  // 创建标注
// 	map.addOverlay(marker);              // 将标注添加到地图中