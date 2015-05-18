<@extend name="layout">
<@block name="head">
    <link rel="stylesheet" type="text/css" href="/styles/login.css"/>
</@block>
<@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="col-xs-12 col-md-12">
                <div class="col-xs-5 col-md-5">
                    <img src="/images/login.jpg">
                </div>
                <div class="col-xs-7 col-md-7">
                    <form class="form-horizontal" action="/login" role="form" id="login_form" method="post">
                <div class="form-group">
                    <input id="t" name="t" value="${exception}" type="hidden"/>
                    <label for="securephone" class="col-xs-3 col-md-3 control-label">用户名:</label>
                    <div class="col-xs-5 col-md-5">
                        <input type="text" class="form-control" id="securephone"  name="securephone" placeholder="请输入手机号" value="${securephone!''}">
                    </div>
                    <div >
                        <span id="loginInfo" class="span_property"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="col-xs-3 col-md-3 control-label">登录密码:</label>
                    <div class="col-xs-5 col-md-5">
                        <input type="password" class="form-control" maxlength="16" id="password" name="password" placeholder="请输入6-16个字符">
                    </div>
                    <div>
                        <span id="pwdErrorInfo" class="span_property"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-xs-offset-3 col-xs-5 col-md-5">
                        <a href="/reset-password" class="resetPwd">忘记密码</a>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-3 col-xs-offset-3  col-xs-5  col-md-5">
                        <button type="button" class="btn btn-primary btn-block" id="create-btn">登录</button>
                    </div>
                </div>
                <div id="register">
                    没有账号，
                    <a href="/register">立刻注册 》</a>
                </div>
            </form>
                </div>
            </div>
        </div>
    </div>
</@block>
<@block name="login_dialog">
</@block>
<@block name="script">
    <script type="text/javascript" src="${static('/scripts/login.js')}"></script>
</@block>
</@extend>




