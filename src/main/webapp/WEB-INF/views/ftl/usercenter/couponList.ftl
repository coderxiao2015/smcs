<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta content="" name="keywords" />
		<meta content="" name="description" />
		<title>优惠券</title>
		<link rel="shortcut icon" href="/images/icon.ico" />
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/css/index.css" />
		<link rel="stylesheet" type="text/css" href="/css/jinbdh.css" />
		<style type="text/css">
			.unyhq{
				height: 0.834rem;
				line-height: 0.834rem;
				background: #fff;
				color: #666;
				font-size: 0.3rem;
				text-align: center;
				border: 1px solid #ededed;
				border-radius: 4px;
				box-shadow:2px px 10px rgba(59,185,255,0.06);
				margin: 0.228rem;
				margin-bottom: 0;
			}
		</style>
	</head>

	<body>
		<div class="content">
			<div class="tjjg">
				<ul class="clearfix tjjgtab">
					<li class="baryhq click"><span>可用券</span></li>
					<li class="barold"><span>已失效</span></li>
				</ul>

			</div>
		</div>
		<div style="height: 1.1rem;"></div>
		<div class="unyhq" onclick="goCancel()">
			不使用优惠券
		</div>
		<ul class="yhq">
			<#list vlist as coupon>
				<li class="clearfix"  onclick="goUse(${coupon.COUMEMID},${coupon.PARVALUE})">
					<div class="mon">
						<p class="mono"><span>${coupon.PARVALUE}</span>元</p>
						<#if coupon.MINVALUE?number != 0>
						<p class="mont">满${coupon.MINVALUE}可用</p>
						</#if>
					</div>
					<p class="name">${coupon.NAME}</p>
					<p class="sti clearfix">
						<span>
							<#if coupon.TYPE == '0'>
							    通用券                                 
                        	</#if>
                        	<#if coupon.TYPE == '1'>
							    会员券                 
                        	</#if>
						</span>
						<i>有效期 - ${coupon.VALIDDATE}</i>
					</p>
				</li>
			</#list>
		</ul>
		<div class="oldyhq">
			<ul>
				<#list ilist as coupon>
				<li>
					<p class="sti clearfix">
					<span>通用券</span><i>有效期- ${coupon.VALIDDATE}</i>
					</p>
				</li>
				</#list>
			</ul>
		</div>
	</body>
	<script src="/js/rem.js" type="text/javascript" charset="utf-8"></script>
	<script src="/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$(".baryhq").click(function() {
			$(".baryhq").removeClass('click').addClass('click');
			$(".barold").removeClass('click');
			$(".yhq").show();
			$(".oldyhq").hide();
		});
		$(".barold").click(function() {
			$(".barold").removeClass('click').addClass('click');
			$(".baryhq").removeClass('click');
			$(".oldyhq").show();
			$(".yhq").hide();
		});
		
		function goUse(coumemid,parvalue){
			var url = document.referrer;
			if(url.indexOf("refurl")>-1){
				var refurl  = jsUrlHelper.getUrlParam(url,'refurl');
				//console.info(refurl);
				var a = jsUrlHelper.putUrlParam(refurl,"coumemid",coumemid);
				var b = jsUrlHelper.putUrlParam(a,"parvalue",parvalue);
				window.location.href=b;
			}else{
				var a = jsUrlHelper.putUrlParam(url,"coumemid",coumemid);
				var b = jsUrlHelper.putUrlParam(a,"parvalue",parvalue);
				//console.info(b);
				window.location.href=b;
			}	
		}
		
		function goCancel(){
			var url = document.referrer;
			var a = jsUrlHelper.delUrlParam(url,"coumemid");
			var b = jsUrlHelper.delUrlParam(a,"parvalue");
			//console.info(b);
			window.location.href=b;
		}
		
		
var jsUrlHelper = {
    getUrlParam : function(url, ref) {
        var str = "";

        // 如果不包括此参数
        if (url.indexOf(ref) == -1)
            return "";

        str = url.substr(url.indexOf('?') + 1);

        arr = str.split('&');
        for (i in arr) {
            var paired = arr[i].split('=');

            if (paired[0] == ref) {
                return unescape(paired[1]);
            }
        }

        return "";
    },
    putUrlParam : function(url, ref, value) {

        // 如果没有参数
        if (url.indexOf('?') == -1)
            return url + "?" + ref + "=" + value;

        // 如果不包括此参数
        if (url.indexOf(ref) == -1)
            return url + "&" + ref + "=" + value;

        var arr_url = url.split('?');

        var base = arr_url[0];

        var arr_param = arr_url[1].split('&');

        for (i = 0; i < arr_param.length; i++) {

            var paired = arr_param[i].split('=');

            if (paired[0] == ref) {
                paired[1] = value;
                arr_param[i] = paired.join('=');
                break;
            }
        }

        return base + "?" + arr_param.join('&');
    },
    delUrlParam : function(url, ref) {

        // 如果不包括此参数
        if (url.indexOf(ref) == -1)
            return url;

        var arr_url = url.split('?');

        var base = arr_url[0];

        var arr_param = arr_url[1].split('&');

        var index = -1;

        for (i = 0; i < arr_param.length; i++) {

            var paired = arr_param[i].split('=');

            if (paired[0] == ref) {

                index = i;
                break;
            }
        }

        if (index == -1) {
            return url;
        } else {
            arr_param.splice(index, 1);
            return base + "?" + arr_param.join('&');
        }
    }
};
		
	</script>
</html>