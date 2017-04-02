var SysUserIndex = function () {

    return{
        init : function () {

            $("#addpower").click(function () {
                $("#div_sysuser_add").modal();
            });

            $("#btnAddSysUserRole").click(function () {
                var sysuserid = $("#sysuserid").val();
                var roleId = $("#roleId").val();

                if (roleId == null || roleId == ""){
                    alert("请选择系统角色");
                    return false;
                }

                $.ajax({
                    url:"/v1/sysuser/role",
                    type:"POST",
                    data:{userId:sysuserid,roleId:roleId},
                    success:function (result) {
                        $.cookie.json = true;
                        if (result.success) {
                            $.cookie('action-message', {
                                action : "success",
                                message : result.msg
                            });
                        } else {
                            $.cookie('action-message', {
                                action : "error",
                                message : result.msg
                            });
                        }
                        window.location.reload();
                    },
                    failure:function (result) {
                        alert("操作失败", result.detail);
                    }
                })
            })
        }
    }
}();

$(function () {
    SysUserIndex.init();
})