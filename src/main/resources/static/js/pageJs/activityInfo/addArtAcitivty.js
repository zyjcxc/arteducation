/**
 * 活动 js 部分
 * @desc
 * 所属静态页面：pages/activityInfo/addartAcitivty.html
 * @author mengqa
 * @date 2018-11-21
 */
var layer;
layui.use('layer', function(){
    layer = layui.layer;
});

activityInfo_add = (function ($, w) {

    var PAGE_FLAG = "activityInfo_add";
    var PAGE_FORM_ID = "form";
    var $PAGE_FORM = $("#form");
    // 权限组
    // var pers = checkPermission();

    var id = getUrlParamDecode("id");
/*
    $PAGE_FORM.bootstrapValidator({
        message: '验证没通过',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            phone: {
                message: '',
                validators: {
                    notEmpty: {
                        message: '公众帐号不能为空'
                    },
                    regexp: {
                        regexp: /^1[3|4|5|8][0-9]\d{4,8}$/,
                        message: '公众帐号必须为手机号'
                    }
                }
            },
            email: {
                validators: {
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '老师昵称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 16,
                        message: '老师昵称长度过长'
                    }
                }
            },
            realName: {
                validators: {
                    notEmpty: {
                        message: '真实姓名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: '真实姓名长度过长'
                    },
                    regexp: {
                        regexp:  /^[\u4E00-\u9FA5]+$/,
                        message: '姓名不合法'
                    }
                }
            },
            idcard: {
                validators: {
                    regexp: {
                        regexp:  /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                        message: '身份证不合法'
                    }
                }
            },
            bankName: {
                validators: {
                    regexp: {
                        regexp:  /^[\u4E00-\u9FA5]+$/,
                        message: '开户姓名不合法'
                    }
                }
            },
            bankAccount: {
                validators: {
                    regexp: {
                        regexp:  /[\d]/,
                        message: '帐号不合法'
                    }
                }
            }
        }
    });
*/
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
					// icon : $("#icon"),
					// state : $("#state"),
					// createtime : $("#createtime"),
					// updatetime : $("#updatetime"),

            },
            $btn: {
                back: $("#backBtn"),
                save: $("#saveBtn")
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
        // initEditor.call(_self);
    }

    /**
     * 初始化Editor
     * <script type="text/javascript" src="../../js/kindeditor/kindeditor-all.js" charset="utf-8"></script>
     <script type="text/javascript" charset="utf-8" src="../../js/kindeditor/lang/zh-CN.js"></script>
     */
    /*function initEditor() {
            var _self = this;
            KindEditor.ready(function (K) {
                    _self.editor = K.create('#introduction', {
                    uploadJson: '/api/upload/uploadImg',
                    /!*fileManagerJson : '../jsp/file_manager_json.jsp',*!/
                    allowFileManager: true,
                    allowImageRemote: false,
                    items: [
                        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
                        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                        'anchor', 'link', 'unlink', '|', 'about'
                    ],
                    afterChange : function() {
                        //////////
                        //限制字数
                        var limitNum = 4000;  //设定限制字数
                        var pattern = '还可以输入' + limitNum + '字';
                        $('.word_surplus').html(pattern); //输入显示
                        if(this.count('text') > limitNum) {
                            pattern = ('字数超过限制，请适当删除部分内容');
                            //超过字数限制自动截取
                            var strValue = _self.editor.text();
                            strValue = strValue.substring(0,limitNum);
                            _self.editor.text(strValue);
                        } else {
                            //计算剩余字数
                            var result = limitNum - this.count('text');
                            pattern = '还可以输入' +  result + '字';
                        }
                        $('.word_surplus').html(pattern); //输入显示
                        ////////
                    }
                });
            });
        }*/

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

        $$.onJq(_self.getPageBtn().back, "click", function () {
            $$.goTo({
                url: getUrlParamDecode("returnUrl", true)
            });
        });

    }

    function save() {
        var _self = this;
        /*_self.editor.sync();*/
        var b = checkFormBefore.call(_self);
        if (!b) {
            return;
        }
        _self.getPageBtn().save.attr("disabled", true);
        $.ajax({
            type : 'post',
            url : WEB_CONFIG._action.ART_ACTIVITY_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify($PAGE_FORM.serializeObject()),
            success : function(data) {
                layer.msg("添加成功", {shift: -1, time: 1000}, function() {
                    $$.goTo({
                        url: getUrlParamDecode("returnUrl", true)
                    });
                });
            },
            error : function (d) {
                var msg = d.responseJSON.message ? d.responseJSON.message : "未知错误";
                layer.msg("操作失败, 错误: " + msg, {shift: -1, time: 3000}, function() {
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
        $.ajax({
            type : 'put',
            url : WEB_CONFIG._action.ART_ACTIVITY_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify($PAGE_FORM.serializeObject()),
            async: false,
            success : function(data) {
                layer.msg("修改成功", {shift: -1, time: 1000}, function() {
                    $$.goTo({
                        url: getUrlParamDecode("returnUrl", true)
                    });
                });
            },
            error : function (d) {
                var msg = d.responseJSON.message ? d.responseJSON.message : "未知错误";
                layer.msg("操作失败, 错误: " + msg, {shift: -1, time: 3000}, function() {
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

    return new _$();

})(jQuery, window);


/*
 /**
     * 数据初始化
     */
function initPageData() {
    var _self = this;
    $.ajax({
        type : 'get',
        url : WEB_CONFIG._action.ART_ACTIVITY_ACTION + '/' + _self.get$Scope().id,
        async : false,
        success : function(data) {
            _self.getPageDom().id.val(data.id);
            _self.getPageDom().name.val(data.name);
        }
    });
}

