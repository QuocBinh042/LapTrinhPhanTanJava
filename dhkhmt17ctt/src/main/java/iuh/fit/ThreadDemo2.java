/*
 * @ (#) ThreadDemo2.java       28/12/2023
 *
 * Copyright (c) 2023 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.stream.*;

/*
 * @description
 * @author: Khanh Nguyen Thi Hoang
 * @date:   28/12/2023
 */
public class ThreadDemo2 {

    public static void main(String[] args) {

        Runnable task1 = new PrintTask('A', 100);

        Runnable task2 = () -> {
            int sum = IntStream.rangeClosed(1, 100).sum();
            System.out.println("Sum = " + sum);
        };

        Thread t1 = new Thread(task2);
        t1.start();
    }
}
