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
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "parcel")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parcel extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    @ApiModelProperty(value = "guest_id")
    private Guest guest;

    @Column(name = "delivery_date")
    @ApiModelProperty(value = "delivery_date")
    private Date deliveryDate;

    @Column(name = "delivery_status", nullable = false)
    @ApiModelProperty(value = "delivery_status")
    private String delivery_status;
}