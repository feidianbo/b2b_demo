<@extend name="layout">
    <@block name="head">
    <link rel="stylesheet" type="text/css" href="/bower_components/bootstrap3-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>
    </@block>
    <@block name="cs">
    <style type="text/css">
        .announceBtn{
            margin-top:-30px;
            background-color: #317ee6;
            float:right;
            width:150px;
        }
        .div-margin{
            margin-bottom: 2%;
        }

        .col-md-2, .col-md-4, .col-md-6, .col-md-8, .col-md-12 {
            padding-left: 0px;
        }
        .btn-delegate{
            background-color: #ff624f;
            color: #fff;
        }
        .delegate-hover input:hover{
            background-color: #ea3c27;
            box-shadow: 0px 0px 4px 4px rgba(192, 192, 192, 0.11);
        }
        .btn-buy{
            margin-left: 20%;
        }
        .mybtn:hover{
            background-color: #ea3c27;
        }
        .wordColor{color: #317EE6;}
        .priceColor{color: red;}
        .padClear{padding-left: 0px;padding-right: 0px;}
        .padClearR{padding-left: 0px;padding-right: 0px;text-align: right;}
        .padClearC{padding-left: 0px;padding-right: 0px;text-align: center;}
        .table>tbody>tr>td{padding-left: 0px;padding-right: 0px;}
        .tableTitle{padding-left: 10px;}
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="body-head">
                <div style="border-left:4px solid red;">
                    <h5>
                        <#if sellInfo.seller == '自营'>
                            &nbsp;&nbsp;XX商城—详细供应信息
                        <#else>
                            &nbsp;&nbsp;我要买—详细供应信息
                        </#if>
                    </h5>
                </div>
                <input type="button" id="announce" class="btn btn-primary announceBtn" value="发布需求>"/>
                <hr>
            </div>
            <h5 style="color:red;margin-bottom: 2%;">请仔细核对以下信息:</h5>
            <#--<div style="padding-bottom: 5px;margin-top: -10px;">-->
                <#--<label>日期:${.now?date}</label>-->
                <#--<label style="color:#ff0000;margin-left:200px;">可销售库存:<span style="font-size:34px;" id="asn">${sellInfo.availquantity!''}</span>瓶</label>-->
            <#--</div>-->
            <#if sellInfo??>
            <table class="table table-bordered" style="font-size:14px;">
            <#assign t ="<M≤"/>
            <#assign pay = (sellInfo.paymode)/>
                <tbody>
                <tr><td class="tableTitle" style="padding-left: 10px;text-align: left; background: #65b3e1; color: #ffffff;"  colspan="8">产品指标</td></tr>
                <tr>
                    <td>产品编号</td>
                    <td colspan="7" class="wordColor" colspan="3">${sellInfo.pid!''}<span style="color: #000000"> (已有 <span style="color: #ff0000;">${sellInfo.viewtimes}</span> 人次浏览)</span></td>
                </tr>
                <tr>
                    <td>美酒种类</td>
                    <td class="wordColor">${sellInfo.pname!''}</td>
                    <td style="width:90px;">产地</td>
                    <td class="wordColor">${sellInfo.originplace!''}</td>
                    <td style="width:90px;">酒精度数</td>
                    <td class="wordColor">${sellInfo.NCV?c!''}</td>
                    <td style="width:115px;">酒类指标4</td>
                    <td class="wordColor">${sellInfo.TM!''}%</td>
                </tr>
                <tr>
                    <td>酒类指标5</td>
                    <#if sellInfo.IM ??>
                        <#if sellInfo.IM != 0>
                            <td class="wordColor">${sellInfo.IM!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标1</td>
                    <#if sellInfo.ADS ??>
                        <#if sellInfo.ADS != 0>
                            <td class="wordColor">${sellInfo.ADS!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>含糖量</td>
                    <#if sellInfo.RS ??>
                        <#if sellInfo.RS != 0>
                            <td class="wordColor">${sellInfo.RS!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标2</td>
                    <#if sellInfo.ADV ??>
                        <#if sellInfo.ADV != 0>
                            <td class="wordColor">${sellInfo.ADV!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                </tr>
                <tr>
                    <td>酒类指标3</td>
                    <#if sellInfo.RV ??>
                        <#if sellInfo.RV != 0>
                            <td class="wordColor">${sellInfo.RV!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标6</td>
                    <#if sellInfo.ASH ??>
                        <#if sellInfo.ASH != 0>
                            <td class="wordColor">${sellInfo.ASH!''}%</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标7</td>
                    <#if sellInfo.AFT ??>
                        <#if sellInfo.AFT != 0>
                            <td class="wordColor">${sellInfo.AFT?c!''}℃</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                    <td>酒类指标8</td>
                    <#if sellInfo.HGI ??>
                        <#if sellInfo.HGI != 0>
                            <td class="wordColor">${sellInfo.HGI!''}</td>
                        <#else>
                            <td class="wordColor">--</td>
                        </#if>
                    </#if>
                </tr>
                <tr><td class="tableTitle" style="padding-left: 10px;text-align: left; background: #65b3e1; color: #ffffff;" colspan="8">产品基本信息</td></tr>
                <tr>
                    <#if sellInfo.seller == '自营'>
                    <td>交货地点</td>
                    <td class="wordColor">${sellInfo.deliveryprovince!''}

                    <#if sellInfo.deliveryplace?exists>
                        <#if sellInfo.deliveryplace == "其它">
                            ${sellInfo.otherharbour!''}
                        <#else>
                            ${sellInfo.deliveryplace!''}
                        </#if>
                    </#if>

                    </td>
                    <td>交货方式</td>
                    <td class="wordColor">${sellInfo.deliverymode!''}</td>
                    <#else>
                    <td>付款方式</td>
                    <#if pay == 1>
                        <td class="wordColor">现汇</td>
                    <#elseif pay == 2>
                        <td class="wordColor">账期${sellInfo.payperiod}个月</td>
                    <#elseif pay == 3>
                        <td class="wordColor">银行承兑汇票</td>
                    <#else >
                        <td class="wordColor">现汇+银行承兑汇票</td>
                    </#if>
                    <td>交货地点</td>
                    <td class="wordColor">${sellInfo.deliveryprovince!''}
                        <#if sellInfo.deliveryplace?exists>
                            <#if sellInfo.deliveryplace == "其它">
                            ${sellInfo.otherharbour!''}
                            <#else>
                            ${sellInfo.deliveryplace!''}
                            </#if>
                        </#if>
                    </td>
                    </#if>
                    <td rowspan="2" style="vertical-align: middle;">供应数量</td>
                    <td style="vertical-align: middle;" class="priceColor" rowspan="2">${sellInfo.supplyquantity!''}瓶</td>
                    <td style="vertical-align: middle;" rowspan="4"><span style="font-size: 30px;">价格</span><br>(含税)</td>
                    <#if sellInfo.ykj==0>
                    <td class=" wordColor" rowspan="4" style="width:260px;color:#ff0000;vertical-align: middle;line-height: 25px;text-align: -webkit-left;">
                    <#if jtj01Obj ??>
                        <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                            <span class="padClearR col-xs-3">0</span>
                            <span class="padClearC col-xs-2">${t}</span>
                            <span class="padClear col-xs-4">${jtj01Obj.amount2?c!''}瓶</span>
                            <span class="padClear col-xs-3">${jtj01Obj.price?c!''}元/瓶</span>
                        </div>
                    </#if>
                    <#if jtj02Obj ??>
                        <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                            <span class="padClearR col-xs-3">${jtj02Obj.amount1?c!''}</span>
                            <span class="padClearC col-xs-2">${t}</span>
                            <span class="padClear col-xs-4">${jtj02Obj.amount2?c!''}瓶</span>
                            <span class="padClear col-xs-3">${jtj02Obj.price?c!''}元/瓶</span>
                        </div>
                    </#if>
                    <#if jtj03Obj ??>
                        <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                            <span class="padClearR col-xs-3">${jtj03Obj.amount1?c!''} </span>
                            <span class="padClearC col-xs-2">${t}</span>
                            <span class="padClear col-xs-4">${jtj03Obj.amount2?c!''}瓶</span>
                            <span class="padClear col-xs-3">${jtj03Obj.price?c!''}元/瓶</span>
                        </div>
                    </#if>
                    <#if jtj04Obj ??>
                        <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                            <span class="padClearR col-xs-3">${jtj04Obj.amount1?c!''} </span>
                            <span class="padClearC col-xs-2">${t}</span>
                            <span class="padClear col-xs-4">${jtj04Obj.amount2?c!''}瓶</span>
                            <span class="padClear col-xs-3">${jtj04Obj.price?c!''}元/瓶</span>
                        </div>
                    </#if>
                    <#if jtj05Obj ??>
                        <div class="col-xs-12" style="padding-left: 0px;padding-right: 0px;">
                            <span class="padClearR col-xs-3">${jtj05Obj.amount1?c!''} </span>
                            <span class="padClearC col-xs-2">${t}</span>
                            <span class="padClear col-xs-4">${jtj05Obj.amount2?c!''}瓶</span>
                            <span class="padClear col-xs-3">${jtj05Obj.price?c!''}元/瓶</span>
                        </div>
                    </#if>
                    </td>
                    <#else>
                    <td rowspan="4" style="text-align: center;vertical-align:middle;color:#ff0000;">${sellInfo.ykj?c!''}元/瓶</td>
                    </#if>
                </tr>
                <tr>
                    <#if sellInfo.seller == '自营'>
                    <td>交货时间</td>
                    <td colspan="3" class="wordColor">${sellInfo.deliverytime1!''} ~ ${sellInfo.deliverytime2!''}</td>
                    <#else>
                    <td>交货方式</td>
                    <td class="wordColor">${sellInfo.deliverymode!''}</td>
                    <td>交货时间</td>
                    <td class="wordColor">${sellInfo.deliverytime1!''} ~ ${sellInfo.deliverytime2!''}</td>
                    </#if>
                </tr>
                <tr>
                    <td>检验机构</td>
                    <#if sellInfo??>
                    <#if sellInfo.inspectorg=='无'>
                    <td colspan="3" style="width: 200px;" class=" wordColor">无</td>
                    <#else>
                        <#if sellInfo.inspectorg=='其它'>
                        <td colspan="3" style="width: 200px;" class=" wordColor">${sellInfo.otherinspectorg!''}</td>
                        <#else>
                        <td colspan="3" style="width: 200px;" class=" wordColor">${sellInfo.inspectorg!''}</td>
                        </#if>
                    </#if>
                    </#if>
                    <td>已销售数量</td>
                    <#if sellInfo.soldquantity ??>
                        <#if sellInfo.soldquantity!=0>
                        <td class="priceColor">${sellInfo.soldquantity!''}瓶</td>
                        <#else>
                        <td class="priceColor">0瓶</td>
                        </#if>
                    </#if>
                </tr>
                <tr>
                    <#if sellInfo.seller == '自营'>
                        <td>交易员</td>
                    <#else>
                        <td>联系人</td>
                    </#if>
                    <#if sellInfo.linktype>
                        <td class="wordColor">${sellInfo.linkmanname!''}</td>
                    <#else>
                        <td class="wordColor">${sellInfo.dealername!''}</td>
                    </#if>
                    <td>联系方式</td>
                    <#if sellInfo.linktype>
                        <td class="wordColor">${sellInfo.linkmanphone!''}</td>
                    <#else>
                        <td class="wordColor">${sellInfo.dealerphone!''}</td>
                    </#if>
                    <#--<td class="wordColor">${sellInfo.dealername!''}</td>-->
                    <td>剩余库存量</td>
                    <#if sellInfo.availquantity ??>
                        <#if sellInfo.availquantity!=0>
                            <td class="priceColor">${sellInfo.availquantity!''}瓶</td>
                        <#else>
                            <td class="priceColor">0瓶</td>
                        </#if>
                    </#if>
                </tr>
                <#if sellInfo.releaseremarks ?length gt 0>
                    <tr><td class="tableTitle" style="padding-left: 10px;text-align: left; background: #65b3e1; color: #ffffff;"  colspan="8">备注</td></tr>
                    <tr><td style="text-align: left; text-indent: 3em;" colspan="8">${sellInfo.releaseremarks!''}</td></tr>
                <#else>
                    <tr><td class="tableTitle" style="padding-left: 10px;text-align: left; background: #65b3e1; color: #ffffff;"  colspan="8">备注</td></tr>
                    <tr><td style="text-align: left; text-indent: 3em;" colspan="8"></td></tr>
                </#if>
                </tbody>
            </table>
            </#if>
        <div>
            <span id="errorInfoRemind" class="col-xs-8 col-md-8 col-md-offset-3"></span>
            <#if sellInfo.seller=="自营">
                <div class="col-xs-3 col-md-3 btn-buy" style="margin-top: 25px;">
                    <input type="button" class="btn btn-primary btn-block"  id="mallAttend" value="关注">
                </div>
                <div class="col-xs-3 col-md-3 delegate-hover" style="margin-top: 25px;">
                    <input type="button" class="btn btn-block btn-delegate" value="认购" id="subscribe">
                </div>
            <#else>
                <div class="col-xs-3 col-md-3 btn-buy" style="margin-top: 25px;">
                    <input type="button" class="btn btn-primary btn-block"  id="buyAttend" value="关注">
                </div>
                <div class="col-xs-3 col-md-3 delegate-hover" style="margin-top: 25px;">
                    <input type="button" class="btn btn-block btn-delegate" value="委托下单" id="delegateOrder">
                </div>
            </#if>
        </div>
        <input type="hidden" value="${sellInfo.id}" id="idText"/>
        <input type="hidden" value="${sellInfo.supplyquantity?c}" id="supplyNum"/>
        <input type="hidden" value="${sellInfo.ykj}" id="unitPrice"/>
        <input type="hidden" value="${sellInfo.seller!''}" id="saleType"/>
        <input type="hidden" value="${sellInfo.deliverymode!''}" id="type"/>
        <input type="hidden" value="${sellInfo.seller!''}" id="pType"/>

        <#if order??>
            <div id="addOrder">
                <img src="/images/sanjiao_03.png" style="margin-top: 28px;margin-left: 56%;"/>
                <form class="form-horizontal" style="height: 300px;width: 48%;margin-left: 21%;border: 1px solid red;background: #fbfbfb;">
                <div class="from-group col-xs-12 col-md-12 div-margin">
                    <br>
                    <label class="control-label col-xs-3 col-md-3">需求瓶数:</label>
                    <div class="col-xs-4 col-md-4">
                        <#if order??>
                            <input class="form-control" id="num" maxlength="6" type="text" value="${order.amount?c!''}">
                        <#else>
                            <input class="form-control" id="num" maxlength="6" type="text">
                        </#if>
                    </div>
                    <label class="control-label col-xs-2 col-md-2">单价:</label>
                    <#if sellInfo.ykj!=0>
                        <#if order.price??>
                            <div class="col-xs-3 col-md-3">
                                <label class="control-label" id="signalPrice" style="color:red; ">${order.price?c!''}</label>
                                <label class="control-label">元/瓶</label>
                            </div>
                        <#else>
                            <div class="col-xs-3 col-md-3">
                                <label class="control-label" id="signalPrice" style="color:red;">${sellInfo.ykj?c!''}</label>
                                <label class="control-label">元/瓶</label>
                            </div>
                        </#if>
                    <#else>
                        <#if order.price??>
                            <div class="col-xs-3 col-md-3">
                                <label class="control-label"  id="signalPrice" style="color:red;">${order.price?c!''}</label>
                                <label class="control-label">元/瓶</label>
                            </div>
                        <#else>
                            <div class="col-xs-3 col-md-3">
                                <label class="control-label"  id="signalPrice" style="color:red;"></label>
                                <label class="control-label">元/瓶</label>
                            </div>
                        </#if>
                    </#if>
                </div>
                <#if sellInfo.deliverymode!='场地自提'>
                    <div class="col-xs-12 col-md-12 div-margin" id="fcdzt">
                        <label class="col-xs-3 col-md-3 control-label">提货时间:</label>
                        <div class="col-xs-6 col-md-6"  id="datetimepicker2">
                            <div class="input-prepend input-group">
                                <#if order.deliverytime1??>
                                    <input type="text" class="form-control col-xs-12 col-md-12" id="deliverytime" readonly value="${order.deliverytime1!''}">
                                <#else>
                                    <input type="text" class="form-control col-xs-12 col-md-12" id="deliverytime" readonly>
                                </#if>
                                <span class="add-on input-group-addon">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                    </span>
                            </div>
                        </div>
                    </div>
                <#else>
                    <div class="col-xs-12 col-md-12 div-margin" id="cdzt">
                        <label class="col-xs-3 col-md-3 control-label">提货时间:</label>
                        <div class="col-xs-4 col-md-4"  id="datetimepicker2">
                            <div class="input-prepend input-group">
                                <#if order.deliverytime1??>
                                    <input type="text" class="form-control col-xs-12 col-md-12" id="deliverytime1" readonly value="${order.deliverytime1!''}">
                                <#else>
                                    <input type="text" class="form-control col-xs-5 col-md-5" id="deliverytime1" readonly>
                                </#if>
                                <span class="add-on input-group-addon">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                    </span>
                            </div>
                        </div>
                        <label class="control-label col-xs-1 col-md-1">至</label>
                        <div class="col-xs-4 col-md-4"  id="datetimepicker1">
                            <div class="input-prepend input-group">
                                <#if order.deliverytime2??>
                                    <input type="text" class="form-control" id="deliverytime2" readonly value="${order.deliverytime2!''}">
                                <#else>
                                    <input type="text" class="form-control" id="deliverytime2" readonly>
                                </#if>
                                <span class="add-on input-group-addon">
                                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                    </span>
                            </div>
                        </div>
                    </div>
                </#if>

                <#if order.isfutures>
                    <#if order.paytype == 'PayTheWhole'>
                        <#if sellInfo.seller=='自营'>
                            <div class="col-xs-12 col-md-12 div-margin" id="payWay">
                                <label class="col-xs-3 col-md-3 control-label">付款方式:</label>
                                <div class="col-xs-3 col-md-3">
                                    <label class="checkbox-inline">
                                        <input class="radioItem" value="0" name="payWayRadio" type="radio" checked onclick="radio_click(this)">付全款
                                    </label>
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <label class="checkbox-inline">
                                        <input class="radioItem" value="1" name="payWayRadio" type="radio" onclick="radio_click(this)">付总货款10%的保证金
                                    </label>
                                </div>
                            </div>
                        </#if>
                    <#else>
                        <div class="col-xs-12 col-md-12 div-margin" id="payWay">
                            <label class="col-xs-3 col-md-3 control-label">付款方式:</label>
                            <div class="col-xs-3 col-md-3">
                                <label class="checkbox-inline">
                                    <input class="radioItem" value="0" name="payWayRadio" type="radio" onclick="radio_click(this)">付全款
                                </label>
                            </div>
                            <div class="col-xs-6 col-md-6">
                                <label class="checkbox-inline">
                                    <input id="promiseChecked" class="radioItem" value="1" name="payWayRadio" type="radio" checked onclick="radio_click(this)">付总货款10%的保证金
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-12 div-margin" id="promiseMoney">
                            <div class="col-xs-3 col-md-3" style="margin-left: 102px;top:10px;">
                                <label class="control-label" style="margin-top: 16px;width:100px;">履约保证金:</label>
                            </div>
                            <div class="col-xs-4 col-md-4"  style="top:26px;">
                                <span id="signMoney" style="height:34px;line-height:34px;font-size: 20px;color:#ff624f;font-weight: bold">${order.totalmoney*0.1}</span>
                            </div>
                            <div class="col-xs-2 col-md-2" style="top:27px;">
                                <label class="control-label">元</label>
                            </div>
                        </div>
                    </#if>
                </#if>

                <div class="col-xs-12 col-md-12 div-margin" id="totalPay">
                    <div class="col-xs-3 col-md-3" style="margin-left: 102px;top:10px;">
                        <label class="control-label" style="margin-top: 16px;">总货款:</label>
                    </div>
                    <div class="col-xs-4 col-md-4" style="top:26px;">
                        <span id="totalMoney" style="height:34px;line-height:34px;font-size: 20px; color:#ff624f;font-weight: bold">${totalmoney!''}</span>
                    </div>
                    <div class="col-xs-2 col-md-2" style="top:27px;">
                        <label class="control-label">元</label>
                    </div>
                </div>

                <div class="col-xs-12 col-md-12 div-margin" style="margin-top: 5%;">
                    <div class="col-xs-6 col-md-6 col-md-offset-3 delegate-hover">
                        <input type="button" class="btn btn-block btn-delegate" id="ordered"  style="margin-top: -11px;" value="下单">
                    </div>
                </div>
                <span id="errorInfo" class="col-xs-8 col-md-8 col-md-offset-3"></span>
            </form>
            </div>
        <#else>
            <div id="addOrder" style="display:none;">
                <img src="/images/sanjiao_03.png" style="margin-top: 28px;margin-left: 56%;"/>
                <form class="form-horizontal" style="height: 300px;width: 48%;margin-left: 21%;border: 1px solid red;background: #fbfbfb;">
                    <div class="from-group col-xs-12 col-md-12 div-margin">
                        <br>
                        <label class="control-label col-xs-3 col-md-3">需求瓶数:</label>
                        <div class="col-xs-4 col-md-4">
                            <input class="form-control" maxlength="6" id="num" type="text">
                        </div>
                        <label class="control-label col-xs-2 col-md-2">单价:</label>
                        <#if sellInfo.ykj!=0>
                            <div class="col-xs-3 col-md-3">
                                <label class="control-label" id="signalPrice">${sellInfo.ykj!''}</label>
                                <label class="control-label">元/瓶</label>
                            </div>
                        <#else>
                            <div class="col-xs-3 col-md-3">
                                <label class="control-label"  id="signalPrice"></label>
                                <label class="control-label">元/瓶</label>
                            </div>
                        </#if>
                    </div>

                    <#if sellInfo.deliverymode=='场地自提'>
                    <!--当交货方式为场地自提时 -->
                    <div class="col-xs-12 col-md-12 div-margin" id="cdzt">
                        <label class="col-xs-3 col-md-3 control-label">提货时间:</label>
                        <div class="col-xs-4 col-md-4"  id="datetimepicker2">
                            <div class="input-prepend input-group">
                                <input type="text" class="form-control col-xs-5 col-md-5" readonly  id="deliverytime1">
                                 <span class="add-on input-group-addon">
                                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                        <label class="control-label col-xs-1 col-md-1" style="padding-left: 0px;">至</label>
                        <div class="col-xs-4 col-md-4"  id="datetimepicker1">
                            <div class="input-prepend input-group">
                                <input type="text" class="form-control" readonly id="deliverytime2">
                                 <span class="add-on input-group-addon" id="smallIcon">
                                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <#else>
                        <!--非场地自提方式 -->
                        <div class="col-xs-12 col-md-12 div-margin" id="fcdzt">
                            <label class="col-xs-3 col-md-3 control-label">提货时间:</label>
                            <div class="col-xs-6 col-md-6 " id="datetimepicker2">
                                <div class="input-group date">
                                    <div class="input-prepend input-group">
                                        <input type="text" class="form-control col-xs-12 col-md-12" readonly id="deliverytime">
                                 <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>

                    <#if sellInfo.seller=='自营'>
                            <div class="col-xs-12 col-md-12 div-margin" id="payWay" style="display: none;">
                                <label class="col-xs-3 col-md-3 control-label">付款方式:</label>
                                <div class="col-xs-3 col-md-3">
                                    <label class="checkbox-inline">
                                        <input class="radioItem" value="0" name="payWayRadio" type="radio" checked onclick="radio_click(this)">付全款
                                    </label>
                                </div>
                                <div class="col-xs-6 col-md-6">
                                    <label class="checkbox-inline">
                                        <input class="radioItem" value="1" name="payWayRadio" type="radio" onclick="radio_click(this)">付10%的保证金锁货
                                    </label>
                                </div>
                            </div>
                    </#if>

                    <div class="col-xs-12 col-md-12 div-margin" id="totalPay">
                        <div class="col-xs-3 col-md-3" style="margin-left: 102px;top:10px;">
                            <label class="control-label" style="margin-top:16px;">总货款:</label>
                        </div>
                        <div class="col-xs-4 col-md-4" style="top:26px;">
                            <span id="totalMoney" style="height:34px;line-height:34px;font-size: 20px;font-weight: bold"></span>
                        </div>
                        <div class="col-xs-2 col-md-2" style="top:27px;">
                            <label class="control-label">元</label>
                        </div>
                    </div>


                    <div class="col-xs-12 col-md-12 div-margin" id="promiseMoney" style="display: none;">
                        <div class="col-xs-3 col-md-3" style="margin-left: 102px;top:10px;">
                            <label class="control-label" style="margin-top:16px;width:100px;">履约保证金:</label>
                        </div>
                        <div class="col-xs-4 col-md-4"  style="top:26px;">
                            <span id="signMoney" style="height:34px;line-height:34px;font-size: 20px;font-weight: bold"></span>
                        </div>
                        <div class="col-xs-2 col-md-2" style="top:27px;">
                            <label class="control-label">元</label>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-12 div-margin" style="margin-top: 5%;">
                        <div class="col-xs-6 col-md-6 col-md-offset-3 delegate-hover">
                            <input type="button" class="btn btn-block btn-delegate" id="ordered"  style="margin-top: -11px;" value="下单">
                        </div>
                    </div>
                    <span id="errorInfo" class="col-xs-8 col-md-8 col-md-offset-3"></span>
                </form>
            </div>
        </#if>
        <div class="col-xs-12 col-md-12" style="margin-top: 20px;">
            <label id="infoPrompt" class="col-xs-offset-3 col-md-offset-3"></label>
        </div>
    </div>
</div>
    <input type="hidden" id="availableQuantity" value="${sellInfo.availquantity}">
    <input type="hidden" id ="mallId" value="${sellInfo.id?c}"/>
    <input type="hidden" id="calMoney">
    <input type="hidden" id="allMoney" value="${totalmoney}">
    <input type="hidden" id="deliveryWay" value="${sellInfo.deliverymode}">
    <input type="hidden" id="typeObj" value="${type}">
    <div class="container">
        <div class="row clearfix">
            <div>
            <div class="modal fade" data-backdrop="static" id="modal-attendSuccess" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="top:200px;left:155px;width:300px;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <br>
                <div class="modal-body" style="text-align: center;height:120px;">
                    <div>
                        <label style="color:red;font-size: 20px;">关注成功</label>
                    </div>
                    <br>
                    <div>
                        <input type="button" class="btn btn-delegate" style="width:120px;height:30px;font-size: 18px;padding: 0px 0px;" data-dismiss="modal" value="确&nbsp;定">
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
    <div class="modal fade my_buy" id="myRelease-deleteConfirm" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 400px;top: 200px;font-size: 18px;">
            <div class="modal-content">
                <div class="modal-body" style="text-align: center;">
                    <span class="my_buyBody" style="color: red;"></span>
                </div>
                <div class="modal-footer footerType" style="text-align: center;">
                    <button type="button" class="btn btnWidthSize btnBgOk btn-sm btn-primary my_buyButton">确&nbsp;&nbsp;&nbsp;&nbsp;定</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btnWidthSize btnBgCancel btn-sm btn-default" data-dismiss="modal">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row clearfix">
                <div class="modal fade" id="modal-delegateInfo" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content" style="top:200px;height:150px;">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <br>
                            <div class="modal-body" style="text-align: center;">
                                <div>
                                    <img src="/images/gantanhao_03.png"/>
                                    <label style="color:#ff624f;">我们会尽快安排交易员与您联系，客服热线：400-123-4567</label>
                                </div>
                                <div class="col-md-offset-4" style='margin-top:20px;'>
                                    <button type="button" class="btn btn-default col-md-6 mybtn" id="delegate-close" style="background-color: #ff624f;color:white;">关闭</button>
                                </div>
                                <p></p>
                                <p></p>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
<input value="${sellInfo.deliverytime2!''}" id="time" type="hidden"/>
<input value="${type}" id="urlType" type="hidden"/>
</@block>
<@block name="script">
<script type="text/javascript" src="${static('/scripts/buy.js')}"></script>
<script type="text/javascript" src="${static('/scripts/login.js')}"></script>
</@block>
</@extend>
