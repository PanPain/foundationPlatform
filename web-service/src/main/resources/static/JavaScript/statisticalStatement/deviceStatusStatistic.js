
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
    var len=areas.length;
    for(var i=0;i<len;i++){
        tree_data['all-areas']['additionalParameters']['children'][areas[i]]={
            text:areas[i],
            type:'item',
        };
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

//删除所有的复选框
function delCheckBox(){
    $('i.icon-item').hide();
}



$(function(){
    //jQuery的入口函数,发起ajax请求获取所有区域的设备状态信息
    //更新左侧的树状图
    // var areas=['安吉县','德清县','度假区','开发区','南浔区','吴兴区','长兴县','织里镇'];
    // var online=[300,200,150,10,200,700,250,300];
    // var outline=[20,100,20,10,50,90,70,100];
    var areas=[],online=[],outline=[];
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"/machineCount/getMachineStatusCountByDistrict",
        data: "",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                    areas.push(objs[i]["districtName"]);
                    online.push(objs[i]["onlineCount"]);
                    outline.push(objs[i]["offlineCount"]);
                }
           }
           else{
               alert('数据获取失败！');
           }
        },
        error:function(resp){
           alert('获取信息失败！');
        }
    });
    console.log(areas);
    var sampleData = initiateDemoData(areas);//see below
    $('#tree1').ace_tree({
        dataSource: sampleData['dataSource1'],
        multiSelect: true,
        cacheItems: true,
        'open-icon' : 'ace-icon tree-minus',
        'close-icon' : 'ace-icon tree-plus',
        'itemSelect' : false,
        'folderSelect': false,
        'selected-icon' : 'ace-icon fa fa-check',
        'unselected-icon' : 'ace-icon fa fa-times',
        loadingHTML : '<div class="tree-loading"><i class="ace-icon fa fa-refresh fa-spin blue"></i></div>'
    });
    $('div.tree-branch-header').click();     //触发点击事件
    $("ul.tree-branch-children").on('click','li',function(e){
        var target=$(e.currentTarget);
        var sp=target.find('span.tree-label').eq(0);
        sp.prev().hide();
        var area=sp.text();
        console.log(area);
        console.log(area.length);
    });
    //异步处理：删除所有的复选框（笨方法）
    setTimeout(() => {
        delCheckBox();
    }, 100);
    
    initView(areas,online,outline);
})
