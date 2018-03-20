<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta content="" name="keywords" />
		<meta content="" name="description" />
		<title>我的</title>
		<link rel="shortcut icon" href="/images/icon.ico" />
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/css/index.css" />
	</head>

	<body>
		<div class="mineTop clearfix"  onclick="window.location.href='login.html'">
			<img src="/images/bigtouxiang.png" />
			<p>点击登录</p>
		</div>
		<#--
		<#list map.pager.list as pages>
			${pages.mid}
		</#list>
		-->
		<div class="inform">
			<ul class="informTop clearfix">
				<li onclick="window.location.href='minetj.html'">
					<img src="/images/wdtjicon.png" />
					<p>我的体检</p>
				</li>
				<li onclick="window.location.href='minedoctor.html'">
					<img src="/images/wdghicon.png" />
					<p>我的挂号</p>
				</li>
				<li onclick="window.location.href='04-1mine.html'">
					<img src="/images/bgjdicon.png" />
					<p>报告解读</p>
				</li>
				<li>
					<img src="/images/wddzicon.png" />
					<p>我的定制</p>
				</li>
			</ul>
			<ul class="informBottom clearfix">
				<li>
					<p>0</p>
					<p>优惠券</p>
				</li>
				<li>
					<p>0</p>
					<p>收益</p>
				</li>
				<li>
					<p>0</p>
					<p>金币</p>
				</li>
			</ul>
		</div>
		<div class="mineList">
			<ul>
				<li onclick="window.location.href='02-1healthRecords.html'">
					<img src="/images/jjdaicon.png" class="minelistimg" />
					<span>健康档案</span>
					<img src="/images/arrows.png" class="moreright" />
				</li>
				<li onclick="window.location.href='myfamily.html'">
					<img src="/images/jrglicon.png" class="minelistimg" />
					<span>家人管理</span>
					<img src="/images/arrows.png" class="moreright" />
				</li>
				<li>
					<img src="/images/wdwdicon.png" class="minelistimg" />
					<span>我的问答</span>
					<img src="/images/arrows.png" class="moreright" />
				</li>
				<li>
					<img src="/images/wdbxicon.png" class="minelistimg" />
					<span>我的保险</span>
					<img src="/images/arrows.png" class="moreright" />
				</li>
			</ul>
		</div>
		<div class="mineList">
			<ul>
				<li onclick="window.location.href='03-1unMember.html'">
					<img src="/images/zghyicon.png" class="minelistimg" />
					<span>尊贵会员</span>
					<img src="/images/arrows.png" class="moreright" />
					<i class="minecue">让家人成为尊贵会员</i>
				</li>
				<li>
					<img src="/images/fxzqicon.png" class="minelistimg" />
					<span>分享赚钱</span>
					<img src="/images/arrows.png" class="moreright" />
					<i class="minecue">佣金高达30%</i>
				</li>
			</ul>
		</div>
		<div class="mineList">
			<ul>
				<li>
					<img src="/images/bzicon.png" class="minelistimg" />
					<span>帮助与反馈</span>
					<img src="/images/arrows.png" class="moreright" />
				</li>
				<li>
					<img src="/images/gywmicon.png" class="minelistimg" />
					<span>关于我们</span>
					<img src="/images/arrows.png" class="moreright" />
				</li>
			</ul>
		</div>
		<!-- 下面内容为tab区域，注意div不要丢掉 -->
		<div style="height: 0.98rem;"></div>
		<footer>
			<ul class="clearfix">
				<li onclick="window.location.href='index.html'">
					<img src="/images/sy.png" />
					<p>首页</p>
				</li>
				<li onclick="window.location.href='checkIndex.html'">
					<img src="/images/tj.png" />
					<p>体检</p>
				</li>
				<li onclick="window.location.href='discovery.html'">
					<img src="/images/fx.png" />
					<p>发现</p>
				</li>
				<li class="click">
					<img src="/images/rewd.png" />
					<p>我的</p>
				</li>
			</ul>
		</footer>
	</body>
	<script src="/js/rem.js" type="text/javascript" charset="utf-8"></script>

</html>