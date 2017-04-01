var SysUserList=function(){

    var checkNull = function (text,msg) {
        if (text == null || text == ""){
            alert(msg);
            return false;
        }
    }
    return{
        init:function(){
            //init page param

            $("#createsysuser").click(function(){
                $("#div_sysuser_add").modal();
            });

            $("a[name=editSysUser]").click(function(){
                var url = "/v1/sysuser/" + $(this).attr("accountId");
                window.location.href=url;
            });

            $("#btnAddSysUser").click(function () {
                var name = $("#name").val();
                var passWord = $("#passWord").val();
                var realName = $("#realName").val();
                var sex = $("#sex").val();
                var phoneNumber = $("#phoneNumber").val();
                var email = $("#email").val();

                if (name == null || name == ""){
                    alert("姓名不能为空");
                    return false;
                }
                if (passWord == null || passWord == ""){
                    alert("密码不能为空");
                    return false;
                }
                reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                if (email != null && email != "") {
                    if (!reg.test(email)) {
                        alert("请输入正确的邮箱");
                        return;
                    }
                }

                $.ajax({
                    url : "/v1/sysuser",
                    type : "POST",
                    data : $("#IndexForm").serialize(),
                    success : function(result) {
                        $.cookie.json = true;
                        if (result.success) {
                            $.cookie('action-message', {
                                action : "success",
                                message : result.detail
                            });
                        } else {
                            $.cookie('action-message', {
                                action : "error",
                                message : result.detail
                            });
                        }
                        window.location.href = "/v1/sysusers";
                        // window.location.reload();
                    },
                    failure : function(result) {
                        alert("操作失败", result.detail);
                    }
                });
                console.info($("#sex").val());
            })

        },

        search:function(){
            $("#form_search").submit();
        }

    };
}();

//界面刷新
function refresh(){
    window.location.reload();
}



//功能: 1)去除字符串前后所有空格
//2)去除字符串中所有空格(包括中间空格,需要设置第2个参数为:g)
function Trim(str,is_global)
{
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g,"");
    if(is_global.toLowerCase()=="g")
        result = result.replace(/\s/g,"");
    return result;
}


$(function(){
    SysUserList.init();
})