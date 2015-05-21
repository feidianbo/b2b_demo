<!DOCTYPE html>
<html>
<head>
    <title>后台管理</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${static('/styles/main.css')}"/>
    <link rel="stylesheet" type="text/css" href="${static('/bower_components/angular-pagedown/angular-pagedown.css')}"/>
    <link rel="stylesheet" type="text/css" href="${static('/bower_components/bootstrap3-datetimepicker/build/css/bootstrap-datetimepicker.min.css')}">
    <link rel="stylesheet" type="text/css" href="${static('/bower_components/angular-ui-select/dist/select.css')}">

</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav" role="banner" ng-controller="index.Index">
    <div class="container" style="padding-left: 0px;padding-right: 0px;">
        <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <#if frameList ??>
                    <#list frameList as frame>
                        <li class="dropdown">
                            <#if frame.menu ??>
                                <#if frame.menusList ??>
                                    <a auth href="${frame.menu.url}" class="dropdown-toggle" data-toggle="dropdown">${frame.menu.menuname}<b class="caret"></b></a>
                                <#else>
                                    <a auth href="${frame.menu.url}">${frame.menu.menuname}</a>
                                </#if>
                            </#if>
                            <#if frame.menusList ??>
                                <ul class="dropdown-menu">
                                    <#list frame.menusList as menu>
                                        <li><a auth href="${menu.url}">${menu.menuname}</a></li>
                                    </#list>
                                </ul>
                            </#if>
                        </li>
                    </#list>
                </#if>


                <li>
                    <a href="/logout">退出</a>
                </li>
            </ul>
            <#--<ul class="nav navbar-nav navbar-right">-->

            <#--</ul>-->
        </nav>
    </div>
</header>
<div style="margin-top: 20px;"></div>
<div class="container"  ng-view=""></div>

<!-- server error msg begin -->
<!-- $('#modal-server-error').modal(true); -->
<div id="modal-server-error" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="mySmallModalLabel">温馨提示：</h4>
            </div>
            <div class="modal-body">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <span class="sr-only">Error:</span>
                    <span id="server-error-msg">您访问的网页不存在...</span>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- server error msg end -->

<script type="text/javascript" src="${static('/bower_components/uri.js/src/URI.min.js')}"></script>
<script type="text/javascript" src="${static('/bower_components/pagedown/Markdown.Converter.js')}"></script>
<script type="text/javascript" src="${static('/bower_components/pagedown/Markdown.Sanitizer.js')}"></script>
<script type="text/javascript" src="${static('/bower_components/pagedown/Markdown.Extra.js')}"></script>
<script type="text/javascript" src="${static('/bower_components/pagedown/Markdown.Editor.js')}"></script>
<script type="text/javascript" src="${static('/bower_components/requirejs/require.js')}"></script>
<script type="text/javascript" src="${static('/scripts/config.js')}"></script>
<script type="text/javascript" src="${static('/scripts/rindex.js')}"></script>
</body>
</html>