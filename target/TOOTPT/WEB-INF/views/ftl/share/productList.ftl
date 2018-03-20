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
    <style type="text/css">
        .trans{transform: rotate(180deg);}
    </style>
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
        <li onclick="conditionQuary(this)" class="saleCount">销量<img class="noTranse" src="${basePath}/images/downicon.png" /></li>
        <li onclick="conditionQuary(this)" class="salePrice">价格<img  class="noTranse" src="${basePath}/images/downicon.png" /></li>
        <li onclick="conditionQuary(this)" class="saleRatio">佣金<img class="noTranse" src="${basePath}/images/downicon.png" /></li>
        <li onclick="conditionQuary(this)" class="order_four">分类<img class="noTranse" src="${basePath}/images/downicon.png" /></li>
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
    var categoryName=null;
    $(function(){
         loadMoreProduct(1);//初始化第一页数据
    });

    function loadMoreProduct(pageNo){
        var url = "/share/loadMoreByPage.do";
        var globalSearch=$("#globalSearch").val();
        var data = {
            "pageNo":pageNo,
            "globalSearch":globalSearch,
            "categoryName":categoryName
        };
        $.getMyJSON(url, data, function(data){
            var list = data.data;
            totalPage=data.totalPages;
            if(((globalSearch!=""))&&(pageNo==1)){
                arry.length=0;
            }
            if(((categoryName!=""))&&(pageNo==1)){
                arry.length=0;
            }
            arry =  arry.concat(list);
            var productHtml = productTemplate.render(arry);

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
        var imgObjet=$(e).children("img").attr("class");
        var desc=imgObjet.indexOf("noTrans");
        var asc=imgObjet.indexOf("trans");
        //修改兄弟节点的样式
        $(e).siblings().children("img").attr("class","noTrans");
        if(desc>-1){
            categoryName=$(e).attr("class")+"Asc";
            $(e).children("img").attr("class","trans");
        }
        if(asc>-1){
            categoryName=$(e).attr("class")+"Desc";
            $(e).children("img").attr("class","noTrans");
        }
        globalSearch=null;
        loadMoreProduct("1");

    }



</script>
</body>
</html>