<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta content="" name="keywords" />
    <meta content="" name="description" />
    <title>选择分享商品</title>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<body>


<script type="application/javascript">
    $(function(){
        var url = window.location.href;
        //ajax注入权限验证
        var url2 = "${tooptUrl}/wxapi/doValidJSSDK.do";
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
            var productDetaliUrl="${productUrl}";
            var isSale = '1';
            var openid = "111";
            var mid="11";
            var selfUrl = encodeURIComponent(window.location.href);

            var title = '分享';
            var imgUrl = $("#proImg").attr("src");

            //拼接分享的地址
            var shareUrl="${pcptUrl}/share/doHandleShareLink";
            shareUrl+="?selfUrl="+selfUrl;
            shareUrl+="&parentOpenId=${map.openid}";
            shareUrl+="&parenMid=${map.mid}";
            shareUrl+="&pid="+pid;
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
</body>
</html>