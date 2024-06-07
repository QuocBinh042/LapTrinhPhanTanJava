/*
_ * @ (#) Customer.java       1.0     Mar 17, 2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package entity;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ToString
public class Customer {
	@SerializedName("customerID")
	private String id;
	private String companyName;
	
	private transient Contact contact;
	 
	private transient Address address;
	
	@ToString.Exclude
	private transient List<Order> orders;
	
}

