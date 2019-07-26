<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin 系统 | 首页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    [#include "/admin/include/css_static.ftl"/]
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
                                            <div class="admin-list-fitlers post form-horizontal">
                                                <div class="row">
                                                    <div class="col-xs-4">
                                                        [#--<div class="form-group">
                                                            <label for="searchValue">管理员信息</label>
                                                            <input type="text" class="form-control" id="searchValue" name="searchValue" placeholder="ID">
                                                        </div>--]
                                                        <div class="form-group">
                                                            <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
                                                            <div class="col-sm-9">
                                                                <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    [#--<div class="col-xs-3">
                                                        <div class="form-group">
                                                            <label for="searchValue">创建日期</label>
                                                            <input type="text" class="form-control" id="createDate" placeholder="ID">
                                                        </div>
                                                    </div>
                                                    <div class="col-xs-3">
                                                        <div class="form-group">
                                                            <label for="searchValue">&nbsp</label>
                                                            <button type="button" class="btn btn-block btn-primary">Primary</button>
                                                        </div>


                                                    </div>--]
                                                </div>
                                            </div>
                                            <div class="admin-list-tools">
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                        <div class="btn-group">
                                                            <button type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i></button>
                                                            <button type="button" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></button>
                                                            <button type="button" class="btn btn-default btn-sm"><i class="fa fa-share"></i></button>
                                                        </div>
                                                        <button type="button" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
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
                                                                    <td></td>
                                                                </tr>
                                                                [/#list]
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="box-footer clearfix">
                                            <ul class="pagination pagination-sm no-margin pull-right">
                                                <li><a href="#">&laquo;</a></li>
                                                <li><a href="#">1</a></li>
                                                <li><a href="#">2</a></li>
                                                <li><a href="#">3</a></li>
                                                <li><a href="#">&raquo;</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    [#include "/admin/include/js_static.ftl"/]
    <script src="/resources/admin/components/moment/min/moment.min.js"></script>
    <script src="/resources/admin/components/bootstrap-daterangepicker/daterangepicker.js"></script>
    <script src="/resources/admin/components/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="/resources/admin/components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="/resources/admin/components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="/resources/admin/components/fastclick/lib/fastclick.js"></script>
    <script src="/resources/admin/adminLTE/js/demo.js"></script>
    <script>
        $(function(){
            $('#createDate').daterangepicker()
        });
    </script>
</body>

</html>