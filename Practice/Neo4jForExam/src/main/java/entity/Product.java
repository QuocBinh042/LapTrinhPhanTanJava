package entity;

import com.google.gson.annotations.SerializedName;

public class Product {
	@SerializedName("productID")
	private String productID;
	@SerializedName("productName")
	private String productName;
	private double unitPrice;
	private transient Supplier supplier;
	private int unitsOnOrder;
	public Product(String productID, String productName, double unitPrice, Supplier supplier, int unitOnOrder) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.supplier = supplier;
		this.unitsOnOrder = unitOnOrder;
	}
	public int getUnitOnOrder() {
		return unitsOnOrder;
	}
	public void setUnitOnOrder(int unitOnOrder) {
		this.unitsOnOrder = unitOnOrder;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Product(String productID, String productName, double unitPrice, Supplier supplier) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.supplier = supplier;
	}
	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", unitPrice=" + unitPrice + "]";
	}
	public Product(String productID, String productName, double unitPrice) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
	}
	public Product() {
		super();
	}
	public Product(String productID, String productName, double unitPrice, int unitOnOrder) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.unitsOnOrder = unitOnOrder;
	}
	
	
}
