<@extend name="layout">
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="col-xs-12 col-md-12">
                <div class="col-xs-7 col-md-7">
                    <form class="form-horizontal" action="/reset-password" role="form" id="reset_form" method="post" >
                        <h2>重置登录密码</h2>
                        <hr>
                        <div class="form-group">
                            <label for="securephone" class="col-xs-2 col-md-2 control-label">用户名</label>
                            <div class="col-xs-6 col-md-6">
                                <input type="text" class="form-control" id="securephone" name="securephone" placeholder="请输入账号">
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <span id="securephoneInfo" ></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="vcode" class="col-xs-2 col-md-2 control-label">验证码</label>
                            <div class="col-xs-3 col-md-3">
                                <input type="text"  class="form-control"  id="vcode" name="vcode" maxlength="4">
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <a href="javascript:void(0);"><img  id="imgObj" class="securitycode"  alt="pic" src="verifyCode"/></a>
                            </div>
                            <div class="col-xs-4 col-md-4">
                                <span id="vcodeInfo" style="height: 34px; line-height: 34px;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-2 col-xs-offset-2 col-xs-6 col-md-6">
                                <button type="button" class="btn  btn-primary btn-block" id="reset-btn">确认提交</button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" action="/validcode" role="form" id="verifyAccount_form" method="post" style="display:none;">
                        <h2>验证账号</h2>
                        <hr>
                        <div class="form-group">
                            <label for="pre_securephone" class="col-xs-3 col-md-3 control-label">手机号码</label>
                            <div class="col-xs-6 col-md-6">
                                <input type="text" class="form-control" id="pre_securephone" name="pre_securephone" readonly="readonly" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="code" class="col-xs-3 col-md-3 control-label">验证码</label>
                            <div class="col-xs-3 col-md-3">
                                <input type="text" class="form-control" id="code" name="code">
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <input type="button" style="float:right; height:34px;" maxlength="6" disabled  id="checkCode"/>
                            </div>
                            <span id="pre_vcodeInfo" ></span>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-6 col-md-offset-3 col-md-6">
                                <button type="button" class="btn btn-primary btn-block" id="vCode-btn">下一步</button>
                            </div>
                        </div>
                    </form>

                    <form class="form-horizontal" role="form" id="modifypassword_form" style="display: none;" method="post">
                        <h2>修改密码</h2>
                        <hr>
                        <div id="modifyInfo" style="margin-bottom:15px;">
                        </div>
                        <#--<input type="hidden" id="securephone" name="securephone" value="${securephone}" />-->
                        <div class="form-group">
                            <label for="password" class="col-xs-3 col-md-3 control-label">新密码</label>
                            <div class="col-xs-6 col-md-6">
                                <input type="password" class="form-control"  maxlength="16" id="password" name="password" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="confrimPwd" class="col-xs-3 col-md-3 control-label">确认密码</label>
                            <div class="col-xs-6 col-md-6">
                                <input type="password" class="form-control" maxlength="16" id="confrimPwd" name="confrimPwd" placeholder="请再次输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-6 col-md-offset-3 col-md-6">
                                <button type="button" class="btn btn-primary btn-block" id="changePwd-btn">修改密码</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-xs-5 col-md-5">
                    <img src="/images/serviceImage.png">
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row clearfix">
                <div class="col-xs-7 col-md-7 column">
                    <div class="modal fade" id="modal-pwdSuccess" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" style="padding-top:160px;">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title"><span style="top: 3px; font-size: 20px;" class="glyphicon glyphicon-ok-circle"></span>&nbsp;&nbsp;修改密码成功</h4>
                                </div>
                                <div class="modal-body">
                                    <span style="padding-left: 30px;">恭喜您修改密码成功，<label id="time"></label> 秒后自动跳转回首页。</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    </@block>

    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/resetpwd.js')}"></script>
    </@block>
</@extend>
