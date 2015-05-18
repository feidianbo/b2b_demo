<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .attr-color{
            color:#317ee6;
        }
        .div-margin{
            margin-bottom: 2%;
        }
        .cols, .col-md-2, .col-md-4, .col-md-6, .col-md-8, .col-md-12 {
            padding-left: 0px;
        }
        .delegate-hover input:hover{
            background-color: #ea3c27;
            box-shadow: 0px 0px 4px 4px rgba(192, 192, 192, 0.11);
        }
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="body-head">
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;团购订单详情</h5></div>
                <hr>
            </div>
            <h5 style="color:red;margin-bottom: 2%;">请仔细核对以下信息:</h5>
            <div class="col-xs-12 col-md-12 div-margin">
                <div class="col-xs-6 col-md-6">
                    <label class="col-xs-4 col-md-4 control-label">团购订单编号:</label>
                    <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuyOrder.groupbuyordercode!''}</span>
                </div>
                <div class="col-xs-6 col-md-6">
                    <label class="col-xs-4 col-md-4 control-label">团购资质编号:</label>
                    <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuyOrder.qualificationcode!''}</span>
                </div>
            </div>
            <div style="border: 1px solid; margin-top:5%;color:#fbfbfb;margin-bottom: 2%;"></div>
            <h5 style="color:red;margin-bottom: 2%;">主要信息如下:</h5>
            <div class="cols">
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">美酒种类:</label>
                        <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.coaltype!''}</span>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒精度数:</label>
                        <#if groupBuySupply.NCV != 0 && groupBuySupply.NCV !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.NCV?c!''}</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标4:</label>
                        <#if groupBuySupply.IM != 0 && groupBuySupply.IM !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.IM!''}%</span>
                        <#else>
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标5:</label>
                        <#if groupBuySupply.TM !=0 && groupBuySupply.TM !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.TM!''}%</span>
                        <#else>
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标1:</label>
                        <#if groupBuySupply.ADS != 0 && groupBuySupply.ADS !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.ADS!''}%</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">含糖量:</label>
                        <#if groupBuySupply.RS != 0 && groupBuySupply.RS !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.RS!''}%</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标2:</label>
                        <#if groupBuySupply.ADV != 0 && groupBuySupply.ADV !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.ADV!''}%</span>
                        <#else>
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标3:</label>
                        <#if groupBuySupply.RV != 0 && groupBuySupply.RV !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.RV!''}%</span>
                        <#else>
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>


                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标7:</label>
                        <#if groupBuySupply.AFT != 0 && groupBuySupply.AFT !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.AFT!''}℃</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标6:</label>
                        <#if groupBuySupply.ASH != 0 && groupBuySupply.ASH !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.ASH!''}℃</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>

                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">酒类指标8:</label>
                        <#if groupBuySupply.HGI != 0 && groupBuySupply.HGI !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.HGI!''}</span>
                        <#else>
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>

                <hr/>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">提货地:</label>
                        <#if groupBuySupply.port == ''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.deliveryprovince!''}${groupBuySupply.deliveryplace!''}</span>
                        <#else>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.deliveryprovince!''}${groupBuySupply.port!''}</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">提货方式:</label>
                        <#if (groupBuySupply.deliverymode)??>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.deliverymode!''}</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">团购数量:</label>
                        <#if groupBuySupply.supplyamount != 0 && groupBuySupply.supplyamount !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.supplyamount!''}瓶</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">提货时间:</label>
                        <#if groupBuySupply.deliverydatestart != ''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.deliverydatestart!''} <#if groupBuySupply.deliverydateend != ''>~ ${groupBuySupply.deliverydateend!''}</#if></span>

                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">已销售量:</label>
                        <#if groupBuySupply.selledamount != 0 && groupBuySupply.selledamount !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.selledamount!''}瓶</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">0瓶</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">检验机构:</label>
                        <#if groupBuySupply.inspectionagency != ''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.inspectionagency!''}</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">可销售库存:</label>
                        <#if groupBuySupply.surplusamount != 0 && groupBuySupply.surplusamount !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.surplusamount!''}瓶</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">0瓶</span>
                        </#if>
                    </div>
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">团购价:</label>
                        <#if groupBuySupply.groupbuyprice != 0 && groupBuySupply.groupbuyprice !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.groupbuyprice?c!''}元/瓶</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
                <div class="col-xs-12 col-md-12 div-margin">
                    <div class="col-xs-6 col-md-6">
                        <label class="col-xs-4 col-md-4 control-label">堆位:</label>
                        <#if groupBuySupply.storageplace !=''>
                            <span class="col-xs-8 col-md-8 control-label attr-color">${groupBuySupply.storageplace!''}</span>
                        <#else >
                            <span class="col-xs-8 col-md-8 control-label attr-color">--</span>
                        </#if>
                    </div>
                </div>
            </div>
            <#if groupBuyOrder.status=='ORDER_CREATE'>
                <div class="col-xs-2 col-md-2 col-md-offset-3">
                    <button type="button" class="btn" style="background:#317ee6;width:200px;height: 40px;font-size: 18px;" id="btn-updateGroupOrder">修改订单</button>
                </div>
                <div class="col-xs-2 col-md-2 col-md-offset-1">
                    <button type="button" class="btn" style="background:#ff624f;width:200px;height: 40px;font-size: 18px;" id="btn-submitOrder">提交订单</button>
                </div>
            </#if>
    </div>

        <div class="container">
            <div class="row clearfix">
                <div class="modal fade" id="modal-orderSuccessPrompt" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="top:200px;height:180px;">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <div class="modal-body" style="text-align: center;">
                                <p style="color:#ff624f;margin-top: 30px;font-size:18px;">
                                    <img src="/images/gantanhao_03.png"/>
                                    您的团购已下单成功,交易员会跟您及时联系!<br/>您确定购买该产品吗?</p>
                                <div style="margin-top: 23px;">
                                    <button type="button" class="btn" style="width: 158px;height:40px;font-size: 18px;background: #ff624f;color:#ffffff;" id="contract-confirm">确&nbsp;定</button>
                                    <button type="button" class="btn btn-sm btn-cancel" style="width: 158px;height:40px;font-size: 18px;" data-dismiss="modal">取&nbsp;消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row clearfix">
                <div class="modal fade" id="modal-orderFailPrompt" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="top:200px;height:180px;">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <div class="modal-body" style="text-align: center;">
                                <p style="color:#ff624f;margin-top: 30px;font-size:18px;">
                                    <img src="/images/gantanhao_03.png"/>
                                    下单失败</p>
                                <div style="margin-top: 23px;">
                                    <button type="button" class="btn btn-sm btn-cancel" style="width: 158px;height:40px;font-size: 18px;" data-dismiss="modal">取&nbsp;消</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    <input type="hidden" value="${groupBuyOrder.volume?c}" id="volume"/>
    <input type="hidden" value="${groupBuyOrder.groupbuyordercode}" id="groupBuyOrderCode"/>
    <input type="hidden" value="${groupBuyOrder.id?c}" id="groupBuyOrderId"/>
    <input type="hidden" value="${groupBuySupply.id?c}" id="groupBuySupplyId"/>
    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/groupBuy.js')}"></script>
    </@block>
</@extend>



