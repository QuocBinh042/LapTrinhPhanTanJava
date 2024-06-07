package entity;

public class OrderDetail {
	private Order order;
	private Customer customer;
	private double unitPrice;
	public OrderDetail(Order order, Customer customer, double unitPrice) {
		super();
		this.order = order;
		this.customer = customer;
		this.unitPrice = unitPrice;
	}
	@Override
	public String toString() {
		return "OrderDetail [order=" + order + ", customer=" + customer + ", unitPrice=" + unitPrice + "]";
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}
