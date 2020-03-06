package com.edu.admin.education.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("art_activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtActivity extends BaseModel {

    /**
     * 名称
     */
    private String name;

    /**
     * 图标地址
     */
    private String icon;

    /**
     * 状态 1 正常  2 删除
     */
    private String state;

    private Date createtime;

    private Date updatetime;

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图标地址
     *
     * @return icon - 图标地址
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标地址
     *
     * @param icon 图标地址
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取状态 1 正常  2 删除
     *
     * @return state - 状态 1 正常  2 删除
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态 1 正常  2 删除
     *
     * @param state 状态 1 正常  2 删除
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return createtime
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * @return updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}