package com.hrs.parceltracker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "CheckInOut")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckInOut extends BaseEntity{

    @Column(name = "room_number", nullable = false)
    @ApiModelProperty(value = "roomNumber", required = true)
    private Short roomNumber;

    @Column(name = "is_checkin", nullable = false)
    @ApiModelProperty(name = "is_checkin", required = true)
    private Short isCheckIn;

    @Column(name = "is_checkout", nullable = false)
    @ApiModelProperty(name = "is_checkout", required = true)
    private Short isCheckOut;

    @Column(name = "check_out_date")
    private Date checkOutDate;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    @ApiModelProperty(value = "guest_id")
    private Guest guest;
}
