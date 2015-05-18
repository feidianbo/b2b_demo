<@extend name="layout">
    <@block name="cs">

    <style type="text/css">
    .mybtn:hover{
        background-color: #ea3c27;
    }
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="body-head">
                <div style="border-left:4px solid #ff624f;height:25px;"><label style="color:#313131;font-size:24px; ">&nbsp;&nbsp;电子合同</label></div>
                <hr style="color:#dcdcdc;">
            </div>
            <h5 style="color:#ff624f;margin-bottom: 2%;">请仔细阅读以下信息:</h5>
            <div>
                <@markdown value=contract/>
            </div>
        </div>
    </div>
</div>

    <input type="hidden" value="${order.deliverymode}">

    <div style="height:30px;font-size: 14px; margin-top: 6%;margin-left: 60%;" class="col-xs-offset-9 col-md-offset-9">
            <input type="checkbox" id="agreementChk" style="color: #dcdcdc;vertical-align: middle;margin-top: -5px;"/>
            <label id="allInvestment" name="accept" style="font-size: 16px;">我已阅读并同意此合同</label>
    </div>
    <div class="form-group" style="padding-top:10px;">
        <div class="col-xs-2 col-md-2 col-md-offset-7">
            <button type="button" disabled class="btn" style="background:#317EE6;color:#ffffff;width:200px;height: 40px;font-size: 18px;" id="btn-contractConfirm">确&nbsp;定</button>
        </div>
        <!--<div class="col-xs-1 col-md-1" style="left:20px;">
            <input type="button" class="btn" value="下载" style="background:#e5e5e5;width:140;height: 40px;font-size: 18px;" id="downloadContract">
        </div>-->
    </div>
    <div>

    <input type="hidden" value="${order.id?c}" id="idText"/>

        <div class="container">
            <div class="row clearfix">
                    <div class="modal fade" data-backdrop="static" id="modal-contractDelay" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content" style="top:200px;height:150px;">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <div class="modal-body" style="text-align: center;">
                                        <label style="color:#ff624f;margin-top: 30px;font-size:18px;">
                                            <img src="/images/gantanhao_03.png"/>
                                    您的合同已过期,订单失效,请重新下单!</label>
                                        <div style="margin-top: 15px;">
                                            <button type="button" class="btn" style="width: 158px;height:40px;font-size: 18px;background: #ff624f;color:#ffffff;" id="contract-confirm" data-dismiss="modal">确&nbsp;定</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
            </div>
        </div>


        <div class="container">
            <div class="row clearfix">
                <div class="modal fade" id="modal-contractPrompt" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="top:200px;height:180px;">
                            <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
                            <div class="modal-body" style="text-align: center;">
                                <p style="color:#ff624f;margin-top: 30px;font-size:18px;">
                                    <img src="/images/gantanhao_03.png"/>
                                    您将与XX网平台签订采购意向,</p>
                                <p style="color:#ff624f;font-size:18px;">请在2个小时内签订合同，否则系统将自动取消订单。</p>
                                <div style="margin-top: 23px;">
                                    <button type="button" class="btn" style="width: 158px;height:40px;font-size: 18px;background: #ff624f;color:#ffffff;" id="contract-confirm" data-dismiss="modal">确&nbsp;定</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    <div class="container">
        <div class="row clearfix">
            <div class="modal fade" id="modal-contractConfirm" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content" style="top:200px;height:150px;">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <br>
                        <div class="modal-body" style="text-align: center;">
                            <div>
                                <img src="/images/gantanhao_03.png"/>
                                <label style="color:#ff624f;">电子合同已确认，请按照合同要求支付货款。</label>
                            </div>
                            <div class="col-md-offset-4" style='margin-top:20px;'>
                                <button type="button" class="btn btn-default col-md-6 mybtn" id="btn-gotoPay" style="background-color: #ff624f;color:white;">确认</button>
                            </div>
                            <p></p>
                            <p></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    </@block>
    <@block name="script">
        <script type="text/javascript" src="${static('/scripts/myOrder.js')}"></script>
    </@block>
</@extend>
