package top.trumandu;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Truman.P.Du
 * @date 2021/3/24 21:37
 * @description
 */
public interface MiniBase extends Closeable {

    boolean put(byte[] key, byte[] value);

    KeyValue get(byte[] key);

    void delete(byte[] key);

    default MiniIterable<KeyValue> scan() throws IOException {
        return scan(Bytes.EMPTY_BYTES, Bytes.EMPTY_BYTES);
    }

    MiniIterable<KeyValue> scan(byte[] key, byte[] value);

    interface MiniIterable<KeyValue> {
        boolean hasNext() throws IOException;

        KeyValue next() throws IOException;
    }

}
