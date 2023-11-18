package com.sndshun.jpa.hibernate.pojo;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author sndshun
 * @Date 2023/11/16 1:49
 * @Version 1.0
 * @Description: 公共属性
 */
@MappedSuperclass
public class BaseLongEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Comment("创建时间")
    @CreatedDate
    private Date createDate;
    @Comment("最后修改时间")
    @LastModifiedDate
    private Date updateDate;
    @Comment("创建人")
    @CreatedBy
    private Long createBy;
    @Comment("最后修改人")
    @LastModifiedBy
    private Long updateBy;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
