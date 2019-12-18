/*global Qiniu */
/*global plupload */
/*global FileProgress */
/*global hljs */

$(function() {
  var uploader = Qiniu.uploader({
    disable_statistics_report: false,
    runtimes: 'html5,flash,html4',
    browse_button: 'pickfiles',
    container: 'container',
    drop_element: 'container',
    max_file_size: '10mb',
    flash_swf_url: 'Moxie.swf',
    dragdrop: true,
    chunk_size: '10mb',
    multi_selection: !(moxie.core.utils.Env.OS.toLowerCase() === "ios"),
    uptoken_func: function(){
        var ajax = new XMLHttpRequest();
        ajax.open('GET', '/qiniu/token', false);
        ajax.setRequestHeader("If-Modified-Since", "0");
        ajax.send();
        if (ajax.status === 200) {
            return ajax.responseText;
        } else {
            return '';
        }
    },
    domain: 'http://static.qgtcs.cn/',
    get_new_uptoken: false,
    unique_names: true,
    // save_key: true,
    auto_start: true,
    log_level: 5,
    init: {
      'BeforeChunkUpload': function(up, file) {
        console.log("before chunk upload:", file.name);
      },
      'FilesAdded': function(up, files) {
        $('table').show();
        $('#qiniuSuccess').hide();
        plupload.each(files, function(file) {
          var progress = new FileProgress(file,
            'fsUploadProgress');
          progress.setStatus("等待...");
          progress.bindUploadCancel(up);
        });
      },
      'BeforeUpload': function(up, file) {
        console.log("this is a beforeupload function from init");
        var progress = new FileProgress(file, 'fsUploadProgress');
        var chunk_size = plupload.parseSize(this.getOption(
          'chunk_size'));
        if (up.runtime === 'html5' && chunk_size) {
          progress.setChunkProgess(chunk_size);
        }
      },
      'UploadProgress': function(up, file) {
        var progress = new FileProgress(file, 'fsUploadProgress');
        var chunk_size = plupload.parseSize(this.getOption(
          'chunk_size'));
        progress.setProgress(file.percent + "%", file.speed,
          chunk_size);
      },
      'UploadComplete': function() {
        $('#qiniuSuccess').show();
        var successTxt = setTimeout(function () {
            $('#qiniuSuccess').hide();
        },3000)
      },
      'FileUploaded': function(up, file, info) {
        var progress = new FileProgress(file, 'fsUploadProgress');
        progress.setComplete(up, info.response);

      },
      'Error': function(up, err, errTip) {
          $('table').show();
          var progress = new FileProgress(err.file, 'fsUploadProgress');
          progress.setError();
          progress.setStatus(errTip);
        }
    }
  });
  //uploader.init();
  uploader.bind('BeforeUpload', function() {
    console.log("hello man, i am going to upload a file");
  });
  uploader.bind('FileUploaded', function() {
    console.log('hello man,a file is uploaded');
  });
});
