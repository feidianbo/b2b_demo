<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        #loanMenuBlock span{
            width:140px;
            height:40px;
            font-size: 16px;
            line-height: 30px;
        }

        #loanIntroductionWord span{
            margin-left: 30px;
        }

        #financingMenuBlock{
            margin-left: 40px;
        }

        #financingMenuBlock span{
            width:140px;
            height:40px;
            font-size: 16px;
            line-height: 30px;
        }

        #financingPicDiv{
            margin-left: 30px;
            width:750px;
            height:450px;
            border: solid 1px #cbcbcb;
        }

        #financingPicDiv img{
            padding-left: 60px;
            padding-top: 20px;
        }

        #financingIntroductionWord{
            margin-left: 30px;
            margin-top: 40px;
        }

        #financingIntroductionWord span{
            margin-left: 30px;
        }

        #tradeExplain{
            display: none;
        }

        #tradeExplainWord span{
            margin-left: 30px;
        }

        .themeLine{
            width: 100%;
            height:1px;
            background-color: #cccccc;
            margin-top: 10px;
        }

        .tradeExplainThemeDiv{
            margin-left: 90px;
        }

        #tradeExplainWord{
            margin-top: 25px;
            margin-left: 90px;
        }

        .tradeExplainTheme{
            font-size: 23px;
            color: #006dcc;
        }

        .applyWord{
            margin-top: 50px;
        }

        #wantFinancing{
            display: none;
        }

        #contactTable{
            margin-top: 30px;
            margin-left: 70px;
        }

        #picWordDiv{
            margin-left:20px;
        }

        #financingMenu1{
            background-color:#317EE6;
            color:#ffffff;
        }

        #picWordDiv span{
            line-height: 30px;
        }
        .glyphicon glyphicon-chevron-right{
            font-size: 16px;
        }

    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                <div class="col-xs-2 col-md-2 column padding_space ">
                        <div class="btn-group btn-group-md  btn-group-vertical" id="financingMenuBlock">
                            <span class="btn btn-default financingMenu" onclick="changeFinancingMenu(1)" id="financingMenu1">快融通简介</span>
                            <span class="btn btn-default financingMenu" onclick="changeFinancingMenu(2)" id="financingMenu2">交易说明</span>
                            <span class="btn btn-default financingMenu" id="financingMenu3">我要融资</span>
                        </div>
                </div>

                <div class="col-xs-9 col-md-9 column" id="picWordDiv">
                    <div id="financingIntroduction">
                        <div id="financingPicDiv">
                            <img src="/images/newFinancing.png">
                        </div>
                        <div id="financingIntroductionWord">
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;什么是“快融通”</h4>
                            <span>“快融通”是为了满足广大客户在美酒购销中提高资金使用效率的需求，通过XX网平台所提供的金</span>
                            <span>融工具及手段，保障客户资金回流稳定、快速。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;“快融通”解决了什么问题</h4>
                            <span>“快融通”运用XX网的金融平台优势，为美酒行业上、下游群体客户提供高效的资金回笼方案。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;“快融通”有什么优点</h4>
                            <span>“快融通”针对上游煤矿和贸易商的资金快速回笼需求及下游煤炭消费企业的延期付款需求提供全面</span>
                            <span>的快捷金融产品工具，渠道资金雄厚，操作流程简便，非常适合解决客户交易账期资金占压问题。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;为什么选择“快融通”</h4>
                            <span>“快融通”拥有可靠的资信和雄厚稳定的资金渠道。“快融通”是由国内领先的煤炭供应链管理平台易</span>
                            <span>煤网主推的供应链金融服务产品，一经问世便得到广大客户的充分认可，并获得众多大型金融机构</span>
                            <span>的关注与渠道支持。目前煤炭行业处于买方市场，导致中上游煤炭供应型企业销售回款滞怠，资金</span>
                            <span>占压严重，同时上游中小企业供应商很难在传统渠道申请较大的融资规模。而“快融通”的推出正是</span>
                            <span>为了解决上述问题，为广大的煤炭供应链环节中上游企业提供成本低廉、操作灵活的贸易融资服</span>
                            <span>务。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp; “快融通”产品类型</h4>
                            <span>1.应收账款买断：是“快融通”基于客户与下游煤炭消费企业签订的商务合同所产生的应收账款，在</span>
                            <span>客户转让其应收账款给“快融通”指定的保理商的前提下，向其提供的一种融资服务。</span><br>
                            <span>2.应收账款质押：是指企业将其合法拥有的应收账款收款权通过“快融通”产品平台质押给定向金融</span>
                            <span>机构从而获得融资的方式。</span>
                        </div>
                    </div>

                    <div id="tradeExplain">
                        <div class="tradeExplainThemeDiv">
                            <div class="tradeExplainTheme">交易说明</div>
                            <div class="themeLine"></div>
                        </div>
                        <div id="tradeExplainWord">
                            <div>
                                <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;申请资质：</h4>
                                <span>1.企业成立24个月以上</span><br>
                                <span>2.具备相关行业资质</span><br>
                                <span>3.记录良好，在金融机构无不良信用记录</span><br>
                                <span>4.下游企业为国企、央企、上市公司</span><br>
                                <span>5.与下游企业合作期限在2年以上</span><br>
                            </div>

                            <div class="applyWord">
                                <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;材料准备：</h4>
                                <span>1.最新年检的营业执照正副本（复印件）</span><br>
                                <span>2.最新年检的组织机构代码证正副本（复印件）</span><br>
                                <span>3.国、地税务登记证正副本（复印件）</span><br>
                                <span>4.机构信用代码证（复印件）</span><br>
                                <span>5.贷款卡（复印件）</span><br>
                                <span>6.开户许可证（复印件）</span><br>
                                <span>7.企业具备相关行业资质证书（复印件）</span><br>
                                <span>8.公司章程及验资报告</span><br>
                                <span>9.至少近两年的财务报告以及近期报表</span><br>
                            </div>

                            <div class="applyWord">
                                <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;业务期限：</h4>
                                <span>依据客户应收账款账期而定。</span>
                            </div>
                        </div>
                    </div>

                    <div id="wantFinancing">
                        <div class="tradeExplainThemeDiv">
                            <div class="tradeExplainTheme">联系方式</div>
                            <div class="themeLine"></div>
                        </div>
                        <div id="contactTable">
                            <div class="col-xs-12 col-md-12">
                                <form class="form-horizontal" action="/finance/saveContact" role="form" id="contact_form" method="post">
                                    <div style="display: none">
                                        <input type="text" id="sessionPhone" value='${(session().user.securephone)!''}'>
                                        <label for="type" >类型:</label>
                                        <input type="text" id="type" name="type" value="快融通">
                                    </div>

                                    <div class="form-group">
                                        <label for="companyname" class="col-xs-2 col-md-2 control-label">公司名称:</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" maxlength="30" id="companyname" name="companyname" placeholder="请输入公司名称">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="address" class="col-xs-2 control-label">公司地址:</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" maxlength="50" id="address" name="address" placeholder="请输入公司地址">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="businessarea" class="col-xs-2 control-label">业务区域:</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" maxlength="50" id="businessarea" name="businessarea" placeholder="请输入业务区域">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="amountnum" class="col-xs-2 control-label">融资金额:</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" maxlength="6" id="amountnum" name="amountnum" placeholder="请输入融资金额">
                                        </div>
                                        <div class="col-xs-2" style="line-height: 34px;height:34px;">万元</div>
                                        <span id="amountnumError"></span>
                                    </div>

                                    <div class="form-group">
                                        <label for="contact" class="col-xs-2 control-label">联系人:</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" maxlength="20" id="contact" name="contact" placeholder="请输入联系人">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="phone" class="col-xs-2 control-label">联系电话:</label>
                                        <div class="col-xs-4">
                                            <input type="text" class="form-control" maxlength="20" id="phone" name="phone" placeholder="请输入联系电话">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class=" col-xs-offset-2  col-xs-4">
                                            <button type="button" class="btn btn-primary btn-block" id="create-btn">确认提交</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

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
                                    <label style="color:red;font-size: 20px;">您的申请已经提交,请等待专线人员与您联系。</label>
                                </div>
                                <br>
                                <div>
                                    <input type="button" class="btn btn-delegate" style="width:120px;height:30px;font-size: 18px;padding: 0px 0px;" id="closeBtn" value="关闭">
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

    </@block>

    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/finance.js')}"></script>
    </@block>
</@extend>