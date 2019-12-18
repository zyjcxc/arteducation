/**
 * js框架
 * @desc：对原生js复杂操作， 进行封装
 * @author mengqa
 * @date 2018.3.21
 */

var $$ = function() {
    this.init()
};

$$.prototype = {
    init:function(){
        this.fnExtend();
    },
    Ajax : function(options) {
        var defaultConfig = {
            dataType:'json',
            type:'post',
            async:false,
            error:function(error, status, thrown) {
                //  默认ajax处理错误的情况
            }
        };
        var ajaxConfig = $.extend(true, defaultConfig, options);
        ajaxConfig.success = function(data, textStatus, jqXHR) {
            options.success(data, textStatus, jqXHR);
            if (options.callback) {
                options.callback(data, textStatus, jqXHR);
            }
        };
        $.ajax(ajaxConfig);
    },
    fnExtend:function (){
        Function.prototype.before = function( func ) {
            var __self = this;
            return function() {
                if ( func.apply( this, arguments ) === false ) {
                    return false;
                }
                return __self.apply( this, arguments );
            }
        },
        Function.prototype.after = function( func ) {
            var __self = this;
            return function() {
                var ret = __self.apply( this, arguments );
                if( ret === false) {
                    return false;
                }
                func.apply( this, arguments );
                return ret;
            }
        }
    },
    ltrim:function(str){
        return str.replace(/(^\s*)/g,'');
    },
    rtrim:function(str){
        return str.replace(/(\s*$)/g,'');
    },
    trim:function(str){
        return str.replace(/(^\s*)|(\s*$)/g, '');
    },
    // 简单的数据绑定formateString
    formateString:function(str, data){
        return str.replace(/@\((\w+)\)/g, function(match, key){
            return typeof data[key] === "undefined" ? '' : data[key]});
    },
    //随机数
    random: function (begin, end) {
        return Math.floor(Math.random() * (end - begin)) + begin;
    },
    extendMany:function() {
        var key,i = 0,len = arguments.length,target = null,copy;
        if(len === 0){
            return;
        }else if(len === 1){
            target = this;
        }else{
            i++;
            target = arguments[0];
        }
        for(; i < len; i++){
            for(key in arguments[i]){
                copy = arguments[i][key];
                target[key] = copy;
            }
        }
        return target;
    },
    extend:function(tar,source) {
        for(var i in source){
            tar[i] = source[i];
        }
        return tar;
    },
    //数据类型检测
    isNumber:function (val){
        return typeof val === 'number' && isFinite(val)
    },
    isBoolean:function (val) {
        return typeof val ==="boolean";
    },
    isString:function (val) {
        return typeof val === "string";
    },
    isUndefined:function (val) {
        return typeof val === "undefined";
    },
    isObj:function (str){
        if(str === null || typeof str === 'undefined'){
            return false;
        }
        return typeof str === 'object';
    },
    isNull:function (val){
        return  val === null;
    },
    isArray:function (arr) {
        if(arr === null || typeof arr === 'undefined'){
            return false;
        }
        return arr.constructor === Array;
    }
};

$$ = new $$();

//事件框架
$$.extend($$,{
    on: function (id, type, fn){
        var dom = $$.isString(id)?document.getElementById(id):id;
        //W3C版本 --火狐 谷歌 等大多数浏览器
        if(dom.addEventListener ){
            om.addEventListener(type, fn, false);
        }else if(dom.attachEvent){
            //如果支持 --IE
            dom.attachEvent('on' + type, fn);
        }
    },
    onJq: function (jqObj, type, fn){
        jqObj.on(type, fn);
    },
    un:function(id, type, fn){
        var dom = $$.isString(id)?document.getElementById(id):id;
        if(dom.removeEventListener){
            dom.removeEventListener(type, fn);
        }else if(dom.detachEvent){
            dom.detachEvent(type, fn);
        }
    },
    trigger: function(id,type){
        var dom = $$.isString(id)?document.getElementById(id):id;
        if(dom.dispatchEvent){
            var evt = document.createEvent('Event');
            evt.initEvent(type, true, true);
            dom.dispatchEvent(evt);
            // IE
        } else if(dom.fireEvent){
            dom.fireEvent('on' + type);
        }
    },
    //事件基础
    getEvent:function(event){
        return event?event:window.event;
    },
    //获取目标
    GetTarget :function(event){
        var e = $$.getEvent(event);
        return e.target|| e.srcElement;
    },
    preventDefault:function(event){
        var event = $$.getEvent(event);
        if(event.preventDefault){
            event.preventDefault();
        }else{
            event.returnValue = false;
        }
    },
    stopPropagation:function(event){
        var event = $$.getEvent(event);
        if(event.stopPropagation){
            event.stopPropagation();
        }else{
            event.cancelBubble = true;
        }
    }
});

// json互转
$$.extend($$,{
    sjson:function (json) {
        return JSON.stringify(json);
    },
    json:function (str) {
        return eval(str);
    },
    /**
     * param 将要转为URL参数字符串的对象
     * key URL参数字符串的前缀
     * encode true/false 是否进行URL编码,默认为true
     *
     * return URL参数字符串
     */
    json2params: function(param, key, encode) {
            if(param==null) return '';
            var paramStr = '';
            var t = typeof (param);
            if (t == 'string' || t == 'number' || t == 'boolean') {
                paramStr += '&' + key + '=' + ((encode==null||encode) ? encodeURIComponent(param) : param);
            } else {
                for (var i in param) {
                    var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i);
                    paramStr += this.json2params(param[i], k, encode);
                }
            }
            return paramStr;
    }
});

$$.extend($$, {
    /**
    * 前往target
    */
    goTo : function(opt) {
        var opt = opt.length ? opt : ( opt || {} );
        var url = opt.url;
        var params = opt.params ? $$.json2params(opt.params) : '';
        var target = params ? url + '?' + params.substring(1) : url;
        window.location.href = target;
    },
    /**
     * 前往target
     */
    goToNormal : function(url) {
        window.location.href = url;
    },
    /**
     * 开窗
     */
    showModel : function(opt) {
        var width = opt.width || '500px';
        var height = opt.height || '400px';
        var content = opt.content || '';
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: [width, height], //宽高
            content: content
        });
        $('.layui-layer-content img').css('width', '100%');
    },
    /*
    *
    * 打开七牛公共iframe (多张)
    * */
    showQiniuMul :function () {
        var deferred = $.Deferred();
        layer.open({
            type:2,
            title:"上传图片",
            area:['90%',  $(top.document).height()-440+"px"],
            btn: ['确定', '关闭'],
            content:'/js/qiniu/iframes/iframe.html',
            yes: function(index, layero){ //或者使用btn1
                var content = layero.find("iframe")[0].contentWindow.GetDocList();
                layer.close(index);
                deferred.resolve(content);
            },
            cancel:function (index) {
                deferred.reject(index);
            }
        })
        return deferred;
    },
    /*
     *
     * 打开七牛 single (单张)
     * */
    showQiniuSgl :function () {
        var deferred = $.Deferred();
        layer.open({
            type:2,
            title:"上传图片",
            area:['90%',  $(top.document).height()-440+"px"],
            btn: ['确定', '关闭'],
            content:'/js/qiniu/iframes/single.html',
            yes: function(index, layero){ //或者使用btn1
                var content = layero.find("iframe")[0].contentWindow.GetDocList();
                if(content.length > 1) {
                    layer.msg('请上传单张图片');
                }else{
                    layer.close(index);
                    deferred.resolve(content);
                }
            },
            cancel:function (index) {
                deferred.reject(index);
            }
        })
        return deferred;
    },
    /**
     * 开窗
     */
    /*showDis : function(content) {
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['500px', '400px'], //宽高
            content: content
        });
        $('.layui-layer-content img').css('width', '100%');
    }*/
    showDis : function(self) {
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['500px', '400px'], //宽高
            content: $(self).prev().val()
        });
        $('.layui-layer-content img').css('width', '100%');
    },
    /**
     * 生成按钮
     * @returns {string}
     */
    htmlShowButton : function(opt) {
        var text = opt.text || '详情';
        var fun = "onclick='$$.showDis(this)'";
        var s = "<input type='hidden' value='" + opt.data + "' />";
        return s + "<button " + fun +" class='layui-btn layui-btn-mini'>" + text + "</button>";
    },
    /**
     * 生成跳转按钮
     * @returns {string}
     */
    htmlJumpButton : function(opt) {
        var text = opt.text || '查看详情';
        if (!opt.url) {
            console.log("参数错误");
            return;
        }
        var fun = "onclick='$$.goToNormal(\"" + opt.url + "\");'";
        return "<button " + fun +" class='layui-btn layui-btn-mini'>" + text + "</button>";
    },
    /**
     * 生成img标签
     * @returns {string}
     */
    htmlImgTag : function(data) {
        if (!data) {
            return '未上传';
        }
        var imgs = data.split(",");
        var html = '';
        var fun = "onclick='$$.showDis(this)'";
        var qn_domain = WEB_CONFIG._qnConfig.DOMAIN;
        if (imgs.length > 0) {
            $.each(imgs, function (index, value) {
                html = "<input type='hidden' value='<img src=\"" + qn_domain + value + "?imageMogr2/auto-orient\"/>'/> <img id='img' " + fun +" " +
                    "style='width: 40px; height: 30px; margin-left: 5px;' src='" + qn_domain + value + "?imageMogr2/auto-orient'/>";
            });
        }

        return html;
    },
    /**
     * 生成url
     * @param url
     * @param keyValues
     */
    makeWithUrl : function(url, keyValues) {
        if (keyValues.length === 0) {
            return url;
        }
        url += "?";
        for (var key in keyValues) {
            url += key + "=" + encodeURI(keyValues[key]) + "&";
        }
        return url.substring(0, url.length-1);
    }

});


// js数据库 字典值 ===> 中文常量
$$.extend($$, {
    /**
     * live_public_account_info == state字段
     * @returns {string}
     */
    PubAccountStateChinese : function(data) {
        // 1 正常  2删除  3 冻结
        if (data === '1' || data === 1) {
            return '正常';
        } else if (data === '2' || data === 2) {
            return '删除';
        } else if (data === '3' || data === 3) {
            return '冻结';
        }
        return '未知状态';
    },
    /**
     * live_public_account_info == sex字段
     * @returns {string}
     */
    SexChinese : function(data) {
        // m 男  g 女
        if (data === 'm') {
            return '男';
        } else if (data === 'g') {
            return '女';
        }
        return '未知性别';
    },
    /**
     * live_public_account_info == sex字段
     * @returns {string}
     */
    YesOrNo : function(data) {
        // 0 否  1 是
        if (parseInt(data) === 0) {
            return '否';
        } else if (parseInt(data) === 1) {
            return '是';
        }
        return '否';
    },
    /**
     * live_course_info == ifcharges字段
     * @returns {string}
     */
    ChargsChinese : function(data) {
        // 0 免费  1 收费
        if (data === '0' || data === 0) {
            return '免费';
        } else if (data === '1' || data === 1) {
            return '收费';
        }
        return '未知';
    },
    /**
     * live_public_account_info 操作 冻结， 解冻
     * @returns {string}
     */
    PublicStateOperatorChinese : function(data) {
        if (data === '1') {
            return '冻结';
        } else if (data === '3') {
            return '解冻';
        }
        return '未知';
    },
    /**
     * 价格 0时的显示
     * @returns {string}
     */
    PriceViewStr : function(data) {
        if (data === '0' || data === 0) {
            return '---';
        }
        return data;
    },
    /**
     * live_course_info 状态 1.正常 2.删除 3.已失效
     * @returns {string}
     */
    CourseInfoStateChinese : function(data) {
        if (data === '1' || data === 1) {
            return '正常';
        } else if (data === '2' || data === 2) {
            return '删除';
        } else if (data === '3' || data === 3) {
            return '已失效';
        }
        return '未知';
    },
    /**
     * live_home_content_info type 1.老师 2.课程 3.课节
     * @returns {string}
     */
    ContentTitleChinese : function(data) {
        if (data === '1' || data === 1) {
            return '老师';
        } else if (data === '2' || data === 2) {
            return '课程';
        } else if (data === '3' || data === 3) {
            return '课节';
        }
        return '未知';
    },
    /**
     * live_order_master_info pay_type 1.支付宝 2.微信
     * @returns {string}
     */
    PayTypeChinese : function(data) {
        if (data === '1' || data === 1) {
            return '支付宝';
        } else if (data === '2' || data === 2) {
            return '微信';
        }
        return '未知';
    },
    /**
     * live_order_master_info ordedr_status 1.学习中 2.已过期
     * @returns {string}
     */
    OrderStatusChinese : function(data) {
        if (data === '1' || data === 1) {
            return '学习中';
        } else if (data === '2' || data === 2) {
            return '已过期';
        }
        return '未知';
    },
    BookTypeChinese : function(data) {
        if (data === '0' || data === 0) {
            return '全部';
        } else if (data === '1' || data === 1) {
            return '白';
        } else if (data === '2' || data === 2) {
            return '红';
        }
        return '未知';
    },
    /**
     * live_push_message audience 0.全部人 1.个人
     * @returns {string}
     */
    AudienceChinese : function(data) {
        if (data === '0' || data === 0) {
            return '全部人';
        } else if (data === '1' || data === 1) {
            return '个人';
        }
        return '未知';
    },

    /**
     * live_public_account_info == sex字段
     * @returns {string}
     */
    NewsTypeChinese : function(data) {
        if (data === '1' || data === 1) {
            return '新闻';
        } else if (data === '2' || data === 2) {
            return '公告';
        }
        return '未知';
    },

    /**
     * 位置 1.首页顶部 2.其他页面顶部 3.首页底部
     * live_public_account_info == sex字段
     * @returns {string}
     */
    SiteChinese : function(data) {
        if (data === '1' || data === 1) {
            return '首页顶部';
        } else if (data === '2' || data === 2) {
            return '其他页面顶部';
        } else if (data === '3' || data === 3) {
            return '首页底部';
        }
        return '未知';
    },
    bigImg : function (pic) {
    //页面层-图片
    layer.open({
        type: 1,
        offset: '100px',
        title: false,
        closeBtn: 0,
        area: "70%",
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: "<div style='width: 100%;text-align: center'><img src='" + pic + "' style='width:auto;max-width: 100%;height: auto;'></div>"
    });
}
});