package com.hrs.parceltracker.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel("BaseEntity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class BaseEntity extends AuditEntity {

    private static final long serialVersionUID = -7176390653391227433L;

    @Id
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.hrs.parceltracker.util.SnowflakeIdGenerator")
    @Column(name = "id", unique = true, nullable = false)
    protected Long id;

    @ApiModelProperty("version")
    @Column(name = "version", length = 32, nullable = false, columnDefinition = "int2 DEFAULT 1")
    private Integer version = 1;

    public boolean checkIsNewRecord() {
        return null == this.id;
    }

    public boolean checkIsExistingRecord() {
        return null != this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
