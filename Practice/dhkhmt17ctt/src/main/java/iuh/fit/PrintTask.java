/*
 * @ (#) PrintTask.java       28/12/2023
 *
 * Copyright (c) 2023 IUH. All rights reserved.
 */

package iuh.fit;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   28/12/2023
 */
public class PrintTask implements Runnable{
    private char ch;
    private int times;

    public PrintTask(char ch, int times) {
        this.ch = ch;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            try {
//                Thread.sleep(1000);
                Thread.yield();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.print(ch + " ");
        }
    }
}
