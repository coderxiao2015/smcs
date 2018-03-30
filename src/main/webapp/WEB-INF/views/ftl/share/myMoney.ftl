<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta content="" name="keywords" />
    <meta content="" name="description" />
    <title>分享记录</title>
    <link rel="shortcut icon" href="${basePath}/images/icon.ico" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/moneyCenter.css" />
    <script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/commonLib.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/rem.js" type="text/javascript" charset="utf-8"></script>
</head>

<body>
<div class="top">
    <p class="toptit">本月当前收入(元)</p>
    <p class="topmoney">1800</p>
</div>
<div class="money">
    <p class="mont">到账收入（元）</p>
    <div class="mmoo clearfix">
        <div class="mola">
            <p class="molo">380</p>
            <img src="${basePath}/images/arrows.png" />
            <p class="molt">上月收入</p>
        </div>
        <div class="mmor">
            <p class="mmoe">800</p>
            <p class="mrew">累积提现金额</p>
        </div>
    </div>
    <div class="almo">
        <p class="mont">可提现金额（元）</p>
        <p class="almoney">380</p>
        <span onclick="window.location.href='tixian.html'">提现</span>
    </div>
</div>
<div class="sharelist" onclick="window.location.href='moneyList.html'">
    收支明细<img src="${basePath}/images/arrows.png" alt="" />
</div>
</body>
</html>