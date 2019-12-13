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
	var baseURL = '../';

	/*获取目录*/
    $.EXTEND.GET('http://cc.aerohuanyou.com:8081/api/qingqi/operate/BannerInfo/QueryBannerInfo',{page_number:1,page_size:10,type:1})
        .then(function (res) {
            var data = [
                {
                    url:'books.html?books_1',
                    title:'新闻'
                },
                {
                    url:'books.html?books_2',
                    title:'公告'
                }
            ];
            config.pages.push({
                url:data[0].url ,
                title:'新闻中心',
                isParent:true,
                children:data});
        });
    function initScript(){
    	for (var i=0,pi;pi = config.script[i++];) {
        	document.write('<script type="text/javascript" src="'+ baseURL + pi +'"></script>');
    	}
    }
    //页面模板，注意navbar_child.html里有脚本代码
    function initHtml(){
    	$('#header-page').load('../pages/common/navbar_child.html');
		$('#breadcrumb-page').load('../pages/common/breadcrumb_child.html');
		$('#menu_list').load('../pages/common/menu_child.html');
		$('#footer-page').load('../pages/common/footer.html');
    }
    
    function init(){
    	initScript();
    	initHtml();
    }
    
    init();
   
    
})();