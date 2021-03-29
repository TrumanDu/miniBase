package top.trumandu;

import java.util.Arrays;

/**
 * @author Truman.P.Du
 * @date 2021/3/29 17:40
 * @description
 */
public class Key {
    private byte[] key;

    public Key(byte[] key) {
        this.key = key;
    }


    public byte[] getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Key key1 = (Key) o;
        return Arrays.equals(key, key1.key);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(key);
    }
}
