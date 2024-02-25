/*
 * @ (#) RaceConditionDemo.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class RaceConditionDemo {
    private static Counter counter = new Counter(); // sharing object, = 0

    public static void main(String[] args) {

        Runnable task = () -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (counter) { // synchronized block
                counter.increment();
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {

            executor.submit(task);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            // wait for all tasks to finish
        }

        System.out.println("Final count is : " + counter.getCount()); // expected 1000, but actual is 1000

    }
}
