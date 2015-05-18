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
            border: 1px solid #eeeeee;
        }
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <dl>
                        <div id="first">
                            <dt><h4>1. XX直通车</h4></dt>
                            <dd>
                                “XX直通车”是XX网推出的能够快速、精确进行产品搜索的便捷通道，通过将产品根据其品类、生产地、销售区域、仓库区域、产品各类指标等进行细分，缩小产品的搜索范围，客户根据所需产品的重点要求，可以快速锁定产品。
                            </dd>
                        </div>

                        <div id="sec">
                            <dt><h4>2. XX商城</h4></dt>
                            <dd>“XX商城”是XX网的核心交易平台，所售的煤炭全部经过“XX网”平台严格认证。能够给客户提供产品质量、资金等全方位的安全保障。</dd>
                            <dd>运营逻辑：为供给方提供“保值代销”服务，为需求方提供“阳光透明，保质保量”的煤炭供给服务</dd>
                            <dd>商城价值：</dd>
                            <dd><img src="/images/footer/startOff/start0ff_img1.png"></dd>
                        </div>

                        <div id="third">
                            <dt><h4>3. 个人中心</h4></dt>
                            <dd><img src="/images/footer/startOff/start0ff_img2.png"></dd>
                        </div>

                        <div id="four">
                            <dt><h4>4. 委托人工</h4></dt>
                            <dd>
                                (1)&nbsp;&nbsp;&nbsp;&nbsp;网页发布采购需求渠道
                            </dd>
                            <dd>
                                点击“XX网”网页【委托人工买卖】按钮，在弹出的文本框内直接填写您的真实需求,包含品类、热值等指标，点击【确定】便可完成；或者在“个人中心”中，发布采购／供应信息即可。
                            </dd>
                            <dd>
                                <img src="/images/footer/startOff/start0ff_img3.png">
                            </dd>
                            <dd><img src="/images/footer/startOff/start0ff_img4.png"></dd>
                            <dd><img  src="/images/footer/startOff/start0ff_img6.png"></dd>
                            <dd>
                                (2)&nbsp;&nbsp;&nbsp;&nbsp;QQ及客服电话渠道：
                            </dd>
                            <dd>
                                您也可以直接联系网站QQ客服或拨打客服电话：   400-123-4567，为您提交采购／供应需求。
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