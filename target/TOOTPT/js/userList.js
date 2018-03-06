var userList = {

    init : function (params) {
        userList.click();
        userList.searchValue(params);
    },

    URL : {
        basePath : function() {
            return "/";
        },
        userList : function() {
            return userList.URL.basePath() + 'user/list';
        }
    },

    /**
     * 点击事件
     * @constructor
     */
    click : function () {
        $("#userSearch").click(function(){
            userList.search();
        });
        $(document).keydown(function(e) {
            if (e.keyCode == 13) {
                userList.search();
            }
        });
    },

    /**
     * 搜索事件
     * @constructor
     */
    search : function () {
        var userCondition = $("#userCondition").val();
        location.href = userList.URL.userList() + "?userCondition=" + userCondition;
    },

    /**
     * 搜索表单赋值
     * @param params
     * @constructor
     */
    searchValue : function (params) {
        $("#userCondition").val(params['userCondition']);
    }

}