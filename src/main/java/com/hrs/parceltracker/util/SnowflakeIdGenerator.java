package com.hrs.parceltracker.util;

import cn.hutool.extra.spring.SpringUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class SnowflakeIdGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        SnowflakeIdWorker snowflakeIdWorker = SpringUtil.getBean(SnowflakeIdWorker.class);
        return snowflakeIdWorker.nextId();
    }
}