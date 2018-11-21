//form序列化为json
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//获取url后的参数值
function getUrlParam(key) {
	var href = window.location.href;
	var url = href.split("?");
	if(url.length <= 1){
		return "";
	}
	var params = url[1].split("&");
	
	for(var i=0; i<params.length; i++){
		var param = params[i].split("=");
		if(key == param[0]){
			return param[1];
		}
	}
}

// 检查登录状态
function loginInfo(){
	var user = "";
    $.ajax({
        type : 'get',
        url : '/sys/login',
        async: false,
        success : function(data){
            if(data != null && data != ""){
                user = data;
            }
        },
        error: function(xhr,textStatus,errorThrown){
            var msg = xhr.responseText;
            var response = JSON.parse(msg);
            $("#info").html(response.message);
        }
    });
    
    return user;
}


function getQiniuToken() {
    var ret = '';
    $.ajax({
        type: 'get',
        url: '/qiniu/token',
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if ($("#token").length > 0) {
                $("#token").val(data);
            }
            ret = data;
        }
    });
    return ret;
}

function getQiniuVideoToken() {
    var ret = '';
    $.ajax({
        type: 'get',
        url: '/qiniu/tokenVideo',
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            debugger
            if ($("#token").length > 0) {
                $("#token").val(data);
            }
            ret = data;
        }
    });
    return ret;
}



//获取url后的参数值(带解码), more复杂参数
function getUrlParamDecode(key, more) {
    var href = window.location.href;
    var url = href.split("?");
    if(url.length <= 1){
        return "";
    }
    var params = '';
    if (!more) {
        params = url[1].split("&");
        return getParam(params, key);
    } else {
        var parmasStr = window.location.href.substring(href.indexOf("?") + 1, href.length);
        // params = parmasStr.split("&");
        var v = parmasStr.substring(parmasStr.indexOf("returnUrl") + 10, parmasStr.length);
        return v;
    }
}

function getParam(params, key) {
    for(var i=0; i<params.length; i++){
        var param = [];
        if (params[i].indexOf("returnUrl") !== -1) {
            var k = params[i].substring(0, params[i].indexOf("="));
            var v = params[i].substring(params[i].indexOf("=") + 1, params[i].length);
            param.push(k);
            param.push(v);
        } else {
            param = params[i].split("=");
        }
        if(key == param[0]){
            return param[1];
        }
    }
}

//获取url后的参数值
function getUrlParam(key) {
    var href = window.location.href;
    var url = href.split("?");
    if(url.length <= 1){
        return "";
    }
    var params = url[1].split("&");

    for(var i=0; i<params.length; i++){
        var param = params[i].split("=");
        if(key == param[0]){
            return param[1];
        }
    }
}
