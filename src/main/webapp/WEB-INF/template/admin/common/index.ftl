<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin 系统 | 首页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    [#include "/admin/include/css_static.ftl" /]
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
                [#include "/admin/common/left.ftl" /]
            </section>
        </aside>
        <div class="content-wrapper">
            <iframe id="iframe" name="iframe" src="" frameborder="0" style="width: 100%;height: 100%"></iframe>
        </div>
        <footer class="main-footer">
            <div class="pull-right hidden-xs">
            </div>
            <strong>Copyright &copy; 2019 <a href="#">www.jzwb.com</a>.</strong> All rights reserved.
        </footer>
        <div class="control-sidebar-bg"></div>
    </div>
    [#include "/admin/include/js_static.ftl"/]
    <script>
    var $iframe = $('iframe');

    function iframeResize() {
        $iframe.parent().css("height", $iframe.parent().css('min-height'));
        $iframe.height($iframe.parent().height() - 10);
    }
    iframeResize();
    setInterval(function() {
        iframeResize();
    }, 1 * 1000);
    </script>
</body>

</html>