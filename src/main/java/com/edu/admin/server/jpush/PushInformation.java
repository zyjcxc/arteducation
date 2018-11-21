package com.edu.admin.server.jpush;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;

/**
 * 推送信息实体
 * @author mengqa
 * @date 2018-04-26 13:55
 **/
public class PushInformation {
    // 内容
    private String content;
    // 标题
    private String title;
    // 系统 0.全部 1.安卓 2.iOS
    private Integer system = 0;
    // 推送用户
    private Set<String> users;
    // 推送平台
    private Platform platform;
    // 推送相关设置
    private Notification notification;
    // 推送群体
    private Audience audience;
    private Notification.Builder builder;

    // 附加参数
    private Map<String, String> extParams;

    public PushInformation() {

    }

    public PushInformation(String content, String title) {
        this.content = content;
        this.title = title;
        this.builder = Notification.newBuilder()
                .setAlert(content);
        initBuilder();
    }

    public PushInformation(String content, String title, Integer system) {
        this.content = content;
        this.title = title;
        this.system = system;
        this.builder = Notification.newBuilder()
                .setAlert(content);
        initBuilder();
    }

    public PushInformation(String content, String title, Integer system, Set<String> users) {
        this.content = content;
        this.title = title;
        this.system = system;
        this.users = users;
        this.builder = Notification.newBuilder()
                .setAlert(content);
        initBuilder();
    }

    public PushInformation(String content, String title, Integer system, Set<String> users, Map<String ,String> extParams) {
        this.content = content;
        this.title = title;
        this.system = system;
        this.users = users;
        this.builder = Notification.newBuilder()
                .setAlert(content);
        this.extParams = extParams;
        initBuilder();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    private void initBuilder() {
        switch (system) {
            case 0 :
                this.platform = Platform.android_ios();
                this.notification = buildAll(title, builder);
                break;
            case 1 :
                this.platform = Platform.android();
                this.notification = buildAndroid(title, builder);
                break;
            case 2 :
                this.platform = Platform.ios();
                notification = buildiOS(builder);
                break;
            default:
                break;
        }
        if (!CollectionUtils.isEmpty(users)) {
            String[] arr = users.toArray(new String[0]);
            this.audience = Audience.alias(arr);
        } else if (users != null && users.size() == 0) {
            // 不给任何人推送
            this.audience = Audience.alias("nobody");
        } else {
            this.audience = Audience.all();
        }
    }

    public PushPayload getPushPayLoad() {
        return PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(audience)
                .setNotification(notification)/*.setOptions(Options.newBuilder().setTimeToLive(1000).build())*/
                .build();
    }

    public Platform getPlatform() {
        return platform;
    }

    public Notification getNotification() {
        return notification;
    }

    public Audience getAudience() {
        return audience;
    }

    private Notification buildAll(String title, Notification.Builder builder) {
        return this.buildAll(title, builder, this.extParams);
    }

    private Notification buildAll(String title, Notification.Builder builder, Map<String, String> extParams) {
        return builder.addPlatformNotification(AndroidNotification.newBuilder()
                .setTitle(title).addExtras(extParams).build())
                .addPlatformNotification(IosNotification.newBuilder()
                        .incrBadge(1).build())
                .build();
    }

    private Notification buildiOS(Notification.Builder builder) {
        IosNotification ios = IosNotification.newBuilder()
                .incrBadge(1).build();
        return builder.addPlatformNotification(ios).build();
    }

    private Notification buildAndroid(String title, Notification.Builder builder) {
        AndroidNotification android = AndroidNotification.newBuilder()
                .setTitle(title).build();
        return builder.addPlatformNotification(android).build();
    }
}
