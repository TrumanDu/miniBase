package top.trumandu;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Truman.P.Du
 * @date 2021/3/29 18:03
 * @description
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class LRUBenchmark {
    private LRUCache lruCache;
    private byte[] key;

    @Setup
    public void init() throws IOException {
        lruCache = new LRUCache();
        key = Bytes.toBytes(UUID.randomUUID().toString());
    }

    @Benchmark
    @GroupThreads(4)
    public void testLocalCacheSet() throws IOException {
        String value = "北京时间3月26日，CBA常规赛继续进行，辽宁对决吉林。辽宁在第二节打出了一波14-0，拉开了分差。下半场付豪不慎戳伤手指，提前退出比赛。不过，辽宁继续扩大分差，最终，100-83轻松击败对手，三杀吉林，完成了3连胜。本场郭艾伦进入大名单，但没有出现在现场。";
        lruCache.add(KeyValue.create(key, Bytes.toBytes(value), KeyValue.Op.Put, 1));
    }

    /**
     * GroupThreads 并发线程数设置为3，可以打出接口最大的ops
     */
    @Benchmark
    @GroupThreads(4)
    public KeyValue testLocalCacheGet() {
        return lruCache.getOrNull(key);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LRUBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
