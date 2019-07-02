layui.use('layedit', function(){
    var layedit = layui.layedit;
    layedit.build('noteBody'); //建立编辑器
});


$(document).ready(function() {
    $("#head").load("/head");
    $("#footer").load("/footer");
});

layui.use(['form'], function() {
    var form = layui.form;
    //监听提交
    form.on('submit(addNewNoteInfo)', function(data){
    });
});