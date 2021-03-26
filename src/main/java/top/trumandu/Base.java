package top.trumandu;

import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Truman.P.Du
 * @date 2021/3/26 20:36
 * @description
 */
public class Base implements MiniBase {


    public Base() {

    }

    @Override
    public boolean put(byte[] key, byte[] value) {
        return false;
    }

    @Override
    public KeyValue get(byte[] key) {
        return null;
    }

    @Override
    public void delete(byte[] key) {

    }

    @Override
    public MiniIterable<KeyValue> scan(byte[] key, byte[] value) {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
