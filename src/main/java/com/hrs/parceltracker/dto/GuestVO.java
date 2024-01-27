package com.hrs.parceltracker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "GuestVO")
public class GuestVO {

    @ApiModelProperty(value="Name of the guest", name="name", required = true)
    private String name;

    @Column(name = "phone")
    @ApiModelProperty(value = "guest phone number", required = true)
    private String Phone;

    @Column(name = "address")
    @ApiModelProperty(value = "guest address", name = "address", required = true)
    private String Address;
}
