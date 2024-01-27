package com.hrs.parceltracker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "GuestVO")
public class GuestVO {

    @ApiModelProperty(value="Name of the guest", name="name", required = true)
    private String name;

    @ApiModelProperty(value="guest checkin date", name="checkInDate", required = true)
    private LocalDate checkInDate;

    @ApiModelProperty(value="guest checkout date", name="checkOutDate")
    private LocalDate checkOutDate;
}
