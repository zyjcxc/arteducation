package com.edu.admin.server.utils;

import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 极光推送工具类
 *
 * @author mengqa
 * @date 2018-04-26 11:06
 **/
public class JgPushUtil {

    public static PushPayload send(String title, String content) {
        return send(title, content, 0, null);
    }

    public static PushPayload send(String title, String content, Integer system) {
        return send(title, content, system, null);
    }

    /**
     *
     * @param title 标题
     * @param content 内容
     * @param system 0.all 1.android 2.iOS
     * @param users 用户手机号
     * @return 结果
     */
    public static PushPayload send(String title, String content, Integer system, List<String> users) {
        Platform platform = null;
        Notification notification = null;
        Notification.Builder builder = Notification.newBuilder()
                .setAlert(content);
        switch (system) {
            case 0 :
                platform = Platform.android_ios();
                notification = buildAll(title, builder);
                break;
            case 1 :
                platform = Platform.android();
//                notification = Notification.android(content, title, null);
                notification = buildAndroid(title, builder);
                break;
            case 2 :
                platform = Platform.ios();
//                notification = Notification.ios(content, null);
                notification = buildiOS(builder);
                break;
            default:
                break;
        }
        Audience alias;
        if (!CollectionUtils.isEmpty(users)) {
            String[] arr = users.toArray(new String[0]);
            alias = Audience.alias(arr);
        } else {
            alias = Audience.all();
        }

        return PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(alias)
                .setNotification(notification)
                .build();
    }

    private static Notification buildAll(String title, Notification.Builder builder) {
        return builder.addPlatformNotification(AndroidNotification.newBuilder()
                        .setTitle(title).build())
                .addPlatformNotification(IosNotification.newBuilder()
                        .incrBadge(1).build())
                .build();
    }

    private static Notification buildiOS(Notification.Builder builder) {
        IosNotification ios = IosNotification.newBuilder()
                .incrBadge(1).build();
        return builder.addPlatformNotification(ios).build();
    }

    private static Notification buildAndroid(String title, Notification.Builder builder) {
        AndroidNotification android = AndroidNotification.newBuilder()
                .setTitle(title).build();
        return builder.addPlatformNotification(android).build();
    }

   /* public static PushPayload send_iOS() {


        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias("15840506867"))
                .setNotification(Notification.android("测试content指定电话号及安卓" + DateUtils.formatDate(new Date()), "测试titile" + DateUtils.formatDate(new Date()), null))
                .build();
    }

    public static PushPayload send_android() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias("15840506867"))
                .setNotification(Notification.android("测试content指定电话号及安卓" + DateUtils.formatDate(new Date()), "测试titile" + DateUtils.formatDate(new Date()), null))
                .build();
    }*/
}
