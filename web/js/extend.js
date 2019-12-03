/**
 * jq 扩展的东西（参数）是以json对象的形式来出现的
 * 举例：
 * ajax 的 get 方法 --- $.EXTEND.GET（接口地址，参数）
 */
(function (){
    var baseUrl = 'http://cc.aerohuanyou.com:8081/api/qingqi/';
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
        return o;
    };
    jQuery.extend({
        EXTEND:{
            GET:function (url,params) {
                var deferred = $.Deferred();
                var data = params?params:{};
                data.token = '349961b9a1da47c4a9e99b8ae9c650cb';
                $.ajax({
                    type : 'get',
                    url : baseUrl + url,
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
                data.token = '349961b9a1da47c4a9e99b8ae9c650cb';
                $.ajax({
                    type : 'post',
                    url : baseUrl + url,
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