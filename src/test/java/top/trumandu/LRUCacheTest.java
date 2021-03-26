package top.trumandu;

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Truman.P.Du
 * @date 2021/3/26 22:32
 * @description
 */
public class LRUCacheTest {

    @Test
    public void test() throws IOException {
        LRUCache lruCache = new LRUCache();
        String value = "北京时间3月26日，CBA常规赛继续进行，辽宁对决吉林。辽宁在第二节打出了一波14-0，拉开了分差。下半场付豪不慎戳伤手指，提前退出比赛。不过，辽宁继续扩大分差，最终，100-83轻松击败对手，三杀吉林，完成了3连胜。本场郭艾伦进入大名单，但没有出现在现场。";
        long byteSize = 0;
        for (int i = 0; i < 1000000; i++) {
            KeyValue keyValue = KeyValue.create(Bytes.toBytes(i), Bytes.toBytes(value), KeyValue.Op.Put, i);
            lruCache.add(keyValue);
            System.out.println(lruCache.getOrNull(Bytes.toBytes(i)));

            byteSize = byteSize + keyValue.getByteSize();
        }

        System.out.println(byteSize);

        System.out.println(lruCache.getOrNull(Bytes.toBytes(1)));

        System.out.println(lruCache.getOrNull(Bytes.toBytes(999195)).getValue());


        System.out.println(lruCache.size());
        System.out.println(lruCache.getTotalByteSizeSize());
    }

    @Test
    public void testMap() {
        byte[] key = Bytes.toBytes(999194);

        Map<byte[], byte[]> m = new LinkedHashMap<>();

        m.put(key, key);

        System.out.println(m.getOrDefault(key, null));
    }
}
