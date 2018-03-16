<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta content="" name="keywords" />
    <meta content="" name="description" />
    <title>选择分享商品</title>
    <link rel="shortcut icon" href="${basePath}/images/icon.ico" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/base.css" />
    <link rel="stylesheet" type="text/css" href="${basePath}/css/fxsp.css" />
    <script src="${basePath}/js/rem.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/js/commonLib.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javaScript" src="${basePath}/js/jsrender.js"></script>
</head>
<body>
<div class="topser clearfix">
    <div class="sera">
        <img src="${basePath}/images/fxser.png" />
        <input type="text" id="globalSearch" value="" placeholder="快速查找你需要的商品" />
    </div>
    <span onclick="search()">搜索</span>
</div>
<div class="tcnav">
    <ul class="clearfix">
        <li>销量<img src="${basePath}/images/downicon.png" onclick="conditionQuary(this)"/></li>
        <li>价格<img src="${basePath}/images/downicon.png" onclick="conditionQuary(this)"/></li>
        <li>佣金<img src="${basePath}/images/downicon.png" onclick="conditionQuary(this)"/></li>
        <li>分类<img src="${basePath}/images/downicon.png" onclick="conditionQuary(this)"/></li>
    </ul>
</div>
<div style="height: 1.7rem;"></div>
<div class="serpro">
    <ul id="productInfoDiv">
    </ul>
</div>
<div class="loadMore"></div>

<script id="productInfo" type="text/x-jsrender">
			 <li class="clearfix">
			 {{if '${pids}'.indexOf(PID) == -1}}
					<a style="overflow: hidden; display: block;" href="javascript:void(0)" onclick="bindSale('{{:pid}}','0')">
			{{else}}
					<a style="overflow: hidden; display: block;" href="javascript:void(0)" onclick="bindSale('{{:pid}}','1')">
				{{/if}}
					<img src="${ctxImg}/product/{{:csid}}/{{:img}}" onerror="this.src='${basePath}/images/default_img.png'" />
						<p class="name">{{:productName}}
							{{if sex == 1}}
								(男)
							{{else sex == 2}}
								(已婚女)
							{{else}}
								（未婚女）
							{{/if}}
						</p>
					    <p class="money">￥{{:shopPrice}}</p>
					    <div>
                                <img src="${basePath}/images/fxsh.png" />
                                <p>赚￥{{:ratio}}</p>
                         </div>
				</a>
			</li>
		</script>

<script type="application/javascript">
    var productTemplate = $.templates('#productInfo');
    var arry =[],totalPage='${pager.totalPages}',i =1;
    var listOrder="desc";//默认排序()降序
    var category="All";//产品类型
    $(function(){
         loadMoreProduct(1);//初始化第一页数据
    });

    function loadMoreProduct(pageNo){
        var url = "/share/loadMoreByPage.do";
        var globalSearch=$("#globalSearch").val();
        var data = {
            "pageNo":pageNo,
            "globalSearch":globalSearch
        };
        $.getMyJSON(url, data, function(data){
            var list = data.data;
            arry =  arry.concat(list);
            var productHtml = productTemplate.render(arry);
            if((globalSearch!="")&&(pageNo==1)){
                arry.length=0;
            }
            $("#productInfoDiv").html(productHtml);
        });

    }

    $(window).scroll(function() {
        var scrollTop = $(this).scrollTop();
        var scrollHeight = $(document).height();
        var windowHeight = $(this).height();
        if (scrollTop + windowHeight == scrollHeight) {
            // 此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
            $(".loadMore").html("<img src='${basePath}/images/1.gif'/>正在加载更多的体检套餐");
            i++;
            if(i<=totalPage){
                loadMoreProduct(i);
            }else{
                $(".loadMore").html("已经到底了");
            }
        }
    });

    //   绑定分销员
    function bindSale(pid,isCard){
            var data={"pid":pid,"isCard":isCard};
        /*给pcpt发送请求，不用返回值，通过form表单的形式*/
        postwith("${pcptUrl}/core/sale.saleApplicate.do",data);
    }

    //点击搜索开始查询数据
    function search(){
        var globalSearch=$("#globalSearch").val();
        if(globalSearch!=null){
            loadMoreProduct(1);
        }

    }

    //点击搜索开始查询数据
    function conditionQuary(e){

    }



</script>
</body>
</html>