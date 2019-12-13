package io.batcloud.cache;

import java.util.Date;

public abstract class AbstractCache {

    protected Date lastFlushTime = new Date();

    public abstract void flushCache();

    public Date getLastFlushTime(){
        return this.lastFlushTime;
    }
}
