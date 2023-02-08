package com.ssg.intern.dev.global.buffer;

import lombok.Getter;

@Getter
public class BufferInfo {

    private final long bufferSize;

    private final boolean deleteFlag;

    public BufferInfo(final long bufferSize, final boolean deleteFlag) {
        this.bufferSize = bufferSize;
        this.deleteFlag = deleteFlag;
    }
}
