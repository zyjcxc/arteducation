package com.edu.admin.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 富客户端vo模型
 *
 * @author xuqiang
 * @create 2017-12-21 下午3:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KindeditorResultVo {

    /**
     * 错误码
     */
    private Integer error;

    /**
     * 返回具体的错误信息
     */
    private String message;

    /**
     * 正确返回时的URL
     */
    private String url;
}
