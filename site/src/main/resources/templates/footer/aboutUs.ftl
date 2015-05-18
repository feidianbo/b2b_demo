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
nd:#28BD19; height:17px; line-height:17px; color:#fff; padding:0 5px; margin-top:4px; display:inline-block; float:right;}
    </style>
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <dl>
                        <div id="first">
                            <dt><h4> 1.关于我们</h4></dt>
                            <dd>
                                XX网（<a href="http://www.xxx.com/" target="_blank">www.xxx.com</a>）由和略电子商务（上海）有限公司基于全球资源和市场，建立的以煤炭交易为主的大宗商品电子交易平台，是国内较早借助互联网思维、金融思维探索煤炭全产业链优化升级的企业。
                            </dd>
                            <dd>XX平台构建了四维（“XX商城、交易撮合、团购业务、金融业务”）于一体的运营模式，通过联合金融、监管、检验、港口仓储等相关利益方，实现产业链上下游企业的高效无缝对接，充分满足其煤炭交易、物流仓储、支付、
                                结算和融资等全方位的需求 ，使交易更高效、更透明。</dd>
                            <dd>我们坚信：一个朝着自己目标坚定前行的人，全世界都会为之让路！ </dd>
                            <dt><h4>2. XX优势</h4></dt>
                            <dd>
                                “XX商城”是XX网的核心交易平台，所售的煤炭全部经过“XX网”平台严格认证。
                            </dd>
                            <dd>
                                运营逻辑：为供给方提供“保值代销”服务，为需求方提供“阳光透明，保质保量”的煤炭供给服务。
                            </dd>
                            <dd><img src="/images/footer/aboutUs/ys.png"></dd>
                        </div>

                        <div id="sec">
                            <dt><h4>3. 资金保证</h4></dt>
                            <dd>
                                XX网优势XX网与多家金融机构进行合作，形成战略合作关系，同时，市值百亿上市公司也是XX的坚强后盾，为XX网提供充足的资金保证，同时，通过科学的风控体系，资金安全也能得到保障。
                            </dd>
                        </div>

                        <div id="third">
                            <dt><h4>4. 专业化团队</h4></dt>
                            <dd>
                                我们认为，人才才是企业可持续发展的核心资本，坚持通过“行业性整合人才+国际性整合人才+内部培养人才”来构建XX的人才版图。
                            </dd>
                            <dd>
                                一批拥有梦想，充满激情的高素质人才相聚XX网，他们100%拥有高等院校本科、研究生或者博士学历。
                            </dd>
                            <dd>
                                他们中有曾在国内外顶尖互联网公司工作的精英，他们精于互联网技术；他们中有在国内优秀大宗商品供应链管理公司从业多年的业务专家，他们精于业务。
                            </dd>
                            <dd>
                                “年轻、梦想、激情、努力、实干”是他们的特征。
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