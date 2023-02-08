package com.ssg.intern.dev.global.buffer;

import com.ssg.intern.dev.global.buffer.BufferInfo;
import com.ssg.intern.dev.global.buffer.BufferStatus;
import com.ssg.intern.dev.global.buffer.ConcurrentMapBuffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConcurrentMapBuffer Unit Test")
class ConcurrentMapBufferTest {

    ConcurrentMapBuffer concurrentMapBuffer;

    @BeforeEach
    void init() {
        concurrentMapBuffer = new ConcurrentMapBuffer();

        //true 인 상태에서 이미 한 번 누르고 난 후이기 때문에 buffer에는 0으로 저장, 즉, check 되지 않은 상태로 된다
        concurrentMapBuffer.put(1L, 1L, true);
        concurrentMapBuffer.put(1L, 2L, false);
        concurrentMapBuffer.put(1L, 3L, true);
        concurrentMapBuffer.put(2L, 1L, true);
        concurrentMapBuffer.put(2L, 2L, false);
    }

    @Test
    @DisplayName("put() : buffer miss이면서 Entity가 체크되면 값이 0이 되고 버퍼 크기가 1 늘어난다.")
    void test_HasCheckedEntity_BufferMiss() {

        //when
        final BufferInfo bufferInfo = concurrentMapBuffer.put(1L, 4L, true);

        //then
        assertEquals(bufferInfo.getBufferSize(), 6);
        assertFalse(bufferInfo.isDeleteFlag());
    }

    @Test
    @DisplayName("put() : buffer miss이면서 Entity가 체크되지 않았다면 값이 1이 되고 버퍼 크기가 1 늘어난다.")
    void test_HasNotCheckedEntity_BufferMiss() {

        //when
        final BufferInfo bufferInfo = concurrentMapBuffer.put(1L, 4L, false);

        //then
        assertEquals(bufferInfo.getBufferSize(), 6);
        assertTrue(bufferInfo.isDeleteFlag());
    }

    @Test
    @DisplayName("put() : buffer hit면서 Entity가 체크되면 값이 1이 되고 버퍼 크기는 그대로다")
    void test_HasCheckedEntity_BufferHit() {

        //when
        final BufferInfo bufferInfo = concurrentMapBuffer.put(1L, 3L, false);

        //then
        assertEquals(bufferInfo.getBufferSize(), 5);
        assertTrue(bufferInfo.isDeleteFlag());
    }

    @Test
    @DisplayName("put() : buffer hit면서 Entity가 체크되지 않았다면 0이 되고 버퍼 크기는 그대로다")
    void test_HasNotCheckedEntity_BufferHit() {

        //when
        final BufferInfo bufferInfo = concurrentMapBuffer.put(1L, 2L, false);

        //then
        assertEquals(bufferInfo.getBufferSize(), 5);
        assertFalse(bufferInfo.isDeleteFlag());
    }

    @Test
    @DisplayName("flush() : buffer의 value가 업데이트 상태(값이 1)인 경우에만 List에 담길 수 있다.")
    void test_OnlyValueOneInList() {

        //when
        final List<BufferStatus> result = concurrentMapBuffer.flush();

        //then

        assertAll(
                () -> assertEquals(result.size(), 5),
                () -> assertEquals(result.stream()
                                         .filter(BufferStatus::isDeleteFlag)
                                         .count(), 3),
                () -> assertThat(result).extracting("accountId")
                                        .contains(2L),
                () -> assertThat(result).extracting("feedId")
                                        .contains(1L, 2L)
        );
    }
}