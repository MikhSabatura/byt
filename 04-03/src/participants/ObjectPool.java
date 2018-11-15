package participants;

import java.util.*;

public class ObjectPool {

    private static ObjectPool instance;

    private List<IntegerCounter> integerCounters;
    private int maxPoolSize;
    private int acquiredCount;

    private ObjectPool() {
        this.integerCounters = new ArrayList<>();
        this.acquiredCount = 0;
        this.maxPoolSize = 100;
    }

    public static ObjectPool getInstance() {
        if (instance == null) {
            return new ObjectPool();
        }
        return instance;
    }

    public IntegerCounter acquireIntCounter() {
        if (acquiredCount >= maxPoolSize) {
            return null;
        }
        acquiredCount++;
        if (integerCounters.isEmpty()) {
            return new IntegerCounter();
        }
        return integerCounters.remove(0);
    }

    public void releaseIntCounter(IntegerCounter integerCounter) {
        acquiredCount--;
        integerCounters.add(integerCounter);
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
