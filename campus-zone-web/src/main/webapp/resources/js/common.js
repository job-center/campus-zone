//重写alert,界面关闭不需要执行某些操作(刷新界面)
function alert(content) {
    $("#alertModalTitle").html("提示")
    $("#alertModalContent").html(content);
    $("#alertModal").modal();
}
function alert(title, content) {
    $("#alertModalTitle").html(title);
    $("#alertModalContent").html(content);
    $("#alertModal").modal();
}
//重写alert，界面关闭需要执行默写操作(刷新界面)
function alert(title, content, functionName) {
    if (typeof(content) == "undefined") {
        $("#alertModalTitle").html("提示")
        $("#alertModalContent").html(title);
        $("#alertModal").modal();
    } else {
        $("#alertModalTitle").html(title);
        $("#alertModalContent").html(content);
        $("#alertOkBtn").bind("click", functionName);
        $("#alertModal").modal();
    }
}
//重写confirm，functionName是确定按钮绑定的方法
function confirm(title, content, functionName) {
    $("#confirmModalTitle").html(title);
    $("#confirmModalContent").html(content);
    $("#confirmOkBtn").bind("click", functionName);
    $("#confirmModal").modal();
}

//Handle Select2 Dropdowns
var handleSelect2 = function () {
    if (jQuery().select2) {
        $('.select2me').select2({
            placeholder: "Select",
            allowClear: true
        });
    }
}