<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin 系统 | 首页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/resources/admin/components/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/admin/components/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/admin/components/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="/resources/admin/components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/resources/admin/adminLTE/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/resources/admin/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/resources/admin/common/css/google_font.css">
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
                列表
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">列表</li>
            </ol>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">Data Table With Full Features</h3>
                        </div>
                        <div class="box-body">
                            <table id="example1" class="table table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>Rendering engine</th>
                                    <th>Browser</th>
                                    <th>Platform(s)</th>
                                    <th>Engine version</th>
                                    <th>CSS grade</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Trident</td>
                                    <td>Internet
                                        Explorer 4.0
                                    </td>
                                    <td>Win 95+</td>
                                    <td> 4</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Trident</td>
                                    <td>Internet
                                        Explorer 5.0
                                    </td>
                                    <td>Win 95+</td>
                                    <td>5</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Trident</td>
                                    <td>Internet
                                        Explorer 5.5
                                    </td>
                                    <td>Win 95+</td>
                                    <td>5.5</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Trident</td>
                                    <td>Internet
                                        Explorer 6
                                    </td>
                                    <td>Win 98+</td>
                                    <td>6</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Trident</td>
                                    <td>Internet Explorer 7</td>
                                    <td>Win XP SP2+</td>
                                    <td>7</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Trident</td>
                                    <td>AOL browser (AOL desktop)</td>
                                    <td>Win XP</td>
                                    <td>6</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Firefox 1.0</td>
                                    <td>Win 98+ / OSX.2+</td>
                                    <td>1.7</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Firefox 1.5</td>
                                    <td>Win 98+ / OSX.2+</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Firefox 2.0</td>
                                    <td>Win 98+ / OSX.2+</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Firefox 3.0</td>
                                    <td>Win 2k+ / OSX.3+</td>
                                    <td>1.9</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Camino 1.0</td>
                                    <td>OSX.2+</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Camino 1.5</td>
                                    <td>OSX.3+</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Netscape 7.2</td>
                                    <td>Win 95+ / Mac OS 8.6-9.2</td>
                                    <td>1.7</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Netscape Browser 8</td>
                                    <td>Win 98SE+</td>
                                    <td>1.7</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Netscape Navigator 9</td>
                                    <td>Win 98+ / OSX.2+</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.0</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>1</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.1</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>1.1</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.2</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>1.2</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.3</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>1.3</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.4</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>1.4</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.5</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>1.5</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.6</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>1.6</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.7</td>
                                    <td>Win 98+ / OSX.1+</td>
                                    <td>1.7</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Mozilla 1.8</td>
                                    <td>Win 98+ / OSX.1+</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Seamonkey 1.1</td>
                                    <td>Win 98+ / OSX.2+</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Gecko</td>
                                    <td>Epiphany 2.20</td>
                                    <td>Gnome</td>
                                    <td>1.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Webkit</td>
                                    <td>Safari 1.2</td>
                                    <td>OSX.3</td>
                                    <td>125.5</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Webkit</td>
                                    <td>Safari 1.3</td>
                                    <td>OSX.3</td>
                                    <td>312.8</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Webkit</td>
                                    <td>Safari 2.0</td>
                                    <td>OSX.4+</td>
                                    <td>419.3</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Webkit</td>
                                    <td>Safari 3.0</td>
                                    <td>OSX.4+</td>
                                    <td>522.1</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Webkit</td>
                                    <td>OmniWeb 5.5</td>
                                    <td>OSX.4+</td>
                                    <td>420</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Webkit</td>
                                    <td>iPod Touch / iPhone</td>
                                    <td>iPod</td>
                                    <td>420.1</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Webkit</td>
                                    <td>S60</td>
                                    <td>S60</td>
                                    <td>413</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera 7.0</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera 7.5</td>
                                    <td>Win 95+ / OSX.2+</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera 8.0</td>
                                    <td>Win 95+ / OSX.2+</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera 8.5</td>
                                    <td>Win 95+ / OSX.2+</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera 9.0</td>
                                    <td>Win 95+ / OSX.3+</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera 9.2</td>
                                    <td>Win 88+ / OSX.3+</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera 9.5</td>
                                    <td>Win 88+ / OSX.3+</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Opera for Wii</td>
                                    <td>Wii</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Nokia N800</td>
                                    <td>N800</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Presto</td>
                                    <td>Nintendo DS browser</td>
                                    <td>Nintendo DS</td>
                                    <td>8.5</td>
                                    <td>C/A<sup>1</sup></td>
                                </tr>
                                <tr>
                                    <td>KHTML</td>
                                    <td>Konqureror 3.1</td>
                                    <td>KDE 3.1</td>
                                    <td>3.1</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>KHTML</td>
                                    <td>Konqureror 3.3</td>
                                    <td>KDE 3.3</td>
                                    <td>3.3</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>KHTML</td>
                                    <td>Konqureror 3.5</td>
                                    <td>KDE 3.5</td>
                                    <td>3.5</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Tasman</td>
                                    <td>Internet Explorer 4.5</td>
                                    <td>Mac OS 8-9</td>
                                    <td>-</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Tasman</td>
                                    <td>Internet Explorer 5.1</td>
                                    <td>Mac OS 7.6-9</td>
                                    <td>1</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Tasman</td>
                                    <td>Internet Explorer 5.2</td>
                                    <td>Mac OS 8-X</td>
                                    <td>1</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>NetFront 3.1</td>
                                    <td>Embedded devices</td>
                                    <td>-</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>NetFront 3.4</td>
                                    <td>Embedded devices</td>
                                    <td>-</td>
                                    <td>A</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>Dillo 0.8</td>
                                    <td>Embedded devices</td>
                                    <td>-</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>Links</td>
                                    <td>Text only</td>
                                    <td>-</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>Lynx</td>
                                    <td>Text only</td>
                                    <td>-</td>
                                    <td>X</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>IE Mobile</td>
                                    <td>Windows Mobile 6</td>
                                    <td>-</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Misc</td>
                                    <td>PSP browser</td>
                                    <td>PSP</td>
                                    <td>-</td>
                                    <td>C</td>
                                </tr>
                                <tr>
                                    <td>Other browsers</td>
                                    <td>All others</td>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>U</td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Rendering engine</th>
                                    <th>Browser</th>
                                    <th>Platform(s)</th>
                                    <th>Engine version</th>
                                    <th>CSS grade</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
        </div>
        <strong>Copyright &copy; 2019 <a href="#">www.jzwb.com</a>.</strong> All rights reserved.
    </footer>
    <div class="control-sidebar-bg"></div>
</div>
<script src="/resources/admin/components/jquery/jquery.min.js"></script>
<script src="/resources/admin/components/bootstrap/js/bootstrap.min.js"></script>
<script src="/resources/admin/components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/resources/admin/components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="/resources/admin/components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="/resources/admin/components/fastclick/lib/fastclick.js"></script>
<script src="/resources/admin/adminLTE/js/adminlte.min.js"></script>
<script src="/resources/admin/adminLTE/js/demo.js"></script>
<script>
    $(function() {
        $('#example1').DataTable()
    })
</script>
</body>

</html>