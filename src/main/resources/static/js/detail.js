$(document).ready(function() {
    $("#head").load("../head");
    $("#advert").load("/advert");
    $("#footer").load("/footer");
    $("#currWeekTopic").load("/currWeekTopic");
});
layui.config({
    version: "3.0.0"
    ,base: '/mods/'
}).extend({
    fly: 'index'
}).use(['fly', 'face'], function(){
    var $ = layui.$
        ,fly = layui.fly;
    //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
    /*
    $('.detail-body').each(function(){
      var othis = $(this), html = othis();
      othis(fly.content(html));
    });
    */
});

function postMsg(){
    var noteNo = document.getElementById("noteNo").value;// $("#noteNo").value();
    var content =  document.getElementById("content").value;// $("#content").value();
    var upMsgNo = "";
    $.ajax({
        type : "POST",//提交方式
        url  : "/message/add",//提交地址
        data : {"noteNo":noteNo,"content":content, "upMsgNo":upMsgNo},//提交的数据
        dataType : "json",
        success  : function(data){  //返回结果
            if (data.result=="success"){
                //layer.msg("评论成功" , {icon: 5});
                var htmlStr = document.getElementById("jieda").innerHTML +
                    "<li data-id=\"111\" class=\"jieda-daan\" style=\"border-bottom:1px dotted #DFDFDF;\">\n" +
                    "            <a name=\"item-1111111111\"></a>\n" +
                    "            <div class=\"detail-about detail-about-reply\">\n" +
                    "              <a class=\"fly-avatar\" href=\"\">\n" +
                    "                <img src=\"https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg\" alt=\" \">\n" +
                    "              </a>\n" +
                    "              <div class=\"fly-detail-user\">\n" +
                    "                <a href=\"\" class=\"fly-link\">\n" +
                    "                  <cite>贤心</cite>\n" +
                    "                  <i class=\"iconfont icon-renzheng\" title=\"认证信息：XXX\"></i>\n" +
                    "                  <i class=\"layui-badge fly-badge-vip\">VIP3</i>              \n" +
                    "                </a>\n" +
                    "                \n" +
                    "                <span>(楼主)</span>\n" +
                    "                <!--\n" +
                    "                <span style=\"color:#5FB878\">(管理员)</span>\n" +
                    "                <span style=\"color:#FF9E3F\">（社区之光）</span>\n" +
                    "                <span style=\"color:#999\">（该号已被封）</span>\n" +
                    "                -->\n" +
                    "              </div>\n" +
                    "\n" +
                    "              <div class=\"detail-hits\">\n" +
                    "                <span >" + data.msgTime + "</span>\n" +
                    "              </div>\n" +
                    "\n" +
                    "              <i class=\"iconfont icon-caina\" title=\"最佳答案\" style=\"display: none\" id=\"best+" + data.msgNo + "\"></i>\n" +
                    "            </div>\n" +
                    "            <div class=\"detail-body jieda-body photos\">\n" +
                    "              <p >" + content + "</p>\n" +
                    "            </div>\n" +
                    "            <div class=\"jieda-reply\">\n" +
                    "              <span class=\"jieda-zan zanok\" type=\"zan\">\n" +
                    "                <i class=\"iconfont icon-zan\"></i>\n" +
                    "                <em >0</em>\n" +
                    "              </span>\n" +
                    "              <span type=\"reply\">\n" +
                    "                <i class=\"iconfont icon-svgmoban53\"></i>\n" +
                    "                回复\n" +
                    "              </span>\n" +
                    "              <div class=\"jieda-admin\">\n" +
                    "                <span type=\"edit\" style='display: none;'>编辑</span>\n" +
                    "                <span type=\"del\" onclick=\"deleteMessage(this);\" value=\""+data.msgNo+"\">删除</span>\n" +
                    "                <!-- <span class=\"jieda-accept\" type=\"accept\">采纳</span> -->\n" +
                    "              </div>\n" +
                    "            </div>\n" +
                    "          </li>";

                document.getElementById("jieda").innerHTML = htmlStr;
                //document.getElementById("jieda").appendChild(li);
                //liInfo.appendTo(document.getElementById(jieda));


            }else{
                layer.msg(data.msg , {icon: 5});
            }
        },
        error : function(data){
            alert("错误");
        }
    });
}

function deleteMessage(obj){
    var msgNo = obj.getAttribute("value");
    $.ajax({
        type : "POST",//提交方式
        url  : "/message/del",//提交地址
        data : {"msgNo": msgNo},//提交的数据
        dataType : "json",
        success  : function(data){  //返回结果
            if (data.result=="success"){
                obj.parentElement.parentElement.parentElement.remove();
            }else{
                layer.msg(data.msg , {icon: 5});
            }
        },
        error : function(data){
            alert("错误");
        }
    });
}
function acceptMsg(obj){
    var msgNo = obj.getAttribute("value");
    $.ajax({
        type : "POST",//提交方式
        url  : "/message/acceptMsg",//提交地址
        data : {"msgNo": msgNo},//提交的数据
        dataType : "json",
        success  : function(data){  //返回结果
            if (data.result=="success"){
               /* $("#best_"+msgNo).show();
                $("#del_"+msgNo).hide();
                obj.style.display = "none";*/
               window.location.reload();
            }else{
                layer.msg(data.msg , {icon: 5});
            }
        },
        error : function(data){
            alert("错误");
        }
    });
}
