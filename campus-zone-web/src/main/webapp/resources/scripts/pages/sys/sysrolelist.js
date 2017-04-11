var SysUserList=function(){

    var batchDelete = function () {
        var roleIds = $("#sysroles_delete").val();
        $.ajax({
            type: "DELETE",
            url: "/v1/roleinfos/batchdelete/" + roleIds,
            //data: {"roleIds":roleIds},
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

    var modifyDetail = function(roleId) {
        $.ajax({
            type: "GET",
            url: "/v1/roleinfos/" + roleId,
            dataType: "json",
            success: function(result){
                $.cookie.json = true;
                if(result.success){
                    //$.cookie('action-message',{action:"success",message:"操作成功"});
                    $("#roleName").val(result.data.roleName);
                    $("#roleDescription").val(result.data.roleDescription);
                    $("#id").val(roleId);
                }else{
                    $.cookie('action-message',{action:"error",message:"操作失败"});
                }
                $("#btnAddRole").text('修改');
                $("#div_sysrole_add").modal();
            }
        });
    }


    return{
        init:function(){
            //init page param

            $("#createRole").click(function(){
                $("#btnAddRole").text("添加");
                $("#div_sysrole_add").modal();
            });

            $("#btnAddRole").click(function(){
                var roleName = $("#roleName").val();
                var roleDescription = $("#roleDescription").val();

                if (roleName == null || roleName == ""){
                    alert("角色名称为空");
                    return false;
                }

                $.ajax({
                    url : "/v1/insertUpdateRole",
                    type : "POST",
                    data : $("#add_admin_form").serialize(),
                    success : function(result) {
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
                        window.location.href = "/v1/roleinfos";
                        // window.location.reload();
                    },
                    failure : function(result) {
                        alert("操作失败", result.detail);
                    }
                });
            });

            $("#editID").click(function(){
                var roleId = $(this).text();
                modifyDetail(roleId);
            })

            $("a[name=editSysRole]").click(function(){
                var roleId = $(this).attr("data");
                modifyDetail(roleId);
            });

            $("#selectAll").change(function(){
                console.info('xxxxx');
                if($(this).attr("checked")){
                    $("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
                        $.uniform.update($(item).attr("checked", true));
                    });
                }else{
                    $("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
                        $.uniform.update($(item).attr("checked", false));
                    });
                }
            });//全选结束")

            $("#removepower").click(function () {
                var roleIds='';
                $("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
                    if($(item).attr("checked")){
                        roleIds+=$(item).val()+",";
                    }
                });
                if(roleIds!=''){
                    roleIds=roleIds.substring(0,roleIds.length-1);
                    $("#sysroles_delete").val(roleIds);
                    confirm("删除角色","确认删除这些角色?",batchDelete);
                }else{
                    alert("删除角色","请选择需要删除的角色.");
                }
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