package main;

import participants.IntegerCounter;
import participants.ObjectPool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ObjectPool pool = ObjectPool.getInstance();
        pool.setMaxPoolSize(3);
//        acquiring all the counters from the pool
        List<IntegerCounter> counters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            counters.add(pool.acquireIntCounter());
        }
//        checking if the pool allows acquiring more counters then it's max capacity
        try {
            IntegerCounter nullCounter = pool.acquireIntCounter();
            nullCounter.calculateResult();
        } catch (NullPointerException e) {
            System.out.println("couldn't acquire the counter");
        }

//        performing the action on the counters and returning them to the pool
        System.out.println("using the counters for the first time:");
        useCoutners(counters, pool);

//        checking if the counters can be reused
        System.out.println("reusing the counters:");
        for (int i = 0; i < 3; i++) {
            counters.add(pool.acquireIntCounter());
        }
        useCoutners(counters, pool);
    }

    //    uses the counters, removes them from the list and returns to the pool
    private static void useCoutners(List<IntegerCounter> counters, ObjectPool pool) {
        Iterator<IntegerCounter> iterator = counters.iterator();
        while (iterator.hasNext()) {
            IntegerCounter c = iterator.next();
            System.out.println(c.calculateResult());
            pool.releaseIntCounter(c);
            iterator.remove();
        }
    }

}
