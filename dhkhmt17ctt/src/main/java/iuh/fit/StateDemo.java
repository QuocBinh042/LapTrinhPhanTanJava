/*
 * @ (#) StateDemo.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class StateDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from " + Thread.currentThread().getName());
        }, "Thread 1");

        System.out.println("State of thread 1 before calling start(): " + t1.getState());

        t1.start();

        System.out.println("State of thread 1 after calling start(): " + t1.getState()); // RUNNABLE

        try {
            t1.join(); // to wait for t1 to finish
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("State of thread 1 when it has finished its job: " + t1.getState()); // TERMINATED
    }
}
