var SysUserList=function(){

    var batchDelete = function () {
        var schoolIds = $("#sysroles_delete").val();
        $.ajax({
            type: "DELETE",
            url: "/v1/roleinfos/batchdelete/" + schoolIds,
            //data: {"schoolIds":schoolIds},
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

    var modifyDetail = function(schoolId) {
        $.ajax({
            type: "GET",
            url: "/v1/schoolinfos/" + schoolId,
            dataType: "json",
            success: function(result){
                $.cookie.json = true;
                if(result.success){
                    //$.cookie('action-message',{action:"success",message:"操作成功"});
                    $("#name").val(result.data.name);
                    $("#fullName").val(result.data.fullName);
                    $("#address").val(result.data.address);
                    $("#personCharge").val(result.data.personCharge);
                    $("#type").val(result.data.type);
                    $("#description").val(result.data.description);
                    $("#postcode").val(result.data.postcode);
                    $("#id").val(schoolId);
                }else{
                    $.cookie('action-message',{action:"error",message:"操作失败"});
                }
                $("#btnAddSchool").text('修改');
                $("#div_school_add").modal();
            }
        });
    }


    return{
        init:function(){
            //init page param

            $("#createSchool").click(function(){
                $("#btnAddRole").text("添加");
                $("#div_school_add").modal();
            });

            $("#btnAddSchool").click(function(){
                var name = $("#name").val();

                if (name == null || name == ""){
                    alert("学校名称为空");
                    return false;
                }

                $.ajax({
                    url : "/v1/insertUpdateSchool",
                    type : 'post',
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
                        window.location.href = "/v1/schools";
                        // window.location.reload();
                    },
                    failure : function(result) {
                        alert("操作失败", result.detail);
                    }
                });
            });

            $("#editID").click(function(){
                var schoolId = $(this).text();
                modifyDetail(schoolId);
            })

            $("a[name=editSchool").click(function(){
                var schoolId = $(this).attr("data");
                modifyDetail(schoolId);
            });

            $("#selectAll").change(function(){
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
                var schoolIds='';
                $("table#fromAccountTable tbody input[type='checkbox']").uniform().each(function(index, item){
                    if($(item).attr("checked")){
                        schoolIds+=$(item).val()+",";
                    }
                });
                if(schoolIds!=''){
                    schoolIds=schoolIds.substring(0,schoolIds.length-1);
                    $("#sysroles_delete").val(schoolIds);
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