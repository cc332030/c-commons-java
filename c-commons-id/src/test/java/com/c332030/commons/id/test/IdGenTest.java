package com.c332030.commons.id.test;

import org.junit.jupiter.api.Test;

import com.c332030.commons.id.IdGen;
import com.c332030.commons.id.impl.IdGenSnowFlakeImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description: IdGenTest
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@Slf4j
public class IdGenTest {

    @Test
    public void testGet() {

        IdGen idGen = new IdGenSnowFlakeImpl();

        long id = idGen.get(1);

        String binaryString = Long.toBinaryString(id);
        System.out.println(binaryString.length());
        System.out.println(binaryString);
    }

    @Test
    public void testGetWhile() {

        IdGen idGen = new IdGenSnowFlakeImpl();

        int size = 1000;

        long start = System.currentTimeMillis();
        while (size-- > 0) {
            long id = idGen.get(1);
            log.info("id: {}", id);
        }
        long end = System.currentTimeMillis();

        System.out.println("cost: " + (end - start));
    }
}
