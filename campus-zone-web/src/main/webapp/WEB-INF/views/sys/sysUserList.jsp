<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp"%>

<head>
    <title>系统用户管理</title>
    <!-- PAGE LEVEL STYLE REFERENCES -->
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <h3 class="page-title">用户管理</h3>
        <ul class="page-breadcrumb breadcrumb">
            <li><i class="fa fa-home"></i> <span> 用户管理
				</span> <i class="fa fa-angle-right"></i></li>
            <li><span> 用户列表 </span></li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="form-actions">
            <form id="form_search" class="form-horizontal"
                  action="/v1/sysusers" method="GET">
                <div class="form-group">

                    <div class="col-md-3">
                        <input type="text" id="nameQ"
                               class="form-control input-medium input-inline" name="name"
                               placeholder="Input name" value="${seed.queryData['name']}" />
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
            <a class="btn green" href="#" id="createsysuser"><i
                    class="fa fa-plus"></i>创建</a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet box grey">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-calendar"></i> 用户列表
                </div>
            </div>
            <div class="portlet-body flip-scroll">
                <campus:pagination position="above"></campus:pagination>
                <table
                        class="table table-bordered table-striped table-condensed flip-content"
                        id="fromAccountTable">
                    <thead class="flip-content">
                    <tr>
                        <th style="width: 42px;">
                            <div>
                                <span><input type="checkbox" id="selectAll"></span>
                            </div>
                        </th>
                        <th style="width: 15%;">id</th>
                        <th style="width: 15%;">姓名</th>
                        <th style="width: 15%;">性别</th>
                        <th style="width: 15%;">手机</th>
                        <th style="width: 15%;">email</th>
                        <th style="width: 15%;">学校</th>
                        <th style="width: 10%;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${seed.result }" var="item">
                        <tr>
                            <td>
                                <div class="">
                                </div>
                            </td>
                            <td><a href="/account/index?account_id=${item.sysUser.id }">${item.sysUser.id}</a></td>
                            <td>${item.sysUser.name}</td>
                            <td>${item.sex} </td>
                            <td>${item.sysUser.phonenumber}</td>
                            <td>${item.sysUser.email}</td>
                            <td><c:if test="${not empty item.school}">${item.school.name}</c:if> </td>
                            <td><a class="btn default btn-xs blue-stripe" href="#"
                                   name="editSysUser" data="${item.sysUser.id}"><i
                                    class="fa fa-edit"></i> 查看详细</a></td>
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
<div class="modal fade" id="div_sysuser_add">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalTitle">添加系统用户基本信息</h4>
            </div>
            <form id="add_admin_form">
                <table align="center">
                    <tr style="height: 50px">
                        <td>昵称：</td>
                        <td>
                            <input type="text" name="name" id="name"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td>姓名：</td>
                        <td>
                            <input type="text" name="realName" id="realName"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td>密码：</td>
                        <td>
                            <input type="text" name="passWord" id="passWord"
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
                        <td>手机号：</td>
                        <td>
                            <input type="text" name="phoneNumber" id="phoneNumber"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td>email：</td>
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
<script src="${ctx}/resources/scripts/pages/sys/sysuserlist.js"
        type="text/javascript"></script>
</body>