function showLoginPage() {
    layer.open({
        type: 1
        ,
        title: false //不显示标题栏
        ,
        closeBtn: false
        ,
        area: '300px;'
        ,
        shade: 0.8
        ,
        id: 'LAY_layuipro' //设定一个id，防止重复弹出
        ,
        btn: ['火速围观', '残忍拒绝']
        ,
        btnAlign: 'c'
        ,
        moveType: 1 //拖拽模式，0或者1
        ,
        content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'
        ,
        success: function (layero) {
            var btn = layero.find('.layui-layer-btn');
            btn.find('.layui-layer-btn0').attr({
                href: 'http://www.layui.com/'
                , target: '_blank'
            });
        }
    });
}
function userLogin(){
    layer.open({
        type: 1,
        title:'登录',
        closeBtn: 1, //不显示关闭按钮
        anim: 2,
        offset: ['30%', '40%'],
        area: ['500px', '300px'],
        shadeClose: true, //开启遮罩关闭
        content:  '<div style="width: 90%;height: 240px;margin-top: 15px;">' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">用户名：</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="text" id="username" lay-verify="required" required placeholder="请输入" class="layui-input">\n' +
            '    </div>\n' +
            '  </div>' +
            '  <div class="layui-form-item">\n' +
            '    <label class="layui-form-label">密码：</label>\n' +
            '    <div class="layui-input-block">\n' +
            '      <input type="password" id="password" lay-verify="required" required placeholder="请输入" class="layui-input">\n' +
            '    </div>\n' +
            '  </div>'+
            '<div class="layui-form-item" align="center">\n' +
            '    <button class="layui-btn" onclick="suer_onclick();">确定</button>\n' +
            '    <button class="layui-btn layui-btn-primary" onclick="cancel_onclick();">取消</button>\n' +
            '  </div>' +
            '</div>'
    });
}

function suer_onclick(){
    var username = $("#username").val(),
        password = $("#password").val();
    if(username==null||username==''||username==undefined){
        layer.msg('请输入用户名');
        return;
    }
    if(password==null||password==''||password==undefined){
        layer.msg('请输入密码');
        return;
    }
    $.ajax({
        type : "POST",//提交方式
        url  : "/user/auth",//提交地址
        data : {"username":username,"password":password },//提交的数据
        dataType : "json",
        success  : function(data){  //返回结果
            if (data.result=="success"){
                //window.open("/index","_self");
                window.location.reload();
            }else{
                layer.msg('登录失败，请重试', {icon: 5});
            }
        },
        error : function(data){
            alert("错误");
        }
    });

}
function cancel_onclick() {
    layer.closeAll();
}
