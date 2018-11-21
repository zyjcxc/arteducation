package com.edu.admin.education.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author mengqa
 * @date 2018-03-31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Transient
    private String orderBy;

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
