<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .price{color:#ff624f;font-size:26px;}
        .textHeight{line-height: 30px;}
        hr {border:1px #cccccc dotted;}
        .spanColor{color:#ff624f;}
        .spanSpace{margin-left: 10%;}
        .purchase input{width: 150px;}
        .div-margin{margin-bottom: 2%;}
        .ulClass {color:#666; font-size: 13px; text-align: left;}
        .btnStyle{background-color:#ff624f;border-radius: 4px;color:white;width:40px;}
        .list_lh{ height: 430px; overflow: hidden; color: #000000; margin-left: 20px;}
        .list_lh tr td {height: auto; text-align: left;}
        .transactionHead{text-align: center; height:57px; vertical-align: middle; font-size: 25px; font-family:'微软雅黑';color:#000000;}
    </style>
    </@block>
    <@block name="body">
        <div class="clear-level">
            <div class="container">
                <div class="row clearfix">
                        <div class="col-xs-12 col-md-12 column">
                                <div class="row clearfix">
                                    <div class="table-box">
                                        <table class="table table-hover">
                                            <div class="col-xs-4 col-md-4 column" style="margin-top: 12px;">
                                                <span>【<#if groupBuySupply.port=='其它'>${groupBuySupply.deliveryplace!''}<#else>${groupBuySupply.port!''}</#if>】 ${groupBuySupply.coaltype!''}</span>
                                                <img src="/images/groupbuytest.png" style="margin-bottom:10px;margin-top:10px;"/>
                                                <span>XX网[和略电子商务有限公司]</span>
                                            </div>
                                            <div class="col-xs-8 col-md-8 column textHeight">
                                                <div class="row">
                                                    <div class="col-md-6" style="margin-top: 20px;">
                                                        <span style="margin-left: 2%;">团购价:</span><span class="price" id="groupUnitPrice" style="margin-left:2%;margin-right:2%;width:60px;display:inline-block;">${groupBuySupply.groupbuyprice!''}</span>元/瓶
                                                        <span style="text-decoration: line-through;margin-left: 2%;">市场价:${groupBuySupply.marketprice!''}元/瓶</span>
                                                         <span class="spanSpace">团购时间:
                                                        &nbsp;<span class="spanColor">${groupBuySupply.groupbuybegindate!''}&nbsp;~<br/>
                                                        <span style="margin-left:109px;">${groupBuySupply.groupbuyenddate!''}</span></span>
                                                    </span>
                                                    </div>
                                                    <div class="col-md-6" style="margin-top: 20px;margin-left:-20px;">
                                                        <span style="float: right; display: none;font-size:34px;" id="groupActivity1">团购还没有开始!<br></span>
                                                        <span style="display: none;" id="groupActivity">距离团购结束还有:<br>
                                                        <input type="button" class="btnStyle" id="day"/><label>天</label>
                                                        <input type="button" class="btnStyle" id="hour"/><label>小时</label>
                                                        <input  class="btnStyle" type="button" id="minute"/><label>分</label>
                                                        <input class="btnStyle" type="button" id="second"/>秒
                                                        </span>
                                                    </div>

                                                </div>
                                                <hr style="margin-top:5px;margin-left:2%;"/>
                                                <div style="margin-top:-12px;">
                                                    <span class="spanSpace">发售量:
                                                        &nbsp;<span class="spanColor" id="groupNum">${groupBuySupply.supplyamount?c!''}</span>瓶
                                                    </span>
                                                    <span class="spanSpace" style="margin-left:16px;">已购量:
                                                        &nbsp;<span class="spanColor" id="soldAmount">${groupBuySupply.selledamount?c!''}</span>瓶
                                                    </span>
                                                     <span class="spanSpace" style="margin-left:16px;">剩余量:
                                                        &nbsp;<span class="spanColor" id="remainAmount">${groupBuySupply.minimumamount?c!''}</span>瓶
                                                    </span>
                                                    <span class="spanSpace" style="margin-left:16px;">起订量:
                                                        &nbsp;<span class="spanColor">${groupBuySupply.minimumamount?c!''}</span>瓶
                                                        <input type="hidden" value="${groupBuySupply.minimumamount}" id="startAmount"/>
                                                    </span>
                                                </div>
                                                <hr style="margin-top:5px;margin-left:2%;"/>
                                                <div style="margin-top:-12px;">
                                                    <span class="spanSpace">提货时间:
                                                        &nbsp;<span class="spanColor">${groupBuySupply.deliverydatestart!''}~${groupBuySupply.deliverydateend!''}</span>
                                                    </span>
                                                </div>
                                                <div class="purchase">
                                                     <span class="spanSpace">需求瓶数:
                                                    <#if .now  lt groupBuySupply.groupbuybegindate>
                                                        &nbsp;<input  type="text" style="width:150px;height:30px;" maxlength="6" id="demandNum" onkeyup="fillMoney(this);"/>瓶
                                                    </span>
                                                    <#else>
                                                        &nbsp;<input  type="text" style="width:150px;height:30px;" id="demandNum" onkeyup="fillMoney(this);"/>瓶
                                                    </#if>
                                                    <span style="margin-left: 5%;">总货款:
                                                        &nbsp;<input  type="text" style="width:150px;height:30px;" id="groupTotal" disabled/>元
                                                    </span>
                                                </div>
                                                <div class="form-group"  style="margin-top: 5px;">
                                                    <div class="col-xs-offset-2 col-xs-4 col-md-offset-2 col-md-4">
                                                        <span id="errors" style="color: #ff0000;margin-left: 15px;"></span>
                                                    </div>
                                                    <div class="col-xs-offset-1 col-xs-4 col-md-offset-1 col-md-4">
                                                        <span id="errorsForGroupMoney" style="color: #ff0000;"></span>
                                                    </div>
                                                </div>
                                                    <div class="col-xs-4 col-md-4" style="margin-left: 19%;margin-top:2px;">
                                                        <#if .now lt groupBuySupply.groupbuybegindate>
                                                    <input type="button" value="立即团购" disabled  class="btn btn-block" id="doGroupPurchase" style="color:white;border-radius: 4px;background:#317ee6;font-size: 18px;margin-bottom: 10px;"/>
                                                    <#else>
                                                        <input type="button" value="立即团购"  class="btn btn-block" id="doGroupPurchase" style="color:white;border-radius: 4px;background:#317ee6;font-size: 18px;margin-bottom: 10px;"/>
                                                    </#if>
                                                    </div>
                                            </div>
                                            </div>
                                        </table>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                        <div class="row">
                                            <div class="col-xs-9 col-md-9">
                                                <div class="table-box">
                                                    <div>
                                                        <h3><span style="padding:10px;">商品详情</span>
                                                            <hr style="border:1px solid #cbcbcb;margin-left:15px;width:750px;"/>
                                                        </h3>
                                                    </div>
                                                    <div>
                                                        <h4><span style="padding:10px;color:#317ee6;">商品信息</span> <span style="font-size: 12px;">commodity information</span>
                                                        </h4>
                                                    </div>
                                                    <table class="table table-hover">
                                                        <div class="col-xs-12 col-md-12 div-margin" style="margin-top:20px;color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">美酒种类:</span>
                                                                <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.coaltype!''}</span>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒精度数:</span>
                                                                <#if groupBuySupply.NCV != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.NCV?c!''}</span>
                                                                <#else >
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标4:</span>
                                                                <#if groupBuySupply.IM != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.IM!''}%</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标5:</span>
                                                                <#if groupBuySupply.TM!=0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.TM!''}%</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标1:</span>
                                                                <#if groupBuySupply.ADS != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.ADS!''}%</span>
                                                                <#else >
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">含糖量:</span>
                                                                <#if groupBuySupply.RS != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.RS!''}%</span>
                                                                <#else >
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标2:</span>
                                                                <#if groupBuySupply.ADV != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.ADV!''}%</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标3:</span>
                                                                <#if groupBuySupply.RV != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.RV!''}%</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标7:</span>
                                                                <#if (groupBuySupply.AFT)??>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.AFT?c!''}℃</span>
                                                                <#else >
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标6:</span>
                                                                <#if (groupBuySupply.ASH)??>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.ASH!''}%</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">酒类指标8:</span>
                                                                <#if (groupBuySupply.HGI)??>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.HGI!''}</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>

                                                        </div>
                                                        <hr style="border:1px solid #cbcbcb;margin-left:15px;width:750px;"/>

                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">提货地:</span>
                                                                <#if groupBuySupply.port == ''>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.deliveryplace!''}</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.deliveryplace!''}${groupBuySupply.port!''}</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">提货方式:</span>
                                                                <#if groupBuySupply.dliverymode != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.deliverymode!''}</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">团购数量:</span>
                                                                <#if groupBuySupply.supplyamount != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.supplyamount?c!''}瓶</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">提货时间:</span>
                                                                <#if groupBuySupply.deliverytime != 0>
                                                                    <span style="padding-right: 0px;" class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.deliverydatestart!''}~${groupBuySupply.deliverydateend!''}</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">已销售量:</span>
                                                                <#if groupBuySupply.selledamount != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.selledamount?c!''}瓶</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">0瓶</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">检验机构:</span>
                                                                <#if groupBuySupply.inspectionagency != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.inspectionagency!''}</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>
                                                        <div class="col-xs-12 col-md-12 div-margin" style="color:#666;">
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">可销售库存:</span>
                                                                <#if groupBuySupply.surplusamount != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.surplusamount?c!''}瓶</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">0瓶</span>
                                                                </#if>
                                                            </div>
                                                            <div class="col-xs-6 col-md-6">
                                                                <span class="col-xs-5 col-md-5 control-label">团购价:</span>
                                                                <#if groupBuySupply.groupbuyprice != 0>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">${groupBuySupply.groupbuyprice?c!''}元/瓶</span>
                                                                <#else>
                                                                    <span class="col-xs-7 col-md-7 control-label attr-color">--</span>
                                                                </#if>
                                                            </div>
                                                        </div>
                                                    </table>
                                                </div>
                                                <div class="table-box">
                                                <div>
                                                    <h4><span style="padding:10px;color:#317ee6;">团购规则</span> <span style="font-size: 12px;">Rules of Group</span>
                                                    </h4>
                                                </div>
                                                <table class="table table-hover">
                                                    <div class="col-xs-12 col-md-12 div-margin" style="margin-top:20px;color:#666;">
                                                        <div class="col-xs-11 col-md-11">
                                                            <p>1.参与XX网团购活动必须为已认证公司信息的XX网会员,并已交纳10万元团购保证金,成为团购会员。</p>
                                                            <p>2.当您确认参与团购并填选了认购瓶数之后,需交纳规定数额的货款保证金,请您仔细阅读货款保证签订的各项条例,点击确认之后立即生效并开始执行。</p>
                                                            <p>3.若您违反团购的相关规定,会按照签订的条款按比例扣除您的货款保证金;若由于团购数量没有达到要求或出现其他不可成交的情况,会按要求退还全部货款保证金。</p>
                                                            <p>4.团购活动的解释权最终归XX网所有。</p>
                                                        </div>
                                                        </div>
                                                    </div>
                                                </table>
                                                </div>
                                            </div>

                                            <div class="col-xs-3 col-md-3" style="margin-top: 30px;">
                                                <div class="notes">
                                                    <h4 class="transactionHead">成交记录</h4>
                                                    <div class="list_lh">
                                                        <table class="table" style="font-size: 12px; text-align: left;">
                                                            <tbody>
                                                                <#list transactionOrders as transactionOrder>
                                                                <tr>
                                                                    <td class="ulClass">
                                                                        港口名:${transactionOrder.port!''}<br />
                                                                        美酒种类:${transactionOrder.coaltype!''}<br />
                                                                        成交量:${transactionOrder.volume?c!''}瓶<br />
                                                                        成交时间:${transactionOrder.deliverytime!''}<br />
                                                                        团购价:${transactionOrder.groupbuyprice!''}元/瓶<br />
                                                                        市场价:${transactionOrder.marketprice!''}元/瓶
                                                                        <hr style="1px solid #cbcbcb; width:200px; margin-left:-20px;"/>
                                                                    </td>
                                                                </tr>
                                                            </#list>
                                                            </tr>
                                                            </tbody>
                                                            </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <input type="hidden" value="${groupBuySupply.groupbuyprice!''}" id="groupPrice"/>
    <input type="hidden" value="${groupBuySupply.id?c!''}" id="groupSupplyId"/>
    <input type="hidden" value="${groupBuySupply.groupbuyenddate!''}" id="endDate"/>
    <input type="hidden" value="${groupBuySupply.groupbuybegindate!''}" id="beginDate"/>
    <input type="hidden" value="${serverdatetime!''}" id="serverTime"/>

    <div class="container">
        <div class="row clearfix">
            <div class="col-xs-7 col-md-7 column">
                <div class="modal fade my_supply" data-backdrop="false" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="top:200px;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="myModalLabel">
                                    温馨提示:
                                </h4>
                            </div>
                            <div class="modal-body">
                                <span class="my_supplyBody" style="padding-left: 30px;"></span>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-primary my_supplyButton"></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/groupBuy.js')}"></script>
    </@block>
</@extend>