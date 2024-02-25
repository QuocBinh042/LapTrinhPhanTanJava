/*
 * @ (#) ThreadDemo.java       28/12/2023
 *
 * Copyright (c) 2023 IUH. All rights reserved.
 */

package iuh.fit;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   28/12/2023
 */
public class ThreadDemo {
    public static void main(String[] args) {

        Runnable task1 = new PrintTask('A', 10);
        Runnable task2 = new PrintTask('B', 10);

        Thread t1 = new Thread(task1); // Thread-0
//        t1.setName("Thread 1");
        Thread t2 = new Thread(task2); // Thread-1

        t1.start();
        t2.start();
    }
}
