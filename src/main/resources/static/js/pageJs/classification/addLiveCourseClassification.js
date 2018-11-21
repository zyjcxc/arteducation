/**
 * 课程分类 js 部分
 * @desc
 * 所属静态页面：pages/classification/addliveCourseClassification.html
 * @author mengqa
 * @date 2018-04-05
 */
var layer;
layui.use('layer', function(){
    layer = layui.layer;
});

var uploader1 = '';
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
					name : $("#name"),
                    icon : $("#icon"),
                    type : $("#type"),
                    token : $("#token"),
                    imgs : $("#imgs"),
                    preview : $("#preview"),
            },
            $btn: {
                back: $("#backBtn"),
                save: $("#saveBtn"),
                up1: $("#pickfiles2"),
            }
        };

        this.uploadScope = {
            token : getQiniuToken(),
            type : this.page.$dom.type.val()
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

        initFileUploader.call(_self);
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
        // img input 赋值
        setImgsValue.call(_self, _self.getPageDom().icon, _self.getPageDom().imgs);

        // if (!_self.getPageDom().icon.val()) {
        //     layer.msg("请上传课程封面");
        //     return;
        // }
        // _self.getPageBtn().save.attr("disabled", true);
        $.ajax({
            type : 'post',
            url : WEB_CONFIG._action.COURSE_CLASSIFICATION_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify($PAGE_FORM.serializeObject()),
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
        // img input 赋值
        setImgsValue.call(_self, _self.getPageDom().icon, _self.getPageDom().imgs);
        _self.getPageBtn().save.attr("disabled", true);
        $.ajax({
            type : 'put',
            url : WEB_CONFIG._action.COURSE_CLASSIFICATION_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify($PAGE_FORM.serializeObject()),
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
            url : WEB_CONFIG._action.COURSE_CLASSIFICATION_ACTION + '/' + _self.get$Scope().id,
            async : false,
            success : function(data) {
                _self.getPageDom().id.val(data.id);
                _self.getPageDom().name.val(data.name);
                if (data.icon) {
                    // 赋值img
                    _self.getPageDom().icon.val(data.icon);
                    makeImgDiv.call(_self, data.icon, _self.getPageDom().imgs);
                }
            }
        });
    }

    function makeImgDiv(data, dom) {
        // var _self = this;
        var domain = WEB_CONFIG._qnConfig.DOMAIN;
        var htmlText = "";
        var arr = (data).split(',');
        if (arr.length > 0 && arr[0] != '') {
            $.each(arr, function (index, domEle) {
                var srcValue = domEle.replace(domain, "");
                htmlText += "<div style='display:inline-block;position: relative;'><img style='width: 80px;height: 80px;' src='" + domain + domEle + "?imageMogr2/auto-orient' />  <div style='position: absolute;top: 0px;right: 0px;width: 20px;height: 20px;display: block;background-size:cover;background-image:url(http://statics.shop.nightpalace.cn/1512615801392)' onclick='removeThumbnail(" + srcValue + ", this)'/></div>";
            });
            $(dom).append(htmlText);
        }
    }

    /**
     * 初始化FileUploader
     */
    function initFileUploader() {
        var _self = this;
        uploader1 = uplodFile({
            // 缩略图上传区域
            finishShowId: 'imgs',
            // 预览区域
            prevId : 'preview',
            // 容器区域
            container :'container',
            btn1Id : 'pickfiles',
            filelist : 'filelist',
            // 选择按钮
            pickBtnId: 'pickfiles'
        });
        _self.getPageDom().preview.hide();
    }

    // img赋值
    function setImgsValue(formInput, img_div) {
        // var _self = this;
        var value = '';
        var imgs = img_div.find("img");
        if (imgs.length > 0) {
            $.each(imgs, function (index, element) {
                value = element.src.replace(WEB_CONFIG._qnConfig.DOMAIN, "");
                value = value.substring(0, value.indexOf("?"));
                value += ",";
            });
            value = value.substring(0, value.length - 1);
            if (formInput.length > 0) {
                formInput.val(value);
            }
        }
    }

    //上传
    function upload(type, uploader) {
        // var _self = this;
        $("#type").val(type);
        if (uploader.files.length > 0) {
            uploader.start();
        } else {
            $.messager.alert('提示', "请选择上传对象", 'info');
        }
    }

    return new _$();

})(jQuery, window);
