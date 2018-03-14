<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta content="" name="keywords" />
		<meta content="" name="description" />
		<title>分享赚钱</title>
		<link rel="shortcut icon" href="img/icon.ico" />
		<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${basePath}/css/base.css" />
		<style type="text/css">
			.gl{
				position: absolute;
				top: 4.7rem;
				left: 2.28rem;
				z-index: 9;
				display: block;
				width: 2.56rem;
				height: 1.5rem;
			}
			.fl{
				position: absolute;
				bottom: 2.4rem;
				right: 0.8rem;
				z-index: 9;
				display: block;
				height: 1.23rem;
				width: 5.6rem;
			}
			.xl{
				position: absolute;
				bottom: 0.65rem;
				right: 0.8rem;
				z-index: 9;
				display: block;
				color: #fff;
				font-size: 0.228rem;
				height: 1.23rem;
				width: 5.6rem;
			}
			.xl span{
				float: right;line-height: 1.23rem;padding-right: 0.45rem;
			}
		</style>
	</head>

	<body>
		<img src="${basePath}/images/fximg.jpg" style="width: 100%;height: 100%;" />
		<a href="${basePath}/share/guide" class="gl"></a>
		<a href="${basePath}/share/getProductInfo" class="fl"></a>
		<a href="fxjl.html" class="xl">
		 <#if map??>
			<span>已获 ￥${map.EARNEDMONEY?string("##0.00")}</span>
		<#else>
			<span>已获 ￥0</span>
		</#if>
		</a> 	
	</body>
	<script src="${basePath}/js/rem.js" type="text/javascript" charset="utf-8"></script>

</html>