/*
 * @ (#) SumRangeTask2.java       04/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;
import java.util.stream.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   04/01/2024
 */
public class SumRangeTask2 implements Callable<Integer> {

    private int from;
    private int to;

    public SumRangeTask2(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Integer call() throws Exception {
//        Thread.sleep(1000);
        return IntStream.range(from, to).sum();
    }
}
