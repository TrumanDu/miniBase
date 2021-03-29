package top.trumandu;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Truman.P.Du
 * @date 2021/3/26 20:36
 * @description
 */
public class Base implements MiniBase {

    private LRUCache lruCache;
    /**
     * 持久化文件线程池
     */
    private ExecutorService pool;
    private AtomicLong sequenceId;
    private MemStore memStore;

    public Base() {
        lruCache = new LRUCache();
        sequenceId = new AtomicLong(0);
        pool = Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("persist-thread-%d").build());
        memStore = new MemStore(pool);
    }

    @Override
    public boolean put(byte[] key, byte[] value) {
        KeyValue keyValue = KeyValue.createPut(key, value, sequenceId.getAndIncrement());
        put(keyValue);
        return true;
    }

    @Override
    public KeyValue get(byte[] key) {
        KeyValue keyValue = lruCache.getOrNull(key);
        if (!Objects.isNull(keyValue)) {
            return keyValue;
        }
        //TODO 从memStore和diskStore获取真实数据

        return null;
    }

    @Override
    public void delete(byte[] key) {
        KeyValue keyValue = KeyValue.createDelete(key, sequenceId.getAndIncrement());
        put(keyValue);
    }

    private void put(KeyValue kv) {
        lruCache.add(kv);
        //TODO wal避免程序挂掉，数据丢失
        memStore.add(kv);
    }

    @Override
    public MiniIterable<KeyValue> scan(byte[] key, byte[] value) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
