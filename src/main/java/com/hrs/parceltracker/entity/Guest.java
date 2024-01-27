package com.hrs.parceltracker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "guest")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Guest extends BaseEntity {

    @Column(name = "name", nullable = false)
    @ApiModelProperty(value = "name")
    private String name;

    @Column(name = "phone")
    @ApiModelProperty(value = "phone")
    private String Phone;

    @Column(name = "address")
    @ApiModelProperty(value = "address")
    private String Address;
}