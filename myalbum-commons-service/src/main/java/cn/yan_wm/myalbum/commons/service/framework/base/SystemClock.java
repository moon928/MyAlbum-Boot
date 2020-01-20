package cn.yan_wm.myalbum.commons.service.framework.base;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: MyAlbum-Boot
 * @description: 系统时钟
 * @author: yan_zt
 * @create: 2020-01-14 11:39
 */
public class SystemClock {
    private final long period;
    private final AtomicLong now;

    private SystemClock(long period, SystemClock systemClock) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        this.scheduleClockUpdating();
    }

    private static SystemClock instance() {
        return SystemClock.InstanceHolder.INSTANCE;
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1, (new BasicThreadFactory.Builder()).namingPattern("System-Clock-%d").daemon(true).build());
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                SystemClock.this.now.set(System.currentTimeMillis());
            }
        }, this.period, this.period, TimeUnit.MILLISECONDS);
    }

    private long currentTimeMillis() {
        return this.now.get();
    }

    public static long now() {
        return instance().currentTimeMillis();
    }

    public static Date nowDate() {
        return new Date(now());
    }

    public static String nowDateStr() {
        return (new Timestamp(now())).toString();
    }

    private static class InstanceHolder {
        public static final SystemClock INSTANCE = new SystemClock(1L, (SystemClock)null);

        private InstanceHolder() {
        }
    }
}
