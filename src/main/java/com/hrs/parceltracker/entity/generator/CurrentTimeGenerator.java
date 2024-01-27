package com.hrs.parceltracker.entity.generator;

import com.hrs.parceltracker.util.DateUtil;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

import java.util.Date;

/**
 * @author Nemo
 * @date 9/30/2021
 */
public class CurrentTimeGenerator implements ValueGenerator<Date> {

    @Override
    public Date generateValue(Session session, Object owner) {
        return DateUtil.getCurrentJavaDate();
    }
}
