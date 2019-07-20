<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin 系统 | 首页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    [#include "/admin/include/static.ftl" /]
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <header class="main-header">
        <a href="index2.html" class="logo">
            <span class="logo-mini"><b>A</b></span>
            <span class="logo-lg"><b>Admin</b> 系统</span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
        </nav>
    </header>
    <aside class="main-sidebar">
        <section class="sidebar">
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/resources/admin/adminLTE/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Admin</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                </div>
            </div>
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="搜索...">
                    <span class="input-group-btn">
                            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </span>
                </div>
            </form>
            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">主菜单</li>
                <li class="active"><a href="#"><i class="fa fa-link"></i> <span>一级菜单 - 选中</span></a></li>
                <li><a href="#"><i class="fa fa-link"></i> <span>一级菜单 - 未选中</span></a></li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>一级菜单</span>
                        <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#">二级菜单</a></li>
                        <li><a href="#">二级菜单</a></li>
                    </ul>
                </li>
                <li><a href="list.html"><i class="fa fa-link"></i> <span>列表</span></a></li>
                <li><a href="form.html"><i class="fa fa-link"></i> <span>表单</span></a></li>
            </ul>
        </section>
    </aside>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Page Header
                <small>Optional description</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 面包屑1</a></li>
                <li class="active">面包屑2</li>
            </ol>
        </section>
        <section class="content container-fluid">
        </section>
    </div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
        </div>
        <strong>Copyright &copy; 2019 <a href="#">www.jzwb.com</a>.</strong> All rights reserved.
    </footer>
    <div class="control-sidebar-bg"></div>
</div>
<script src="${static}/resources/admin/components/jquery/jquery.min.js"></script>
<script src="${static}/resources/admin/components/bootstrap/js/bootstrap.min.js"></script>
<script src="${static}/resources/admin/adminLTE/js/adminlte.min.js"></script>
</body>

</html>