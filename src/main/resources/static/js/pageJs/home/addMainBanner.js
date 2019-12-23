
var layer;
layui.use('layer', function(){
    layer = layui.layer;
});

classification_add = (function ($, w) {

    var PAGE_FLAG = "classification_add";
    var $PAGE_FORM = $("#form");

    var id = getUrlParamDecode("id");

    function _$() {
        // 是否为修改
        this.editFlag = id;
        this.pageFlag = PAGE_FLAG;
        this.page = {
            $scope: {
                id : id
            },
            $dom: {
                id : $("#id"),
                picurl : $("#picurl"),
                site : $("#site"),
                sort : $("#sort"),
                url : $("#url"),
                recommend : $("#recommend"),
                name : $("#name"),
                title : $("#title"),
            },
            $btn: {
                back: $("#backBtn"),
                save: $("#saveBtn"),
                up1: $("#pickfiles2"),
            }
        };


        createPageDOM.call(this);


    }

    _$.prototype = {

        getPageFlag: function () {
            return this.pageFlag;
        },
        getPage: function () {
            return this.page;
        },
        getPageDom: function () {
            return this.page.$dom;
        },
        get$Scope: function () {
            return this.page.$scope;
        },
        getPageBtn: function () {
            return this.page.$btn;
        }
    };

    /**
     * Dom相关初始化
     */
    function createPageDOM() {
        var _self = this;
        if (_self.editFlag) {
            initPageData.call(_self);
        }
        addEventListeners.call(_self);

    }

    /**
     * 注册事件
     */
    function addEventListeners() {
        var _self = this;

        if (_self.editFlag) {
            $$.onJq(_self.getPageBtn().save, "click", function () {
                update.call(_self);
            });
        } else {
            $$.onJq(_self.getPageBtn().save, "click", function () {
                save.call(_self);
            });
        }

        $$.onJq(_self.getPageBtn().up1, "click", function () {
            upload.call(_self, 'thumbnail', uploader1);
        });

        $$.onJq(_self.getPageBtn().back, "click", function () {
            $$.goTo({
                url: getUrlParamDecode("returnUrl", true)
            });
        });

    }

    function save() {
        var _self = this;
        var b = checkFormBefore.call(_self);
        if (!b) {
            return;
        }
        var params = $PAGE_FORM.serializeObject();
        if(!params.picurl || params.params === ''){
            layer.msg("请上传图片！", {shift: -1, time: 3000});
            return;
        }
        $.ajax({
            type : 'post',
            url : WEB_CONFIG._action.MAIN_BANNER_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(params),
            success : function(data) {
                layer.msg("添加成功", {shift: -1, time: 1000}, function() {
                    $$.goTo({
                        url: getUrlParamDecode("returnUrl", true)
                    });
                });
            }
        });
    }

    function update() {
        var _self = this;
        var b = checkFormBefore.call(_self);
        if (!b) {
            return;
        }
        _self.getPageBtn().save.attr("disabled", true);
        var params = $PAGE_FORM.serializeObject();
        if(!params.picurl || params.params === ''){
            layer.msg("请上传图片！", {shift: -1, time: 3000});
            return;
        }
        $.ajax({
            type : 'put',
            url : WEB_CONFIG._action.MAIN_BANNER_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(params),
            async: false,
            success : function(data) {
                layer.msg("修改成功", {shift: -1, time: 1000}, function() {
                    $$.goTo({
                        url: getUrlParamDecode("returnUrl", true)
                    });
                });
            }
        });
    }

    /**
     * 表单前置验证
     */
    function checkFormBefore() {
        $PAGE_FORM.bootstrapValidator({
            fields:{
                url:{
                    validators:{
                        notEmpty: {
                            message: '跳转url不能为空'
                        },
                        regexp: { //正则表达式
                            regexp: /https|http|ftp|rtsp|mms?:\/\//,
                            message: '请填写正确的跳转url'
                        },
                    }
                }
            }
        });
        return $PAGE_FORM.data('bootstrapValidator').validate().isValid();

    }

    /**
     * 数据初始化
     */
    function initPageData() {
        var _self = this;
        $.ajax({
            type : 'get',
            url : WEB_CONFIG._action.MAIN_BANNER_ACTION + '/' + _self.get$Scope().id,
            async : false,
            success : function(data) {
                $("#banner").show().attr('src',data.picurl);
                _self.getPageDom().id.val(data.id);
                _self.getPageDom().picurl.val(data.picurl);
                _self.getPageDom().site.val(data.site);
                _self.getPageDom().sort.val(data.sort);
                _self.getPageDom().url.val(data.url);
                _self.getPageDom().title.val(data.title);
                _self.getPageDom().name.val(data.name);
                _self.getPageDom().recommend.val(data.recommend);
                changeSelect(data.site);
            }
        });
    }

    $("#open").click(function () {
        $$.showQiniuSgl().then(function (data) {
            buildImgs(data[0]);
        })
    });
    function buildImgs(data) {
        $("#banner").show().attr('src',data.url);
        $("#picurl").val(data.url);
    }


    return new _$();

})(jQuery, window);

function changeSelect(v) {
    $("#topic").hide();
    $("#link").hide();
    if(v == 1){
        $("#sizeTxt").text("1900*440");
    }else if(v == 2){
        $("#sizeTxt").text("800*400");
    }else{
        $("#sizeTxt").text("1900*200");
        $("#topic").show();
        $("#link").show();
    }
}