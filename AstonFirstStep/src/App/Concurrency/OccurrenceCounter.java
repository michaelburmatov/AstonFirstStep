package App.Concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;


public final class OccurrenceCounter {

    private OccurrenceCounter() {}

    public static <T> int count(List<T> data, Predicate<T> predicate, int threadCount)
            throws InterruptedException {

        if (data == null || data.isEmpty()) return 0;
        if (threadCount <= 0) threadCount = 1;
        if (threadCount > data.size()) threadCount = data.size();

        AtomicInteger total = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<>();

        int chunkSize = (data.size() + threadCount - 1) / threadCount;

        for (int i = 0; i < threadCount; i++) {
            final int from = i * chunkSize;
            final int to   = Math.min(from + chunkSize, data.size());
            if (from >= to) break;

            Thread t = new Thread(() -> {
                int local = 0;
                for (int j = from; j < to; j++) {
                    if (predicate.test(data.get(j))) {
                        local++;
                    }
                }
                total.addAndGet(local);
            });
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
        return total.get();
    }
}