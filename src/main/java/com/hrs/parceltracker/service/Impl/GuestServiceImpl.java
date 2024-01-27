package com.hrs.parceltracker.service.Impl;

import com.hrs.parceltracker.dto.GuestVO;
import com.hrs.parceltracker.entity.Guest;
import com.hrs.parceltracker.repository.GuestRepository;
import com.hrs.parceltracker.service.IGuestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class GuestServiceImpl implements IGuestService {

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest createGuest(GuestVO guest) {
        Guest guestUser = new Guest();
        guestUser.setName(guest.getName());
        guestUser.setPhone(guest.getPhone());
        guestUser.setAddress(guest.getAddress());
        log.info("creating guest user");
        return guestRepository.save(guestUser);
    }

    @Override
    public void updateGuestInfo(Long id, GuestVO guestVO) {
        try {
            Optional<Guest> opt = guestRepository.findById(id);
            if (opt.isPresent()) {
                Guest guest = opt.get();
                guest.setName(guestVO.getName());
                guest.setPhone(guestVO.getPhone());
                guest.setAddress(guestVO.getAddress());
                guestRepository.saveAndFlush(guest);
            }
        } catch (Exception e) {
            log.error("User not found");
        }
    }
}
