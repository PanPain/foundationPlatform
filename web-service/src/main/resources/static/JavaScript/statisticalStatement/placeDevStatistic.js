//全局变量
var areas=[];
var areaObj={};
var areaItems=[],online=[],outline=[];
var allLevel={};
var tree_data={};


function initView(areas,online,outline){
    var len=areas.length;
    $('#barChart').html("");
    var chart = new twaver.charts.BarChart();
    var box = chart.getDataBox();

    chart.setType("group");
    
    var data = new twaver.Element();
    var data2 = new twaver.Element();
    data.setStyle("chart.values", online);
    data.setStyle("chart.color", "red");
    data2.setStyle("chart.values", outline);
    data2.setStyle("chart.color", "#2f4f4f");

    box.add(data);
    box.add(data2);
    chart.setXScaleTexts(new twaver.List(areas));
    chart = chart.getView();

    var dom=$('#barChart');
    var width=dom.css('width');
    var height=dom.css('height');
    console.log(width,height);
    if(len>3){
        chart.style.width = width;
        chart.style.height = height;
    }
    else{
        chart.style.width = parseInt(width)/2+'px';
        chart.style.height = parseInt(height)/2+'px';
    }
    $("#barChart").append(chart);
}

//更新树状图要用的数据
function initiateDemoData(areas){
    var tree_data = {
        'all-areas' : {text: '所有区域', type: 'folder'}	,
    }
    tree_data['all-areas']['additionalParameters'] = {
        'children' : {
        }
    }
    var keys=Object.keys(areas);
    var len=keys.length;
    for(var i=0;i<len;i++){
        tree_data['all-areas']['additionalParameters']['children'][keys[i]]={
            text:keys[i],
            type:'folder',
        };
    }
    for(var i=0;i<len;i++){
        tree_data['all-areas']['additionalParameters']['children'][keys[i]]['additionalParameters'] = {
            'children' : {
            }  
        }
    }
    for(var i=0;i<len;i++){
        var secondItem=areas[keys[i]];
        var secondLen=secondItem.length;
        for(var j=0;j<secondLen;j++){
            tree_data['all-areas']['additionalParameters']['children'][keys[i]]['additionalParameters']['children'][secondItem[j]]={
                text:secondItem[j],
                type:'item',
            };
        }
    }
    
   

    var dataSource1 = function(options, callback){
        var $data = null
        if(!("text" in options) && !("type" in options)){
            $data = tree_data;//the root tree
            callback({ data: $data });
            return;
        }
        else if("type" in options && options.type == "folder") {
            if("additionalParameters" in options && "children" in options.additionalParameters)
                $data = options.additionalParameters.children || {};
            else $data = {}//no data
        }
        
        if($data != null)//this setTimeout is only for mimicking some random delay
            setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);
    }
    return {'dataSource1': dataSource1};
}

//更新树状图：整合成一个函数
function updateTreeView(areas){
    var sampleData = initiateDemoData(areas);//see below
    $('#tree1').ace_tree({
        dataSource: sampleData['dataSource1'],
        multiSelect: true,
        cacheItems: true,
        'open-icon' : 'ace-icon tree-minus',
        'close-icon' : 'ace-icon tree-plus',
        'itemSelect' : true,
        'folderSelect': false,
        'selected-icon' : 'ace-icon fa fa-check',
        'unselected-icon' : 'ace-icon fa fa-times',
        loadingHTML : '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>'
    });
    $('div.tree-branch-header').eq(1).click();     //模拟点击事件，展开二级菜单
}
//删除所有的复选框
function delCheckBox(){
    $('i.icon-item').hide();
}



$(function(){
    //jQuery的入口函数,发起ajax请求获取所有区域的设备状态信息
    //更新左侧的树状图
    // var areas=['安吉县','德清县','度假区'];
    // var arr=['安吉县公安局','报福派出所','昌硕派出所','凌峰派出所','梅溪派出所'];
    // var city='德清县';

    
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"/deviceCount/getAllDistrict",
        data: "",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                    areas.push(objs[i]["districtName"]);
                    areaObj[objs[i]["districtName"]]=objs[i]["districtId"];
                }
           }
           else{
               alert('数据未正常获取！');
           }
        },
        error:function(resp){
           alert('获取信息失败！');
        }
    });
    
    var keys=Object.keys(areaObj);
    var keyLen=keys.length;
    for(var i=0;i<keyLen;i++){
        var id=areaObj[keys[i]];
        allLevel[keys[i]]=[];
        $.ajax({
            type:"get",
            async:false,    //同步
            contentType: "application/json;charset=UTF-8",
            url:"/deviceCount/getDeviceCount",
            data: {"districtId":id},
            success: function(resp) {
                console.log(JSON.stringify(resp));
                if(resp.meta.success){
                    var objs=resp.data;
                    var len=objs.length;
                    for(var j=0;j<len;j++){
                        allLevel[keys[i]].push(objs[j]["policeName"]);
                    }
               }
               else{
                   alert('派出所数据未正常获取！');
               }
            },
            error:function(resp){
               alert('派出所信息获取失败！');
            }
        });

    }
    console.log(allLevel);

    //更新左侧树状图
    updateTreeView(allLevel);

   
    //当点击县级条目的时候，选择呈现当前选中的县级场所设备状态统计
    $("ul.tree-branch-children").on('click','div.tree-branch-header',function(e){
        var target=$(e.currentTarget);
        var par=target.parent();
        var classname=par.attr('class');
        
        var sp=target.find('span.tree-label').eq(0);
        var area=sp.text();
        var areaId=areaObj[area];
        console.log(area,areaId);
        var param={
            "districtId":areaId,
        };
        //获取当前点击对象的父节点li
        var liParent=target.parent();
        var liSiblings=liParent.siblings();   //获取所有的兄弟节点
        var liLen=liSiblings.length;
        for(var k=0;k<liLen;k++){
            var tem=liSiblings.eq(k);
            var cls=tem.attr('class');
            if(cls.indexOf('tree-open')!=-1){
                console.log('click click click');
                var child=tem.children('div.tree-branch-header').eq(0);
                child.click();
                console.log(tem);
            }

        }
        //结束

        if(classname.indexOf('tree-open')==-1){
            $.ajax({
                type:"get",
                async:false,    //同步
                contentType: "application/json;charset=UTF-8",
                url:"/deviceCount/getDeviceCount",
                data: {"districtId":areaId},
                success: function(resp) {
                    console.log(JSON.stringify(resp));
                    areaItems=[];
                    online=[];
                    outline=[];
                    if(resp.meta.success){
                        var objs=resp.data;
                        var len=objs.length;
                        for(var i=0;i<len;i++){
                            areaItems.push(objs[i]["policeName"]);
                            online.push(objs[i]["onlineCount"]);
                            outline.push(objs[i]["offlineCount"]);  
                        }
                        initView(areaItems,online,outline);
    
                   }
                   else{
                       alert('派出所数据未正常获取！');
                   }
                },
                error:function(resp){
                   alert('派出所信息获取失败！');
                }
            });
        }
        else{
            console.log('清空柱状图！');
            $('#barChart').html("");    //清空柱状图
        }
        

    });
    //异步处理：删除所有的复选框（笨方法）
    setTimeout(() => {
        delCheckBox();
    }, 100);
    
    //initView(areas,arr);
})