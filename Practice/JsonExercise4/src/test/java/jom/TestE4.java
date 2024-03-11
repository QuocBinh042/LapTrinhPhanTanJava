package jom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import entity.Product;

class TestE4 {

	private JsonHandler jsonHandler;
	private List<Product> products;

	@Before
	void setUp() {
		jsonHandler = new JsonHandler();
		products = jsonHandler.fromJson("data/products.json");
	}

	@Test
	void testFindBySku() {
		// Tìm sản phẩm có SKU là 43900
		Product foundProduct = jsonHandler.findBySku(43900);
		assertNotNull(foundProduct);
		assertEquals("Duracell", foundProduct.getName());

		// Tìm sản phẩm có SKU là 999
		foundProduct = jsonHandler.findBySku(999);
		assertNull(foundProduct);
	}

	@Test
	void testFindByPrice() {
		// Tìm các sản phẩm có giá từ 5.0 đến 6.0
		List<Product> foundProducts = jsonHandler.findByPrice(5.0, 6.0);
		assertNotNull(foundProducts);
		assertEquals(2, foundProducts.size());

		// Tìm các sản phẩm có giá từ 5.0 đến 10.0
		foundProducts = jsonHandler.findByPrice(5.0, 10.0);
		assertNotNull(foundProducts);
		assertEquals(0, foundProducts.size());
	}

	@Test
	public void testFromJson() {
		assertNotNull(products);
		assertEquals(2, products.size());

		Product product = products.get(0);
		assertEquals("Duracell - AAA Batteries (4-Pack)", product.getName());
		assertEquals("HardGood", product.getType());
		assertEquals(5.49, product.getPrice(), 0.001);
		assertEquals("041333424019", product.getUpc());
		assertEquals(5.49, product.getShipping(), 0.001);
		assertEquals("Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack",
				product.getDescription());
	}

}
