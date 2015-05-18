<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .delegate-hover input:hover{
            background-color: #ea3c27;
            box-shadow: 0px 0px 4px 4px rgba(192, 192, 192, 0.11);
        }
        .pay-space{
            line-height: 2.0;
        }
        .pay-btn{
            background: #ffffff;
            color: #317ee6;
            border:1px solid #317ee6;
            width:100px;
            height:34px;
            font-size:14px;
        }
        .v_padding{padding-top: 20px; margin-left: 0px; margin-right: 0px;}
        .file_border{
            border:0px;
            box-shadow: none;
        }
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="body-head">
                <div style="border-left: 4px solid #ff624f;"><h3>&nbsp;&nbsp;订单状态:<span style="color:#ff624f;font-size: 24px;"><#if order.status != 'WaitBalancePayment '>待支付货款<#else>待支付尾款</#if></span></h3></div>
                <div style="padding-left: 15px;">
                    <h4>公司名称:测试公司</h4>
                    <h4>开户行:测试行</h4>
                    <h4>账号:111111111111</h4>
                </div>
                <div class="col-xs-8 col-md-8">
                    <#if order.status == '待付款'>
                        <label class="control-label" style="color:#ff624f;font-size: 14px;">注意:请您在24小时内完成支付，超过24小时不支付XX网会自动取消订单。</label>
                    <#else>
                        <label class="control-label" style="color:#ff624f;font-size: 14px;">为了您的方便，请及时支付货款</label>
                    </#if>
                </div>
            </div>
            <div style="border: 1px solid; margin-top:5%;color:#f7f7f7;margin-bottom: 3%;"></div>
            <div class="col-xs-12 col-md-12 pay-space" style="font-size: 18px;">
                <label>订单编号:&nbsp;&nbsp;&nbsp;<span style="color:#317ee6">${order.orderid!''}</span></label>
                <label style="margin-left:30px;">单价:&nbsp;&nbsp;&nbsp;<span style="color:#317ee6">${order.price!''}</span>元/瓶</label>
                <label style="margin-left:30px;">数量:&nbsp;&nbsp;&nbsp;<span style="color:#317ee6">${order.amount?c!''}</span>瓶</label>
                <label style="margin-left: 30px;">总货款:&nbsp;&nbsp;&nbsp;<span style="color:#ff624f">${order.totalmoney!''}</span>元</label>
            </div>

            <div class="col-xs-12 col-md-12 pay-space" style="font-size: 18px;">
                <#if order.status == '待付款'>
                    <#if order.paytype != '付全款'>
                        <label style="margin-left: 30px;">履约金:&nbsp;&nbsp;&nbsp;<label style="color:#ff624f;">${order.totalmoney*0.1!''}</label><label>元</label></label>
                    </#if>
                </#if>
                <div class="col-md-3" style="padding-left: 0px;">
                    <label>已支付货款:&nbsp;&nbsp;<label style="color:#ff624f">${order.paidmoney}</label><label>元</label></label>
                </div>
                <div class="col-md-6">
                    <label style="margin-left: 30px;">待支付尾款:&nbsp;&nbsp;&nbsp;<label style="color:#ff624f">${order.waitmoney}</label><label>元</label></label>
                </div>
            </div>
            <div class="col-xs-12 col-md-12">
                <label class="control-label">请上传支付凭证:<img src="/images/jia_06.png" id="add" style="margin-left: 30px;"/></label>
            </div>

        <#--first-->
          <div class="row v_padding">
            <div class="col-xs-12 col-md-12 pay-space">
                <input type="hidden" id="hide-certification1" name="hide-certification1" />
                <input type="hidden" id="paymentid01" name="paymentid01" />
                <span class="col-xs-2 control-label col-md-2" style="padding-left: 0px;">支付凭证一:</span>
                <div class="col-xs-7 col-md-7">
                    <form class="form-horizontal"  id="form-pay1" method="post" enctype="multipart/form-data">
                        <div class="col-xs-6 ">
                            <input class="form-control file_border" type="file" id="certification1" name="file">
                        </div>
                        <button class="col-xs-4 pay-btn" type="button" id="payCertificationClick1"  name="payCertificationClick1">上传图片</button>
                    </form>
                </div>
                <input type="hidden" value="${order.id}" name="id"/>
                <div class="col-xs-3">
                    <span id="certificationInfo1" class="index-color"></span>
                </div>
            </div>
            <div class="col-xs-12 col-md-12" >
                <div class="col-xs-offset-3 col-md-offset-3" id="certificationFirstImg">
                    <img  id="picForCertification1" src="">
                </div>
            </div>
            <div class="col-xs-offset-7 col-md-offset-7" id="delFirst" style="display: none;">
                <a href="javascript:void(0);" style="font-size:16px;color:#317ee6;">删  除</a>
            </div>
            <div style="height:20px;"></div>
          </div>
            <#--first-->


            <#--second-->
            <div class="row v_padding">
                <div class="col-xs-12 col-md-12 pay-space" style="display: none;" id="payPicTwo">
                    <input type="hidden" id="hide-certification2" name="hide-certification2" />
                    <input type="hidden" id="paymentid02" name="paymentid02" />
                    <span class="col-xs-2 control-label col-md-2" style="padding-left: 0px;">支付凭证二:</span>
                    <div class="col-xs-7 col-md-7">
                    <form  id="form-pay2" method="post" enctype="multipart/form-data">
                        <div class="col-xs-6 ">
                        <input class="form-control file_border" type="file" id="certification2" name="file">
                        </div>
                        <button class="col-xs-4 pay-btn" type="button" id="payCertificationClick2"  name="payCertificationClick2">上传图片</button>
                        <div class="col-xs-2">
                            <img  src="/images/jian_03.png" id="reduceFirst">
                        </div>
                    </form>
                    </div>
                    <input type="hidden" value="${order.id}" name="id"/>
                    <div class="col-xs-3">
                        <span id="certificationInfo2" class="index-color"></span>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12">
                    <div class="col-xs-offset-3 col-md-offset-3" id="certificationSecondImg">
                        <img  id="picForCertification2" src="">
                    </div>
                </div>
                <div class="col-xs-offset-7 col-md-offset-7" id="delSecond" style="display: none;">
                    <a href="javascript:void(0);" style="font-size:16px;color:#317ee6;">删  除</a>
                </div>
                <div style="height:20px;"></div>
            </div>
            <#--second-->

            <div class="row v_padding">
            <div class="col-xs-12 col-md-12 pay-space" style="display: none;" id="payPicThree">
                <span class="control-label col-xs-2 col-md-2" style="padding-left: 0px;">支付凭证三:</span>
                    <input type="hidden" id="hide-certification3" name="hide-certification3" />
                    <input type="hidden" id="paymentid03" name="paymentid03" />
                    <div class="col-xs-7 col-md-7">
                    <form  id="form-pay3" method="post" enctype="multipart/form-data">
                        <div class="col-xs-6 ">
                            <input class="form-control file_border" type="file" id="certification3" name="file">
                        </div>
                        <button class="col-xs-4 pay-btn" type="button" id="payCertificationClick3"  name="payCertificationClick3">上传图片</button>
                        <div class="col-xs-2">
                            <img  src="/images/jian_03.png" id="reduceSecond">
                        </div>
                    </form>
                    </div>
                    <input type="hidden" value="${order.id}" name="id"/>
                <div class="col-xs-3 col-md-3">
                    <span id="certificationInfo3" class="index-color"></span>
                </div>
            </div>

            <div class="col-xs-12 col-md-12">
                <div class="col-xs-offset-3 col-md-offset-3" id="certificationThirdImg">
                    <img  id="picForCertification3" src="">
                </div>
            </div>
            <div class="col-xs-offset-7 col-md-offset-7" id="delThird" style="display: none;">
                <a href="javascript:void(0);" style="font-size:16px;color:#317ee6;">删  除</a>
            </div>
                </div>
            <div class="col-xs-12 col-md-12">
                <span id="certificationInfoError" style="padding-left: 0px;"></span>
            </div>

            <div class="col-xs-2 col-md-2 col-md-offset-8">
                <button type="button" class="btn btn-block" id="btn-gotoOrder" style="color:#ffffff;background: #ff624f;">确认提交</button>
            </div>
        </div>
        </div>
        </div>
    </div>
</div>

    <input type="hidden" value="${order.id?c}" id="oId"/>

    <div class="container">
        <div class="row clearfix">
            <div>
                <div class="modal fade" id="modal-payInfo" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h4 class="modal-title" id="">
                                    温馨提示
                                </h4>
                            </div>
                            <div class="modal-body">
                                您的支付凭证上传成功,我们会尽快审核,和你们联系!
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="${order.id?c}" id="pay_orderId">
    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/myOrder.js')}"></script>
    </@block>
</@extend>