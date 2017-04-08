var Students = function () {

    var handleSchool = function () {
        //处理学校，年级以及班级级联操作
        $.ajax({
            url:"/v1/list/schools",
            type:"GET",
            success:function (result) {
                var obj = eval(result);
                $("#schoolId").empty();
                $("#schoolId").append(
                    "<option value='' selected=true>-----------------请选择-------------------</option>");

                for (var p in obj.data){
                    $("#schoolId").append(
                        "<option value='" + obj.data[p].id + "'>" + obj.data[p].name
                        + "</option>");
                }
            },
            failure:function (result) {
                alert("操作失败", result.msg);
            }
        });

        $("#schoolId").change(function () {
            var schoolId = $("#schoolId").val();
            if(schoolId != null && schoolId != ''){
                $.ajax({
                    url:"/v1/school/"+schoolId+"/grads",
                    type:"GET",
                    success:function (result) {
                        var obj = eval(result);
                        $("#gradId").empty();
                        $("#gradId").append(
                            "<option value='' selected=true>请选择</option>");

                        for (var p in obj.data){
                            $("#gradId").append(
                                "<option value='" + obj.data[p].id + "'>" + obj.data[p].name
                                + "</option>");
                        }
                    },
                    failure:function (result) {
                        alert("操作失败", result.msg);
                    }
                });
            }
        });

        $("#gradId").change(function () {
            var gradeId = $("#gradId").val();
            if(gradeId != null && gradeId != ''){
                $.ajax({
                    url:"/v1/grade/"+gradeId+"/groups",
                    type:"GET",
                    success:function (result) {
                        var obj = eval(result);
                        $("#groupId").empty();
                        $("#groupId").append(
                            "<option value='' selected=true>请选择</option>");

                        for (var p in obj.data){
                            $("#groupId").append(
                                "<option value='" + obj.data[p].id + "'>" + obj.data[p].name
                                + "</option>");
                        }
                    },
                    failure:function (result) {
                        alert("操作失败", result.msg);
                    }
                });
            }
        })
    };

    var checkProperty = function (field,message) {
        if (field == null || field == ""){
            throw new Error(message);
        }
    };

    var changeStudentStatus = function (studentId) {
        $.ajax({
            url:"/v1/student/"+studentId+"/status",
            type:"PUT",
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
                alert("操作失败", result.msg);
            }
        });
    }

    var createStudent = function () {
        var name = $("#name").val();
        var groupId = $("#groupId").val();
        var phoneNo = $("#phoneNo").val();
        var birthday = $("#birthday").val();

        try {
            checkProperty(name,"学生姓名不能为空");
            checkProperty(groupId,"请选择班级信息");
            checkProperty(phoneNo,"手机号不能为空");
            // checkProperty(birthday,"出生日期不能为空");
        }catch (e){
            alert(e.message);
            return false;
        }

        $.ajax({
            url : "/v1/student",
            type : "POST",
            data : $("#add_studnet_form").serialize(),
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
                // window.location.href = "/v1/sysusers";
                window.location.reload();
            },
            failure : function(result) {
                alert("操作失败", result.detail);
            }
        });
    };

    return {
        init:function () {
            handleSelect2();
            handleSchool();
            $("#birthday").datepicker({ changeYear: true ,dateFormat: "yy-mm-dd"});

            $("#createstudent").click(function () {
                $("#div_student_add").modal();
            });

            $("#btnAddStudent").click(function () {
                createStudent();
            })

            $("a[name='changeStudentStatus']").click(function () {
                var studentId = $(this).attr("data");
                if (studentId != null && studentId != ''){
                    changeStudentStatus(studentId);
                }
            })
        }

    }
}();

$(function () {
    Students.init();
})