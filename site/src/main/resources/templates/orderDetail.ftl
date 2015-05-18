<@extend name="layout">
    <@block name="body">
    <div class="clear-level">
        <div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">产品详细信息</h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">请仔细核对信息:</label>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">主要信息如下:</label>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;">
                            <label class="col-sm-2 control-label">酒精度数:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.lowcalorificvalue!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">酒类指标1:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.airdrybasissulfur!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">含糖量:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.receivebasissulfur!''}</label>
                            </div>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;">
                            <label class="col-sm-2 control-label">酒类指标4:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.totalmoisture!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">酒类指标5:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.innermoisture!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">酒类指标2:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.airdrybasisvolatile!''}</label>
                            </div>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;">
                            <label class="col-sm-2 control-label">酒类指标3:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.receivebasisvolatile!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">酒类指标7:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.ashfusionpoint!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">颗粒度:</label>
                            <div class="col-sm-2">
                                <label class="control-label" >${demand.ash!''}</label>
                            </div>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;">
                            <label class="col-sm-2 control-label">酒类指标8:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.hashgrind!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">提货地点:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.deliveryplace!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">需求数量:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.demandamount!''}</label>
                            </div>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;">
                            <label class="col-sm-2 control-label">提货时间:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.deliverydate!''}(土2天)</label>
                            </div>
                            <label class="col-sm-2 control-label">提货时间:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.deliverydate!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">提货方式:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.deliverymode!''}</label>
                            </div>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;">
                            <label class="col-sm-2 control-label">单价:</label>
                            <div class="col-sm-2">
                                <label class="control-label">${demand.unitprice!''}</label>
                            </div>
                            <label class="col-sm-2 control-label">检验机构:</label>
                            <div class="col-sm-4">
                                <label class="control-label">${demand.inspectionagency!''}</label>
                            </div>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;margin-top: 20px;">
                            <label class="col-sm-offset-3 col-sm-2 control-label">申供瓶数:</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control"  id="supplyton" name="supplyton">
                            </div>
                            <label class="col-sm-1 control-label">定价:</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="quote" name="quote">
                            </div>
                            <div class="col-sm-2">
                                <button type="button" class="btn btn-primary" id="btnOK">确定</button>
                            </div>
                        </div>

                        <div class="form-group"  style="margin-left: 2%;margin-top: 20px;">
                            <div class="col-sm-offset-5 col-sm-7">
                                <span id="errors" style="color: #ff0000;"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">请核对以上供应信息</div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-7 column">
                <div class="modal fade" id="modal-confirm" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin: 0px auto;width: 600px;height: 250px;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title" id="myModalLabel">
                                    温馨提示:
                                </h4>
                            </div>
                            <div class="modal-body">
                                <span>您的报价已成功提交!若您的报价有优势，我们会及时与您联系。</span>
                                <br>
                                <span>本条求购负责专员(张三:021-88888888,可直接电话报价)</span>
                            </div>
                            <div class="modal-footer">
                                <button id="close_modal" type="button" class="btn btn-default">关闭</button>
                            </div>
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