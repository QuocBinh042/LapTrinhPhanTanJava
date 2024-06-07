/*
 * @ (#) Order.java       1.0     Mar 17, 2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import java.time.LocalDate;
import java.util.List;

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
public class Order {
	private String id;
	private LocalDate orderDate;
	private LocalDate requiredDate;
	
	@ToString.Exclude
	private List<OrderDetail> orderDetails;

	public Order(String id, LocalDate orderDate, LocalDate requiredDate) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
	}
	
	
}

