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
        String value = "中共中央总书记、国家主席、中央军委主席习近平24日到武警第二机动总队视察。他强调，武警机动总队是武警部队机动作战的重要力量，使命光荣，责任重大。要贯彻新时代党的强军思想，贯彻新时代军事战略方针，抓好常态化疫情防控，全面加强练兵备战，全面提高履行使命任务能力，奋力开创部队建设新局面，以优异成绩迎接中国共产党建党100周年。\n" +
                "\n" +
                "三月的八闽大地，山清水秀，春意盎然。上午10时15分许，习近平来到武警第二机动总队。武警第二机动总队是在深化国防和军队改革中调整组建的，近年来出色完成一系列急难险重任务。习近平对练兵备战高度重视，他一下车就察看部队训练情况。训练场上，操作口令此起彼伏，专业装备隆隆作响，一派繁忙练兵景象。习近平依次察看反爆炸器材操作使用、防化侦察洗消、工程机械应用作业、桥梁架设等专业训练，详细了解有关情况。习近平同工程机械操作手亲切交流，勉励大家刻苦训练、科学训练，提高遂行任务能力。离开训练场时，官兵整齐列队，向习主席敬礼，齐声高呼“听党指挥、能打胜仗、作风优良”。";
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
