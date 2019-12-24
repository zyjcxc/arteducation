/**
 * 文件导入
 */
(function (){
	
	//新增页面需要配置
	//code需要与文件名保持一致！
	var config = {
		pages:[
            {
                code:'books',
                url:'books.html',
                title:'使用教材',
                isParent:false
            },{
                code:'teacher',
                url:'teacher.html',
                title:'师资团队',
                isParent:false
            },{
                code:'credentials',
                url:'credentials.html',
                title:'证书展示',
                isParent:false
            },
            {
                code:'aboutus',
                url:'aboutus.html',
                title:'关于我们',
                isParent:false
            },
            {
                code:'authorization',
                url:'authorization.html',
                title:'授权书',
                isParent:false
            },
            {
                code:'download',
                url:'download.html',
                title:'文件下载',
                isParent:false
            },
            {
                code:'news',
                url:'news.html?news_1',
                title:'新闻中心',
                isParent:true,
                children:[
                    {
                        pCode:'news',
                        code:'news_1',
                        url:'news.html?news_1',
                        title:'新闻'
                    },
                    {
                        pCode:'news',
                        code:'news_2',
                        url:'news.html?news_2',
                        title:'公告'
                    }
                ]
            }
        ],
		script:['utils/bootstrap.js','utils/jquery.pagination.min.js']
	}
	
	window.localStorage.setItem('config', JSON.stringify(config));
	var baseURL = '../../';

    function initScript(){
    	for (var i=0,pi;pi = config.script[i++];) {
        	document.write('<script type="text/javascript" src="'+ baseURL + pi +'"></script>');
    	}
        /*banner展示*/
        $.EXTEND.GET('artBannerInfo/findAllBySite',{site:2,limit:1})
            .then(function (res) {
                if(res && res[0]){
                    $(".detail-top").css("background","url('"+res[0].picurl+"') center");
                }
            });
    }
    //页面模板，注意navbar_child.html里有脚本代码
    function initHtml(){
    	$('#header-page').load('../common/navbar_detail.html');
//		$('#breadcrumb-page').load('../common/breadcrumb_detail.html');
//		$('#menu_list').load('../common/menu_detail.html');
		$('#footer-page').load('../common/footer.html');
    }
    
    function init(){
    	initScript();
    	initHtml();
    }
    
    init();
   
    
})();