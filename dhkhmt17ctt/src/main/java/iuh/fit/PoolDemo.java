/*
 * @ (#) PoolDemo.java       04/01/2024
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
public class PoolDemo {
    public static void main(String[] args) {

        Callable task1 = new SumRangeTask2(0, 100);
        Callable task2 = new SumRangeTask2(100, 200);
        Callable task3 = new SumRangeTask2(200, 300);
        Callable task4 = new SumRangeTask2(300, 400);
        Callable task5 = new SumRangeTask2(400, 500);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<Integer> future1 = executor.submit(task1);
        Future<Integer> future2 = executor.submit(task2);
        Future<Integer> future3 = executor.submit(task3);
        Future<Integer> future4 = executor.submit(task4);
        Future<Integer> future5 = executor.submit(task5);

        executor.shutdown();
        while (!executor.isTerminated()){}

        try {
            int result = future1.get() + future2.get() + future3.get() + future4.get() + future5.get();
            System.out.println("Ressult from 0 to 500: " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }
}
