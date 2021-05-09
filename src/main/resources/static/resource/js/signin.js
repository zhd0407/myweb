function signin(){
    $.ajax({
        type : "POST",//提交方式
        url  : "/user/signin",//提交地址
        dataType : "json",
        success  : function(data){  //返回结果
            if (data.result=="success"){
               $("#signinSuccessDiv").show();
                $("#signinDiv").hide();
            }else{
                layer.msg(data.message, {icon: 5});
            }
        },
        error : function(data){
            alert("错误");
        }
    });
}