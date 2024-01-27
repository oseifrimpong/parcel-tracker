package com.hrs.parceltracker.service;


import com.hrs.parceltracker.dto.GuestVO;
import com.hrs.parceltracker.entity.Guest;

public interface IGuestService {

    public Guest createGuest(GuestVO guest);

    public void updateGuestInfo(Long id, GuestVO guestVO);
}
