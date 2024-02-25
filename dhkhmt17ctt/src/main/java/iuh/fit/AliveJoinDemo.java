/*
 * @ (#) AliveDemo.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.stream.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class AliveJoinDemo {
    public static void main(String[] args) {
        Runnable task = () -> {
            int total = IntStream.range(0, 1_000_000).sum();
            System.out.println("Total: " + total);
        };

//        FutureTask<Integer> futureTask = new FutureTask<>(task);

        Thread t = new Thread(task, "Thread 1");

        System.out.println("Thread 1 is alive: " + t.isAlive()); // false

        t.start();

        System.out.println("Thread 1 is alive: " + t.isAlive()); // true

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        while (!t.isAlive()) {
//        }

        System.out.println("Thread 1 is alive: " + t.isAlive()); // false

    }
}
