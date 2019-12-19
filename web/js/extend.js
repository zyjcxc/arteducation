/**
 * jq 扩展的东西（参数）是以json对象的形式来出现的
 * 举例：
 * ajax 的 get 方法 --- $.EXTEND.GET（接口地址，参数）
 */
(function (){
    //var baseUrl = 'http://188.131.148.168:8081/web/';
    var baseUrl = 'http://localhost:9808/web/';
    //var baseUrl = 'http://10.30.50.152:9808/web/';



    $.fn.serializeObject = function(noEmpty)
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                if(noEmpty !== '' || !noEmpty){
                    o[this.name].push(this.value || '');
                }else{
                    o[this.name].push(this.value);
                }
            } else {
                if(noEmpty !== '' || !noEmpty){
                    o[this.name] = this.value || '';
                }else{
                    o[this.name] = this.value;
                }
            }
        });
        return JSON.stringify(o);
    };
    jQuery.extend({
        EXTEND:{
            GET:function (url,params) {
                var deferred = $.Deferred();
                var data = params?params:{};
                var action = (/^(http|https):\/\//.test(url))? url : (baseUrl + url);
                $.ajax({
                    type : 'GET',
                    url : action,
                    contentType: "application/json; charset=utf-8",
                    data : data,
                    success : function(res) {
                        deferred.resolve(res);
                    },
                    error : function (e) {
                        deferred.reject(e);
                    }
                });
                return deferred
				},
            POST:function (url,params) {
                var deferred = $.Deferred();
                var data = params?params:{};
                var action = (/^(http|https):\/\//.test(url))? url : (baseUrl + url);
                $.ajax({
                    type : 'POST',
                    url : action,
                    contentType: "application/json; charset=utf-8",
                    data : data,
                    success : function(res) {
                        deferred.resolve(res);
                    },
                    error : function (e) {
                        deferred.reject(e);
                    }
                });
                return deferred
            },
            /*获取地址栏参数*/
            GETREQUEST : function () {
                var url = decodeURIComponent(window.location.search); //获取url中"?"符后的字串
                var theRequest = new Object();
                if (url.indexOf("?") != -1) {
                    var str = url.substr(1);
                    strs = str.split("&");
                    for(var i = 0; i < strs.length; i ++) {
                        theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
                    }
                }
                return theRequest;
            }
        }
    });

})();