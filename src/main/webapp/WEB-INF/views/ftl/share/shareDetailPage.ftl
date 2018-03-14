<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta content="" name="keywords" />
    <meta content="" name="description" />
    <title>分享链接</title>
    <link rel="shortcut icon" href="${basePath}/images/icon.ico" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/fxlj.css" />
    <script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/commonLib.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/rem.js" type="text/javascript" charset="utf-8"></script>
    <style type="text/css">
        .mask1 {
            position: fixed;
            left: 0px;
            top: 0px;
            right: 0px;
            bottom: 0px;
            z-index: 9999;
            display: none;
            background: url('${basePath}/images/lphzz.png');
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            opacity: .84;
        }
    </style>
</head>

<body>
<div class="product">
    <div class="serpro">
        <ul>
            <li class="clearfix">
               <#-- <#if Session["web_browser"]??>
                    aaaa
                </#if>-->
                <img src="" />
                <p class="name"></p>
                <p class="money">￥1168.00</p>
                <p class="smo">赚￥50</p>
            </li>
        </ul>
    </div>
</div>
<div class="tcnav">
    <ul class="clearfix">
        <li><i>0</i>点击</li>
        <li><i>0</i>购买</li>
        <li><i>0</i>佣金</li>
    </ul>
</div>
<div class="share">
    <p class="sname">分享链接已生成，请选择分享方式</p>
    <p class="scon">在分享并产生购买后，您最多将获得<i>￥50</i>的佣金</p>
    <p class="sti">分享到以下平台：</p>
    <ul class="clearfix">
        <li onclick="window.location.href='lkp.html'">
            <img src="${basePath}/images/slkp.png" />
            <p>生成礼品函</p>
        </li>
        <li id="mask1">
            <img src="${basePath}/images/spy.png" />
            <p>朋友圈</p>
        </li>
        <li id="mask1">
            <img src="${basePath}/images/swc.png" />
            <p>微信好友</p>
        </li>
        <li >
            <img src="${basePath}/images/sweibo.png" />
            <p>微博</p>
        </li>
    </ul>
    <ul class="clearfix">
        <li id="mask1">
            <img src="${basePath}/images/sqq.png" />
            <p>QQ好友</p>
        </li>
        <li id="mask1">
            <img src="${basePath}/images/sqzone.png" />
            <p>QQ空间</p>
        </li>
        <li>
            <img src="${basePath}/images/slink.png" />
            <p>复制链接</p>
        </li>
    </ul>
</div>
<div class="sharelist" onclick="window.location.href='fxjl.html'">
    分享记录<img src="${basePath}/images/arrows.png" alt="" />
</div>
<div class="mask1"></div>
<#--引入微信分享接口-->

<#--  <#include "./jssdk.ftl" parse=false/>-->
</body>
<script type="application/javascript">
    window.addEventListener("popstate", function(e) {
        //返回上两页，因为做了中间页，当满足一定条件的时候才会到中间页面
        window.history.go(-2);
    }, false);

    document.onclick = function(e) {
        var id = e.srcElement.id;
        if(id != "mask1" && id != "mask2") {
            document.querySelector(".mask1").style.display = "none";
        }
    };
    window.onload = function() {
        document.querySelector("#mask1").onclick = function(e) {
            document.querySelector(".mask1").style.display = "block";
        };
    };
</script>
</html>