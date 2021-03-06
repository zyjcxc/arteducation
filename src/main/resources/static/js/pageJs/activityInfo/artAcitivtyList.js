/**
 * 活动列表 js 部分
 * @desc
 * 所属静态页面：pages/activityInfo/artAcitivtyList.html
 * @author mengqa
 * @date 2018-11-21
 */
var layer;
layui.use('layer', function(){
    layer = layui.layer;
});

activityInfo_list = (function ($, w) {

    var PAGE_FLAG = "activityInfo_list";
    // var PAGE_FORM_ID = "form";
    // 权限组
    var pers = checkPermission();

    function _$() {
        this.pageFlag = PAGE_FLAG;
        this.page = {
            $scope: {
                // id :  $("#id").val() || '',
                cur_page: w.document.location.href
            },
            $dom: {
					// id : $("#id"),
					// name : $("#name"),
					// icon : $("#icon"),
					// state : $("#state"),
					// createtime : $("#createtime"),
					// updatetime : $("#updatetime"),

            },
            $btn: {
                search: $("#searchBt"),
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
                    "url": WEB_CONFIG._action.ART_ACTIVITY_ACTION,
                    "type": "get",
                    "data": function (d) {
									// d.id = page.$dom.id.val();
									// d.name = page.$dom.name.val();
									// // d.icon = page.$dom.icon.val();
									// // d.state = page.$dom.state.val();
									// d.createtime = page.$dom.createtime.val();
									// d.updatetime = page.$dom.updatetime.val();

                        // d.beginTime = page.$dom.beginTime.find("input").eq(0).val();
                        // d.endTime = page.$dom.endTime.find("input").eq(0).val();
                    }
                },
                "dom": "<'dt-toolbar'r>t<'dt-toolbar-footer'<'col-sm-6 hidden-xs'i><'col-sm-6 col-xs-12' p v>>",
                "columns": [
				{"data" : "id", "defaultContent" : ""},
				{"data" : "name", "defaultContent" : ""},
				// {"data" : "icon", "defaultContent" : ""},
				// {"data" : "state", "defaultContent" : ""},
				{"data" : "createtime", "defaultContent" : ""},
				// {"data" : "updatetime", "defaultContent" : ""},

                    {
                        "data": "",
                        "defaultContent": "",
                        "render": function (data, type, row) {
                            var id = row['id'];
                            // 编辑地址
                            var edit = buttonEdit(WEB_CONFIG._page.ACTIVITY_INFO_UPDATE_PAGE_WITH_PARAMS({
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

                            return "<div style='min-width:85px;'>"+edit + del_op+"</div>";
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
        /*$$.onJq(_self.getPageBtn().add, "click", function () {
            $$.goTo.call(_self, {
                url: WEB_CONFIG._page.ACTIVITY_INFO_ADD_PAGE
            });
        });*/
        $$.onJq(_self.getPageBtn().add, "click", function () {
           $$.goTo.call(_self, {
               url : WEB_CONFIG._page.ACTIVITY_INFO_ADD_PAGE_WITH_PARAMS({
                       returnUrl :  _self.get$Scope().cur_page
               })
           });
        });

        $$.onJq(_self.getPageBtn().search, "click", function () {
            _self.reloadDataTable();
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
            url : WEB_CONFIG._action.ACTIVITY_INFO_ACTION + '/' +id,
            success : function(data) {
                activityInfo_list.reloadDataTable();
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
            url : WEB_CONFIG._action.ART_ACTIVITY_ACTION ,
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(obj),
            success : function(data) {
                activityInfo_list.reloadDataTable();
                layer.msg(operator +"成功");
            }
        });

        layer.close(1);
    });

}
