<@extend name="layout">
    <@block name="cs">
    <style type="text/css" xmlns="http://www.w3.org/1999/html">
        .nllo{line-height:1.5;}
    </style>
</@block>
<@block name="body">
    <div class="clear-level">
        <div class="container">
            <div class="col-xs-4 col-sm-4">
            <img src="/images/register.png">
        </div>
        <div class="col-xs-8 col-sm-8" style="padding-left: 60px;margin-top: -10px;">
            <h4 style="margin-left:3%;color:#317ee6;">欢迎注册XX网!</h4>
            <form class="form-horizontal" role="form" id="register" method="post">
                <div class="form-group">
                    <label for="securephone" class="col-xs-2 col-md-2 control-label">用户名:</label>
                    <div class="col-xs-5 col-md-5">
                        <input type="text" class="form-control" id="securephone" name="securephone" placeholder="请输入手机号">
                    </div>
                    <div class="col-xs-5 col-md-5 form-info">
                        <span class="error" id="gradeInfo"></span>
                        <p id="phone" style="display:none;color:gray">•输入手机号</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-xs-2 col-md-2 control-label">登录密码:</label>
                    <div class="col-xs-5 col-md-5">
                        <input type="password" class="form-control" id="password" name="password" placeholder="请输入6-16个字符">
                    </div>
                    <div class="col-xs-5 col-md-5 form-info">
                        <span id="pass"></span>
                        <p id="passRule" style="display:none;color:gray">•6-16个字符,不支持空格</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirm_password" class="col-xs-2 col-md-2 control-label">确认密码:</label>
                    <div class="col-xs-5 col-md-5">
                        <input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="请再次输入密码" style="color:gray;font-size:14px;">
                    </div>
                    <div class="col-xs-5 col-md-5 form-info">
                        <span id="pwd"></span>
                        <p id="repeatPass" style="display:none;color:gray">•请再次输入登录密码</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="smsCode" class="col-xs-2 col-md-2 control-label">验证码:</label>
                    <div class="col-xs-8 col-md-8">
                        <input type="text" id="smsCode" class="form-control-code" style="width:110px;height:34px;" placeholder="输入验证码"/>&nbsp;&nbsp;
                        <input type="button" id="sendCode" class="form-control-code" value="获取验证码"/>
                        <p id="smsphonecode" style="display:none;color:gray;">•请输入验证码</p>
                    </div>
                    <div class="col-xs-2 col-md-2">
                        <span id="code" style="margin-left:-150px;font-size: 14px;"></span>
                    </div>
                </div>
                <div style="height:30px;margin-left:17%;font-size: 12px;">
                    <input type="checkbox" id="agreementChk"/>
                    <label id="allInvestment" name="accept">我已阅读并同意XX网</label>
                    <a href="javascript:void(0);" id="show-use-agreement">使用协议</a>及
                    <a href="javascript:void(0);" id="show-private-policy"> 隐私条款</a>
                    <span id="itemInfo" style="margin-left:2px;font-size: 14px;"></span>
                </div>
                <div class="form-group">
                    <div class="col-xs-5 col-md-offset-2 col-md-5">
                        <button type="button" disabled class="btn btn-lg btn-primary btn-block" id="register-btn">立即注册</button>
                    </div>
                </div>
            </form>
        </div>
        </div>
    </div>
<div class="modal fade" id="modal-secretDialog" data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:700px;">
        <div class="modal-content" style="width:700px;height:500px;">
            <div class="modal-header" style="border-bottom:2px solid red;">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    隐私条款
                </h4>
            </div>
            <div class="modal-body">
                <div  style="height: 350px; overflow-y: auto; width:670px;">
                    <p></p>
                    <p class="nllo">以下是本网站(XX网域名:www.xxx.com)及和略电子商务(上海)有限公司(以下合称"本网站")的隐私规则条款。</p>
                    <p class="nllo"> 您成为本网站用户前务必仔细阅读本隐私条款并同意本隐私条款,作为本网站服务正常操作程序的一部分,本网站将按照隐私规则的规定收集、使用并(在一些情况下)向第三方披露您的有关资料。本隐私条款作为本网站服务协议的附件,在您注册成为本网站用户后立即生效,并对您及本网站产生法律约束力。</p>
                    <p><b>1. 用户身份限制</b></p>
                    <p  class="nllo">本网站只接受具有中华人民共和国法人资格的企业或具有完全行为能力的自然人注册成为用户。本网站暂不接受境外企业、港澳台注册企业、无民事行为能力人、限制民事行为能力人、外籍人士注册成为本网站用户并使用本网站的服务。
                    <p class="nllo">不符合本网站注册资格的用户，本网站有权作出独立判断并采取删除其注册信息、暂停或关闭用户帐号等措施。</p></p>
                    <p><b>2. 涉及的个人及公司资料</b></p>
                    <p class="nllo">本网站收集个人及公司资料的主要目的在于向您提供一个顺利、有效和度身订造的交易经历。本网站仅收集本网站认为就此目的及达成该目的所必须的关于您的个人及公司资料。 本网站可能自公开及私人和公司资料来源收集您的额外资料，以更好地了解本网站用户，并为其度身订造本网站服务、解决争议并有助确保在网站进行交易的安全性。 本网站按照您在本网站网址上的行为自动追踪关于您及公司的某些资料。本网站利用这些资料进行有关本网站之用户的数量统计、兴趣及行为的内部研究，以更好地了解您以便向您和本网站的用户社区提供更好的服务。本网站在本网站的某些网页上使用诸如"Cookies"的资料收集装置。"Cookies"是设置在您的硬盘上的小档案，以协助本网站为您提供度身订造的服务。本网站亦提供某些只能通过使用"Cookies"才可得到的功能。本网站还利用"Cookies"使您能够在某段期间内减少输入密码的次数。"Cookies"还可以协助本网站提供专门针对您的兴趣而提供的资料。如果您将个人通讯信息(例如：手机短信、电邮或信件)交付给本网站，或如果其他用户或第三方向本网站发出关于您在本网站上的活动或登录事项的通讯信息，本网站可以将这些资料收集在您的专门档案中。</p>
                    <p class="nllo">用户同意并授权"XX网"收集、保存以下信息,"XX网"对用户的信息、资料及商业秘密负保密责任。</p>
                    <p class="nllo">1、	用户为自然人的，用户姓名、身份证扫描件、住所、联系方式等资料;</p>
                    <p class="nllo">2、	用户为企业法人的，企业全套资质扫描件(需加盖公章)、企业法定代表人身份证扫描件、企业地址、联系方式、年检信息及开票信息等企业资料。</p>
                    <p class="nllo">3、用户为自然人的还需提供企业授权其开展业务、订立合同的授权委托书。</p>
                    <p class="nllo">4、其他"XX网"要求提供的资料。</p>

                    <p><b>3. 本网站对用户资料的使用</b></p>
                    <p class="nllo">用户同意并授权本网站合理使用其个人及公司资料，以保证用户在本网站进行安全交易、或者便利本网站斡旋交易纠纷、解决交易争议。用户授权本网站使用的用户资料包括但不限于用户注册时提交的资料、本网站通过用户网上交易行为所获取的其他资料。</p>
                    <p class="nllo">用户同意并授权本网站使用其个人及公司资料的方式为：</p>
                    <p class="nllo">(1)本网站为交易安全及争议解除可以审查用户个人及公司的资料，区分使用多个用户名或别名的用户。</p>
                    <p class="nllo">(2) 本网站为监控违法交易、欺诈交易或其他刑事犯罪活动，可以通过人工或自动程序审查用户个人及公司资料或交易进展。</p>
                    <p class="nllo">(3)本网站为优化用户体验，可以使用用户个人及公司资料以分析网站的使用率、改善网站的内容及产品推广形式。</p>
                    <p class="nllo">(4)本网站为推广产品，可以使用用户个人及公司资料与用户联络，并通过网页、邮箱、短信等一切便利的方式向用户推送网站行政管理通知、产品信息、广告等信息，用户接受《XX网用户注册协议》和《XX网隐私规则》即视为用户明示同意网站可以推送上述信息。</p>
                    <p class="nllo"></p>
                    <p><b>4. 本网站对用户资料的修改或删除</b></p>
                    <p class="nllo"> 用户可以授权本网站帮助您修改您在本网站填写的一切个人或企业资料。如用户违反本网站规则或法律规定，本网站有权经电子邮件告知后在网站数据库中删除用户的资料、关闭账户或者限制用户使用本网站。网站有权根据实际审核结果在不通知用户的情况下对用户所填写的与事实不符的资料进行修正或更改。</p>
                    <p class="nllo"></p>
                    <p><b>5. 本网站对您的资料的披露</b></p>
                    <p class="nllo">本网站采用行业标准惯例来确保用户个人及公司信息、资料的保密性及安全性，但鉴于技术限制，本网站不能确保用户的资料不会通过本隐私条款中未列明的途径泄露出去。除本网站人为过错导致的信息泄露外，本网站不承担任何责任。</p>
                    <p class="nllo">本网站对用户的信息及资料负保密义务，但下列情形除外:</p>
                    <p class="nllo">(1)适用法律法规或上市规则要求披露的;</p>
                    <p class="nllo">(2)司法部门或政府部门要求披露的;</p>
                    <p class="nllo">(3)向乙方的外部专业顾问披露的;</p>
                    <p class="nllo">(4)甲方同意或授权乙方进行披露的。</p>
                    <p class="nllo"></p>
                    <p><b>6. 本网站用户对其他用户资料的使用</b></p>
                    <p class="nllo"> 在本网站提供的交易活动中，您无权要求本网站提供其他用户的个人及公司资料，除非符合以下条件：</p>
                    <p class="nllo">(1)您已向法院起诉其他用户的在本网站活动中的违约行为;</p>
                    <p class="nllo">(2)本网站被吊销营业执照、解散、清算、宣告破产或者其他情形。</p>
                    <p></p>
                    <p><b>7. 密码的安全性</b></p>
                    <p class="nllo"> 用户须对其使用用户名和密码的一切行为负责。因此，本网站建议用户不要向任何第三方披露您在本网站的用户名和密码。</p>
                    <p></p>
                    <p><b>8. 规则修改</b></p>
                    <p class="nllo">本网站可能不时按照用户的意见和本网站的需要修改本用户隐私规则，以准确地反映本网站的资料收集及披露规则。本网站用户隐私规则的所有修改，自本网站公布有关修改内容后自动生效或在隐私规则指定的日期生效。</p>
                    <p></p>
                    <div style="text-align: right;">
                        XX网网站
                        <br>2014.12.15
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>


    <div class="modal fade" id="modal-signDialog"  data-backdrop="static" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:700px;">
            <div class="modal-content" style="width:700px;height:500px;">
                <div class="modal-header" style="border-bottom:2px solid red;">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 id="myModalLabel">
                        <label>使用协议</label>
                    </h4>
                </div>
                <div class="modal-body">
                    <div  style="height: 350px; overflow-y: auto; width:670px;">
                        <p></p>
                        <p class="nllo">本协议是用户与"XX网"(域名 www.xxx.com )所有者之间就"XX网"网络平台服务等相关事宜所订立的契约，请仔细阅读本注册协议，点击"同意"按钮后，本协议即成立并生效，对双方均具有法律约束力。</p>
                        <p><b>第一条 总则</b></p>
                        <p class="nllo">1.1 "XX网"的所有权和运营权归和略电子商务(上海)有限公司所有。</p>
                        <p class="nllo">1.2 用户在注册之前，应当仔细阅读本协议，用户同意所有注册协议条款并完成注册程序，才能成为本站的正式用户，因此本协议所指用户为在"XX网"注册成功后的用户。</p>
                        <p class="nllo">1.3 在本网站注册的用户应当是依据中华人民共和国法律合法成立并依法存续的企业法人或自然人，用户需具有完全的民事权利能力和民事行为能力，能够依法独立享有民事权利和承担民事义务。用户为企业法人的，其在"XX网"平台购销煤炭的行为符合其经营范围;用户为自然人的，其在"XX网"平台从事购销煤炭的行为已经取得企业的合法授权，具备以企业名义订立合同、从事商业活动的资格。</p>
                        <p class="nllo">1.4用户承诺在"XX网"平台购买或销售煤炭的行为完全符合国家法律法规对煤炭交易管理的有关要求，包括但不限于企业经营范围、煤炭经营资格、税票开具等资质。</p>
                        <p class="nllo">1.5"XX网"保留在中华人民共和国大陆地区法施行之法律允许的范围内独自决定拒绝服务、关闭用户账户、清除或编辑内容或取消订单的权利。</p>
                        <p></p>
                        <p><b>第二条 本站服务</b></p>
                        <p class="nllo"> 2.1 "XX网"通过互联网依法为用户提供煤炭产品信息等服务，用户在完全同意本协议及本站规定的情况下，方有权使用本站的相关服务。</p>
                        <p class="nllo">2.2  "XX网"特别声明，其作为网络服务方，与本站注册用户之间存在服务合同关系，用户通过本网站签订煤炭买卖合同等经营活动如发生的违约行为，则根据合同主体签约的具体约定来主张违约责任，本网站运营商对注册用户的经营活动发生的任何风险或责任不负连带赔偿责任或义务。</P>
                        <p></p>
                        <p><b>第三条 用户信息</b></p>
                        <p class="nllo">3.1用户应本着诚实信用的原则向本站提供注册资料，用户同意其提供的注册资料真实、准确、完整、合法有效。用户注册资料如有变动的，应及时更新其注册资料。如果用户提供的注册资料不合法、不真实、不准确、不详尽的，用户需承担因此引起的相应责任及后果，"XX网"保留终止用户使用"XX网"各项服务的权利。</p>
                        <p class="nllo">3.2用户在本站进行浏览、下单等活动时，涉及用户真实名称、通信地址、联系方式、营业执照、法定代表人等隐私信息的，本站将予以严格保密，除非得到用户授权或法律另有规定，本站不会向外界披露用户隐私信息。</p>
                        <p class="nllo">3.3用户注册成功后，将产生用户名和密码等账户信息，用户可以根据本站规定修改密码。用户应谨慎合理的保存、使用其用户名和密码。用户若发现任何非法使用用户账号或存在安全漏洞的情况，请立即通知本站。</p>
                        <p class="nllo">3.4用户同意"XX网"拥有通过邮件、短信或微信等形式向其发送订单信息、促销活动等信息的权利。</p>
                        <p class="nllo">3.5用户在本网站注册的账号仅限本企业使用，不得将其账号转借给其他企业法人、自然人或其他机构使用，否则必须承担由此产生的全部法律责任及经济责任。</p>
                        <p class="nllo">3.6用户同意"XX网"有权使用用户的注册信息、用户名、密码等信息，登陆进入用户的注册账户，进行证据保全，包括但不限于公证、见证等国家法律法规规定许可的范围。</p>
                        <p></p>
                        <p><b>第四条 用户的承诺与保证</b></p>
                        <p class="nllo">4.1 用户保证按照"XX网"平台的要求进行实名注册，通过"XX网"平台提供的企业名称、经营范围、注册资金、法定代表人、地址、联系方式、年检信息及开票信息等企业资料均真实、有效。</p>
                        <p class="nllo">4.2 用户通过"XX网"平台发布煤炭销售信息的，用户承诺其发布的有关煤炭质量、数量、交提货地点等信息真实、准确、完整，不存在发布虚假信息及故意隐瞒重要信息的情形，同时用户保证其所销售的煤炭系合法所有并可依法转让。用户可用来销售的煤炭未被质押或转让给任何第三方，不存在第三人可主张的任何其他权利，没有任何权利瑕疵。用户承诺其销售煤炭所开具的税票真实有效且无虚开税票行为。</p>
                        <p class="nllo">4.3用户通过"XX网"平台发布煤炭采购信息的，用户承诺其发布的有关煤炭质量、数量、交提货地点等信息真实、准确、完整，不存在发布虚假信息及故意隐瞒重要信息的情形。同时用户保证其所使用的资金款项系其合法所得，其用来支付货款可转让/背书的有价票据真实合法且没有任何权利瑕疵。</p>
                        <p class="nllo">4.4无论是煤炭销售用户还是煤炭采购用户，均承诺在煤炭交易过程中，将严格履行合同所约定的义务，不存在隐瞒、欺诈行为，通过"XX网"平台开展的煤炭信息发布、交易等企业行为均真实有效，没有通过合法的交易外表来达到私下非法目的的单个企业行为或串通企业行为。</p>
                        <p class="nllo">4.5 用户承诺其在"XX网"注册登记的联系方式均为有效且企业经常使用的联系方式，所有书面文件达到用户注册登记的邮箱即为送达。</p>
                        <p></p>
                        <p><b>第五条 用户依法言行义务</b></p>
                        <p class="nllo">5.1本协议依据国家相关法律法规规章制定，用户同意严格遵守以下义务：</p>
                        <p class="nllo">(1)不得传输或发表：煽动抗拒、破坏宪法和法律、行政法规实施的言论，煽动颠覆国家政权，推翻社会主义制度的言论，煽动分裂国家、破坏国家统一的的言论，煽动民族仇恨、民族歧视、破坏民族团结的言论;</p>
                        <p class="nllo">(2)从中国大陆向境外传输资料信息时必须符合中国有关法规;</p>
                        <p class="nllo">(3)不得利用本站从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动;</p>
                        <p class="nllo">(4)不得干扰本站的正常运转，不得侵入本站及国家计算机信息系统;</p>
                        <p class="nllo">(5)不得传输或发表损害国家社会公共利益和涉及国家安全的信息资料或言论;</p>
                        <p class="nllo">(6)不得教唆他人从事本条款所禁止的行为;</p>
                        <p class="nllo">(7)不得利用在本站注册的账户进行非法牟利经营活动;</p>
                        <p class="nllo">5.2用户应不时关注并遵守本站不时公布或修改的各类合法规则规定。</p>
                        <p class="nllo">5.3本站保有删除站内各类不符合法律政策或不真实的信息内容而无须通知用户的权利。</p>
                        <p class="nllo">5.4若用户未遵守以上规定的，本站有权作出独立判断并采取暂停或关闭用户帐号等措施。用户须对自己在网上的言论和行为承担法律责任。</p>
                        <p></p>
                        <p><b>第六条 商品信息</b></p>
                        <p class="nllo">本网站所发布的货物价格、数量、是否有货等商品信息将根据市场行情及销售情况会随时发生变动，本站不作特别通知。由于网站货物信息数量庞大，虽然本站会尽最大努力保证您所浏览商品信息的准确性，但由于网络及电脑终端兼容性等客观原因存在，本站网页显示的信息可能会有一定的滞后性或差错，对此情形请您知悉并理解。</p>
                        <p></p>
                        <p><b>第七条 信息发布与订单</b></p>
                        <p class="nllo"> 7.1用户通过"XX网"发布销售或采购信息时，请仔细确认所销售或采购煤炭的品种、煤质、价格、数量、交提货地点和时间、企业名称和开票资料等信息，若用户自身提供的信息错误从而导致交易过程中可能会出现利益损失的，请及时更改、删除或与"XX网"客服联系，否则给用户带来的任何经济损失，"XX网"不承担任何法律责任及连带责任。</p>
                        <p class="nllo">7.2通过"XX网"实施的商品交易行为，除法律另有强制性规定外，有关方约定如下：</p>
                        <p class="nllo">本网站展示的商品和价格等信息仅仅是"XX网"、煤炭销售方或煤炭采购方的要约邀请，用户下单时须谨慎选择和填写。</p>
                        <p class="nllo">系统生成的订单信息是计算机信息系统根据用户选择或填写的内容自动生成的数据信息，仅是用户向"XX网"、煤炭销售方或煤炭采购方发出的合同要约。</p>
                        <p class="nllo">"XX网"、煤炭销售方或煤炭采购方收到针对其发布的合同要约所下单的用户交易信息，并通过"XX网"约定的方式与下单用户签约后，方视为下单用户与"XX网"、煤炭销售方或煤炭采购方之间建立了交易的合同关系。</p>
                        <p></p>
                        <p><b>第八条 责任限制及不承诺担保</b></p>
                        <p class="nllo">除非另有明确的书面说明,本站及其所包含的或以其它方式通过本站提供给您的全部信息、资料、货物(包括软件)和服务，均是在"按现状"和"按现有"的基础上提供的。</p>
                        <p class="nllo">除非另有明确的书面说明，"XX网"不对本站的运营及其包含在本网站上的信息、资料、货物(包括软件)和服务作任何形式的、明示或默示的声明或担保(根据中华人民共和国法律另有规定的以外)。</p>
                        <p class="nllo">如因不可抗力或其它本站无法控制的原因使本站销售系统崩溃或无法正常使用导致网上交易无法完成或丢失有关的信息、记录等，"XX网"会合理地尽力协助处理善后事宜。</p>
                        <p></p>
                        <p><b>第九条 协议更新及用户关注义务</b></p>
                        <p class="nllo">根据国家法律法规变化及网站运营需要，"XX网"有权对本协议条款不时地进行修改，修改后的协议一旦被张贴在本站上即生效，并代替原来的协议。用户可随时登陆查阅最新协议;用户有义务不时关注并阅读最新版的协议及网站公告。如用户不同意更新后的协议，可以且应立即停止接受"XX网"依据本协议提供的服务;如用户继续使用本网站提供的服务的，即视为同意更新后的协议。"XX网"建议您在使用本站之前阅读本协议及本站的公告。</p>
                        <p class="nllo">如果本协议中任何一条被视为废止、无效或因任何理由不可执行，该条应视为可分的且并不影响任何其余条款的有效性和可执行性。</p>
                        <p></p>
                        <p><b>第十条 法律管辖和适用</b></p>
                        <p class="nllo">本协议的订立、执行和解释及争议的解决均应适用在中华人民共和国大陆地区适用之有效法律(但不包括其冲突法规则)。</p>
                        <p class="nllo">如发生本协议与适用之法律相抵触时，则这些条款将完全按法律规定重新解释，而其它有效条款继续有效。</p>
                        <p class="nllo">如缔约方就本协议内容或其执行发生任何争议，双方应尽力友好协商解决;协商不成时，任何一方均可向有管辖权的中华人民共和国大陆地区法院提起诉讼。</p>
                        <p></p>
                        <p><b>第十一条 其他</b></p>
                        <p class="nllo">11.1"XX网"所有者是指在国家有关部门依法许可或备案的"XX网"经营主体。</p>
                        <p class="nllo"> 11.2"XX网"尊重所有注册用户的合法权利，本协议及本网站上发布的各类规则、声明等其他内容，均是为了更好的、更加便利的为注册用户提供服务。本站欢迎注册用户和社会各界提出意见和建议，"XX网"将虚心接受并适时修改本协议及本站上的各类规则。</p>
                        <p class="nllo"> 11.3 用户点击本协议下方的"同意"按钮即视为用户完全接受本协议，请在点击之前再次确认已经知悉并完全理解本协议的全部内容。</p>
                        <div style="text-align: right;">
                            XX网网站
                            <br>2014.12.15
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<div>


</@block>
<@block name="script">
<script type="text/javascript" src="${static('/scripts/register.js')}"></script>
</@block>
</@extend>
