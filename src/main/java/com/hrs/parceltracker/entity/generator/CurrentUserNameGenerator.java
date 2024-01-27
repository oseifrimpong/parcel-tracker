package com.hrs.parceltracker.entity.generator;


import com.hrs.parceltracker.constant.Constants;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

/**
 * @author Nemo
 * @date 9/30/2021
 */
public class CurrentUserNameGenerator implements ValueGenerator<String> {
    @Override
    public String generateValue(Session session, Object owner) {
        return Constants.USERNAME_SYSTEM;
    }
}

