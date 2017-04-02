<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/fragment/taglib.jsp" %>


<head>
    <title>系统用户管理</title>
    <script type="text/javascript"
            src="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2.css"/>
    <link rel="stylesheet" type="text/css"
          href="${ctx }/resources/third-party/metronic/assets/plugins/select2/select2-metronic.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <h3 class="page-title">系统用户管理</h3>
        <ul class="page-breadcrumb breadcrumb">
            <li><i class="fa fa-home"></i> <span> 系统用户管理</span> <i
                    class="fa fa-angle-right"></i></li>
            <li><span> 用户详细信息</span></li>

        </ul>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="portlet">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-calendar"></i>系统用户
                </div>
            </div>
            <div class="portlet-body">
                <div class="tabbable">
                    <div class="tab-content no-space">
                        <div class="form-horizontal form-row-seperated">
                            <div class="form-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">昵称:
                                    </label>
                                    <div class="col-md-10">
                                        ${sysuserinfo.sysUser.name}
                                        <input type="hidden" value="${sysuserinfo.sysUser.id}" id="sysuserid">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">姓名:
                                    </label>
                                    <div class="col-md-10">
                                        ${sysuserinfo.sysUser.realname}
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">性别:
                                    </label>
                                    <div class="col-md-10">
                                        ${sysuserinfo.sex}
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">手机号:
                                    </label>
                                    <div class="col-md-10">
                                        ${sysuserinfo.sysUser.phonenumber}
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">email:
                                    </label>
                                    <div class="col-md-10">
                                        ${sysuserinfo.sysUser.email}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>

                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="blank-form-actions">
            <a class="btn green" href="#" id="addpower"><i
                    class="fa fa-plus"></i>添加用户权限</a>
            <a class="btn blue" href="#" id="removepower"><i class="fa fa-remove"></i>删除用户权限</a>
        </div>
    </div>
    <table class="table table-bordered table-striped table-condensed flip-content" id="tableSysUserRole">
        <thead>
        <tr>
            <th style="width: 42px;">
                <div>
                    <span><input type="checkbox" id="selectAll"></span>
                </div>
            </th>
            <td>角色名称</td>
            <td>角色id</td>
            <td>角色描述</td>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${sysuserinfo.roles}" var="item">
            <tr>
                <td>
                    <div class="">
											<span><input class="" type="checkbox" id="test"
                                                         value="${item.id}"> </span>
                    </div>
                </td>
                <td>${item.roleName}</td>
                <td>${item.id}</td>
                <td>${item.roleDescription}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal 添加管理员 -->
<div class="modal fade" id="div_sysuser_add">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalTitle">系统用户角色</h4>
            </div>
            <form id="add_admin_form">
                <table align="center">
                    <tr style="height: 50px">
                        <td>用户角色：</td>
                        <td>
                            <select name="roleId" id="roleId">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.id}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary do_audit"
                            id="btnAddSysUserRole">添加
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>

<input type="hidden" id="nameExists" value="0">
<input type="hidden" id="sysuserRoleIds_delete">
<script src="${ctx}/resources/scripts/pages/sys/sysuserindex.js"
        type="text/javascript"></script>


</body>

