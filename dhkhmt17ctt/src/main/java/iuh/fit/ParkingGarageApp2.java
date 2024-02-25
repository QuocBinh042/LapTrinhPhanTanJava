/*
 * @ (#) ParkingGarageApp.java       11/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/*
 * @description
 * @author: Khanh Nguyen Thi Hoang
 * @date:   11/01/2024
 */
public class ParkingGarageApp2 {

    private static final int CAPACITY = 5;
    private static ParkingGarage2 garage = new ParkingGarage2(CAPACITY);

    public static void main(String[] args) {

        Runnable taskEnter = () -> {
            try {
                garage.enter();
                System.out.println("Enter successfully!, available: " + garage.getAvailable());
            } catch (RuntimeException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable taskLeave = () -> {
            try {
                Thread.sleep(2000);
                garage.leave();
                System.out.println("Leave successfully!, available: " + garage.getAvailable());
            }catch (RuntimeException | InterruptedException e){
                System.out.println(e.getMessage());
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executor.execute(taskEnter);
        }

        executor.execute(taskLeave);

        executor.execute(taskEnter);
        executor.execute(taskEnter);

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("Available: " + garage.getAvailable()); //Expected Available: 0
    }
}

class ParkingGarage2 { // bai do xe
    private int capacity; // so luong xe toi da
    private int available; // so luong cho do con trong
    private static final Lock LOCK = new ReentrantLock();
    private static final Condition CONDITION = LOCK.newCondition();

    public ParkingGarage2(int capacity) {
        this.capacity = capacity;
        this.available = capacity;
    }

    public void enter() throws InterruptedException {

        LOCK.lock(); // Acquire the lock
        try {
            while (available == 0) {
                System.out.println("Parking garage is full, please wait!");
                CONDITION.await(2, TimeUnit.SECONDS); // wait for 2 seconds, then check again if available
            }

            available--; // xe vao duoc
        }finally {
            LOCK.unlock(); // Release the lock
        }
    }

    public void leave(){
        LOCK.lock();
        try {
            if (available == capacity) {
                throw new RuntimeException("No car in parking garage!");
            } else { // xe ra duoc
                available++;
                CONDITION.signalAll(); // notify all threads waiting for this condition
            }
        }finally {
            LOCK.unlock();
        }
    }

    public int getAvailable() {
        return available;
    }

    public int getCapacity() {
        return capacity;
    }
}