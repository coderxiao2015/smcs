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
    <p class="sname">分享链接已生成，请选择分享方式${tooptUrl}</p>
    <p class="scon">在分享并产生购买后，您最多将获得<i>￥50</i>的佣金</p>
    <p class="sti">分享到以下平台：</p>
    <ul class="clearfix">
        <li onclick="window.location.href='lkp.html'">
            <img src="${basePath}/images/slkp.png" />
            <p>生成礼品函</p>
        </li>
        <li >
            <img class="maskName" src="${basePath}/images/spy.png" />
            <p>朋友圈</p>
        </li>
        <li >
            <img class="maskName" src="${basePath}/images/swc.png" />
            <p>微信好友</p>
        </li>
        <li >
            <img src="${basePath}/images/sweibo.png" />
            <p>微博</p>
        </li>
    </ul>
    <ul class="clearfix">
        <li >
            <img class="maskName" src="${basePath}/images/sqq.png" />
            <p>QQ好友</p>
        </li>
        <li >
            <img class="maskName" src="${basePath}/images/sqzone.png" />
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

</body>
<script type="application/javascript">
    console.info(1);
    console.info("${map.openid}");
    window.addEventListener("popstate", function(e) {
        //返回上两页，因为做了中间页，当满足一定条件的时候才会到中间页面
        window.history.go(-2);
    }, false);

    document.onclick = function(e) {
        var className = e.srcElement.className;
        if(className != "maskName" ) {
            document.querySelector(".mask1").style.display = "none";
        }else{
            document.querySelector(".mask1").style.display = "block";
        }
    };
</script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="application/javascript">
    $(function(){
        var url = window.location.href;
        //ajax注入权限验证
        var url2 = "/wxapi/doValidJSSDK.do";
        var data = {"url" : url};
        $.getMyJSON2(url2,data,function(data){
            var appId = data.data[0];
            var noncestr = data.data[1];
            var jsapi_ticket = data.data[3];
            var timestamp = data.data[2];
            var signature = data.data[4];
            wx.config({
                debug: true, //开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: appId, //必填，公众号的唯一标识
                timestamp: timestamp, // 必填，生成签名的时间戳
                nonceStr: noncestr, //必填，生成签名的随机串
                signature: signature,// 必填，签名，见附录1
                jsApiList: ['hideMenuItems','showMenuItems','closeWindow','onMenuShareAppMessage','onMenuShareTimeline','hideAllNonBaseMenuItem','showAllNonBaseMenuItem','chooseImage','uploadImage','downloadImage','onMenuShareQQ','onMenuShareQZone'] //必填，需要使用的JS接口列表，所有JS接口列表 见附录2
            });
        });

        wx.ready(function(){
            var productDetaliUrl="${map.productUrl}";
            var selfUrl = encodeURIComponent(window.location.href);

            var title = '分享';
            var imgUrl = $("#proImg").attr("src");

            //拼接分享的地址
            var shareUrl="${tooptUrl}/share/doHandleShareLink";
            shareUrl+="?selfUrl="+selfUrl;
            shareUrl+="&parentOpenId=${map.openid}";
            shareUrl+="&parentMid=${map.mid}";
            shareUrl+="&pid=${map.pid}";
            shareUrl+="&productUrl=${map.productUrl}";
            shareUrl+="&isSale=1";

            //分享给朋友
            wx.onMenuShareAppMessage({
                title: title, // 分享标题
                desc: '全面体检，全面疾病筛查，专业医生量身定制套餐', // 分享描述
                link: shareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: imgUrl, // 分享图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                trigger: function(){
                    alert(shareUrl)
                },
                success: function () {
                    // 用户确认分享后执行的回调函数
                    //alert(3)
                },
                cancel: function () {
                    //alert(4)
                    // 用户取消分享后执行的回调函数
                }
            });

            //分享到朋友圈
            wx.onMenuShareTimeline({
                title: title, // 分享标题
                link: shareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: imgUrl, // 分享图标
                trigger: function(){
                    //alert(shareUrl)
                },
                success: function () {
                    // 用户确认分享后执行的回调函数
                    //alert(3)
                },
                cancel: function () {
                    // 用户取消分享后执行的回调函数
                    //alert(4)
                }
            });

            //分享给qq
            wx.onMenuShareQQ({
                title: title, // 分享标题
                desc: '全面体检，全面疾病筛查，专业医生量身定制套餐', // 分享描述
                link: shareUrl , // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: imgUrl, // 分享图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                trigger: function(){
                    alert(shareUrl)
                },
                success: function () {
                    // 用户确认分享后执行的回调函数
                    //alert(3)
                },
                cancel: function () {
                    //alert(4)
                    // 用户取消分享后执行的回调函数
                }
            });

            //分享给扣扣空间
            wx.onMenuShareQZone({
                title: title, // 分享标题
                desc: '全面体检，全面疾病筛查，专业医生量身定制套餐', // 分享描述
                link: shareUrl , // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: imgUrl, // 分享图标
                type: 'link', // 分享类型,music、video或link，不填默认为link
                trigger: function(){
                    alert(shareUrl)
                },
                success: function () {
                    // 用户确认分享后执行的回调函数
                    //alert(shareUrl)
                },
                cancel: function () {
                    //alert(4)
                    // 用户取消分享后执行的回调函数
                }
            });
        });
    });
</script>
</html>