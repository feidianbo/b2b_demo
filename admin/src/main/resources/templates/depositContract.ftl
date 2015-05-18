<html>
<body>
    <head>
        <title>电子合同</title>
        <link rel="stylesheet" type="text/css" href="${static('/styles/main.css')}"/>
    </head>
    <div class="container" style="width:80%; margin:0 auto;">
            <div id="body-head">
                <div style="border-left:4px solid #ff624f;height:25px;">
                    <label style="color:#313131;font-size:24px; ">&nbsp;&nbsp;电子合同</label>
                </div>
                <hr style="color:#dcdcdc;">
            </div>
            <h5 style="color:#ff624f;margin-bottom: 2%;">请仔细阅读以下信息:</h5>
            <div>
                <!--合同内容-->
                <@markdown value=contract/>
            </div>
        <div class="col-md-5 col-md-offset-6">
            <input type="checkbox" id="aggrecontract"><label style="font-size:16px;">我已阅读并同意此合同</label>
        </div>
            <div class="col-md-12" style="margin-top:20px;">
                <div class="col-md-3">
                    <input type="button" class="btn btn-cancel btn-block"  onclick="javascript:history.go(-1)" value="返回" >
                </div>
                <div class="col-md-3 col-md-offset-3">
                <input type="button" class="btn btn-primary btn-block" id="downloadDeposit" disabled="true"  value="下载" >
                </div>
            </div>
    </div>

    <script type="text/javascript" src="${static('/bower_components/jquery/dist/jquery.js')}"></script>
    <script type="text/javascript" src="${static('/scripts/kitt/download.js')}"></script>
</body>
</html>
