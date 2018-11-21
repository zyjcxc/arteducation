// var uploader;
var str="normal"; //一个图片时为normal
var state=1; //是换图片
var time=0;  //第几次进来
var lastStr;

function uplodFile(opt) {
    var uploader = '';
    uploader = Qiniu.uploader({
        runtimes: 'html5,flash,html4',    //上传模式,依次退化
        browse_button: [opt.pickBtnId,'pickfilesbig'],       //上传选择的点选按钮，**必需**
        // uptoken_url:sy.qiniutoken,            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
        uptoken : $("#token").val(), //若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
        // unique_names: true, // 默认 false，key为文件名。若开启该选项，SDK为自动生成上传成功后的key（文件名）。
        // save_key: true,   // 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK会忽略对key的处理
        domain:'http://wxwall.nightpalace.cn',   //bucket 域名，下载资源时用到，**必需**
        get_new_uptoken: false,  //设置上传文件的时候是否每次都重新获取新的token
        container: opt.container,           //上传区域DOM ID，默认是browser_button的父元素，
        max_file_size: '100mb',           //最大文件体积限制
        flash_swf_url: 'js/plupload/Moxie.swf',  //引入flash,相对路径
        max_retries: 3,                   //上传失败最大重试次数
        dragdrop: true,                   //开启可拖曳上传
        drop_element: opt.container,        //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
        chunk_size: '4mb',                //分块上传时，每片的体积
        auto_start: false,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
        multi_selection: false,
        filters: {
            mime_types : [ //只允许上传图片
                { title : "Image files", extensions : "jpeg,jpg,png" }]
        },
        init: {
            'FilesAdded': function (up, files) {

                console.info("add");
                /* plupload.each(files, function (file) {
                     //BPCLOSEALTWATING= layer.load(2);
                     // 文件添加进队列后,处理相关的事情
                 });*/
                var btn1 = $("#" +opt.btn1Id);
                var filelistDom = $("#" + opt.filelist);
                var prevDom = $("#" + opt.prevId);

                if(((btn1.attr("class")).toString()).indexOf("focus")<0){
                    str = "doule";
                }else {
                    str = "normal";
                }

                var filelistb= $('#filelistb').find('strong').html();
                var filelist= filelistDom.find('strong').html();

                console.info(filelistb+"vvvvvvvvvvvvvvvvv"+filelist);


                if(time){
                    if(filelistb!=undefined){
                        filelistb=filelistb.indexOf("100%")
                    }

                    if(filelist!=undefined){
                        filelist=filelist.indexOf("100%")
                    }

                    if(lastStr!=str&&uploader.files.length!=0){
                        state=0;
                    }

                    if((filelistb>=0||filelist>=0)&&state==0){
                        state=1;
                        uploader.files.splice(0,1)[1];
                    }
                }
                time++;
                lastStr=str;

                if(state==0){
                    parent.$.messager.alert('提示', '请先上传你选择的图片！', 'error');
                    uploader.files.splice(1,2)[1];
                }else {
                    $.each(files, function(i, file) {

                        if(str=="doule"){
                            if($('#filelistb').find('div').attr('id')!=undefined){
                                //uploader.removeFile(uploader.getFile($('#filelist').find('div').attr('id')));
                                $('#filelistb').empty();
                                uploader.files.splice(0,1)[1];
                            }
                            $('#filelistb').html('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');

                        }else {
                            if(filelistDom.find('div').attr('id')!=undefined){
                                //uploader.removeFile(uploader.getFile($('#filelist').find('div').attr('id')));
                                filelistDom.empty();
                                uploader.files.splice(0,1)[1];
                            }
                            filelistDom.html('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');

                        }

                    });



                    for (var i = 0; i < files.length; i++) {

                        showPreview(prevDom, files[i],str);
                    }


                }
                console.info(uploader.files.length);

            },
            'BeforeUpload': function (up, file) {
                console.info("before");
                console.info(uploader.files.length);
                console.info(uploader.filters);
                // 每个文件上传前,处理相关的事情
                /*                if (uploader.files.length > 1000) {
                                    parent.$.messager.alert('提示', '只允许选择6张照片！', 'error');
                                    uploader.stop();
                                    return;
                                }*/
                $('.ext-icon-cross').hide();
            },
            'UploadProgress': function (up, file) {
                console.info("upload");
                // 每个文件上传时,处理相关的事情

                var msg;
                if (file.percent == 100) {
                    msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制客户看到99%，等后台合并完成...
                } else {
                    msg = file.percent;
                }
                $('#' + file.id + '>strong').html(msg + '%');

                /*parent.sy.progressBar({//显示文件上传滚动条
                    title : '文件上传中...',
                    value : msg
                });*/
            },
            'FileUploaded': function (up, file, info) {
                // document.getElementById("LT_BP_add_img_bt").src = nw.qidomain+JSON.parse(info).key+"?imageMogr2/auto-orient";
                //document.getElementById("LT_BP_add_img").value =JSON.parse(info).key;
                //layer.close(BPCLOSEALTWATING)
                //回显图片
                var qn_domain = WEB_CONFIG._qnConfig.DOMAIN;
                if($("#type").val()=="thumbnail"){
                    $("#" + opt.finishShowId).append("<div style='display:inline-block;position: relative;'><img style='width: 80px;height: 80px;' src='"+qn_domain+JSON.parse(info.response).key+"?imageMogr2/auto-orient' />  <div style='position: absolute;top: 0px;right: 0px;width: 20px;height: 20px;display: block;background-size:cover;background-image:url(http://statics.shop.nightpalace.cn/1512615801392)' onclick='removeThumbnail("+JSON.parse(info.response).key+", this)'/></div>");

                }else if($("#type").val()=="details"){
                    $("#imgDe").append("<div style='display:inline-block;position: relative;'><img style='width: 80px;height: 80px;' src='"+qn_domain+JSON.parse(info.response).key+"' />  <div style='position: absolute;top: 0px;right: 0px;width: 20px;height: 20px;display: block;background-size:cover;background-image:url(http://statics.shop.nightpalace.cn/1512615801392)' onclick='removeDetails("+JSON.parse(info.response).key+")'/></div>");

                }

                /*$(':input[name="data.imgurl"]').val(JSON.parse(info.response).key);*/



                $('#' + file.id + '>strong').html("100%");
                //parent.sy.progressBar('close');//关闭上传进度条
            },
            'Error': function (up, err, errTip) {
                //上传出错时,处理相关的事情理相关的事情
            },
            'UploadComplete': function () {
                //队列文件处理完毕后,处
            },
            'Key': function (up, file) {
                // 若想在前端对每个文件的key进行个性化处理，可以配置该函数
                // 该配置必须要在 unique_names: false , save_key: false 时才生效

                var key = new Date().getTime();
                // do something with key here
                return key
            }
        }
    });
    return uploader;
}


//预览
function showPreview(prevDom, file) {
    var image = new Image();
    var preloader = new mOxie.Image();
    preloader.onload = function () {
        preloader.downsize(300, 300);
        image.setAttribute("src", preloader.getAsDataURL());
        image.style.width = "100%";
        image.style.height = "100%";
        $(prevDom).show();
        $(prevDom).html(image);
    };
    preloader.load(file.getSource());
}

//删除缩略图
function removeThumbnail(key, self) {
    var $imgs = $(self).parent().parent();
    var imgs = $imgs.find("img");
    $.each(imgs, function (index, domEle) {
        if (domEle.src.indexOf(key) > 0) {
            var parentDiv = domEle.parentNode;
            var value = $(parentDiv).prev().val();
            $(parentDiv).prev().val(value.replace(key, ""));
            parentDiv.remove();
        }
    });
    $(self).parent().parent().parent().next().find("input").eq(1).next().hide();
}

