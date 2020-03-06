package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Transient;

/**
 * @author mengqa
 * @date 2018-03-31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable {
    @TableId(type = IdType.AUTO)
//    @Column(name = "id")
//    @GeneratedValue(generator = "JDBC")
    private Long id;

//    @Transient
    @TableField(exist = false)
    private String orderBy;

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
