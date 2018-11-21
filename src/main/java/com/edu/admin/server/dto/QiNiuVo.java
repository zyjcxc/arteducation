package com.edu.admin.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 七牛上传vo模型
 *
 * @author xuqiang
 * @create 2017-12-21 下午4:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QiNiuVo {

    private String hash;

    private String key;
}
