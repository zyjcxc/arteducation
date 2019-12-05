/**
 * 七牛相关配置
 */
(function (){
    var paths  = [
            'qiniu.min.js',
            'ui.js',
            'main.js'
        ],
        baseURL = '/js/qiniu/js/';
    for (var i=0,pi;pi = paths[i++];) {
        document.write('<script type="text/javascript" src="'+ baseURL + pi +'"></script>');
    }
})();
