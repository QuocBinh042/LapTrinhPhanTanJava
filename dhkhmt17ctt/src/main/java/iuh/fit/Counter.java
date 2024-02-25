/*
 * @ (#) Count.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

//    public synchronized void decrement() {
    public void decrement() {
        this.count--;
    }

//    public synchronized void increment() { // synchronized method
    public void increment() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }
}
