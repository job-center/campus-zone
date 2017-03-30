var SysUserList=function(){

    return{
        init:function(){
            //init page param

            $("#createAccount").click(function(){
                var url =  $("#setOptionUrl").val() + "index";
                window.location.href=url;
            });

            $("a[name=editSysUser]").click(function(){
                var url = "/v1/sysuser/" + $(this).attr("accountId");
                window.location.href=url;
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
    ZuesAccount.init();
})