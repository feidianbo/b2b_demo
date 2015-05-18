<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .announceBtn{
            margin-top:-30px;
            background-color: #317ee6;
            float:right;
            width:150px;
        }
        .attr-color{
            color:#317ee6;
        }
        color{
            color:#317ee6;
        }
        .div-margin{
            margin-bottom: 2%;
        }
        .cols, .col-md-2, .col-md-4, .col-md-6, .col-md-8, .col-md-12 {
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
        .mybtn:hover{
            background-color: #ea3c27;
        }
        .wordColor{color: #317EE6;}
        .priceColor{color: red;}
        .table > tbody > tr > td{padding-right: 0px;padding-left: 0px;}

    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="body-head">
                <div style="border-left:4px solid red;"><h5>&nbsp;&nbsp;详细需求信息</h5></div>
                <a><button type="button" id="sellInfo_supply" class="btn btn-primary announceBtn" value="">发布供应></button></a>
                <hr>
            </div>
            <h5 style="color:red;margin-bottom: 2%;">请仔细核对以下需求信息:</h5>

            <table class="table table-bordered">
                <tbody>
                <tr><td style="text-align: left; background: #65b3e1; color: #ffffff; padding-left: 10px;"  colspan="6">美酒指标</td></tr>

                <tr>
                    <td>产品编号</td>
                    <td class=" wordColor" colspan="5">${demand.demandcode!''}&nbsp;&nbsp;<span style="color: #000000">(已有 <span style="color: #ff0000;">${demand.viewtimes}</span> 人次浏览)</span></span></td>
                </tr>

                <tr>
                    <td>美酒种类</td>
                    <td class="wordColor">${demand.coaltype!''}</td>
                    <td>酒精度数</td>
                    <td class="wordColor">${demand.NCV?c!''}</td>
                    <td>酒类指标1</td>
                    <#if demand.ADS != 0>
                        <td class="wordColor">${demand.ADS!''}%</td>
                    <#else>
                        <td class="wordColor">--</td>
                    </#if>
                </tr>

                <tr>
                    <td>含糖量</td>
                    <td class="wordColor">${demand.RS!''}%</td>
                    <td>酒类指标2</td>
                    <td class="wordColor">${demand.ADV!''}%</td>
                    <td>酒类指标3</td>
                    <#if demand.RV != 0>
                        <td class="wordColor">${demand.RV!''}%</td>
                    <#else>
                        <td class="wordColor">--</td>
                    </#if>
                </tr>

                <tr>
                    <td>酒类指标4</td>
                    <td class="wordColor">${demand.TM!''}%</td>
                    <td>酒类指标5</td>
                    <#if demand.IM != 0>
                        <td class="wordColor">${demand.IM!''}%</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                    <td class="">酒类指标6</td>
                    <#if demand.ASH != 0>
                        <td class="wordColor">${demand.ASH!''}%</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                </tr>

                <tr>
                    <td>酒类指标7</td>
                    <#if demand.AFT != 0>
                        <td class="wordColor">${demand.AFT?c!''}℃</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                    <td>酒类指标8</td>
                    <#if demand.HGI != 0>
                        <td class="wordColor">${demand.HGI?c!''}</td>
                    <#else >
                        <td class="wordColor">--</td>
                    </#if>
                    <td><td></td></td>
                </tr>

                <tr><td style="text-align: left; background: #65b3e1; color: #ffffff;padding-left: 10px;" colspan="6">需求基本信息</td></tr>
                <tr>
                    <td>提货地点</td>
                    <#if demand.deliveryplace == "其它">
                        <td  class="wordColor">${demand.deliveryprovince!''}${demand.otherplace!''}</td>
                    <#else>
                        <td class="wordColor">${demand.deliveryprovince!''}${demand.deliveryplace!''}</td>
                    </#if>
                    <td>提货方式</td>
                    <td class="wordColor">${demand.deliverymode!''}</td>
                    <td style="vertical-align:middle;" rowspan="2">需求数量</td>
                    <td style="vertical-align:middle;" rowspan="2" class="priceColor">${demand.demandamount}瓶</td>
                </tr>

                <tr>
                <td>提货时间</td>
                <#if demand.deliverymode == "场地自提">
                    <td colspan="3" class="priceColor">${demand.deliverydatestart!''} ~ ${demand.deliverydateend!''}</td>
                <#else>
                    <td colspan="3" class="priceColor">${demand.deliverydate!''}</td>
                </#if>
                </tr>

                <tr>
                    <td>检验机构</td>
                    <#if (demand.inspectionagency) == "其它">
                        <td colspan="3" class=" wordColor">${demand.otherorg!''}</td>
                    <#else>
                        <td colspan="3" class=" wordColor">${demand.inspectionagency!''}</td>
                    </#if>
                    <td style="vertical-align:middle;" rowspan="2">报价截止日</td>
                    <td style="vertical-align:middle;" rowspan="2" class="priceColor">${demand.quoteenddate!''}</td>
                </tr>

                <tr>
                    <td>交易员</td>
                    <td class="wordColor">${demand.tradername!''}</td>
                    <td>联系电话</td>
                    <td class="wordColor">${demand.traderphone!''}</td>
                </tr>

                <#if demand.releasecomment ?length gt 0>
                <tr><td style="text-align: left; background: #65b3e1;padding-left: 10px;"  colspan="6">备注</td></tr>
                <tr>
                    <td style="text-align: left; text-indent: 2em; word-break: break-all;" colspan="6">${demand.releasecomment}</td>
                </tr>
                </#if>
                </tbody>
            </table>

            <#if demand.tradestatus != "报价结束">
                <div class="row" style="margin-top: 30px;">
                    <div class="col-xs-4 col-xs-offset-2">
                        <input type="button" class="btn btn-primary btn-block" id="btnAttend" value="关&nbsp;&nbsp;注">
                    </div>
                    <div class="col-xs-4">
                        <input type="button" class="btn btn-block btn-delegate" id="btnShow" value="报&nbsp;&nbsp;价">
                        <div id="isShowDialog" style="display: none;">
                            <img src="/images/sanjiao_03.png" style="margin-top: 8px;margin-left: 48%;"/>

                            <form class="form-horizontal" style="height: auto;border: 1px solid red;background: #fbfbfb;">
                                <div style="margin-left: -10px;">
                                <div class="form-group" style="margin-top: 15px;">
                                    <label class="control-label col-xs-4">申供瓶数:</label>
                                    <div class="col-xs-6">
                                        <#if quote.supplyton?exists>
                                            <#if reqsource == "myquote">
                                                <input class="form-control" readonly="readonly" id="supplyton" value="${quote.supplyton?c!''}" maxlength="6" type="text" >
                                            <#else>
                                                <input class="form-control" id="supplyton" value="${quote.supplyton?c!''}" maxlength="6" type="text" >
                                            </#if>
                                        <#else>
                                            <input class="form-control" id="supplyton" maxlength="6" type="text" >
                                        </#if>
                                    </div>
                                    <div class="col-xs-2" style="padding-left: 0px;">
                                        <label class="control-label">瓶</label>
                                    </div>
                                </div>
                                <span style="color: red; margin-bottom: 15px; text-align: left;" id="errors" class="control-label col-xs-offset-4 col-xs-8"></span>

                                <div class="form-group">
                                    <label class="control-label col-xs-4">报价:</label>
                                    <div class="col-xs-6">
                                        <#if quote.quote?exists>
                                            <#if reqsource == "myquote">
                                                <input class="form-control" readonly="readonly"  id="quote" value="${quote.quote?c!''}" maxlength="6" type="text" >
                                            <#else>
                                                <input class="form-control" id="quote" value="${quote.quote?c!''}" maxlength="6" type="text" >
                                            </#if>
                                        <#else>
                                            <input class="form-control" id="quote" maxlength="6" type="text" >
                                        </#if>
                                    </div>
                                    <div class="col-xs-2" style="padding-left: 0px;">
                                        <label class="control-label">元/瓶</label>
                                    </div>
                                </div>
                                <span style="color: red;margin-bottom: 15px;text-align: left;" id="errorsForQuote" class="control-label col-xs-offset-4 col-xs-8"></span>

                                <#if reqsource != "myquote">
                                    <div class="form-group">
                                        <div class="col-xs-offset-2 col-xs-8">
                                            <input class="btn btn-block btn-delegate" id="btnOK" type="button"  value="确定">
                                        </div>
                                    </div>
                                </#if>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </#if>
        </div>
    </div>

    <input type="hidden" value="${demand.id?c!''}" id="idText"/>
    <input type="hidden" id="demandcode" name="demandcode" value="${demand.id?c!''}">
    <input type="hidden" id="demandCount" value="${demand.demandamount?c!''}">
    <#if modifyPrice?exists>
        <input type="hidden" id="modifyPrice" value="${modifyPrice!''}">
    <#else>
        <input type="hidden" id="modifyPrice" value="0">
    </#if>

    <div class="container">
        <div class="row clearfix">
            <div class="modal fade dialog_Info" data-backdrop="static" id="modal-attendSuccess" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div id="dialog_InfoContent" class="modal-content" style="top:200px;left:155px;width:300px;">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <br>
                        <div class="modal-body" style="text-align: center;height:120px;">
                            <div>
                                <label style="color:red;font-size: 20px;" class="dialog_InfoBody"></label>
                            </div>
                            <br>
                            <div>
                                <input type="button" class="btn btn-delegate dialog_InfoBtn" style="width:120px;height:30px;font-size: 18px;padding: 0px 0px;" data-dismiss="modal" value="确&nbsp;定">
                            </div>
                            <p></p>
                            <p></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal fade dialog_companyInfo" data-backdrop="static" id="verify-companyInfo" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content" style="top:200px;height:150px;">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <br>
                        <div class="modal-body" style="text-align: center;">
                            <div>
                                <img src="/images/gantanhao_03.png"/>
                                <label style="color:#ff624f;" class="dialog_body"></label>
                            </div>
                            <div class="col-xs-offset-4 col-md-offset-4" style='margin-top:20px;'>
                                <button type="button" class="btn btn-default col-xs-6 col-md-6 mybtn dialog_btn"  style="background-color: #ff624f;color:white;">完善信息</button>
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
    <script type="text/javascript" src="${static('/scripts/sellInfo.js')}"></script>
    </@block>
</@extend>
