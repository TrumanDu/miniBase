package top.trumandu;

/**
 * @author: Truman.P.Du
 * @date: 2021/3/24 21:38
 * @description:
 */
public class KeyValue implements Comparable<KeyValue> {
    @Override
    public int compareTo(KeyValue o) {
        int rep = Bytes.compare(this.key, o.key);
        if (rep != 0) {
            return rep;
        }
        if (this.sequenceId != o.sequenceId) {
            return this.sequenceId > o.sequenceId ? -1 : 1;
        }
        if (this.op != o.op) {
            return this.op.getCode() > o.op.getCode() ? -1 : 1;
        }

        return 0;
    }

    public enum Op {
        Put((byte) 0),
        Delete((byte) 1);
        /**
         * 操作编码
         */
        private byte code;

        Op(byte code) {
            this.code = code;
        }

        public static Op code2Op(byte code) {
            switch (code) {
                case 0:
                    return Put;
                case 1:
                    return Delete;
                default:
                    throw new IllegalArgumentException("Unknown code: " + code);
            }
        }

        public byte getCode() {
            return this.code;
        }
    }

    private byte[] key;
    private byte[] value;
    private Op op;
    private long sequenceId;

    public static KeyValue create(byte[] key, byte[] value, Op op, long sequenceId) {
        return new KeyValue(key, value, op, sequenceId);
    }

    public static KeyValue createPut(byte[] key, byte[] value, long sequenceId) {
        return KeyValue.create(key, value, Op.Put, sequenceId);
    }

    public static KeyValue createDelete(byte[] key, long sequenceId) {
        return KeyValue.create(key, Bytes.EMPTY_BYTES, Op.Delete, sequenceId);
    }

    private KeyValue(byte[] key, byte[] value, Op op, long sequenceId) {
        assert key != null;
        assert value != null;
        assert op != null;
        assert sequenceId >= 0;
        this.key = key;
        this.value = value;
        this.op = op;
        this.sequenceId = sequenceId;
    }

    public byte[] getKey() {
        return key;
    }

    public byte[] getValue() {
        return value;
    }

    public Op getOp() {
        return this.op;
    }

    public long getSequenceId() {
        return this.sequenceId;
    }


}
