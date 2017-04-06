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
                    url:"/v1/list/"+schoolId+"/grads",
                    type:"GET",
                    success:function (result) {
                        var obj = eval(result);
                        $("#gradId").empty();
                        $("#gradId").append(
                            "<option value='' selected=true>-----------------请选择-------------------</option>");

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
            var gradeId = $("gradId").val();
            if(gradeId != null && gradeId != ''){

            }
        })
    }

    return {
        init:function () {
            handleSelect2();
            handleSchool();
            $("#birthday").datepicker({ changeYear: true ,dateFormat: "yy-mm-dd"});

            $("#createstudent").click(function () {
                $("#div_student_add").modal();
            });

        }

    }
}();

$(function () {
    Students.init();
})