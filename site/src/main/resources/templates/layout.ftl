<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>XX网</title>
    <link rel="shortcut icon" href="/images/favicon.png" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="/styles/main.css"/>
    <!--[if lt IE 9]>
    <script src="${static('/bower_components/html5shiv/dist/html5shiv.min.js')}"></script>
    <script src="${static('/bower_components/respond/dest/respond.min.js')}"></script>
    <![endif]-->
    
    <@block name="head">
    </@block>
    <@block name="cs">
    </@block>
    
</head>
<body>
<div class="wrap">
    <!--header begin -->
    <div class="login-reg">
        <ul class="reg">
            <li><span>欢迎来到XX网,&nbsp;&nbsp;&nbsp;您好!</span></li>
        <#if session().user?exists>
            <li><a href="/account/individualCenter">${(session().user.nickname)!(session().user.securephone)}</a></li><li>|</li>
            <li><a href="/logout">退出</a></li><li>|</li>
        <#else>
            <li><a href="/login">登录</a></li><li>|</li>
            <li><a href="/register">注册</a></li><li>|</li>
        </#if>
            <li><a href="/account/individualCenter">个人中心</a></li>
        </ul>
    </div>
    <div class="logo-so-tel">
        <div class="logo" style="margin-top: 0px;">
            <#--<div style="float: left;padding-top: 15px;">-->
                <#--<a href="/"><img src="/images/newLogo.png"></a>-->
            <#--</div>-->
            <#--<div class="solgan" style="padding-top: 45px;">-->
                <#--<div class="solgan-b">XX网&nbsp;通天下</div>-->
                <#--<div style="font-size: 14px;color: #898989;">中国美酒全产业链领导者</div>-->
            <#--</div>-->
            <div><a href="/"><img src="/images/logo.jpg"></a> </div>
        </div>
        <div class="so-tel">
            <#--<div class="so">-->
                <#--<form>-->
                    <#--<div class="input-group">-->
                        <#--<input type="text" placeholder="请输入搜索内容" class="form-control">-->
                        <#--<span class="input-group-btn">-->
                            <#--<button class="btn btn-so" type="submit">-->
                                <#--<span class="glyphicon glyphicon-search"></span>-->
                            <#--</button>-->
                        <#--</span>-->
                    <#--</div>-->
                <#--</form>-->
            <#--</div>-->

            <div class="tel">
                <span class="glyphicon glyphicon-earphone" style="font-size: 28px;"></span>
                <div class="tel_txt" style="font-size: 14px;">24小时贵宾热线<br>
                    <p style="font-size: 14px; letter-spacing: 1px;">400-123-4567</p>
                </div>
            </div>
        </div>
    </div>

    <nav class="navbar" style="background-color: #317ee6; border-radius: 0px; border: none; margin-bottom: 0px;" role="navigation">
        <div class="container" style="padding-left: 30px;position: absolute;right: 0;left: 0;">
            <ul class="nav navbar-nav" style="text-align: center;">
                <li id="navShow" style="font-size: 18px;">
                    <a href="javascript:void(0);" style="width:180px;" id="throughTrain">XX直通车 <span class="caret"></span></a>
                    <div id="menu" style="box-shadow: 0 0 0px #999; margin-left: 0px; display: none;">
                        <ul class="ulMain">
                            <div onmouseover="IsShowIcon(this);">
                                <li class="powerCoal"><a href="javascript:void(0);" >动力煤</a>
                                    <p style="margin-top:12px;">供应数量 <span style="color: red;">${statistics.totalSupplyAmount!}</span>&nbsp;&nbsp;瓶</p>
                                    <p>需求数量 <span style="color: red;">${statistics.totalDemandAmount!}</span>&nbsp;&nbsp;瓶</p>
                                    <span><p class="glyphicon glyphicon-chevron-right first_icon" style="  margin-left: 150px;margin-top: -30px; position: static; display: block;"></p></span>
                                    <ul class="ulSub">
                                        <li class="frist_li">
                                            <a href="/mall/index?region=华北地区&coaltype=动力煤" class="autoWord" onmouseover="ToggleLi(this,'huabei')">华北地区</a>
                                            <a href="/mall/index?region=华东地区&coaltype=动力煤" class="autoWord" onmouseover="ToggleLi(this,'huadong')">华东地区</a>
                                            <a href="/mall/index?region=华中地区&coaltype=动力煤" class="autoWord" onmouseover="ToggleLi(this,'huazhong')">华中地区</a>
                                            <a href="/mall/index?region=华南地区&coaltype=动力煤" class="autoWord" onmouseover="ToggleLi(this,'huanan')">华南地区</a>
                                        </li>

                                        <li class="huabei">
                                        <#list ['秦皇岛港','曹妃甸港', '京唐港', '黄骅港', '天津港'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=动力煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>
                                        <li class="huabei">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=动力煤" class="autoWord">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=动力煤" class="autoWord">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=动力煤" class="autoWord">4100-5000</a>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=动力煤" class="autoWord">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=动力煤" class="autoWord">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=动力煤" class="autoWord">6500以上</a>
                                        </li>

                                        <#--<li class="huadong">-->
                                        <#--<#list ['莆田港(东吴)','厦门港', '万寨港', '邳州港','罗泾港', '可门港'] as item>-->
                                        <#--<a href="${url('/mall/index', {'harbour':item})}&coaltype=动力煤" class="autoWord">${item}</a>-->
                                        <#--</#list>-->
                                        <#--</li>-->
                                        <li class="huadong">
                                        <#list ['福建东吴港','如皋港', '万寨港', '扬子江港','罗泾港', '扬州海昌码头'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=动力煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>
                                        <li class="huadong">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=动力煤" class="autoWord">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=动力煤" class="autoWord">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=动力煤" class="autoWord">4100-5000</a>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=动力煤" class="autoWord">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=动力煤" class="autoWord">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=动力煤" class="autoWord">6500以上</a>
                                        </li>

                                        <li class="huazhong">
                                        <#list ['九江港', '黄石港','南昌港', '枝城港','城陵矶港'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=动力煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>

                                        <li class="huazhong">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=动力煤" class="autoWord">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=动力煤" class="autoWord">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=动力煤" class="autoWord">4100-5000</a>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=动力煤" class="autoWord">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=动力煤" class="autoWord">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=动力煤" class="autoWord">6500以上</a>
                                        </li>

                                        <#--<li class="huanan">-->
                                        <#--<#list ['防城港','钦州港', '海昌码头(东莞)', '广州港(西基)','广州港(新沙)'] as item>-->
                                        <#--<a href="${url('/mall/index', {'harbour':item})}&coaltype=动力煤" class="autoWord">${item}</a>-->
                                        <#--</#list>-->
                                        <#--</li>-->
                                        <li class="huanan">
                                        <#list ['防城港','钦州港', '广州新沙港','广东东莞海昌码头','广州西基码头'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=动力煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>

                                        <li class="huanan">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=动力煤" class="autoWord">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=动力煤" class="autoWord">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=动力煤" class="autoWord">4100-5000</a>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=动力煤" class="autoWord">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=动力煤" class="autoWord">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=动力煤" class="autoWord">6500以上</a>
                                        </li>
                                    </ul>
                                </li>
                            </div>
                            <div onmouseover="IsShowIcon(this);">
                                <li>
                                    <a href="javascript:void(0);">无烟煤</a>
                                    <p style="margin-top: 12px;">供应数量 <span style="color: red;">${statistics.totalAnthraciteSupplyAmount!}</span>&nbsp;&nbsp;瓶</p>
                                    <p>需求数量 <span style="color: red;">${statistics.totalAnthraciteDemandAmount!}</span>&nbsp;&nbsp;瓶</p>
                                    <span><p class="glyphicon glyphicon-chevron-right sec_icon" style="  margin-left: 150px;margin-top: -30px; position: static; display: block;"></p></span>
                                    <ul class="ulSub1">
                                        <li class="second_li" style="margin-top: -110px;">
                                            <a href="/mall/index?region=华北地区&coaltype=无烟煤" class="autoWord" onmouseover="ToggleLi(this,'huabei')">华北地区</a>
                                            <a href="/mall/index?region=华东地区&coaltype=无烟煤" class="autoWord" onmouseover="ToggleLi(this,'huadong')">华东地区</a>
                                            <a href="/mall/index?region=华中地区&coaltype=无烟煤" class="autoWord" onmouseover="ToggleLi(this,'huazhong')">华中地区</a>
                                            <a href="/mall/index?region=华南地区&coaltype=无烟煤" class="autoWord" onmouseover="ToggleLi(this,'huanan')">华南地区</a>
                                        </li>

                                        <li class="huabei">
                                        <#list ['秦皇岛港','曹妃甸港', '京唐港', '黄骅港', '天津港'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=无烟煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>

                                        <li class="huabei">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=无烟煤">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=无烟煤">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=无烟煤">4100-5000</a><br>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=无烟煤">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=无烟煤">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=无烟煤">6500以上</a>
                                        </li>

                                        <#--<li class="huadong">-->
                                        <#--<#list ['莆田港(东吴)','厦门港', '万寨港', '邳州港','罗泾港', '可门港'] as item>-->
                                        <#--<a href="${url('/mall/index', {'harbour':item})}&coaltype=无烟煤" class="autoWord">${item}</a>-->
                                        <#--</#list>-->
                                        <#--</li>-->
                                        <li class="huadong">
                                        <#list ['福建东吴港','如皋港', '万寨港', '扬子江港','罗泾港', '扬州海昌码头'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=无烟煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>

                                        <li class="huadong">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=无烟煤">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=无烟煤">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=无烟煤">4100-5000</a><br>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=无烟煤">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=无烟煤">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=无烟煤">6500以上</a>
                                        </li>

                                        <li class="huazhong">
                                        <#list ['九江港', '黄石港','南昌港', '枝城港','城陵矶港'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=无烟煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>

                                        <li class="huazhong">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=无烟煤">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=无烟煤">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=无烟煤">4100-5000</a><br>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=无烟煤">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=无烟煤">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=无烟煤">6500以上</a>
                                        </li>

                                        <li class="huanan">
                                        <#list ['防城港','钦州港', '广州新沙港','广东东莞海昌码头','广州西基码头'] as item>
                                            <a href="${url('/mall/index', {'harbour':item})}&coaltype=无烟煤" class="autoWord">${item}</a>
                                        </#list>
                                        </li>
                                        <#--<li class="huanan">-->
                                        <#--<#list ['防城港','钦州港', '海昌码头(东莞)', '广州港(西基)','广州港(新沙)'] as item>-->
                                        <#--<a href="${url('/mall/index', {'harbour':item})}&coaltype=无烟煤" class="autoWord">${item}</a>-->
                                        <#--</#list>-->
                                        <#--</li>-->

                                        <li class="huanan">
                                            <a href="/mall/index?NCV01=1&NCV02=3600&coaltype=无烟煤">3500以下</a>
                                            <a href="/mall/index?NCV01=3500&NCV02=4000&coaltype=无烟煤">3500-4000</a>
                                            <a href="/mall/index?NCV01=4100&NCV02=5000&coaltype=无烟煤">4100-5000</a><br>
                                            <a href="/mall/index?NCV01=5100&NCV02=6000&coaltype=无烟煤">5100-6000</a>
                                            <a href="/mall/index?NCV01=6100&NCV02=6400&coaltype=无烟煤">6100-6400</a>
                                            <a href="/mall/index?NCV01=6500&NCV02=10000&coaltype=无烟煤">6500以上</a>
                                        </li>
                                    </ul>
                                </li>
                            </div>
                            <div onmouseover="IsShowIcon(this);">
                                <li>
                                    <a href="javascript:void(0);">喷吹煤</a>
                                    <p style="margin-top: 12px;">供应数量 <span style="color: red;">0</span>&nbsp;&nbsp;瓶</p>
                                    <p>需求数量 <span style="color: red;">0</span>&nbsp;&nbsp;瓶</p>
                                    <span><p class="glyphicon glyphicon-chevron-right sec_icon" style="  margin-left: 150px;margin-top: -30px; position: static; display: block;"></p></span>
                                </li>
                            </div>

                            <div onmouseover="IsShowIcon(this);">
                                <li>
                                <a href="javascript:void(0);">焦煤</a>
                                <p style="margin-top: 12px;">供应数量 <span style="color: red;">0</span>&nbsp;&nbsp;瓶</p>
                                <p>需求数量 <span style="color: red;">0</span>&nbsp;&nbsp;瓶</p>
                                <span><p class="glyphicon glyphicon-chevron-right sec_icon" style="  margin-left: 150px;margin-top: -30px; position: static; display: block;"></p></span>
                            </li>
                            </div>
                        </ul>
                    </div>
                </li>
                <li><a class="${menuclass('/index')}" href="/" style="font-size: 18px; width: 105px;" >首页</a></li>
                <li><a class="${menuclass('/mall')}" href="/mall" style="font-size: 18px;width: 105px;">XX商城</a></li>
                <li><a class="${menuclass('/buy')}" href="/buy" style="font-size: 18px;width: 105px;">我要买</a></li>
                <li><a class="${menuclass('/sell')}" href="/sell" style="font-size: 18px;width: 105px;">我要卖</a></li>
                <li id="finance"><a class="${menuclass('/finance')}" href="/finance/financing"  style="font-size: 18px;width: 105px;">金融<span class="caret"></span></a>
                    <div style="display: none;position: absolute;z-index: 10;" id="finance_children">
                        <ul style="list-style: none; padding-left: 0px;">
                            <li><a href="/finance/financing" style="font-size: 16px;color:#ffffff">
                                <div style="background-color: #317ee6;width:105px;height: 40px;line-height: 40px;" id="finance_children1">快融通</div></a>
                            </li>
                            <li><a href="/finance/loan" style="font-size: 16px;color: #ffffff">
                                <div style="background-color: #317ee6;width:105px;height: 40px;line-height: 40px;" id="finance_children2">煤易贷</div></a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li><a class="${menuclass('/storage')}" href="/storage" style="font-size: 18px;width: 105px;">仓储</a></li>
                <li><a class="${menuclass('/group')}" href="/group" style="font-size: 18px;width: 105px;">团购</a></li>
            </ul>
        </div>
    </nav>
    <!--header end -->

    <div id="isShow" style="margin-top:220px; margin-left:93%;position:fixed !important; top:0px;z-index:1; top:expression(offsetParent.scrollTop-200);">
        <div>
            <a href="/manualsell_in"><img id="manual_lookup" src ="/images/manual_lookup.png"/></a>
        </div>
        <div style="margin-top:10px;">
            <a href="/manualsell_out"><img id="manual_sell" src="/images/manual_sell.png"/></a>
        </div>
    </div>

    <!--main content begin -->
    <@block name="body">
    </@block>

    <@block name="login_dialog">
        <div class="modal fade" id="modal-login" role="dialog" tabindex="-1"  aria-labelledby="myModalLabel" data-backdrop="false" aria-hidden="true">
            <div class="modal-dialog" style="top:125px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">
                            <span class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;用户登录
                        </h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" action="/login" role="form" id="login_form" method="post">
                            <input id="gotoUrl" name="gotoUrl"  type="hidden"/>
                            <div class="form-group">
                                <label for="securephone" class="col-xs-3 col-md-3 control-label">用户名:</label>
                                <div class="col-xs-5 col-md-5">
                                    <input type="text" class="form-control" maxlength="11" id="login-securephone" name="login-securephone" placeholder="请输入手机号">
                                </div>
                                <div >
                                    <span id="loginInfo" style="height: 34px; line-height: 34px;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-xs-3 col-md-3 control-label">登录密码:</label>
                                <div class="col-xs-5 col-md-5">
                                    <input type="password" maxlength="16" class="form-control" id="login-password" name="login-password" placeholder="请输入6-16个字符">
                                </div>
                                <div>
                                    <span id="pwdErrorInfo" style="height: 34px; line-height: 34px;"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-5 col-md-offset-3 col-md-5">
                                    <a href="/reset-password" class="resetPwd">忘记密码</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-5 col-md-offset-3 col-md-5">
                                    <button type="button" class="btn btn-primary btn-block" id="login-btn">登录</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-5 ">
                                    <span>没有账号,<a href="/register">现在注册 》</a></span>
                                </div>
                            </div>
                            <hr>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </@block>

    <!-- server error msg begin -->
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

    <!--main content end-->


    <!--footer begin -->
    <div class="clear-level">
        <div class="container" style="padding-top: 20px; line-height: 23px;">
            <div style="padding-left: 45px;">
                
               
               <#-- <div class="row footerColor">
                    <div class="col-xs-offset-1 col-xs-2">
                        <ul style="padding-left: 0px;">
                            <li><a href="/footer/startOff?pos=first">什么是XX直通车</a></li>
                            <li><a href="/footer/startOff?pos=sec">什么是XX商城</a></li>
                            <li><a href="/footer/startOff?pos=third">个人中心</a></li>
                            <li><a href="/footer/startOff?pos=four">委托人工</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-2">
                        <ul style="padding-left: 10px;">
                            <li><a href="/footer/buyCoal">如何买煤</a></li>
                            <li><a href="/footer/sellCoal">如何卖煤</a></li>
                            <li><a href="/footer/aboutContract?pos=first">如何付款</a></li>
                            <li><a href="/footer/aboutContract?pos=sec">交付流程</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-2">
                        <ul style="padding-left: 10px;">
                            <li><a href="/getArticleList?path=/国内市场">国内市场</a></li>
                            <li><a href="/getArticleList?path=/国际市场">国际市场</a></li>
                            <li><a href="/getArticleList?path=/期货交易">期货交易</a></li>
                            <li><a href="/getArticleList?path=/美酒周边">美酒周边</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-2">
                        <ul style="padding-left: 10px;">
                            <li><a href="/footer/aboutServices?pos=first">金融服务</a></li>
                            <li><a href="/footer/aboutServices?pos=sec">XX团购</a></li>
                            <li><a href="/footer/aboutServices?pos=third">XX仓储</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-2">
                        <ul style="padding-left: 10px;">
                            <li><a href="/footer/aboutUs?pos=first">XX优势</a></li>
                            <li><a href="/footer/aboutUs?pos=sec">资金保证</a></li>
                            <li><a href="/footer/aboutUs?pos=third">专业团队</a></li>
                        </ul>
                    </div>
                </div>-->
            </div>
            <div class="clear-level"></div>
            <div class="row">
                <div>
                    
                </div>
            </div>
        </div>
    </div>
    <!--footer end-->
</div>
  <script type="text/javascript" src="${static('/bower_components/requirejs/require.js')}"></script>
  <script type="text/javascript" src="${static('/scripts/config.js')}"></script>
  <script type="text/javascript" src="${static('/scripts/layout.js')}"></script>
<div class="hidden">
</div>

<@block name="script">
<#--<script type="text/javascript" src="${static('/scripts/project.js')}"></script>-->
</@block>
</body>
</html>
