package com.hrs.parceltracker.service.Impl;

import com.hrs.parceltracker.dto.CheckInVO;
import com.hrs.parceltracker.dto.CheckOutVO;
import com.hrs.parceltracker.entity.CheckInOut;
import com.hrs.parceltracker.entity.Guest;
import com.hrs.parceltracker.repository.CheckInOutRepository;
import com.hrs.parceltracker.repository.GuestRepository;
import com.hrs.parceltracker.service.ICheckInOut;

import java.util.Optional;

public class CheckInOutImpl implements ICheckInOut {

    private static final Short isCheckIn = 1;

    private final CheckInOutRepository checkInOutRepository;

    private final GuestRepository guestRepository;

    public CheckInOutImpl(CheckInOutRepository checkInOutRepository, GuestRepository guestRepository) {
        this.checkInOutRepository = checkInOutRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public void createGuestCheckIn(CheckInVO checkInVO) {

        Optional<Guest> opt = this.guestRepository.findById(checkInVO.getGuestID());
        if(opt.isPresent()) {
            Guest guest = opt.get();

            CheckInOut checkInUser = new CheckInOut();
            checkInUser.setIsCheckIn(isCheckIn);
            checkInUser.setRoomNumber(checkInVO.getRoomNumber());
            checkInUser.setGuest(guest);

            this.checkInOutRepository.save(checkInUser);
        }
    }

    @Override
    public void checkGuestOut(CheckOutVO checkOutVO) {

    }
}
