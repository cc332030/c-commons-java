package com.c332030.commons.id.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import com.c332030.commons.id.IdGen;

import static com.c332030.util.data.CDateUtils.DATE_TIME_FORMATTER;

/**
 * <p>
 * Description: SnowFlakeIdGenImpl
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public class IdGenSnowFlakeImpl implements IdGen {

    private static final Random RANDOM = new Random();

    private static final String EPOCH_STRING = "2021-01-01 00:00:00";

    private static final long EPOCH_MILLS = LocalDateTime.parse(EPOCH_STRING, DATE_TIME_FORMATTER)
        .toInstant(ZoneOffset.of("+8"))
        .toEpochMilli()
        ;

    /**
     * worker id 存储字节数
     */
    private static final int WORKER_ID_BITS = 10;

    /**
     * 序列号 存储字节数
     */
    private static final int SEQUENCE_BITS = 12;

    /**
     * 时间戳偏移量
     */
    private static final int TIME_SHIFT = WORKER_ID_BITS + SEQUENCE_BITS;

    /**
     * max sequence
     *
     * -1 = all 1 (bit)
     */
    private static final int MAX_SEQUENCE = ~(-1 << SEQUENCE_BITS);

    private long lastMills = 0L;

    private int sequence = 0;

    @Override
    public synchronized long get(int workerId) {

        var currentMills = System.currentTimeMillis();
        if(currentMills < lastMills) {
            throw new IllegalStateException("Clock moved backwards, moved mills: " + (lastMills - currentMills));
        }

        if(currentMills == lastMills) {

            sequence = (sequence + 1) & MAX_SEQUENCE;
            if(0 == sequence) {
                sequence = random();
            }
        } else {
            sequence = random();
        }
        lastMills = currentMills;

        return (currentMills - EPOCH_MILLS) << TIME_SHIFT
            | (long) workerId << SEQUENCE_BITS
            | sequence
            ;
    }

    private static int random() {
        return RANDOM.nextInt(100);
    }

}
