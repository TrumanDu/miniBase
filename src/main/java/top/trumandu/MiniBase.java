package top.trumandu;

import java.io.Closeable;

/**
 * @author: Truman.P.Du
 * @date: 2021/3/24 21:37
 * @description:
 */
public interface MiniBase extends Closeable {

    public boolean put(byte[] key, byte[] value);

    public byte[] get(byte[] key);

    public void delete(byte[] key);


}
