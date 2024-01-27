package com.hrs.parceltracker.entity;

import com.hrs.parceltracker.entity.generator.CurrentTimeGenerator;
import com.hrs.parceltracker.entity.generator.CurrentUserNameGenerator;
import com.hrs.parceltracker.constant.Constants;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel("AuditEntity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class AuditEntity implements Serializable {

    @GeneratorType(type = CurrentTimeGenerator.class, when = GenerationTime.INSERT)
    @Column(name = "create_time", length = 6, updatable = false)
    private Date createTime;

    @GeneratorType(type = CurrentUserNameGenerator.class, when = GenerationTime.INSERT)
    @Column(name = "create_by", length = 50, updatable = false)
    private String createBy;

    @GeneratorType(type = CurrentTimeGenerator.class, when = GenerationTime.ALWAYS)
    @Column(name = "update_time", length = 6)
    private Date updateTime;

    @GeneratorType(type = CurrentUserNameGenerator.class, when = GenerationTime.ALWAYS)
    @Column(name = "update_by", length = 50)
    private String updateBy;

    @Column(name = "is_active", length = 16, nullable = false, columnDefinition = "int2 DEFAULT 1")
    protected Integer isActive = 1;

    public boolean checkIsActive() {
        return Constants.ACTIVE == this.isActive;
    }

    public boolean checkIsNotActive() {
        return !checkIsActive();
    }
}