 // var theUrl="http:192.168.0.200:8888";
 var theUrl="http:192.168.0.1:8080";
 $(function(){
  var Inmap = new BMap.Map("allmap");
  var Inpoint = new BMap.Point(108.93, 34.27);
  Inmap.centerAndZoom(Inpoint, 15);
 })

//设置时间样式
function p(s) {
    return s < 10 ? '0' + s: s;
}

function showTime(theTime){
   time=theTime[0]+'-'+p(theTime[1])+"-"+p(theTime[2])+" "+
              p(theTime[3])+':'+p(theTime[4])+":"+p(theTime[5]);
  return time;
}
 
 //地图各点mac路径显示功能
  function showMap(data_info){
  // 百度地图API功能
  var map = new BMap.Map("allmap");
  //默认中心点为西安市
  var point = new BMap.Point(108.93, 34.27); 
  map.centerAndZoom(point, 15);
  // 自定义覆盖物
  function ComplexCustomOverlay(point, text, mouseoverText){
    this._point = point;
    this._text = text;
    this._overText = mouseoverText;
  }
  ComplexCustomOverlay.prototype = new BMap.Overlay();
  ComplexCustomOverlay.prototype.initialize = function(map){
    this._map = map;
    var div = this._div = document.createElement("div");
    div.style.position = "absolute";
    div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
    div.style.backgroundColor = "#EE5D5B";
    div.style.border = "1px solid #BC3B3A";
    div.style.color = "white";
    div.style.height = "18px";   //改进
    div.style.padding = "2px";
    div.style.lineHeight = "18px";
    div.style.whiteSpace = "nowrap";
    div.style.MozUserSelect = "none";
    div.style.fontSize = "12px"
    var span = this._span = document.createElement("span");
    div.appendChild(span);
    span.appendChild(document.createTextNode(this._text));      
    var that = this;

    var arrow = this._arrow = document.createElement("div");
    arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
    arrow.style.position = "absolute";
    arrow.style.width = "11px";
    arrow.style.height = "10px";
    arrow.style.top = "22px";
    arrow.style.left = "10px";
    arrow.style.overflow = "hidden";
    div.appendChild(arrow);

    div.onmouseover = function(){
      this.style.backgroundColor = "#6BADCA";
      this.style.borderColor = "#0000ff";
      this.style.height = "60px";
      this.getElementsByTagName("span")[0].innerHTML = that._overText;
      arrow.style.backgroundPosition = "0px -100px";
    }

    div.onmouseout = function(){
      this.style.backgroundColor = "#EE5D5B";
      this.style.borderColor = "#BC3B3A";
      this.style.height = "18px";
      this.getElementsByTagName("span")[0].innerHTML = that._text;
      arrow.style.backgroundPosition = "0px 0px";
    }

    map.getPanes().labelPane.appendChild(div);

    return div;
  }
  ComplexCustomOverlay.prototype.draw = function(){
    var mp = this._map;
    var pixel = mp.pointToOverlayPixel(this._point);
    this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
    this._div.style.top  = pixel.y - 30 + "px";
  }
  var pointArray = new Array();
  var txt = "第一个", mouseoverTxt = txt +"<br>"+ " 位置点" +new Date();
  for(var i=0;i<data_info.length;i++){ 
     var mouseoverTxt = "地点编号："+data_info[i].placeNo+"<br>"+
                  "起始时间："+showTime(data_info[i].firstTime)+"<br>" + 
                  "离开时间："+showTime(data_info[i].lastTime) ;
    var marker= new ComplexCustomOverlay(new BMap.Point
                (data_info[i].longitude, data_info[i].latitude), i+1,mouseoverTxt); 
     map.addOverlay(marker);    //增加点 -->
     pointArray[i] = new BMap.Point(data_info[i].longitude, data_info[i].latitude); 
     // marker.addEventListener("click",attribute); 
   } 
  //让所有点在视野范围内
  map.setViewport(pointArray);
  //缩放地图
  map.enableScrollWheelZoom(true);
 }

$("#searchTrack").click(function(e){
     var macId=$("#macId").val();
     var startTime=$("#startTime").val();
     var endTime=$("#endTime").val();
     console.log('add ');
     console.log(JSON.stringify({
      "mac": macId,
      "startTime":startTime,
      "endTime":endTime
    }));
      $.ajax({
      type: "get",
      url:theUrl+"/judgeAnalyze/trackQuery?mac="
          +macId+"&startTime="+startTime+"&endTime="+endTime,
    success: function(response){
      if(response.meta.success){
        var macData=response.data;
        console.log(macData);
        var dataLen=macData.length;
        showMap(macData);

      }
    }
  });
})