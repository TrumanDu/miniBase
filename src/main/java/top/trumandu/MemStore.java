package top.trumandu;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Truman.P.Du
 * @date 2021/3/29 20:53
 * @description
 */
public class MemStore {

    private volatile ConcurrentSkipListMap<KeyValue, KeyValue> memMap;
    private volatile ConcurrentSkipListMap<KeyValue, KeyValue> snapshot;
    private final AtomicLong dataSize = new AtomicLong(0);
    private ExecutorService pool;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private long DEFAULT_MEM_SIZE = 10 * 1024 * 1024;

    public MemStore(ExecutorService pool) {
        memMap = new ConcurrentSkipListMap<>();
        snapshot = null;
        this.pool = pool;
    }

    public void add(KeyValue kv) {
        flushIfNeeded(true);
        lock.readLock().lock();

        try {
            KeyValue old = memMap.put(kv, kv);
            if (old != null) {
                dataSize.addAndGet(kv.getByteSize() - old.getByteSize());
            } else {
                dataSize.addAndGet(kv.getByteSize());
            }
        } finally {
            lock.readLock().unlock();
        }

        flushIfNeeded(false);

    }

    private void flushIfNeeded(boolean shouldBlock) {
        if (getDataSize() > DEFAULT_MEM_SIZE) {
            //TODO
        }
    }

    public long getDataSize() {
        return this.dataSize.get();
    }

}
