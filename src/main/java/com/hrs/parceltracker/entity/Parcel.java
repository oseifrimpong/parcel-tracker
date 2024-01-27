package com.hrs.parceltracker.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parcel extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    private String status;

    // Constructors, getters, and setters
    // ...
}