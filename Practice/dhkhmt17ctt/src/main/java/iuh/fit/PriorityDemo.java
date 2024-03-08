/*
 * @ (#) PriorityDemo.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class PriorityDemo {

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println("Hello from " + Thread.currentThread().getName());
        };

        System.out.println("Before setting priority: " +
                Thread.currentThread().getName() + " priority = " + Thread.currentThread().getPriority()); // default priority = 5

        Thread.currentThread().setPriority(7); // set priority for main thread

        Thread thread = new Thread(task, "Thread 1"); // default name = Thread-0

        System.out.println("After setting priority: " + thread.getName() + " priority = " + thread.getPriority()); // ??
        System.out.println("After setting priority: " +
                Thread.currentThread().getName() + " priority = " + Thread.currentThread().getPriority()); // Priority = 7

        System.out.println("Normal priority: " + Thread.NORM_PRIORITY); // 5
        System.out.println("Min priority: " + Thread.MIN_PRIORITY); // 1
        System.out.println("Max priority: " + Thread.MAX_PRIORITY); // 10


    }

}
