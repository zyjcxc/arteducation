/**
 * 配置
 * @desc：
 * @author mengqa
 * @date 2018.4.4
 */
WEB_CONFIG = {

    _action: {
        // 特长生
        ART_STUDENT : "/artStudents",
        // 特长生删除
        ART_STUDENT_LOGIC_DELETE_ACTION : "/artStudents/deleteLogic",
        // 添加
        ART_STUDENT_ACTION : "/artStudents",
        // 导出
        EXPORT_ART_STUDENT_ACTION : "/artStudents/export",

        // 活动管理
        ART_ACTIVITY_ACTION : "/artActivitys",
        ART_ACTIVITY_FIND_ALL_ACTION : "/artActivitys/findAll",

        // APP用户
        APP_USER_ACTION : "/liveAppUserInfos",
        // 导出APP用户
        EXPORT_APP_USER_ACTION : "/liveAppUserInfos/export",
        // 课程
        COURSE_ACTION : "/liveCourseInfos",
        // 检查老师是否存在
        COURSE_CHECK_TEACHER_ACTION : "/liveCourseInfos/checkTeacherLive",

        // 课程删除
        COURSE_LOGIC_DELETE_ACTION : "/liveCourseInfos/deleteLogic",
        // 导出课程/
        EXPORT_COURSE_ACTION : "/liveCourseInfos/export",
        // 课程分类
        COURSE_CLASSIFICATION_ACTION : "/liveCourseClassifications",
        COURSE_CLASSIFICATION_FIND_ALL_ACTION : "/liveCourseClassifications/findAll",
        // 课节
        LESSION_ACTION : "/liveCourseLessonInfos",
        // 课节删除
        LESSION_LOGIC_DELETE_ACTION : "/liveCourseLessonInfos/deleteLogic",
        // 导出课节
        EXPORT_LESSION_ACTION : "/liveCourseLessonInfos/export",
        // 课节---判断是否是唯一课时的课节
        LESSION_CHECK_ONLY_ACTION : "/liveCourseLessonInfos/checkOnlyOne",
        // 老师
        PUBLIC_ACCOUNT_ACTION : "/livePublicAccountInfos",
        PUBLIC_CHECK_ONLY_ACTION : "/livePublicAccountInfos/checkOnlyOne",
        // 老师删除
        PUBLIC_ACCOUNT_DELETE_ACTION : "/livePublicAccountInfos/deleteLogic",
        // 导出账号
        EXPORT_PUBLIC_ACCOUNT_ACTION : "/livePublicAccountInfos/export",
        // 老师轮播推荐
        PUBLIC_SHUFFLING_ACTION : "/livePublicShufflings",
        // 老师轮播推荐列表
        PUBLIC_SHUFFLING_LIST_ACTION : "/livePublicShufflings/listByAccountId",
        // 根据账号id及课程id改变推荐状态
        PUBLIC_SHUFFLING_SAVE_UPDATE_ACTION : "/livePublicShufflings/saveOrUpdate",
        // 根据账号id及课程id删除推荐状态
        PUBLIC_SHUFFLING_DELETE_ACTION : "/livePublicShufflings/deleteByCourseIdAndTeacherId",
        // 课程及关联删除，根据老师Id及课程id
        COURSE_ACCOUNT_ACTION : "/liveCourseInfos/deleteByTeacherId",
        // 内容管理列表
        HOME_CONTENT_ACTION : "/liveHomeContentInfos",
        // 意见反馈
        FEEDBACK_INFO_ACTION: "/liveUserFeedbackInfos",
        // 导出意见反馈
        EXPORT_FEEDBACK_INFO_ACTION: "/liveUserFeedbackInfos/export",
        // 礼物管理
        GIFT_INFO_ACTION : "/liveGiftInfos",
        // 订单管理
        ORDER_MASTER_ACTION : "/liveOrderMasterInfos",
        // 导出订单管理
        EXPORT_ORDER_MASTER_ACTION : "/liveOrderMasterInfos/exportOrder",
        EXPORT_REWARD_MASTER_ACTION : "/liveOrderMasterInfos/exportRewards",
        // 提现申请
        EXTRACT_MONEY_ACTION : "/liveExtractionMoneys",
        EXPORT_EXTRACT_MONEY_ACTION : "/liveExtractionMoneys/export",
        // 分层比例
        PROPORTION_ACTION : "/livePlatformProportions",
        // 分层比例获取一个
        PROPORTION_GET_ONE_ACTION : "/livePlatformProportions/getOne",
        // 销售课程管理
        SALES_INFO_ACTION : "/liveCourseSalesInfos",
        // 导出销售课程管理
        EXPORT_SALES_INFO_ACTION : "/liveCourseSalesInfos/export",
        // 用户分销管理
        USER_SALES_INFO_ACTION : "/liveUserSalesInfos",
        // 导出用户分销管理
        EXPORT_USER_SALES_INFO_ACTION : "/liveUserSalesInfos/export",
        // 推送管理
        PUSH_MESSAGE_ACTION : "/livePushMessages",
        // 检查是否存在
        PUSH_MESSAGE_CHECK_HAS_ACTION : "/livePushMessages/checkHasOne",
        // 导出推送管理
        EXPORT_PUSH_MESSAGE_ACTION : "/livePushMessages/export",
        // 操作日志管理
        OPT_LOG_ACTION : "/liveOptLogs",
        // 导出日志列表
        EXPORT_OPT_LOG_ACTION : "/liveOptLogs/export",
        // 版本管理
        VERSION_ACTION : "/liveAndroidVersionInfos",
        VERSION_CHECK_ONLY_ACTION : "/liveAndroidVersionInfos/checkOnlyOne",
        //教材相关
        ART_TEXT_BOOK_ACTION : "/artTextbook",
        //教材分类
        ART_TEXT_BOOK_TYPE_ACTION : "/artTextbookType",
        ART_TEXT_BOOK_TYPE_ALL_ACTION : "/artTextbookType/findAll",
        //老师相关
        ART_TEACHER_ACTION : "/artTeacher",
        //授权书相关接口
        ART_AUTHBOOK_ACTION : "/artAuthbook",
        //新闻中心相关接口
        ART_NEWS_ACTION : "/artNews",
        //首页banner
        MAIN_BANNER_ACTION : "/artBannerInfo",
        //联系我们
        ART_GUEST_INFO : "/artGuestInfo",
    },
    _page : {
        // 特殊长生列表
        ART_STUDENT_LIST_PAGE : "/pages/artStudent/artStudentList.html",
        ART_STUDENT_ADD_PAGE : "/pages/artStudent/addArtStudent.html",
        ART_STUDENT_ADD_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ART_STUDENT_ADD_PAGE, keyValues);
        },

        ACTIVITY_INFO_LIST_PAGE : "/pages/activityInfo/artAcitivtyList.html",
        ACTIVITY_INFO_ADD_PAGE : "/pages/activityInfo/addArtAcitivty.html",
        ACTIVITY_INFO_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ACTIVITY_INFO_ADD_PAGE, keyValues);
        },
        ACTIVITY_INFO_ADD_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ACTIVITY_INFO_ADD_PAGE, keyValues);
        },

        // APP用户详情
        APP_USER_DETAIL_PAGE: "/pages/appUserInfo/showDetail.html",
        APP_USER_DETAIL_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.APP_USER_DETAIL_PAGE, keyValues);
        },
        // 课程列表(仅针对老师)
        COURSE_LIST_PAGE : "/pages/courseInfo/liveCourseInfoList.html",
        COURSE_LIST_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.COURSE_LIST_PAGE, keyValues);
        },
        // 课程列表(全部)
        COURSE_LIST_ALL_PAGE : "/pages/courseInfo/liveCourseInfoListAll.html",
        // 新增课程
        COURSE_ADD_PAGE : "/pages/courseInfo/addLiveCourseInfo.html",
        COURSE_ADD_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.COURSE_ADD_PAGE, keyValues);
        },
        // 修改课程
        COURSE_UPDATE_PAGE : "/pages/courseInfo/addLiveCourseInfo.html",
        COURSE_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.COURSE_UPDATE_PAGE, keyValues);
        },
        // 课程详情1
        COURSE_DETAIL_PAGE : "/pages/courseInfo/showDetail.html",
        COURSE_DETAIL_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.COURSE_DETAIL_PAGE, keyValues);
        },
        // 课程详情2(课程管理下--课程列表的详情)
        COURSE_CLASS_DETAIL_PAGE : "/pages/courseInfo/showDetailAll.html",
        COURSE_CLASS_DETAIL_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.COURSE_CLASS_DETAIL_PAGE, keyValues);
        },
        // 课程分类修改页
        COURSE_CLASSIFICATION_UPDATE_PAGE : "/pages/classification/addLiveCourseClassification.html",
        COURSE_CLASSIFICATION_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.COURSE_CLASSIFICATION_UPDATE_PAGE, keyValues);
        },
        // 课节添加
        LESSION_ADD_PAGE : "/pages/lessonInfo/addLiveCourseLessonInfo.html",
        LESSION_ADD_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.LESSION_ADD_PAGE, keyValues);
        },
        // 课节列表（仅针对课程）
        LESSION_LIST_PAGE : "/pages/lessonInfo/liveCourseLessonInfoList.html",
        LESSION_LIST_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.LESSION_LIST_PAGE, keyValues);
        },
        // 课节修改
        LESSION_UPDATE_PAGE : "/pages/lessonInfo/addLiveCourseLessonInfo.html",
        LESSION_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.LESSION_ADD_PAGE, keyValues);
        },
        // 课程分类新增页
        COURSE_CLASSIFICATION_ADD_PAGE : "/pages/classification/addLiveCourseClassification.html",
        COURSE_CLASSIFICATION_ADD_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.COURSE_CLASSIFICATION_ADD_PAGE, keyValues);
        },
        // 公众号列表(课程管理下)
        PUBLIC_ACCOUNT_CLASS_LIST_PAGE : "/pages/pubAccount/livePublicAccountInfoListAtClass.html",
        // 公众号添加
        PUBLIC_ACCOUNT_ADD_PAGE : "/pages/pubAccount/addLivePublicAccountInfo.html",
        // 公众号详情
        PUBLIC_ACCOUNT_DETAIL_PAGE : "/pages/pubAccount/showDetail.html",
        PUBLIC_ACCOUNT_DETAIL_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.PUBLIC_ACCOUNT_DETAIL_PAGE, keyValues);
        },
        // 公众号修改
        PUBLIC_ACCOUNT_UPDATE_PAGE : "/pages/pubAccount/addLivePublicAccountInfo.html",
        PUBLIC_ACCOUNT_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.PUBLIC_ACCOUNT_UPDATE_PAGE, keyValues);
        },
        // 公众号(正常)
        PUBLIC_ACCOUNT_LIST_PAGE : "/pages/pubAccount/livePublicAccountInfoList.html",
        // 空间管理
        SPACE_LIST_PAGE : "/pages/pubAccount/liveSpaceInfoList.html",
        SPACE_LIST_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.SPACE_LIST_PAGE, keyValues);
        },
        // 推送消息
        PUSH_MESSAGE_ADD_PAGE : "/pages/pushMessage/addLivePushMessage.html",
        PUSH_MESSAGE_ADD_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.PUSH_MESSAGE_UPDATE_PAGE, keyValues);
        },
        PUSH_MESSAGE_UPDATE_PAGE : "/pages/pushMessage/addLivePushMessage.html",
        PUSH_MESSAGE_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.PUSH_MESSAGE_UPDATE_PAGE, keyValues);
        },
        VERSION_ACTION_ADD_PAGE : "/pages/version/addLiveAndroidVersionInfo.html",
        VERSION_ACTION_ADD_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.VERSION_ACTION_ADD_PAGE, keyValues);
        },
        VERSION_ACTION_LIST_PAGE : "/pages/version/liveAndroidVersionInfoList.html",
        // 教材相关修改页
        ART_TEXT_BOOK_UPDATE_PAGE : "/pages/textbook/addArtTextbook.html",
        ART_TEXT_BOOK_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ART_TEXT_BOOK_UPDATE_PAGE, keyValues);
        },
        // 教材分类修改页
        ART_TEXT_BOOK_TYPE_UPDATE_PAGE : "/pages/textbook/addArtTextbookType.html",
        ART_TEXT_BOOK_TYPE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ART_TEXT_BOOK_TYPE_UPDATE_PAGE, keyValues);
        },
        // 老师相关修改页
        ART_TEACHER_UPDATE_PAGE : "/pages/teacher/addArtTeacher.html",
        ART_TEACHER_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ART_TEACHER_UPDATE_PAGE, keyValues);
        },
        // 授权书相关修改页
        ART_AUTHBOOK_UPDATE_PAGE : "/pages/authbook/addAuthbook.html",
        ART_AUTHBOOK_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ART_AUTHBOOK_UPDATE_PAGE, keyValues);
        },
        // 新闻中心修改页
        ART_News_UPDATE_PAGE : "/pages/news/addArtNews.html",
        ART_News_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ART_News_UPDATE_PAGE, keyValues);
        },
        // 关于我们修改页
        ABOUT_US_UPDATE_PAGE : "/pages/aboutUs/addAboutUs.html",
        ABOUT_US_UPDATE_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.ABOUT_US_UPDATE_PAGE, keyValues);
        },
        // banner修改页
        MAIN_BANNER_UPDATE_PAGE : "/pages/home/addMainBanner.html",
        MAIN_BANNER_PAGE_WITH_PARAMS : function(keyValues) {
            return $$.makeWithUrl(WEB_CONFIG._page.MAIN_BANNER_UPDATE_PAGE, keyValues);
        },
    },
    _qnConfig : {
        DOMAIN : getDomain()
    }
};

function getDomain() {
    $.ajax({
        type: 'get',
        url: "/qiniu/domain",
        async: false,
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            domain = data;
        }
    });
    return domain;
}

