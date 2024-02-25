/*
 * @ (#) ThreadDemo4.java       04/01/2024
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
public class ThreadDemo4 {
    public static void main(String[] args) {
        SumRangeTask2 task1 = new SumRangeTask2(0, 100);
        SumRangeTask2 task2 = new SumRangeTask2(100, 200);
        SumRangeTask2 task3 = new SumRangeTask2(200, 300);

        FutureTask<Integer> futureTask1 = new FutureTask<>(task1);
        FutureTask<Integer> futureTask2 = new FutureTask<>(task2);
        FutureTask<Integer> futureTask3 = new FutureTask<>(task3);

        Thread thread1 = new Thread(futureTask1, "Thread 1");
        Thread thread2 = new Thread(futureTask2, "Thread 2");
        Thread thread3 = new Thread(futureTask3, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            int total = futureTask1.get() + futureTask2.get() + futureTask3.get();
            System.out.println("Total = " + total);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " finished");
    }
}
