package com.ssg.intern.dev.bookmark.application.port.in;

public interface BufferUseCase {

    void bufferCaching(final long accountId, final long feedId);

    void bufferFlush();
}
