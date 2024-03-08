/*
 * @ (#) ThreadDemo3.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        SumRangeTask task1 = new SumRangeTask(0, 100);
        SumRangeTask task2 = new SumRangeTask(100, 200);
        SumRangeTask task3 = new SumRangeTask(200, 300);

        Thread thread1 = new Thread(task1, "Thread 1");
        Thread thread2 = new Thread(task2, "Thread 2");
        Thread thread3 = new Thread(task3, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

        int total = task1.getResult() + task2.getResult() + task3.getResult();
        System.out.println("Total = " + total);
    }
}
