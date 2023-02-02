package com.ssg.intern.dev.global.buffer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapBuffer {

    private static final boolean UPDATE_STATE = true;
    private static final boolean STAY_STATE = false;

    private final ConcurrentMap<BufferKey, Boolean> buffer;

    public ConcurrentMapBuffer() {
        buffer = new ConcurrentHashMap<>();
    }

    private void fillBuffer(BufferKey bufferKey, boolean state) {
        buffer.put(bufferKey, state);
    }

    public boolean isBufferActivityInBuffer(final long feedId, final long accountId) {
        final BufferKey bufferKey = BufferKey.of(feedId, accountId);

        return buffer.get(bufferKey) != null;
    }

    public BufferInfo put(long feedId, long accountId, boolean isCheckedEntity) {

        BufferKey bufferKey = BufferKey.of(feedId, accountId);

        if (buffer.get(bufferKey) == null) {
            fillBuffer(bufferKey, isCheckedEntity ? UPDATE_STATE : STAY_STATE);
        }

        if (buffer.get(bufferKey).equals(STAY_STATE)) {
            buffer.put(bufferKey, UPDATE_STATE);

            return new BufferInfo(buffer.size(), UPDATE_STATE);
        }

        buffer.put(bufferKey, STAY_STATE);
        return new BufferInfo(buffer.size(), STAY_STATE);
    }

    public List<BufferStatus> flush() {
        List<BufferStatus> flushEntityCandidate = new ArrayList<>();

        for (final BufferKey bufferKey : buffer.keySet()) {
            if (buffer.get(bufferKey).equals(UPDATE_STATE)) {
                flushEntityCandidate.add(BufferStatus.of(bufferKey.getFeedId(),
                                                         bufferKey.getAccountId(),
                                                         false));
            } else {
                flushEntityCandidate.add(BufferStatus.of(bufferKey.getFeedId(),
                                                         bufferKey.getAccountId(),
                                                         true));
            }
        }

        clearBuffer();

        return flushEntityCandidate;
    }

    private void clearBuffer() {
        buffer.clear();
    }
}
