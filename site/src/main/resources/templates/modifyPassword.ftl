<@extend name="layout">
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="modifyPwd" class="col-xs-12 col-md-12">
                <div class="col-xs-7 col-md-7">
                    <form class="form-horizontal" role="form" id="modifypassword_form" method="post">
                        <h2>修改密码</h2>
                        <hr>
                        <div id="modifyInfo" style="margin-bottom:15px;">
                        </div>
                        <input type="hidden" id="securephone" name="securephone" value="${securephone}" />
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
    </@block>

    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/modifyPassword.js')}"></script>
    </@block>
</@extend>
