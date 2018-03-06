<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
    <nav style="width: auto;display: table;margin-left: auto;margin-right: auto;">
        <ul class="pagination pagination-lg">
            <li id="paginationPrevious">
                <a href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
        </ul>
        <ul id="paginationPage" class="pagination pagination-lg">
            <%--<li class="active"><a href="javascript:void(0);">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>--%>
        </ul>
        <ul class="pagination pagination-lg">
            <li id="paginationNext">
                <a href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>
        <ul class="pagination pagination-lg">
            <p id="paginationTotalCount" class="navbar-text"></p>
        </ul>
    </nav>

    <script src="/js/pagination.js" ></script>
    <script type="text/javascript">
        $(function(){
            //使用EL表达式传入参数
            pagination.init({
                pageNum : ${page.pageNum},
                totalPages : ${page.totalPages},
                totalCount : ${page.totalCount}
            });
        });
    </script>
</body>
</html>
