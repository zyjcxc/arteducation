
var layer;
layui.use('layer', function(){
    layer = layui.layer;
});

classification_list = (function ($, w) {

    var PAGE_FLAG = "classification_list";
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
                title : $("#title")
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
                    "url": WEB_CONFIG._action.ART_AUTHBOOK_ACTION,
                    "type": "get",
                    "data": function (d) {
                        d.id = page.$dom.id.val();
                        d.title = page.$dom.title.val();
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-6 hidden-xs'i><'col-sm-6 col-xs-12' p v>>",
                "columns": [
				{"data" : "id", "defaultContent" : ""},
				{"data" : "title", "defaultContent" : ""},
                {"data" : "picurl", "defaultContent" : "",
                    "render": function (data) {
                        var fun = "onclick='$$.bigImg(\"" + data + "\")'";
                        var img = "<img src='" + data +"' style='height:70px;width:auto;max-width:500px;' " + fun +"/ >";

                        return img;
                    }, "orderable" : false},
				{"data" : "createUserId", "defaultContent" : ""},
				{"data" : "createtime", "defaultContent" : ""},
				{"data" : "updatetime", "defaultContent" : ""},
                    {
                        "data": "",
                        "defaultContent": "",
                        "render": function (data, type, row) {
                            var id = row['id'];
                            // 编辑地址
                            var edit = buttonEdit(WEB_CONFIG._page.ART_AUTHBOOK_UPDATE_PAGE_WITH_PARAMS({
                                id : id,
                                returnUrl :  _self.get$Scope().cur_page
                            }), "", pers);

                            var del_fun = "onclick='updateState(\"" + id + "\", \"2\", \"删除\")'";
                            var del_op = "<button title='删除' class='layui-btn layui-btn-mini' " + del_fun +">删除</button>";

                            return edit + del_op;
                        }, "orderable" : false
                    }

                ],
                "order": [[0, "asc"]]
            });
    }

    /**
     * 添加事件
     */
    function addEventListeners() {
        var _self = this;
        $$.onJq(_self.getPageBtn().add, "click", function () {
            $$.goTo.call(_self, {
                url : WEB_CONFIG._page.ART_AUTHBOOK_UPDATE_PAGE_WITH_PARAMS({
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

function updateState(id, state, operator) {
    var obj = {
        id: id
    };

    layer.confirm('确定要' + operator + '吗？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            type : 'DELETE',
            url : WEB_CONFIG._action.ART_AUTHBOOK_ACTION + '/' + id ,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(obj),
            success : function() {
                classification_list.reloadDataTable();
                layer.msg(operator +"成功");
            }
        });

        layer.close(1);
    });

}
