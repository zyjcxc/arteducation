/**
 * 课程分类列表 js 部分
 * @desc
 * 所属静态页面：pages/classification/liveCourseClassificationList.html
 * @author mengqa
 * @date 2018-04-05
 */
var layer;
layui.use('layer', function(){
    layer = layui.layer;
});

classification_list = (function ($, w) {

    var PAGE_FLAG = "classification_list";
    // var PAGE_FORM_ID = "form";
    // 权限组
    var pers = checkPermission();

    function _$() {
        this.pageFlag = PAGE_FLAG;
        this.page = {
            $scope: {
                cur_page: w.document.location.href
            },
            $dom: {
                id : $("#id"),
                name : $("#name")
            },
            $btn: {
                search: $("#searchBt"),
                rest: $("#restBt"),
                add: $("#addBtn")
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
        addEventListeners.call(_self);
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
                    "url": WEB_CONFIG._action.COURSE_CLASSIFICATION_ACTION,
                    "type": "get",
                    "data": function (d) {
                        d.id = page.$dom.id.val();
                        d.name = page.$dom.name.val();
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-6 hidden-xs'i><'col-sm-6 col-xs-12' p v>>",
                "columns": [
				{"data" : "id", "defaultContent" : ""},
				{"data" : "name", "defaultContent" : ""},
				{"data" : "createtime", "defaultContent" : ""},
                    {
                        "data": "",
                        "defaultContent": "",
                        "render": function (data, type, row) {
                            var id = row['id'];
                            // 编辑地址
                            var edit = buttonEdit(WEB_CONFIG._page.COURSE_CLASSIFICATION_UPDATE_PAGE_WITH_PARAMS({
                                id : id,
                                returnUrl :  _self.get$Scope().cur_page
                            }), "", pers);
                            // 物理删除操作
                            // var del = buttonDel(id, "", pers);
                            // 逻辑删除操作
                            var del_fun = "onclick='updateState(\"" + id + "\", \"2\", \"删除\")'";
                            var del_op = "<button title='删除' class='layui-btn layui-btn-mini' " + del_fun +">删除</button>";

                            // 特殊操作
                            /*var sp1 = $$.htmlJumpButton({
                                text : '课程列表',
                                url : WEB_CONFIG._page.COURSE_LIST_PAGE_WITH_PARAMS({
                                    teacherId : id
                                })
                            });*/

                            return edit + del_op;
                        }, "orderable" : false
                    }

                ],
                "order": [[0, "asc"]]
            });
    }

    /**
     * 注册事件
     */
    function addEventListeners() {
        var _self = this;
        $$.onJq(_self.getPageBtn().add, "click", function () {
            $$.goTo.call(_self, {
                url : WEB_CONFIG._page.COURSE_CLASSIFICATION_ADD_PAGE_WITH_PARAMS({
                        returnUrl :  _self.get$Scope().cur_page
                })
            });
        });

        $$.onJq(_self.getPageBtn().search, "click", function () {
            _self.reloadDataTable();
        });

        $$.onJq(_self.getPageBtn().rest, "click", function () {
            w.location.reload();
        });

    }

    return new _$();

})(jQuery, window);

/*
// 物理删除
function del(id){
    layer.confirm('确定要删除吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            type : 'delete',
            url : WEB_CONFIG._action.COURSE_CLASSIFICATION_ACTION + '/' +id,
            success : function(data) {
                classification_list.reloadDataTable();
                layer.msg("删除成功");
            }
        });

        layer.close(1);
    });
}
*/

function updateState(id, state, operator) {
    var obj = {
        id: id,
        state: state
    };

    layer.confirm('确定要' + operator + '吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            type : 'put',
            url : WEB_CONFIG._action.COURSE_CLASSIFICATION_ACTION ,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(obj),
            success : function(data) {
                classification_list.reloadDataTable();
                layer.msg(operator +"成功");
            }
        });

        layer.close(1);
    });

}
