/*
 * @ (#) OrderDetail.java       1.0     Mar 17, 2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * @description: 
 * @author: Khanh Nguyen
 * @date:   Mar 17, 2024
 * @version:    1.0
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderDetail {
	private Product product;
	private int quantity;
	private double price;
	private double discount;
}

