/*
 * @ (#) SumRangeTask.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.stream.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class SumRangeTask implements Runnable{

    private int from;
    private int to;

    private int result;

    public SumRangeTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getResult() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void run() {
        result = IntStream.range(from, to).sum();
    }
}
