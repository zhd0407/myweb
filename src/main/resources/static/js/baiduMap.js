var map = new BMapGL.Map("container", {enableMapClick:false}); // 创建Map实例,GL版命名空间为BMapGL(鼠标右键控制倾斜角度)
// map.setMapType(BMAP_EARTH_MAP);
map.centerAndZoom(new BMapGL.Point(116.404, 39.915), 11);      // 初始化地图,设置中心点坐标和地图级别
map.enableScrollWheelZoom(true);                               // 开启鼠标滚轮缩放

var styleOptions = {
    strokeColor: '#5E87DB',   // 边线颜色
    fillColor: '#5E87DB',     // 填充颜色。当参数为空时，圆形没有填充颜色
    strokeWeight: 2,          // 边线宽度，以像素为单位
    strokeOpacity: 1,         // 边线透明度，取值范围0-1
    fillOpacity: 0.2          // 填充透明度，取值范围0-1
};
var labelOptions = {
    borderRadius: '2px',
    background: '#FFFBCC',
    border: '1px solid #E1E1E1',
    color: '#703A04',
    fontSize: '12px',
    letterSpacing: '0',
    padding: '5px'
};

// 实例化鼠标绘制工具
var drawingManager = new BMapGLLib.DrawingManager(map, {
    // isOpen: true,        // 是否开启绘制模式
    enableCalculate: false, // 绘制是否进行测距测面
    enableSorption: true,   // 是否开启边界吸附功能
    sorptiondistance: 20,   // 边界吸附距离
    circleOptions: styleOptions,     // 圆的样式
    polylineOptions: styleOptions,   // 线的样式
    polygonOptions: styleOptions,    // 多边形的样式
    rectangleOptions: styleOptions,  // 矩形的样式
    labelOptions: labelOptions,      // label样式
});
drawingManager.addEventListener('overlaycomplete', showInfo);

function draw(e) {
    var arr = document.getElementsByClassName('bmap-btn');
    for(var i = 0; i<arr.length; i++) {
        arr[i].style.backgroundPositionY = '0';
    }
    e.style.backgroundPositionY = '-52px';
    switch(e.id) {
        case 'addMarker': {
            var drawingType = BMAP_DRAWING_MARKER;
            break;
        }
        case 'polyline': {
            var drawingType = BMAP_DRAWING_POLYLINE;
            break;
        }
        case 'rectangle': {
            var drawingType = BMAP_DRAWING_RECTANGLE;
            break;
        }
        case 'polygon': {
            var drawingType = BMAP_DRAWING_POLYGON;
            break;
        }
        case 'circle': {
            var drawingType = BMAP_DRAWING_CIRCLE;
            break;
        }
    }
    // 进行绘制
    if (drawingManager._isOpen && drawingManager.getDrawingMode() === drawingType) {
        e.style.backgroundPositionY = '0';
        drawingManager.close();
    } else {
        drawingManager.setDrawingMode(drawingType);
        drawingManager.open();
    }
};

function showInfo(e,overlay){

    var params = {};
    var points = [];
    if(e.drawingMode!='circle'){
        var point = overlay.overlay.points;
        params['radius'] = 0;
        for(var i in point){
            if(point[i].latLng){
                points.push({lng:point[i].latLng.lng,lat:point[i].latLng.lat});
            }
        }
    }else{
        var point = overlay.overlay.point;
        points.push({ lng:point.latLng.lng, lat:point.latLng.lat});
        params['radius'] = overlay.overlay.radius;
    }
    params['type'] = e.drawingMode;
    params['points'] = points;
    debugger
    $.ajax({
        type : "POST",//提交方式
        url  : "/insertMapAreaInfo",//提交地址
        data : JSON.stringify(params),//提交的数据
        dataType : "json",
        contentType: "application/json;charset=utf-8",
        success  : function(data){  //返回结果
            alert("保存成功");
        },
        error : function(data){
            debugger
            alert("错误");
        }
    });
}

$.ajax({
    type : "GET",//提交方式
    url  : "/getOverlayInfo",//提交地址
    success  : function(data){  //返回结果
        debugger
        if(data){
            for(var i in data){

                var type = data[i].type;
                var points = JSON.parse(data[i].points);

                var overlays = [];
                if(type=="polygon"||type=="polyline"||type=="rectangle"){
                    var ps = [];
                    for(var j in points){
                        ps.push(new BMapGL.Point(points[j].lng, points[j].lat));
                    }
                    if(type=="polygon"||type=="rectangle"){
                        var polygon = new BMapGL.Polygon(ps, {strokeColor: 'blue',strokeWeight: 2,strokeOpacity: 0.5 });
                        map.addOverlay(polygon);
                        overlays.push(polygon);
                    }else{
                        var polyline = new BMapGL.Polyline(ps, {strokeColor: 'blue',strokeWeight: 2,strokeOpacity: 0.5 });
                        map.addOverlay(polyline);
                        overlays.push(polyline);
                    }
                }else if(type=="circle"){
                    debugger;
                    var circle = new BMapGL.Circle(new BMapGL.Point(points[0].lng, points[0].lat), data[i].radius, {strokeColor: 'blue',strokeWeight: 2,strokeOpacity: 0.5});
                    map.addOverlay(circle);
                    overlays.push(circle);
                }
                var clickEvts = ['click', 'dblclick', 'rightclick'];
                var moveEvts = ['mouseover', 'mouseout'];

                for (var i = 0; i < overlays.length; i++) {
                    const overlay = overlays[i];
                   // overlay.enableEditing();
                    overlay.addEventListener('click', function(){alert(overlay.toString() +  '被单击!')});
                    overlay.addEventListener('dbclick', function(){alert(overlay.toString() +  '被双击!')});
                    overlay.addEventListener('rightclick', function(){alert(overlay.toString() +  '被右击!')});
                    overlay.addEventListener('mouseover', function(){overlay.setFillColor('#6f6cd8')});
                    overlay.addEventListener('mouseout', function(){overlay.setFillColor('#fff');});
                }
            }
        }
    },
    error : function(data){

        alert("错误");
    }
});


var opts = {
    position: new BMapGL.Point(116.2787, 40.0492), // 指定文本标注所在的地理位置
    offset: new BMapGL.Size(30, -30) // 设置文本偏移量
};
// 创建文本标注对象
var label = new BMapGL.Label('欢迎使用百度地图JSAPI GL版本', opts);
// 自定义文本标注样式
label.setStyle({
    color: 'blue',
    borderRadius: '5px',
    borderColor: '#ccc',
    padding: '10px',
    fontSize: '16px',
    height: '30px',
    lineHeight: '30px',
    fontFamily: '微软雅黑'
});
map.addOverlay(label);

/* var point = new BMapGL.Point(116.404, 39.925);
 map.centerAndZoom(point, 15);
 // 创建点标记
 var marker = new BMapGL.Marker(point);
 map.addOverlay(marker);
 // 创建信息窗口
 var opts = {
     width: 200,
     height: 100,
     title: '故宫博物院'
 };
 var infoWindow = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts);
 // 点标记添加点击事件
 marker.addEventListener('click', function () {
     map.openInfoWindow(infoWindow, point); // 开启信息窗口
 });
*/
//覆盖物的编辑权限开启和关闭
function openEdit() {
    polyline.enableEditing();
    polygon.enableEditing();
    circle.enableEditing();
}
function closeEdit() {
    polygon.disableEditing();
    polyline.disableEditing();
    circle.disableEditing();
}

/* var sContent = `<h4 style='margin:0 0 5px 0;'>天安门</h4>
 <img style='float:right;margin:0 4px 22px' id='imgDemo' src='../img/tianAnMen.jpg' width='139' height='104'/>
 <p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>
 天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...
 </p></div>`;
 var infoWindow = new BMapGL.InfoWindow(sContent);
 // marker添加点击事件
 marker.addEventListener('click', function () {
     this.openInfoWindow(infoWindow);
     // 图片加载完毕重绘infoWindow
     document.getElementById('imgDemo').onload = function () {
         infoWindow.redraw(); // 防止在网速较慢时生成的信息框高度比图片总高度小，导致图片部分被隐藏
     };
 });*/

/*
//加载完成后进行点的加载等
var map = new BMapGL.Map('container');
map.centerAndZoom(new BMapGL.Point(116.404, 39.928), 15);
map.enableScrollWheelZoom(true);
map.addEventListener('tilesloaded', function () {
    alert('地图加载完成！');
});*/