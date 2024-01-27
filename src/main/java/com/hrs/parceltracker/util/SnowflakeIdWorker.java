package com.hrs.parceltracker.util;

import com.hrs.parceltracker.common.exception.ApplicationException;

public class SnowflakeIdWorker {

    // ==============================Fields===========================================
    /**
     * the bit number of worker ID
     */
    private static final long WORKER_ID_BITS = 7L;
    /**
     * supported max worker ID, the result is 127 (this shift algorithm can compute
     * the max number of Decimal system based on n bit of Binary system quickly)
     */
    public static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    /**
     * start timestamp (2015-01-01)
     */
    private static final long TWEPOCH = 1420041600000L;
    /**
     * the bit number of data center ID
     */
    private static final long DATACENTER_ID_BITS = 3L;
    /**
     * supported max data center ID, the result is 7
     */
    private static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS);

    /**
     * the bit number of sequence
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * worker ID after doing left shift 12 bit
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * data center ID after doing left shift 19 bit(12+7)
     */
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * timestamp after doing left shift 22 bit(12+7+3)
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;

    /**
     * mask code of sequence, the result is 4095 (0b111111111111=0xfff=4095)
     */
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);

    /**
     * worker ID(0~31)
     */
    private final long workerId;

    /**
     * data center ID(0~31)
     */
    private final long datacenterId;

    /**
     * sequence in millisecond(0~4095)
     */
    private long sequence = 0L;

    /**
     * generated timestamp of last ID
     */
    private long lastTimestamp = -1L;

    // ==============================Constructors=====================================

    /**
     * Constructor method
     *
     * @param workerId     worker ID (0~31)
     * @param datacenterId data center ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================Methods==========================================

    /**
     * get next ID (thread safe)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // if current timestamp is less than last timestamp it means system clock has
        // been rolled back and throw exception
        if (timestamp < lastTimestamp) {
            throw new ApplicationException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // if it is same, increase sequence in millisecond
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // if sequence overflow in millisecond
            if (sequence == 0) {
                // block until next millisecond, get new timestamp
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // if timestamp changed, reset sequence in millisecond
        else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // combine a 64 bits ID by shift bit and or operator
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) //
                | (datacenterId << DATACENTER_ID_SHIFT) //
                | (workerId << WORKER_ID_SHIFT) //
                | sequence;
    }

    /**
     * block one millisecond until getting new timestamp
     *
     * @param lastTimestamp generated timestamp of last ID
     * @return current timestamp
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * return current time based on millisecond
     *
     * @return current time millisecond
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }
}