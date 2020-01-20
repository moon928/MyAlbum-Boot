package cn.yan_wm.myalbum.commons.domain;

import cn.yan_wm.myalbum.commons.dto.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "tb_vip")
public class TbVip extends AbstractBaseDomain {
    private static final long serialVersionUID = -1847417606487926300L;


    /**
     * 名称
     */
    private String name;

    /**
     * vip积分下限
     */
    @Column(name = "lower_limit")
    private Integer lowerLimit;

    /**
     * vip积分上限
     */
    @Column(name = "upper_limit")
    private Integer upperLimit;


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
     * 获取vip积分下限
     *
     * @return lower_limit - vip积分下限
     */
    public Integer getLowerLimit() {
        return lowerLimit;
    }

    /**
     * 设置vip积分下限
     *
     * @param lowerLimit vip积分下限
     */
    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    /**
     * 获取vip积分上限
     *
     * @return upper_limit - vip积分上限
     */
    public Integer getUpperLimit() {
        return upperLimit;
    }

    /**
     * 设置vip积分上限
     *
     * @param upperLimit vip积分上限
     */
    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }
}