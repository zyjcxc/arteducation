/**
 * jq 扩展的东西（参数）是以json对象的形式来出现的
 * 举例：
 * ajax 的 get 方法 --- $.EXTEND.GET（接口地址，参数）
 */
(function (){
	var baseUrl = 'http://10.30.50.152:9808/web/';
    
    
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
                data.token = 'bea32763ad3d4c629f45634ba4959e91';
                var action = /^(http|https):\/\//.test(url) ? url : (baseUrl + url);
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
                console.log(params);
                var action = /^(http|https):\/\//.test(url) ? url : (baseUrl + url);
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
            }
        }
    });

})();