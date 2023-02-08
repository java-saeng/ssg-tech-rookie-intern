package com.ssg.intern.dev.global.buffer;

import lombok.Getter;

@Getter
public class BufferStatus {

    private final long feedId;

    private final long accountId;

    private final boolean deleteFlag;

    private BufferStatus(final long feedId, final long accountId, final boolean deleteFlag) {
        this.feedId = feedId;
        this.accountId = accountId;
        this.deleteFlag = deleteFlag;
    }

    public static BufferStatus of(final long feedId, final long accountId, final boolean deleteFlag) {
        return new BufferStatus(feedId, accountId, deleteFlag);
    }
}
