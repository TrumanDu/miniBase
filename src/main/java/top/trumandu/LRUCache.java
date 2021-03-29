package top.trumandu;

import java.util.*;

/**
 * @author Truman.P.Du
 * @date 2021/3/26 20:48
 * @description
 */
public class LRUCache {
    private Map<Key, KeyValue> map;
    private long maxByteSize;
    /**
     * 目前已存数据大小
     */
    private long totalByteSize = 0;
    /**
     * 为测试 1M
     */
    private int DEFAULT_BYTE_SIZE = 1 * 1024 * 1024;

    public LRUCache() {
        this.maxByteSize = DEFAULT_BYTE_SIZE;
        map = new LinkedHashMap<>(5000);
    }

    public LRUCache(int maxByteSize) {
        this.maxByteSize = maxByteSize;
        map = new LinkedHashMap<>(5000);
    }


    public synchronized void add(KeyValue keyValue) {
        Key key = new Key(keyValue.getKey());
        if (totalByteSize <= DEFAULT_BYTE_SIZE) {
            KeyValue oldValue = map.put(key, keyValue);
            long size = keyValue.getByteSize();
            if (oldValue != null) {
                size = size - oldValue.getByteSize();
            }
            totalByteSize = totalByteSize + size;
        } else {
            Object[] entries = map.values().toArray();
            KeyValue earlyKeyValue = (KeyValue) entries[0];
            map.remove(new Key(earlyKeyValue.getKey()));
            totalByteSize = totalByteSize - earlyKeyValue.getByteSize();
            add(keyValue);
        }

        map.put(key, keyValue);
    }

    public KeyValue getOrNull(byte[] key) {

        return map.getOrDefault(new Key(key), null);
    }

    public long size() {
        return map.size();
    }

    public long getTotalByteSizeSize() {
        return totalByteSize;
    }
}
