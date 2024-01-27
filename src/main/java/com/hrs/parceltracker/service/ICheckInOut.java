package com.hrs.parceltracker.service;

import com.hrs.parceltracker.dto.CheckInVO;
import com.hrs.parceltracker.dto.CheckOutVO;
import com.hrs.parceltracker.entity.CheckInOut;

public interface ICheckInOut {

    public void createGuestCheckIn(CheckInVO checkIn);

    public void checkGuestOut(CheckOutVO checkOutVO);
}
