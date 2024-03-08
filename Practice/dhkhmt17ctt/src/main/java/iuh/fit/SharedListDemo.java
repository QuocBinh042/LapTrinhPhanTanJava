/*
 * @ (#) SharedListDemo.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.*;
import java.util.concurrent.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class SharedListDemo {

//    private static List<Integer> list = new ArrayList<>(); // Not thread-safe
    private static List<Integer> list = new Vector<>(); // Thread-safe

    public static void main(String[] args) {
        Random random = new Random();

        Runnable task = () -> {
//            synchronized (list) {
                list.add(random.nextInt(100)); // Range: 0 - 99
//            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10); // Number of threads: 10
        for (int i = 0; i < 1000; i++) {
            executorService.execute(task);
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // Wait for all threads to finish
        }

        System.out.println("List size: " + list.size()); // Expected: 1000
    }
}
