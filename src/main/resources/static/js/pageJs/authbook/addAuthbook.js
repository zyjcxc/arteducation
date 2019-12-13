
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
                title : $("#title"),
                content : $("#content")
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
    function hasContent() {
        var arr = [];
        arr.push(UM.getEditor('addAuthbookEditor').hasContents());
        UM.getEditor('addAuthbookEditor').focus();
        return (arr.join("\n"));
    }
    function setContent(isAppendTo) {
        UM.getEditor('addAuthbookEditor').setContent(isAppendTo);
    }
    function save() {
        var _self = this;
        var b = checkFormBefore.call(_self);
        if (!b) {
            return;
        }
        if(hasContent() === 'false'){
            layer.msg("请填写内容！", {shift: -1, time: 3000});
            return;
        }
        var params = $PAGE_FORM.serializeObject();
        if(!params.picurl || params.params === ''){
            layer.msg("请上传图片！", {shift: -1, time: 3000});
            return;
        }
        $.ajax({
            type : 'post',
            url : WEB_CONFIG._action.ART_AUTHBOOK_ACTION,
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
        var params = $PAGE_FORM.serializeObject();
        if(!params.picurl || params.picurl === ''){
            layer.msg("请上传图片！", {shift: -1, time: 3000});
            return;
        }
        _self.getPageBtn().save.attr("disabled", true);
        $.ajax({
            type : 'put',
            url : WEB_CONFIG._action.ART_AUTHBOOK_ACTION,
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
        $PAGE_FORM.bootstrapValidator();

        return $PAGE_FORM.data('bootstrapValidator').validate().isValid();
    }

    /**
     * 数据初始化
     */
    function initPageData() {
        var _self = this;
        $.ajax({
            type : 'get',
            url : WEB_CONFIG._action.ART_AUTHBOOK_ACTION + '/' + _self.get$Scope().id,
            async : false,
            success : function(data) {
                _self.getPageDom().id.val(data.id);
                _self.getPageDom().title.val(data.title);
                $("#banner").show().attr('src',data.picurl);
                setContent(data.content);
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
