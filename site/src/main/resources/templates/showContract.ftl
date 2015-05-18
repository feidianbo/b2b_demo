<@extend name="layout">
    <@block name="cs">
    </@block>
    <@block name="body">
    <div class="clear-level">
        <div class="container">
            <div id="body-head">
                <div style="border-left:4px solid #ff624f;height:25px;"><label style="color:#313131;font-size:24px; ">&nbsp;&nbsp;电子合同</label></div>
                <hr style="color:#dcdcdc;">
            </div>
            <h5 style="color:#ff624f;margin-bottom: 2%;">下面是合同详细:</h5>
            <div>
                <@markdown value=contract/>
            </div>
        </div>
    </div>
    </div>
    <input type="hidden" value="${order.id}" id="idText"/>
    !--<div class="form-group" style="padding-top:10px;">
        <div class="col-xs-1 col-md-1" style="left:700px;">
            <input type="button" class="btn" value="下载合同" style="background:#e5e5e5;width:140;height: 40px;font-size: 18px;" id="downloadContract">
        </div>
    </div>-->
    <div>

    </@block>
    <@block name="script">
        <script type="text/javascript" src="${static('/scripts/myOrder.js')}"></script>
    </@block>
</@extend>
