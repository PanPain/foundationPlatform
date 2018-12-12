//全局变量
var areas=[],arr=[],prop=["0","1","3"],serviceType=["1","2","3","4","5","6","7","8","9","A","B","C","D"];
var props={};
var lgt=prop.length;

var serviceTypes={};
var types=serviceType.length;


//柱状图：按经营性质统计
function initView(areas,arr){
    var len=areas.length;
    $('#barChart').html("");
    var chart = new twaver.charts.BarChart();
    var box = chart.getDataBox();

    chart.setType("group");
    var keys=Object.keys(arr);
    var lens=keys.length;
    for(var i=0;i<lens;i++){
        var color='red';
        if(keys[i]=='0'){
            color="red";
        }
        else if(keys[i]=='1'){
            color="#2f4f4f";
        }
        else if(keys[i]=='3'){
            color="#66cdaa";
        }
        var data = new twaver.Element();
        data.setStyle("chart.values", arr[keys[i]]);
        data.setStyle("chart.color", color);
        box.add(data);
    }
    chart.setLowerLimit(0);
    chart.setYScaleValueGap(2);
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
//柱状图：按服务类型统计
function initViewBar(areas,arr){
    var len=areas.length;
    $('#barChart').html("");
    var chart = new twaver.charts.BarChart();
    var box = chart.getDataBox();

    chart.setType("stack");
    var keys=Object.keys(arr);
    var lens=keys.length;
    for(var i=0;i<lens;i++){
        var color='red';
        if(keys[i]=='1'){
            color="red";
        }
        else if(keys[i]=='2'){
            color="#2f4f4f";
        }
        else if(keys[i]=='3'){
            color="#66cdaa";
        }
        else if(keys[i]=='4'){
            color="#deb887";
        }
        else if(keys[i]=='5'){
            color="#87ceeb";
        }
        else if(keys[i]=='6'){
            color="green";
        }
        else if(keys[i]=='7'){
            color="#4b0082";
        }
        else if(keys[i]=='8'){
            color="#90ee90";
        }
        else if(keys[i]=='9'){
            color="gray";
        }
        else if(keys[i]=='A'){
            color="#20b2aa";
        }
        else if(keys[i]=='B'){
            color="yellow";
        }
        else if(keys[i]=='C'){
            color="pink";
        }
        else if(keys[i]=='D'){
            color="#e0ffff";
        }
        else{
            color="red";
        }
        var data = new twaver.Element();
        data.setStyle("chart.values", arr[keys[i]]);
        data.setStyle("chart.color", color);
        box.add(data);
    }
    chart.setLowerLimit(0);
    chart.setYScaleValueGap(1);
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
//线形图
function initLineView(areas,arr){
    var len=areas.length;
    $('#barChart').html("");

    var chart = new twaver.charts.LineChart();
    box = chart.getDataBox();

    var keys=Object.keys(arr);
    var lens=keys.length;
    for(var i=0;i<lens;i++){
        var color='red';
        if(keys[i]=='1'){
            color="red";
        }
        else if(keys[i]=='2'){
            color="#2f4f4f";
        }
        else if(keys[i]=='3'){
            color="#66cdaa";
        }
        else if(keys[i]=='4'){
            color="#deb887";
        }
        else if(keys[i]=='5'){
            color="#87ceeb";
        }
        else if(keys[i]=='6'){
            color="green";
        }
        else if(keys[i]=='7'){
            color="#4b0082";
        }
        else if(keys[i]=='8'){
            color="#90ee90";
        }
        else if(keys[i]=='9'){
            color="gray";
        }
        else if(keys[i]=='A'){
            color="#20b2aa";
        }
        else if(keys[i]=='B'){
            color="yellow";
        }
        else if(keys[i]=='C'){
            color="pink";
        }
        else if(keys[i]=='D'){
            color="#e0ffff";
        }
        else{
            color="red";
        }
        var data = new twaver.Element();
        data.setStyle("chart.values", arr[keys[i]]);
        data.setStyle("chart.color", color);
        box.add(data);
    }
    chart.setXScaleTexts(new twaver.List(areas));

    chart.setBackgroundVisible(false);  //不显示背景
    chart.setLowerLimit(0);
    chart.setYScaleValueGap(1);
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
    // var arr={
    //     "旅游宾馆类":[100,200,300,400,500,400,300,200],
    //     "图书馆阅览室":[500,400,300,200,100,200,300,400],
    //     "电脑培训中心类":[100,100,300,300,200,200,500,500],
    // };
    //按经营性质统计：数据初始化
    for(var k=0;k<lgt;k++){
        props[prop[k]]=[];
    }
    //按服务类型统计：数据初始化
    for(k=0;k<types;k++){
        serviceTypes[serviceType[k]]=[];
    }
   

    //按经营性质统计
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/placeCount/countByProp",
        data: "",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                areas=[];
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                    areas.push(objs[i]["districtName"]);
                    var cnt=objs[i]["countByProp"];
                    var cntLen=cnt.length;
                    var obj={};
                    for(var j=0;j<cntLen;j++){
                        var tem=cnt[j];
                        if(!(tem["prop"] in obj)){
                            obj[tem["prop"]]=tem["count"];
                        }
                    }
                    for(var l=0;l<lgt;l++){
                        if(prop[l] in obj){
                            props[prop[l]].push(obj[prop[l]]);
                        }
                        else{
                            props[prop[l]].push(0);
                        }
                    }
                }
           }
           else{
               alert('按经营性质统计时数据获取失败！');
           }
        },
        error:function(resp){
           alert('按经营性质统计时获取信息失败！');
        }
    });
    console.log(props);
    //按服务类型统计
    $.ajax({
        type:"get",
        async:false,    //同步
        contentType: "application/json;charset=UTF-8",
        url:"http://192.168.0.1:8080/placeCount/countByType",
        data: "",
        success: function(resp) {
            console.log(JSON.stringify(resp));
            if(resp.meta.success){
                var objs=resp.data;
                var len=objs.length;
                for(var i=0;i<len;i++){
                    var cnt=objs[i]["countByType"];
                    var cntLen=cnt.length;
                    var obj={};
                    for(var j=0;j<cntLen;j++){
                        var tem=cnt[j];
                        if(!(tem["type"] in obj)){
                            obj[tem["type"]]=tem["count"];
                        }
                    }
                    for(var l=0;l<types;l++){
                        if(serviceType[l] in obj){
                            serviceTypes[serviceType[l]].push(obj[serviceType[l]]);
                        }
                        else{
                            serviceTypes[serviceType[l]].push(0);
                        }
                    }
                }
           }
           else{
               alert('按服务类型统计时数据获取失败！');
           }
        },
        error:function(resp){
           alert('按服务类型统计时获取信息失败！');
        }
    });
    console.log(serviceTypes);
    

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
    
    initView(areas,props);    //柱状图：按经营性质统计
})
//点击下拉框，更改统计类型
$('ul#statisticType').on('click','a',function(e){
    var target=$(e.currentTarget);
    var type=target.text();
    var nowType=$('#statisticBtn span').eq(0).text();
    console.log(type,nowType);
    if(type!=nowType){
        console.log('当前是:',type);
        $('#statisticBtn span').eq(0).text(type);
        if(type=="按经营性质统计"){
            //隐藏上述提示信息
            $('#description').show();
            initView(areas,props);

        }
        else if(type=="按服务类型统计"){
            $('#description').hide();
            initViewBar(areas,serviceTypes);
        }
       
    }
});