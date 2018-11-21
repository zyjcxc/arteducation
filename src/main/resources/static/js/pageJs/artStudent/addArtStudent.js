/**
 * 添加特长生 js 部分
 * @desc
 * 所属静态页面：pages/artStudent/addArtStudent.html
 * @author mengqa
 * @date 2018-03-26
 */
var layer;
layui.use('layer', function(){
    layer = layui.layer;

});

layui.use(['layer','laydate'], function(){
    layer = layui.layer;
    var laydate = layui.laydate;
    laydate.render({
        elem: '#born',
        type: 'date'
    });
});

pubAccount_add = (function ($, w) {

    var PAGE_FLAG = "pubAccount_add";
    var $PAGE_FORM = $("#form");

    var id = getUrlParamDecode("id");

    // $PAGE_FORM.bootstrapValidator({
    //     message: '验证没通过',
    //     feedbackIcons: {
    //         valid: 'glyphicon glyphicon-ok',
    //         invalid: 'glyphicon glyphicon-remove',
    //         validating: 'glyphicon glyphicon-refresh'
    //     },
    //     fields: {
    //         phone: {
    //             message: '',
    //             validators: {
    //                 notEmpty: {
    //                     message: '公众帐号不能为空'
    //                 },
    //                 regexp: {
    //                     regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
    //                     message: '公众帐号必须为手机号'
    //                 }
    //             }
    //         },
    //         email: {
    //             validators: {
    //                 emailAddress: {
    //                     message: '邮箱地址格式有误'
    //                 }
    //             }
    //         },
    //         name: {
    //             validators: {
    //                 notEmpty: {
    //                     message: '老师昵称不能为空'
    //                 },
    //                 stringLength: {
    //                     min: 1,
    //                     max: 16,
    //                     message: '老师昵称长度过长'
    //                 }
    //             }
    //         },
    //         realName: {
    //             validators: {
    //                 notEmpty: {
    //                     message: '真实姓名不能为空'
    //                 },
    //                 stringLength: {
    //                     min: 1,
    //                     max: 10,
    //                     message: '真实姓名长度过长'
    //                 }
    //                 /*regexp: {
    //                     regexp:  /^[\u4E00-\u9FA5]+$/,
    //                     message: '姓名不合法'
    //                 }*/
    //             }
    //         },
    //         idcard: {
    //             validators: {
    //                 regexp: {
    //                     regexp:  /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
    //                     message: '身份证不合法'
    //                 }
    //             }
    //         },
    //         // bankName: {
    //         //     validators: {
    //         //         regexp: {
    //         //             regexp:  /^[\u4E00-\u9FA5]+$/,
    //         //             message: '开户姓名不合法'
    //         //         }
    //         //     }
    //         // },
    //         bankAccount: {
    //             validators: {
    //                 regexp: {
    //                     regexp:  /[\d]/,
    //                     message: '帐号不合法'
    //                 }
    //             }
    //         }
    //     }
    // });

    function _$() {
        // 是否为修改
        this.editFlag = id;
        this.pageFlag = PAGE_FLAG;
        this.page = {
            $scope: {
                id :  id,
                classificationId : '',
                activityId : '',
            },
            $dom: {
                classificationId : $("#classificationId"),
                activityId : $("#activityId"),
                id : $("#id"),
                name : $("#name"),
                country : $("#country"),
                nation : $("#nation"),
                sex : $("#sex"),
                cardNo : $("#cardNo"),
                level : $("#level"),
                born : $("#born"),
                state : $("#state")
            },
            $btn: {
                back: $("#backBtn"),
                save: $("#saveBtn")
            }
        };

        // this.uploadScope = {
        //     token : getQiniuToken(),
        //     type : this.page.$dom.type.val()
        // };
        if (!this.editFlag) {
            // 默认正常状态
            this.getPageDom().state.val("1");
        }

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
        },
    };

    /**
     * Dom相关初始化
     */
    function createPageDOM() {
        var _self = this;

        initClassInfo.call(_self);
        initActivityInfo.call(_self);
        if (_self.editFlag) {

            initPageData.call(_self);
        }




        addEventListeners.call(_self);
    }

    /**
     * 初始化分类信息
     */
    function initClassInfo() {
        var _self = this;
        $.ajax({
            type : 'get',
            url : WEB_CONFIG._action.COURSE_CLASSIFICATION_FIND_ALL_ACTION,
            contentType: "application/json; charset=utf-8",
            success : function(data) {
                var $selectDom = _self.getPageDom().classificationId;
                var selectId = _self.get$Scope().classificationId;
                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var $item = '';
                        if (selectId && selectId === data[i].id) {
                            $item = $("<option value='" + data[i].id + "' selected>" + data[i].name + "</option>");
                        } else {
                            $item = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                        }
                        $selectDom.append($item);
                    }
                }
            }
        });
    }

    function initActivityInfo() {
        var _self = this;
        $.ajax({
            type : 'get',
            url : WEB_CONFIG._action.ART_ACTIVITY_FIND_ALL_ACTION,
            contentType: "application/json; charset=utf-8",
            success : function(data) {
                var $selectDom = _self.getPageDom().activityId;
                var selectId = _self.get$Scope().activityId;
                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var $item = '';
                        if (selectId && selectId === data[i].id) {
                            $item = $("<option value='" + data[i].id + "' selected>" + data[i].name + "</option>");
                        } else {
                            $item = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                        }
                        $selectDom.append($item);
                    }
                }
            }
        });
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

        $$.onJq(_self.getPageBtn().back, "click", function () {
            $$.goTo({
                url: WEB_CONFIG._page.ART_STUDENT_LIST_PAGE
            });
        });

    }

    function save() {
        var _self = this;
        var b = checkFormBefore.call(_self);
        if (!b) {
            return;
        }
        _self.getPageBtn().save.attr("disabled", true);
        $.ajax({
            type : 'post',
            url : WEB_CONFIG._action.ART_STUDENT_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify($PAGE_FORM.serializeObject()),
            async: false,
            success : function(data) {
                $$.goTo({
                    url: WEB_CONFIG._page.ART_STUDENT_ADD_PAGE
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
            url : WEB_CONFIG._action.ART_STUDENT_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify($PAGE_FORM.serializeObject()),
            async: false,
            success : function(data) {
                layer.msg("修改成功", {shift: -1, time: 1000}, function() {
                    $$.goTo({
                        url: WEB_CONFIG._page.ART_STUDENT_LIST_PAGE
                    });
                });
            }
        });
    }

    /**
     * 表单前置验证
     */
    function checkFormBefore() {
        var _self = this;

        // if (!checkOnlyOne.call(_self)) {
        //     layer.msg("添加失败, 账号已经存在");
        //     return;
        // }

        // $PAGE_FORM.bootstrapValidator();

        // if(!$PAGE_FORM.data('bootstrapValidator').validate().isValid()){
        //     return false;
        // }

        return true;
    }


    /**
     * 数据初始化
     */
    function initPageData() {
        var _self = this;
        $.ajax({
            type : 'get',
            url : WEB_CONFIG._action.ART_STUDENT_ACTION + '/' + _self.get$Scope().id,
            async : false,
            success : function(data) {
                _self.getPageDom().id.val(data.id);
                _self.getPageDom().name.val(data.name);
                _self.getPageDom().sex.val(data.sex);
                _self.getPageDom().country.val(data.country);
                _self.getPageDom().nation.val(data.nation);
                // _self.getPageDom().classificationId.val(data.classificationId);
                _self.get$Scope().classificationId = data.classificationId;
                _self.getPageDom().cardNo.val(data.cardNo);
                _self.getPageDom().level.val(data.level);
                _self.getPageDom().born.val(data.born);
                _self.getPageDom().state.val(data.state);

            }
        });
    }

    return new _$();

})(jQuery, window);
