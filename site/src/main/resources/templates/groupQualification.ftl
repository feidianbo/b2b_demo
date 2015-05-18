<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .btn-delegate{
            background-color: #ff624f;
            color: #fff;
        }
        .delegate-hover input:hover{
            background-color: #ea3c27;
            box-shadow: 0px 0px 4px 4px rgba(192, 192, 192, 0.11);
        }
        .groupBtn{
            background: #ffffff;
            color: #317ee6;
            border:1px solid #317ee6;
            width:70px;
            height:30px;
            font-size:14px;
        }
    </style>
    </@block>
    <@block name="body">
    <div>
        <div class="container">
            <div id="body-head">
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;尊敬的XX网用户</h5></div>
                <hr>
            </div>
            <h5 style="color:#ff624f;margin-bottom: 2%;">请仔细阅读以下信息:</h5>
            <div>
                <@markdown value=contract/>
            </div>

            <!--表明是从个人中心进入-->
            <#if root?exists>
                <#if root=='account'>
                    <div style="height:30px;font-size: 14px; margin-top: 1%;" class="col-xs-offset-6 col-md-offset-6">
                    </div>
                    <div class="form-group" style="padding-top:10px;">
                        <div class="col-xs-1 col-md-1 col-md-offset-4" style="left:20px;">
                            <input type="button" class="btn" id="depositBack" value="返回" style="background:#e5e5e5;width:100px;height: 40px;font-size: 18px;">
                        </div>
                    </div>
                </#if>
            <#else>
                <div style="height:30px;font-size: 14px; margin-top: 6%;" class="col-xs-offset-6 col-md-offset-6">
                    <input type="checkbox" id="agreementChk" style="color: #dcdcdc;vertical-align: middle;margin-top: -5px;"/>
                    <label id="allInvestment" name="accept" style="font-size: 16px;">我已阅读并同意此合同</label>
                </div>
                <div class="form-group" style="padding-top:10px;">
                    <div class="col-xs-2 col-md-2 col-md-offset-5">
                        <button type="button" disabled class="btn" style="background:#317EE6;color:#ffffff;width:200px;height: 40px;font-size: 18px;" id="btn-depositConfirm">获取参团资格</button>
                    </div>
                </div>
            </#if>
            <div class="form-group" style="padding-top:10px;">
            <div class="col-xs-2 col-md-2" style="left:50px;top:-25px;">
                <!--<input type="button" class="btn" value="下载" style="background:#bfbfbf;width:100px;height: 40px;font-size: 18px;" id="downloadDepositContract">-->
            </div>
            </div>

            <div id="uploadGroupCertification" style="display: none;">
                <img src="/images/sanjiao_03.png" style="margin-top: 10px;margin-left: 51%;"/>
                <div class="form-horizontal" style="height: 300px;width: 50%;margin-left: 34%;border: 1px solid red;background: #fbfbfb;">
                    <div class="from-group col-xs-12 col-md-12 div-margin">
                        <br>
                        <input type="hidden" id="hide-groupCertification" name="hide-groupCertification" />
                        <input type="hidden" id="groupCertification" name="groupCertification" />
                        <label class="col-xs-4 col-md-4">上传支付凭证:</label>
                        <div class="col-xs-8 col-md-8">
                            <form class="form-horizontal" id="form-payGroup" method="post" enctype="multipart/form-data">
                                <input type="file" id="groupCertificationFile" name="file" style="float:left;margin-left: -30px;">
                            </form>
                        </div>
                        <div class="col-xs-12 col-md-12">
                            <div class="col-xs-9 col-md-9">
                                <div class="col-xs-11 col-md-11" style="overflow:auto; border:1px solid;width:300px;height:135px;margin-top: 24px;" id="certificationImg">
                                    <img  id="picForGroupCertification" style="margin-left: -15px;">
                                </div>
                            </div>
                            <div class="col-xs-3 col-md-3">
                                <button type="button" class="groupBtn" id="groupCertificationClick" style="width:90px;margin-top: 24px;" name="groupCertificationClick">上传图片</button>
                                <button type="button" id="delGroupClick" class="groupBtn" style="margin-top: 75px;width:90px;" name="delGroupCertificationClick">删除</button>
                            </div>
                        </div>
                        <span id="errorInfo" style="margin-top:10px;" class="col-xs-9 col-md-9 col-md-offset-1"></span>
                    </div>
                    <div class="col-xs-12 col-md-12">
                        <div class="col-xs-3 col-md-3 col-md-offset-3 delegate-hover">
                            <input type="button" class="btn btn-block btn-delegate" id="uploadConfirm"  style="margin-top: 20px;margin-left: -30px;width:170px;" value="确认上传">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div>
                <div class="modal fade" data-backdrop="static" id="modal-uploadSuccess" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="top:200px;left:155px;width:300px;">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <br>
                            <div class="modal-body" style="text-align: center;height:120px;">
                                <div>
                                    <label style="color:red;font-size: 20px;">上传成功!请等待平台审核</label>
                                </div>
                                <br>
                                <div>
                                    <input type="button" class="btn btn-delegate" style="width:120px;height:30px;font-size: 18px;padding: 0px 0px;" id="uploadToCenter" value="确&nbsp;定">
                                </div>
                                <p></p>
                                <p></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="${id?c}" id="idText"/>
    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/groupBuy.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/welcome.js')}"</script>
    </@block>
</@extend>