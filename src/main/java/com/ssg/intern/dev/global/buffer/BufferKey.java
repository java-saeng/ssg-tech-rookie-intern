package com.ssg.intern.dev.global.buffer;

import lombok.Getter;

import java.util.Objects;

public class BufferKey {

    @Getter
    private final long feedId;

    @Getter
    private final long accountId;

    private static BufferKey bufferKey;

    private BufferKey(final long feedId, final long accountId) {
        this.feedId = feedId;
        this.accountId = accountId;
    }

    public static BufferKey of(final long feedId, final long accountId) {
        if (bufferKey == null) {
            bufferKey = createBuffer(feedId, accountId);
            return bufferKey;
        }

        if (bufferKey.getFeedId() == feedId && bufferKey.getAccountId() == accountId) {
            return bufferKey;
        }

        bufferKey = createBuffer(feedId, accountId);
        return bufferKey;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BufferKey that = (BufferKey) o;
        return feedId == that.feedId && accountId == that.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedId, accountId);
    }

    private static BufferKey createBuffer(final long feedId, final long accountId) {
        return new BufferKey(feedId, accountId);
    }
}
