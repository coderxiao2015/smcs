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
		<ul class="yhq">
			<li class="clearfix">
				<div class="mon">
					<p class="mono"><span>50</span>元</p>
					<p class="mont">满400可用</p>
				</div>
				<p class="name">新人专享（全场通用）</p>
				<p class="sti clearfix"><span>通用券</span><i>2017/11/30 - 2018/12/31</i></p>
			</li>
			<li class="clearfix">
				<div class="mon">
					<p class="mono"><span>20</span>元</p>
					<p class="mont">限实体商品</p>
				</div>
				<p class="name">双12礼品券（限实体商品）</p>
				<p class="sti clearfix"><span>通用券</span><i>有效期 - 2018/12/31</i></p>
			</li>
			<li class="clearfix">
				<div class="mon">
					<p class="mono"><span>100</span>元</p>
					<p class="mont">限大咖名医就诊</p>
				</div>
				<p class="name">双12会员券（限大咖名医就诊）</p>
				<p class="sti clearfix"><span>会员券</span><i>有效期 - 2018/12/31</i></p>
			</li>
		</ul>
		<div class="oldyhq">
			<ul>
				<li>
					<p class="sti clearfix"><span>通用券</span><i>2017/11/30 - 2018/12/31</i></p>
				</li>
				<li>
					<p class="sti clearfix"><span>通用券</span><i>2017/11/30 - 2018/12/31</i></p>
				</li>
				<li>
					<p class="sti clearfix"><span>通用券</span><i>2017/11/30 - 2018/12/31</i></p>
				</li>
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
		})
	</script>
</html>