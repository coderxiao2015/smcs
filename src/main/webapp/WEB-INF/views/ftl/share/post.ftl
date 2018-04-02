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
    <link rel="stylesheet" type="text/css" href="${basePath}/css/lkp.css" />
    <script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script src="${basePath}/js/commonLib.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/rem.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/jquery.qrcode.min.js" type="text/javascript" charset="utf-8"></script>
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
<div class="lkp">
    <img src="/images/tcbg.jpg" class="tcbg" />
    <div class="lkpcon">
        <#if resultMap??>
        <img src="/images/lphbg.png" class="lph"  />

<#--
        <img src="/images/lkpewm.png" class="lphewm" />
-->
          <div class="lphewm"  id="qrcode"></div>
        <img  class="tcimg" src="${ctxImg}/product/${resultMap.CSID}/${resultMap.IMG}" onerror="this.src='${basePath}/images/default_img.png'"/>
        <p class="tctit">${resultMap.PRODUCT_NAME}</p>
        <p class="tctime">这款健康套餐不错哦，
                ${shareUser?default("您的好友")}
            邀您一起购买。
        </p>
        </#if>
    </div>
    <#--只有微信内有这个分享给好友-->
    <div class="lkps maskName" >
        <img class=" maskName" src="/images/yktshare.png" />
        <span class=" maskName">分享给好友</span>
    </div>
</div>


<div class="mask1"></div>

</body>
<script type="application/javascript">

    /*分享的连接*/
    function shareLink(type){
        var shareUrl="${tooptUrl}/share/doHandleShareLink";
        shareUrl+="?parentOpenId=${map.openid}";
        shareUrl+="&parentMid=${map.mid}";
        shareUrl+="&pid=${map.pid}";
        shareUrl+="&isCard=${map.isCard}";
        shareUrl+="&isSale=1";
        if(type=='1'){
            return encodeURIComponent(shareUrl);
        }
        return shareUrl;
    }

    var logoImg="http://18eb075862.iask.in:25267/images/tcbg.jpg";

/*生成二维码*/
    $('#qrcode').qrcode({
        render: "canvas", //也可以替换为table
        width: 100,
        height: 100,
        background : "#ffffff",       //二维码的后景色
        foreground : "#000000",        //二维码的前景色
        text:shareLink("0")   //二维码的内容，若是带中文则要重新编码
    });

   // $("#qrcode").children().css("width",1.51rem);
  //  console.info($("#qrcode").children("canvas:eq(0)"));
   // $("#qrcode").children("canvas:first-child").css("width",1.5rem);
    document.onclick = function(e) {
        var className = e.target.className;
        if((className!=null)&&(className.indexOf("maskName")>-1)  ) {
            document.querySelector(".mask1").style.display = "block";
        }else{
            document.querySelector(".mask1").style.display = "none";
        }
    };
</script>
<script type="application/javascript">
    var url = window.location.href;
    //var productPic=$("#productImg").attr("src");
    var productPic="${tooptUrl}/images/bgjdicon.png";
    /*分享的连接*/
    function shareLink(type){
        var shareUrl="${tooptUrl}/share/doHandleShareLink";
        shareUrl+="?parentOpenId=${map.openid}";
        shareUrl+="&parentMid=${map.mid}";
        shareUrl+="&pid=${map.pid}";
        shareUrl+="&isCard=${map.isCard}";
        shareUrl+="&isSale=1";
        if(type=='1'){
            return encodeURIComponent(shareUrl);
        }
        return shareUrl;
    }


    //ajax注入权限验证
    var url2 = "${pcptUrl}/core/wxapi.doValidJSSDK.do";
    var data={"url":url};
    $.getMyJSON2(url2,data,function(data){
        var appId = data.data[0];
        var noncestr = data.data[1];
        var jsapi_ticket = data.data[3];
        var timestamp = data.data[2];
        var signature = data.data[4];
        wx.config({
            debug: false, //开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: appId, //必填，公众号的唯一标识
            timestamp: timestamp, // 必填，生成签名的时间戳
            nonceStr: noncestr, //必填，生成签名的随机串
            signature: signature,// 必填，签名，见附录1
            jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ',
                'onMenuShareWeibo','onMenuShareQZone','chooseImage',
                'uploadImage','downloadImage','startRecord','stopRecord',
                'onVoiceRecordEnd','playVoice','pauseVoice','stopVoice',
                'translateVoice','openLocation','getLocation','hideOptionMenu',
                'showOptionMenu','closeWindow','hideMenuItems','showMenuItems',
                'showAllNonBaseMenuItem','hideAllNonBaseMenuItem'] //必填，需要使用的JS接口列表，所有JS接口列表 见附录2
        });
    });

    wx.ready(function(){
        var title = '分享';
        //分享给朋友
        wx.onMenuShareAppMessage({
            title: title, // 分享标题
            desc: '全面体检，全面疾病筛查，专业医生量身定制套餐', // 分享描述
            link: shareLink("0"), // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: "http://s.51towin.com/images/bgjdicon.png", // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            trigger: function(){
                //点击分享触发的动作
            },
            success: function () {
                // 用户确认分享后执行的回调函数
                document.querySelector(".mask1").style.display = "none";
                //alert(3)
            },
            cancel: function () {
                //alert(4)
                // 用户取消分享后执行的回调函数
                document.querySelector(".mask1").style.display = "none";
            }
        });

        //分享到朋友圈
        wx.onMenuShareTimeline({
            title: title, // 分享标题
            desc: '全面体检，全面疾病筛查，专业医生量身定制套餐', // 分享描述
            link: shareLink("0"), // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: "http://s.51towin.com/images/bgjdicon.png", // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            trigger: function(){
                //点击分享触发的动作
            },
            success: function () {
                // 用户确认分享后执行的回调函数
                document.querySelector(".mask1").style.display = "none";
                //alert(3)
            },
            cancel: function () {
                //alert(4)
                document.querySelector(".mask1").style.display = "none";
                // 用户取消分享后执行的回调函数
            }
        });

        //分享给qq
        wx.onMenuShareQQ({
            title: title, // 分享标题
            desc: '全面体检，全面疾病筛查，专业医生量身定制套餐', // 分享描述
            link: shareLink("0"), // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: "http://s.51towin.com/images/bgjdicon.png", // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            trigger: function(){
                //点击分享触发的动作
            },
            success: function () {
                // 用户确认分享后执行的回调函数
                document.querySelector(".mask1").style.display = "none";
                //alert(3)
            },
            cancel: function () {
                //alert(4)
                document.querySelector(".mask1").style.display = "none";
                // 用户取消分享后执行的回调函数
            }
        });

        //分享给扣扣空间
        wx.onMenuShareQZone({
            title: title, // 分享标题
            desc: '全面体检，全面疾病筛查，专业医生量身定制套餐', // 分享描述
            link: shareLink("0"), // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: "http://s.51towin.com/images/bgjdicon.png", // 分享图标
            type: 'link', // 分享类型,music、video或link，不填默认为link
            trigger: function(){
                //点击分享触发的动作
            },
            success: function () {
                // 用户确认分享后执行的回调函数
                document.querySelector(".mask1").style.display = "none";
                //alert(3)
            },
            cancel: function () {
                //alert(4)
                document.querySelector(".mask1").style.display = "none";
                // 用户取消分享后执行的回调函数
            }
        });
    });
</script>
</html>