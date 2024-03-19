package Json_ObjectModelAPI_Product.entity;

import java.util.List;

public class Product {
	private Integer sku;
	private String name;
	private String type;
	private Double price;
	private String upc;
	private List<Category> category;
	private Double shipping;
	private String description;
	private String url;

	
	public Product() {
	}


	public Product(Integer sku, String name, String type, Double price, String upc, List<Category> category,
			Double shipping, String description, String url) {
		super();
		this.sku = sku;
		this.name = name;
		this.type = type;
		this.price = price;
		this.upc = upc;
		this.category = category;
		this.shipping = shipping;
		this.description = description;
		this.url = url;
	}


	public Integer getSku() {
		return sku;
	}


	public void setSku(Integer sku) {
		this.sku = sku;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getUpc() {
		return upc;
	}


	public void setUpc(String upc) {
		this.upc = upc;
	}


	public List<Category> getCategory() {
		return category;
	}


	public void setCategory(List<Category> category) {
		this.category = category;
	}


	public Double getShipping() {
		return shipping;
	}


	public void setShipping(Double shipping) {
		this.shipping = shipping;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return "Product [sku=" + sku + ", name=" + name + ", type=" + type + ", price=" + price + ", upc=" + upc
				+ ", category=" + category + ", shipping=" + shipping + ", description=" + description + ", url=" + url
				+ "]";
	}

	

}
