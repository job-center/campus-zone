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
            <li><i class="fa fa-home"></i> <span> 角色管理
				</span> <i class="fa fa-angle-right"></i></li>
            <li><span> 角色列表 </span></li>
        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="form-actions">
            <%--<form id="form_search" class="form-horizontal"--%>
                  <%--action="/account/list" method="GET">--%>
                <%--<div class="form-group">--%>
                    <%--<label class="col-md-1 control-label">昵称: </label>--%>
                    <%--<div class="col-md-3">--%>
                        <%--<input type="text" id="nameQ"--%>
                               <%--class="form-control input-medium input-inline" name="nameQ"--%>
                               <%--placeholder="Input name" value="${seed.filter['nameQ']}" />--%>
                    <%--</div>--%>
                    <%--<label class="col-md-1 control-label">手机号: </label>--%>
                    <%--<div class="col-md-3">--%>
                        <%--<input type="text" id="mobileQ"--%>
                               <%--class="form-control input-medium input-inline" name="mobileQ"--%>
                               <%--placeholder="Input mobile" value="${seed.filter['mobileQ']}" />--%>
                    <%--</div>--%>
                    <%--<div class="pull-right">--%>
                        <%--<a href="javascript:ZuesAccount.search();" class="btn dark">搜索--%>
                            <%--<i class="fa fa-search"></i>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</form>--%>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="blank-form-actions">
            <a class="btn green" href="#" id="createRole"><i
                    class="fa fa-plus"></i>创建</a>
            <a class="btn blue" href="#" id="removepower"><i class="fa fa-remove"></i>删除</a>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet box grey">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-calendar"></i> 角色列表
                </div>
            </div>
            <div class="portlet-body flip-scroll">
                <campus:pagination position="above"></campus:pagination>
                <table class="table table-bordered table-striped table-condensed flip-content" id="fromAccountTable">
                    <thead class="flip-content">
                    <tr>
                        <th style="width: 42px;">
                            <div>
                                <span><input type="checkbox" id="selectAll"></span>
                            </div>
                        </th>
                        <th style="width: 10%;">id</th>
                        <th style="width: 25%;">角色名称</th>
                        <th style="width: 45%;">角色描述</th>
                        <th style="width: 20%;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${seed.result }" var="item">
                        <tr>
                            <td>
                                <div class="">
                                    <span><input class="" type="checkbox" id="ids" value="${item.id}"> </span>
                                </div>
                            </td>
                            <td><a href="/account/index?account_id=${item.id }">${item.id}</a></td>
                            <td>${item.roleName}</td>
                            <td>${item.roleDescription} </td>
                            <td><a class="btn default btn-xs blue-stripe" href="#"
                                   name="editSysRole" data="${item.id}"><i
                                    class="fa fa-edit"></i> 详细</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <campus:pagination position="below"></campus:pagination>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="div_sysrole_add">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalTitle">添加角色基本信息</h4>
            </div>
            <form id="add_admin_form">
                <table align="center">
                    <tr style="height: 50px">
                        <td>角色名称：</td>
                        <td>
                            <input type="text" name="roleName" id="roleName"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td>角色描述：</td>
                        <td>
                            <input type="text" name="roleDescription" id="roleDescription"
                                   size="30" style="height: 35px"/>
                        </td>
                    </tr>
                </table>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary do_audit"
                            id="btnAddRole">添加
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="setOptionUrl" value="${ctx}/account/" />
<input type="hidden" id="sysroles_delete">

<input type="hidden" id="ctxUrl" value="${ctx}" />
<!-- PAGE LEVEL JS REFERENCES -->
<script src="${ctx}/resources/scripts/pages/sys/sysrolelist.js" type="text/javascript"></script>
</body>