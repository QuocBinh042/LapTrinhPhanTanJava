package entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
	private String id;
	private LocalDate orderDate;
	private LocalDate requiredDate;
	private transient List<OrderDetail> orderDetails;
	
	public Order(String id, LocalDate orderDate, LocalDate requiredDate, List<OrderDetail> orderDetails) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.orderDetails = orderDetails;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDate getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}
	public Order(String id, LocalDate orderDate, LocalDate requiredDate) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate + "]";
	}
	
	
}
