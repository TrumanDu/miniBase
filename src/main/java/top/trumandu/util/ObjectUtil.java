package top.trumandu.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Truman.P.Du
 * @date 2021/3/26 21:15
 * @description
 */
public class ObjectUtil {
    public static long getByteSize(Object obj) throws IOException {
        byte[] bytes = new byte[0];

        try (ByteArrayOutputStream bo = new ByteArrayOutputStream(); ObjectOutputStream oo = new ObjectOutputStream(bo)) {
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        }
        return bytes.length;
    }
}
