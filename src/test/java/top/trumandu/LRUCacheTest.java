package top.trumandu;

import org.junit.Assert;
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
            byteSize = byteSize + keyValue.getByteSize();
        }

        System.out.println(byteSize);
        Assert.assertNull(lruCache.getOrNull(Bytes.toBytes(1)));
        System.out.println(new String(lruCache.getOrNull(Bytes.toBytes(999195)).getValue()));
        System.out.println(lruCache.size());
        Assert.assertEquals(1024 * 1024, lruCache.getTotalByteSizeSize());
    }

    @Test
    public void testMap() throws IOException {
        byte[] key = Bytes.toBytes(999194);
        KeyValue keyValue = KeyValue.create(key, Bytes.toBytes("value"), KeyValue.Op.Put, 100);
        Map<byte[], KeyValue> m = new LinkedHashMap<>();

        m.put(key, keyValue);

        System.out.println(m.getOrDefault(key, null));
        System.out.println(m.getOrDefault(Bytes.toBytes(999194), null));
    }
}
