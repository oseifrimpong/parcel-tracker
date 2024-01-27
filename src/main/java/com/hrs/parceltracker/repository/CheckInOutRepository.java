package com.hrs.parceltracker.repository;

import com.hrs.parceltracker.entity.CheckInOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInOutRepository extends JpaRepository<CheckInOut, Long> {
}
