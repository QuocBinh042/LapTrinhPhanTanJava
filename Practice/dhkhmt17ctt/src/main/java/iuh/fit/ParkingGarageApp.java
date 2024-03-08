/*
 * @ (#) ParkingGarageApp.java       11/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;

/*
 * @description
 * @author: Khanh Nguyen Thi Hoang
 * @date:   11/01/2024
 */
public class ParkingGarageApp {

    private static final int CAPACITY = 5;
    private static ParkingGarage garage = new ParkingGarage(CAPACITY);

    public static void main(String[] args) throws InterruptedException {

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

//        while (!executor.isTerminated()) {
//        }

        if(!executor.awaitTermination(6, TimeUnit.SECONDS)) {
            System.exit(0);
        }

        System.out.println("Available: " + garage.getAvailable()); //Expected Available: 0
    }
}

class ParkingGarage { // bai do xe
    private int capacity; // so luong xe toi da
    private int available; // so luong cho do con trong

    public ParkingGarage(int capacity) {
        this.capacity = capacity;
        this.available = capacity;
    }

    public synchronized void enter() throws InterruptedException {
        while (available == 0){
            System.out.println("Parking garage is full, please wait!");
            wait(2000); // Wait for 2 seconds, then check again
        }

        available--; // xe vao duoc
    }

    public synchronized void leave(){
        if(available == capacity){
            throw new RuntimeException("No car in parking garage!");
        }else { // xe ra duoc
            available++;
            notifyAll();
        }
    }

    public int getAvailable() {
        return available;
    }

    public int getCapacity() {
        return capacity;
    }
}