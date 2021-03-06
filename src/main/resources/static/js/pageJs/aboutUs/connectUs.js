
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
                    "url": WEB_CONFIG._action.ART_GUEST_INFO,
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
                    {"data" : "phone", "defaultContent" : ""},
                    {"data" : "email", "defaultContent" : ""},
                    {"data" : "address", "defaultContent" : ""},
                    {"data" : "message", "defaultContent" : ""},
                    {"data" : "createtime", "defaultContent" : ""},
                    {"data" : "updatetime", "defaultContent" : ""}
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
                url : WEB_CONFIG._page.ART_AWARD_UPDATE_PAGE_WITH_PARAMS({
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

