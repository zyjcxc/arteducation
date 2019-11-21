/**
 * 特长生列表 js 部分
 * @desc
 * 所属静态页面：pages/artStudent/artStudentList.html
 * @author mengqa
 * @date 2018-04-03
 */
var layer;

layui.use('layer', function(){
    layer = layui.layer;
});


art_student_list = (function ($, w) {

    var PAGE_FLAG = "art_student_list";
    var pers = checkPermission();

    function _$() {
        this.pageFlag = PAGE_FLAG;
        this.page = {
            $scope: {
                cur_page : w.document.location.href
            },
            $dom: {
					cardNo : $("#cardNo"),
                    name : $("#name"),
                    level : $("#level"),
					sex : $("#sex"),
                    classificationId : $("#classificationId"),
                    activityId : $("#activityId"),
            },
            $btn: {
                search: $("#searchBt"),
                export: $("#export"),
                import: $("#import"),
                rest: $("#restBt")
            }
        };
        this.dataTable = initDateTable.call(this);

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
        getDataTable: function () {
            return this.dataTable;
        },
        reloadDataTable : function() {
            this.getDataTable().ajax.reload();
        }

    };

    /**
     * Dom相关初始化
     */
    function createPageDOM() {
        var _self = this;
        initClassInfo.call(_self);
        initActivityInfo.call(_self);
        addEventListeners.call(_self);
        // initTimePicker.call(_self);
    }

    function initClassInfo() {
        var _self = this;
        $.ajax({
            type : 'get',
            url : WEB_CONFIG._action.COURSE_CLASSIFICATION_FIND_ALL_ACTION,
            contentType: "application/json; charset=utf-8",
            success : function(data) {
                var $selectDom = _self.getPageDom().classificationId;
                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var $item = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
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
                if (data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var $item = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                        $selectDom.append($item);
                    }
                }
            }
        });
    }

    function initDateTable() {
        var _self = this;
        var page = _self.getPage();
        return ret =
            $('#dt-table').DataTable({
                "searching": false,
                "processing": false,
                "serverSide": true,
                "language": {
                    "url": "/js/plugin/datatables/Chinese.lang"
                },
                "ajax": {
                    "url": WEB_CONFIG._action.ART_STUDENT,
                    "type": "get",
                    "data": function (d) {
                        d.name = page.$dom.name.val();
                        d.sex  = page.$dom.sex.val();
                        d.level  = page.$dom.level.val();
                        d.cardNo  = page.$dom.cardNo.val();
                        d.classificationId  = page.$dom.classificationId.val();
                        d.activityId  = page.$dom.activityId.val();
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-6 hidden-xs'i><'col-sm-6 col-xs-12' p v>>",
                "columns": [
				{"data" : "id", "defaultContent" : "", "orderable" : false},
				{"data" : "name", "defaultContent" : ""},
                {"data" : "sex", "defaultContent" : "",
                    "render": function (data) {
                        return $$.SexChinese(data);
                    }
                },
                {"data" : "country", "defaultContent" : ""},
                {"data" : "nation", "defaultContent" : ""},
                {"data" : "born", "defaultContent" : ""},
                {"data" : "classificationName", "defaultContent" : ""},
                {"data" : "activityName", "defaultContent" : ""},
                {"data" : "level", "defaultContent" : ""},
                // {"data" : "wenpingLevel", "defaultContent" : "",
                //     "render": function (data) {
                //         return $$.YesOrNo(data);
                //     }
                // },
                {"data" : "cardNo", "defaultContent" : ""},

                {
                    "data": "",
                    "defaultContent": "",
                    "render": function (data, type, row) {
                        var id = row['id'];
                        // 编辑地址
                        var edit = buttonEdit(WEB_CONFIG._page.ART_STUDENT_ADD_PAGE_WITH_PARAMS({
                            id : id,
                            returnUrl :  _self.get$Scope().cur_page
                        }), "", pers);

                        // 逻辑删除操作
                        var del_fun = "onclick='updateState(\"" + id + "\", \"2\", \"删除\")'";
                        var del_op = "<button title='删除' class='layui-btn layui-btn-mini' " + del_fun +">删除</button>";

                        return edit + del_op;
                    }, "orderable" : false
                }

                ],
                "order": [[0, "asc"]]
            });
    }

    function exportGrid() {
        var _self = this;
        var link = getLink.call(_self);
        window.open(link);
    }

    function importList() {
        alert(2);
        var _self = this
        var file_obj = document.getElementById('fafafa').files[0];



    }

    function getLink() {
        var _self = this;
        var init = WEB_CONFIG._action.EXPORT_ART_STUDENT_ACTION + "?";
        var name = _self.getPageDom().name.val() || '';
        var level = _self.getPageDom().level.val() || '';
        var cardNo = _self.getPageDom().cardNo.val() || '';
        var classificationId = _self.getPageDom().classificationId.val() || '';
        var sex = _self.getPageDom().sex.val() || '';
        init += "name=" + name + "&level=" + level
            + "&sex=" + sex
            + "&classificationId=" + classificationId
            + "&cardNo=" + cardNo;
        return init;
    }

    /**
     * 注册事件
     */
    function addEventListeners() {
        var _self = this;

        $$.onJq(_self.getPageBtn().search, "click", function () {
            _self.reloadDataTable();
        });
        $$.onJq(_self.getPageBtn().export, "click", function () {
            exportGrid.call(_self);
        });
        $$.onJq(_self.getPageBtn().rest, "click", function () {
            w.location.reload();
        });
        $$.onJq(_self.getPageBtn().import, "click", function () {
            importList.call(_self);
        });
    }

    return new _$();

})(jQuery, window);

function updateState(id, state, operator) {
    var obj = {
        id: id,
        state: state
    };

    layer.confirm('确定要' + operator + '吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            type : 'post',
            url : WEB_CONFIG._action.ART_STUDENT_LOGIC_DELETE_ACTION,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(obj),
            success : function(data) {
                art_student_list.reloadDataTable();
                layer.msg(operator +"成功");
            }
        });

        layer.close(1);
    });

}

function importData() {
    var file_obj = document.getElementById('fafafa').files[0];
    var fd = new FormData();
    fd.append('excelFile', file_obj);
    $.ajax({
        url:'/api/upload/uploadExcel',
        type:'POST',
        data:fd,
        processData:false,  //tell jQuery not to process the data
        contentType: false,  //tell jQuery not to set contentType
        //这儿的三个参数其实就是XMLHttpRequest里面带的信息。
        success:function (arg,a1,a2) {
            // console.log(arg);
            // console.log(a1);
            if (a1 == "success") {
                layer.msg("导入成功", {shift: -1, time: 1000}, function() {
                    window.location.reload();
                });

            }
        }

    })
}