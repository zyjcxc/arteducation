function uploader(opt) {
    var uploader = Qiniu.uploader({
        runtimes: 'html5,flash,html4',    //上传模式,依次退化
        browse_button: opt.btn,       //上传选择的点选按钮，**必需**
        // uptoken_url:sy.qiniutoken,            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
        uptoken : opt.token, //若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
        // unique_names: true, // 默认 false，key为文件名。若开启该选项，SDK为自动生成上传成功后的key（文件名）。
        // save_key: true,   // 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK会忽略对key的处理
        domain:'http://wxwall.nightpalace.cn',   //bucket 域名，下载资源时用到，**必需**
        get_new_uptoken: false,  //设置上传文件的时候是否每次都重新获取新的token
        container: opt.container,           //上传区域DOM ID，默认是browser_button的父元素，
        max_file_size: '10mb',           //最大文件体积限制
        flash_swf_url: 'js/plupload/Moxie.swf',  //引入flash,相对路径
        max_retries: 3,                   //上传失败最大重试次数
        dragdrop: false,                   //开启可拖曳上传
        chunk_size: '4mb',                //分块上传时，每片的体积
        auto_start: true,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
        multi_selection: false,
        //文件类型过滤，这里限制为图片类型
        filters: {
            mime_types : [
                {title : "Image files", extensions: "jpg,jpeg,gif,png"}
            ]
        },
        init: {
            'FilesAdded': function(up, files) {
                //do something
            },
            'BeforeUpload': function(up, file) {
                //do something
            },
            'UploadProgress': function(up, file) {
                //可以在这里控制上传进度的显示
                //可参考七牛的例子
            },
            'UploadComplete': function() {
                //do something
            },
            'FileUploaded': function(up, file, info) {
                if (opt.success) {
                    opt.success(up, file, info);
                } else {
                    var key = JSON.parse(info.response).key;
                    widget.add("img_preview", {
                        key : key,
                        node : 'default_node',
                        input : 'default_input'
                    });
                }
            },
            'Error': function(up, err, errTip) {
                alert(errTip);
            },
            'Key': function(up, file) {
                var key = new Date().getTime();
                // do something with key here
                return key
            }
        }
    });
    return uploader;
}

(function(w){
    function uploaderObj() {
        this.uploaderObj = {};
    }
    uploaderObj.prototype.register = function (name, comment) {
        this.uploaderObj[name] = comment;
    };
    uploaderObj.prototype.add = function (name, obj) {
        if (this.uploaderObj[name]) {
            this.uploaderObj[name].node = obj.node;
            return this.uploaderObj[name].init(obj);
        }
        return false;
    };
    w.uploaderObj = new uploaderObj();
})(window);