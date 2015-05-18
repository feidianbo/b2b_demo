<@extend name="layout">
    <@block name="cs">
        <style type="text/css">
            dl dd{
                text-indent: 2em;
                line-height: 36px;
            }
            dl{
                padding-left: 50px;
                padding-right: 50px;
            }
            dl dt{
                margin-left: -15px;
                line-height: 36px;
            }
            dl img{
                margin-top: 20px;
                margin-bottom: 30px;
            }
        </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <dl>
                        <#--<div id="first">-->
                            <#--<dt><h4>一. 如何买煤</h4></dt>-->
                            <#--<dt >1. XX商城交易</dt>-->
                            <#--<dt>⑴ 查询产品：登录“XX网”，点击“XX商城”，查阅产品的详细信息；</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img2.png"></dd>-->
                            <#--<dt>⑵选择下单：选择合适的产品，并填写采购的数量、提货时间、地点等下单信息，提交订单；</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img3.png"></dd>-->
                            <#--<dt>⑶签订合同：与“XX网”平台签订采购电子合同；</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img4.png"></dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img5.png"></dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img8.png"></dd>-->
                            <#--<dt>⑷支付货款</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img9.png"></dd>-->
                            <#--<dt>⑸交易成功：线下验货运输，完成票据处理，结束本次采购。</dt>-->

                            <#--<dt style="margin-top: 30px;">2. 委托人工</dt>-->
                            <#--<dd class="marginLeft">-->
                                <#--客户可通过委托“XX网”客服人员，实现快速优质的匹配交易。-->
                            <#--</dd>-->
                            <#--<dd class="marginLeft">具体操作如下：</dd>-->
                            <#--<dt>⑴ 网页发布渠道</dt>-->
                            <#--<dd>点击“XX网”网页【委托人工买卖】按钮，在弹出的文本框内直接填写您的真实需求,包含品类、热值等指标，点击【确定】便可完成。</dd>-->
                            <#--<dd><img src="/images/footer/startOff/start0ff_img3.png"></dd>-->
                            <#--<dd><img src="/images/footer/startOff/start0ff_img4.png"></dd>-->
                            <#--&lt;#&ndash;<dd style="margin-left: -49px;"><img src="/images/footer/startOff/start0ff_img6.png"></dd>&ndash;&gt;-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img1.png"></dd>-->
                            <#--<dt>⑵ QQ及客服电话渠道</dt>-->
                            <#--<dd>直接联系网站QQ客服或拨打客服电话：400-123-4567，提供您的采购需求。</dd>-->

                            <#--<dt style="margin-top: 30px;">3. XX直通车交易</dt>-->
                            <#--<dt>⑴发布采购：登录“XX网”，发布采购需求；</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img10.png"></dd>-->
                            <#--<dt>⑵确认需求：您将收到我们交易中心客服电话，确认您的采购信息，为您创建标准订单；</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img12.png"></dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img13.png"></dd>-->
                            <#--<dt>⑶选择报价：点击页面顶部【个人中心】按钮进入用户管理系统，点击【我的发布】；在报价截止日期过后可查看供应客户即时为您匹配的现货资源报价，选择您需要的报价；</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img14.png"></dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img15.png"></dd>-->
                            <#--<dt>⑷提交订单：勾选“报价”（可选择一个或者多个报价）完成，点击【确认报价】按钮下单完成；</dt>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img16.png"></dd>-->
                            <#--<dt>⑸转交线下：指定交易员会与您和供应方取得联系；交易转交至线下完成；</dt>-->
                            <#--<dt>⑹支付货款：根据显示的银行信息，确认无误后，完成线下付款；</dt>-->
                            <#--<dt>⑺交易成功：线下验货运输，完成票据处理，结束本次采购。</dt>-->
                        <#--</div>-->

                        <#--<div id="sec">-->
                            <#--<dt style="margin-top: 30px;">二.如何卖煤</dt>-->

                            <#--<dt style="margin-top: 10px;">1. XX直通车交易</dt>-->
                            <#--<dd>交易流程：发布供应→确认供应→转入线下→查看信息→交易成功。</dd>-->
                            <#--<dd>⑴发布供应：登录“XX网”的“我要卖”，点击“发布供应”，发布供应信息；</dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img17.png"></dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img18.png"></dd>-->
                            <#--<dd>⑵确认供应：您将收到我们交易中心客服电话，确认您的供应信息，进行审核；</dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img19.png"></dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img20.png"></dd>-->
                            <#--<dd>⑶转入线下：审核通过的供应信息将转交至线下，由交易员与您和采购方联系；</dd>-->
                            <#--<dd>⑷查看信息：点击页面顶部【个人中心】按钮进入用户管理系统，点击【我的供应】；查看发布的供应信息；点击【卖货订单】；查看采购客户向您提交的采购数量；</dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img21.png"></dd>-->
                            <#--<dd><img style="margin-left: -20px;" src="/images/footer/aboutContract/aboutContract_img22.png"></dd>-->

                            <#--<dt style="margin-top: 30px;">2. 委托人工</dt>-->
                            <#--<dd class="marginLeft">客户可通过委托“XX网”客服人员，实现快速优质的匹配交易。</dd>-->
                            <#--<dd class="marginLeft">具体操作如下：</dd>-->
                            <#--<dt>⑴ 网页发布渠道</dt>-->
                            <#--<dd><img src="/images/footer/startOff/start0ff_img3.png"></dd>-->
                            <#--&lt;#&ndash;<dd><img src="/images/footer/startOff/start0ff_img4.png"></dd>&ndash;&gt;-->
                            <#--<dd style="margin-left: -49px;"><img src="/images/footer/startOff/start0ff_img6.png"></dd>-->
                            <#--<dd><img src="/images/footer/aboutContract/aboutContract_img1.png"></dd>-->
                            <#--<dt>⑵ QQ及客服电话渠道</dt>-->
                            <#--<dd>直接联系网站QQ客服或拨打客服电话：400-123-4567，提供您的采购需求。</dd>-->
                        <#--</div>-->

                        <#--<div id="third">-->
                        <div id="first">
                            <dt><h4>一. 如何付款</h4></dt>
                            <dd>付款方式分电汇和非电汇两种方式。</dd>
                            <dd>电汇支付方式：需要您在网上直接进行银行转账或者到银行营业部办理转账，然后在“XX网”上传您的支付凭证，收到凭证后，我们会查收账款，并我们会及时通知您查收结果。</dd>
                            <dd>非电汇方式包括承兑汇票、信用证等。需要贵公司人员携带票据交付XX网财务部，财务部会给您开接收承兑汇票、信用证的凭证，然后您将支付凭证上传到“XX网”，等待XX网财务人员审核结果的通知。</dd>
                        </div>

                        <#--<div id="four">-->
                        <div id="sec">
                            <dt><h4>二. 交付流程</h4></dt>
                            <dd>
                                XX商城产品交割流程：客户在网上下单并支付完成后，交易员及时协助客户办理货物交割单、协调物流运输、协调货物装载等交割流程。
                            </dd>
                            <dd>
                                线下撮合的产品交割流程：交割流程严格按照双方签订的《煤炭购销合同》中的货物交割流程的交割条约执行。
                            </dd>
                        </div>
                    </dl>
                </div>
                <input type="hidden" id="scrollTop" name="scrollTop" value="${pos!'first'}">
            </div>
        </div>
    </div>
    </@block>
    <@block name="script">
    <script type="text/javascript" src="${static('/scripts/footerScroll.js')}"></script>
    </@block>
</@extend>