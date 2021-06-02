package com.wthfeng.kurdran.core.event;

import java.util.EventObject;

/**
 * @author wangtonghe
 * @since 2021/6/2 16:16
 */
public abstract class ApplicationEvent extends EventObject {

    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return timestamp;
    }
}
