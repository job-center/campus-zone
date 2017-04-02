var SysUserIndex = function () {

    var batchDelete = function () {
        var sysuserRoleIds = $("#sysuserRoleIds_delete").val();
        var sysuserId = $("#sysuserid").val();
        $.ajax({
            type: "DELETE",
            url: "/v1/sysuser/"+sysuserId+"/role/batch",
            data: {"sysuserRoleIds":sysuserRoleIds},
            dataType: "json",
            success: function(result){
                $.cookie.json = true;
                if(result.success){
                    $.cookie('action-message',{action:"success",message:"操作成功"});
                }else{
                    $.cookie('action-message',{action:"error",message:"操作失败"});
                }
                window.location.reload();
            }
        });
    };

    return{
        init : function () {

            $("#addpower").click(function () {
                $("#div_sysuser_add").modal();
            });

            $("#selectAll").change(function(){
                if($(this).attr("checked")){
                    $("table#tableSysUserRole tbody input[type='checkbox']").uniform().each(function(index, item){
                        $.uniform.update($(item).attr("checked", true));
                    });
                }else{
                    $("table#tableSysUserRole tbody input[type='checkbox']").uniform().each(function(index, item){
                        $.uniform.update($(item).attr("checked", false));
                    });
                }
            });//全选结束")

            $("#removepower").click(function () {
                var roleIds='';
                $("table#tableSysUserRole tbody input[type='checkbox']").uniform().each(function(index, item){
                    if($(item).attr("checked")){
                        roleIds+=$(item).val()+",";
                    }
                });
                if(roleIds!=''){
                    roleIds=roleIds.substring(0,roleIds.length-1);
                    $("#sysuserRoleIds_delete").val(roleIds);
                    confirm("删除用户角色","确认删除这些用户角色?",batchDelete);
                }else{
                    alert("删除用户角色","请选择需要删除的角色.");
                }
            })

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