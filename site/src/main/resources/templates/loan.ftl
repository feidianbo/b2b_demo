<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        #loanMenuBlock{
            margin-left: 40px;
        }

        #loanMenuBlock span{
            width:140px;
            height:40px;
            font-size: 16px;
            line-height: 30px;
        }

        #loadPicDiv{
            margin-left: 30px;
            width:750px;
            height:450px;
            border: solid 1px #cbcbcb;
        }

        #loadPicDiv img{
            padding-left: 60px;
            padding-top: 35px;
        }

        #loanIntroductionWord{
            margin-left: 30px;
            margin-top: 40px;
        }

        #loanIntroductionWord span{
            margin-left: 30px;
        }

        #financingMenuBlock span{
            width:140px;
            height:40px;
            font-size: 16px;
            line-height: 30px;
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

        #loadMenu1{
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
                        <div class="btn-group btn-group-md  btn-group-vertical" id="loanMenuBlock">
                            <span class="btn btn-default loanMenu" onclick="changeLoadMenu(1)" id="loadMenu1">煤易贷简介</span>
                            <span class="btn btn-default loanMenu" onclick="changeLoadMenu(2)" id="loadMenu2">交易说明</span>
                            <span class="btn btn-default loanMenu" id="loadMenu3">我要融资</span>
                        </div>
                </div>
                <div class="col-xs-9 col-md-9 column" id="picWordDiv">
                    <div id="loanIntroduction">
                        <div id="loadPicDiv">
                            <img src="/images/newLoan.png">
                        </div>
                        <div id="loanIntroductionWord">
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;什么是“煤易贷”</h4>
                            <span>“煤易贷”是XX网平台针对广大煤炭行业中上游企业货权融资而推出的高效金融服务产品。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;“煤易贷”解决了什么问题</h4>
                            <span>“煤易贷”利用XX网平台优势和机构资源，灵活运用货物质押和仓单质押等多种手段，为广大客户</span>
                            <span>提供高效的短期资金周转方案。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;“煤易贷”有什么优点</h4>
                            <span>"煤易贷”针对广大中小煤炭企业量身订做，手续简单，放款迅速，非常适合解决广大中小煤炭企业</span>
                            <span>的短期资金周转问题。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;为什么选择“煤易贷”</h4>
                            <span>“煤易贷”拥有可靠的第三方监管渠道和及时的资金融通渠道。“煤易贷”背靠XX网煤炭供应链电商</span>
                            <span>平台，形成了多渠道的资金来源和全方位的第三方监管体系，充分具备及时满足广大中上游煤炭企</span>
                            <span>业因销售不畅或急需短期资金周转而面临的资金需求。</span>
                            <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;“煤易贷”产品说明</h4>
                            <span>“煤易贷”业务是指XX网根据客户的申请，通过质押客户合法拥有的煤炭现货货权而提供快速资金</span>
                            <span>融通的平台业务，XX网按照市场价格估值后，委托第三方仓储机构对货物在XX网确定的最低价</span>
                            <span>值范围内进行监管。</span>
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
                                <span>1.企业资质符合XX网要求</span><br>
                                <span>2.记录良好，在金融机构无不良信用记录</span><br>
                                <span>3.货物存放在XX网认可的港口或监管库</span><br>
                            </div>

                            <div class="applyWord">
                                <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;材料准备：</h4>
                                <span>1.最新年检的营业执照正副本（复印件）</span><br>
                                <span>2.最新年检的组织机构代码证正副本（复印件）</span><br>
                                <span>3.国、地税务登记证正副本（复印件）</span><br>
                                <span>4.机构信用代码证（复印件）</span><br>
                                <span>5.开户许可证（复印件）</span><br>
                                <span>6.企业具备相关行业资质证书（复印件）</span><br>
                                <span>7.煤易贷业务所需的其他企业资质</span><br>
                            </div>

                            <div class="applyWord">
                                <h4><div class="glyphicon glyphicon-chevron-right"></div>&nbsp;&nbsp;&nbsp;业务期限：</h4>
                                <span>依据客户实际需求协商而定。</span>
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
                                        <input type="text" id="type" name="type" value="煤易贷">
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