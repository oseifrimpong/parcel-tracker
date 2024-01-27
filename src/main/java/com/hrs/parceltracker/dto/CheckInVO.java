package com.hrs.parceltracker.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "CheckInVO")
public class CheckInVO {

    @ApiModelProperty(value = "roomNumber", required = true)
    private Short roomNumber;

    @ApiModelProperty(value = "guestId")
    private Long guestID;
}
