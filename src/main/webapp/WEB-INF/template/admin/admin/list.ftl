<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin 系统 | 首页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    [#include "/admin/static/css_static.ftl"/]
    <link rel="stylesheet" href="/resources/admin/components/bootstrap-daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="/resources/admin/components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/resources/admin/adminLTE/css/skins/_all-skins.min.css">
</head>

<body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
        <div class="content-wrapper admin-content">
            <section class="content-header">
                <h1>
                    用户管理
                    <small></small>
                </h1>
            </section>
            <section class="content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="nav-tabs-custom">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#activity" data-toggle="tab">列表</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="active tab-pane" id="activity">
                                    <div class="box">
                                        <div class="box-body no-padding">
                                            <form id="listForm" action="${base}/admin/admin/list/" method="get">
                                                <div class="admin-list-fitlers post">
                                                    <div class="row">
                                                        <div class="col-xs-3">
                                                            <div class="form-group">
                                                                <label for="searchValue">管理员信息</label>
                                                                <input type="text" class="form-control" id="searchValue" placeholder="ID">
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <div class="form-group">
                                                                <label for="createDate">创建日期</label>
                                                                <input type="text" class="form-control" id="dateRange" placeholder="创建日期" readonly>
                                                                <input type="hidden" name="startDate">
                                                                <input type="hidden" name="endDate">
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-1">
                                                            <div class="form-group">
                                                                <label>&nbsp</label>
                                                                <button type="submit" class="btn btn-block btn-default">搜索</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="admin-list-tools">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i></button>
                                                                <button type="button" class="btn btn-default btn-sm"><i class="fa fa-trash"></i></button>
                                                            </div>
                                                            <button type="submit" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="admin-list">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <table class="table table-bordered table-hover">
                                                                <thead>
                                                                <tr>
                                                                    <th>用户名</th>
                                                                    <th>姓名</th>
                                                                    <th>登陆日期</th>
                                                                    <th>登陆IP</th>
                                                                    <th>状态</th>
                                                                    <th>创建日期</th>
                                                                    <th>操作</th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                    [#list page.content as admin]
                                                                    <tr>
                                                                        <td>${admin.username}</td>
                                                                        <td>${admin.name}</td>
                                                                        <td>[#if admin.loginDate?has_content]${admin.loginDate?string("yyyy-MM-dd HH:mm:ss")}[#else]-[/#if]</td>
                                                                        <td>${admin.loginIp!"-"}</td>
                                                                        <td>-</td>
                                                                        <td>${admin.createDate?string("yyyy-MM-dd HH:mm:ss")}</td>
                                                                        <td>
                                                                            <button type="button" class="btn btn-info btn-xs">编辑</button>
                                                                            <button type="button" class="btn btn-danger btn-xs">删除</button>
                                                                        </td>
                                                                    </tr>
                                                                    [/#list]
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
                                            [#include "/admin/include/pagination.ftl"]
                                        [/@pagination]
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    [#include "/admin/static/js_static.ftl"/]
    <script src="/resources/admin/components/moment/min/moment.min.js"></script>
    <script src="/resources/admin/components/bootstrap-daterangepicker/daterangepicker.js"></script>
    <script src="/resources/admin/components/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="/resources/admin/components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="/resources/admin/components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="/resources/admin/components/fastclick/lib/fastclick.js"></script>
    <script src="/resources/admin/adminLTE/js/demo.js"></script>
    <script src="/resources/admin/common/js/common.js"></script>
    <script>
        $(function() {
            $('#dateRange').dateRange();
        });
    </script>
</body>

</html>