/**
 * 修改密码
 */
function changePwd() {
    //1.获取请求参数
    var lastPwd = $("#last_password").val().trim();
    var newPwd = $("#new_password").val().trim();
    var finalPwd = $("#final_password").val().trim();
    var userId = getCookie("uid");
    //2.参数格式校验
    var ok = true;
    if (lastPwd == ""){
        ok = false;
        $("#warning_1 span").html("密码为空");
    }
    if (newPwd == ""){
        ok = false;
        $("#warning_2 span").html("密码为空");
    }
    if (finalPwd == ""){
        ok = false;
        $("#warning_3 span").html("密码为空");
    }
    if (userId == null){
        ok = false;
        window.location.href = '/log_in.html';
    }
    if (ok){
        //3.发送Ajax
        $.ajax({
            url: base_path+"/user/changePwd.do",
            type: "post",
            data: {"lastPwd":lastPwd,"newPwd":newPwd,"userId":userId},
            dataType: "json",
            success: function (result) {
                //如果旧密码输入不正确，追加提示信息
                //修改成功，status=0，
                if (result.status == 1){
                    alert(result.msg);
                    window.location.href = '/Change_password.html';
                }
                //状态为0则成功修改密码
                if (result.status == 0){
                    alert(result.msg);
                }
                //状态为2则修改密码失败
                if (result.status == 2){
                    alert(result.msg);
                }
            },
            error: function () {
                alert("修改密码异常");
            }
        })
    }
}