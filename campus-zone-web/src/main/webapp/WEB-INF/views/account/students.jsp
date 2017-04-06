<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<head>
    <title>账户管理</title>
    <!-- PAGE LEVEL STYLE REFERENCES -->
    <script type="text/javascript"
            src="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.css" />
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2-metronic.css" />
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <h3 class="page-title">账户管理</h3>
        <ul class="page-breadcrumb breadcrumb">
            <li><i class="fa fa-home"></i> <span> 账户管理
				</span> <i class="fa fa-angle-right"></i></li>
            <li><span> 学生账户 </span></li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="form-actions">
            <form id="form_search" class="form-horizontal"
                  action="/v1/account/students" method="GET">
                <div class="form-group">

                    <label class="col-md-1 control-label">姓名:</label>
                    <div class="col-md-3">
                        <input type="text" id="studentName"
                               class="form-control input-medium input-inline" name="studentName"
                               placeholder="Input student name" value="${seed.queryData['studentName']}" />
                    </div>

                    <label class="col-md-1 control-label">手机号:</label>
                    <div class="col-md-3">
                        <input type="text" id="phoneNum"
                               class="form-control input-medium input-inline" name="phoneNumber"
                               placeholder="Input student phone number" value="${seed.queryData['phoneNumber']}" />
                    </div>

                    <label class="col-md-1 control-label">学号:</label>
                    <div class="col-md-3">
                        <input type="text" id="studentNo"
                               class="form-control input-medium input-inline" name="studentNo"
                               placeholder="Input student student number" value="${seed.queryData['studentNo']}" />
                    </div>

                    <div class="pull-right">
                        <a href="javascript:SysUserList.search();" class="btn dark">搜索
                            <i class="fa fa-search"></i>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="blank-form-actions">
            <a class="btn green" href="#" id="createstudent"><i
                    class="fa fa-plus"></i>创建</a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet box grey">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-calendar"></i> 学生列表
                </div>
            </div>
            <div class="portlet-body flip-scroll">
                <campus:pagination position="above"></campus:pagination>
                <table
                        class="table table-bordered table-striped table-condensed flip-content"
                        id="fromAccountTable">
                    <thead class="flip-content">
                    <tr>
                        <th style="width: 5%;">姓名</th>
                        <th style="width: 10%;">学校</th>
                        <th style="width: 10%;">学号</th>
                        <th style="width: 5%;">班级</th>
                        <th style="width: 5%;">性别</th>
                        <th style="width: 10%;">手机</th>
                        <th style="width: 10%;">微信号</th>
                        <th style="width: 5%;">父亲姓名</th>
                        <th style="width: 10%;">父亲手机</th>
                        <th style="width: 5%;">母亲姓名</th>
                        <th style="width: 10%;">母亲手机</th>
                        <th style="width: 5%;">账号状态</th>
                        <th style="width: 5%;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${seed.result }" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td>衡水一中 </td>
                            <td>${item.studentNo}</td>
                            <td>初一三班</td>
                            <td>男</td>
                            <td>${item.wechatId}</td>
                            <td>111</td>
                            <td>111</td>
                            <td>111</td>
                            <td>111</td>
                            <td>111</td>
                            <td><a class="btn default btn-xs blue-stripe" href="#"
                                   name="editSysUser" data="${item.sysUser.id}"><i
                                    class="fa fa-edit"></i> </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <campus:pagination position="below"></campus:pagination>
            </div>
        </div>
    </div>
</div>

<!-- Modal 添加管理员 -->
<div class="modal fade" id="div_student_add">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalTitle">添加学生基本信息</h4>
            </div>
            <form id="add_admin_form">
                <table align="center">
                    <tr style="height: 50px">
                        <td>姓名：</td>
                        <td>
                            <input type="text" name="name" id="name"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td>学校：</td>
                        <td>
                            <select id="schoolId" name="schoolId" class="select2me">
                                <option selected>-----------------请选择-------------------</option>
                            </select>
                        </td>
                    </tr>

                    <tr style="height: 50px">
                        <td>年级：</td>
                        <td>
                            <select id="gradId" name="gradId" class="select2me">
                                <option selected>-----------------请选择-------------------</option>
                            </select>
                        </td>
                    </tr>

                    <tr style="height: 50px">
                        <td>班级：</td>
                        <td>
                            <select id="groupId" name="groupId" class="select2me">
                                <option selected>-----------------请选择-------------------</option>
                            </select>
                        </td>
                    </tr>

                    <tr style="height: 50px">
                        <td>手机号：</td>
                        <td>
                            <input type="text" name="phoneNo" id="phoneNo"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>

                    <tr style="height: 50px">
                        <td>出生日期：</td>
                        <td>
                            <input type="text" name="birthday" id="birthday"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td>性别：</td>
                        <td>
                            <select class="form-control" name="sex"
                                    id="sex">
                                <option disabled="true" selected>请选择</option>
                                <option value="0">男</option>
                                <option value="1">女</option>
                            </select>
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td>微信号：</td>
                        <td>
                            <input type="text" name="email" id="email"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                </table>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary do_audit"
                            id="btnAddSysUser">添加
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="ctxUrl" value="${ctx}" />
<!-- PAGE LEVEL JS REFERENCES -->
<script src="${ctx}/resources/scripts/pages/account/students.js"
        type="text/javascript"></script>
</body>