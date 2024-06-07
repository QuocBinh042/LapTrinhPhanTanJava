package dao;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Node;

import entity.Supplier;
import util.AppUtils;

public class SupplierDao {
	private Driver driver;
	
	public SupplierDao() {
		driver = AppUtils.initDriver();
	}
	
	public Supplier findOne(String id) {
		String query = "MATCH (n:Supplier) WHERE n.supplierID = $id RETURN n LIMIT 25";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try(Session session = driver.session()) {
			return session.executeRead(tx -> {
				Result result = tx.run(query, map);
				if(!result.hasNext())
					return null;				
				Node node = result.next().get("n").asNode();				
				return AppUtils.convert(node, Supplier.class);
			});
		}
	}
	
	public void addSupplier(Supplier supplier) {
		String query = "create(n:Supplier {supplierID : $supplierID})";
		Map<String, Object> map = AppUtils.convertProperties(supplier);
		
		try(Session s = driver.session()) {
			s.executeWrite(tx -> {
				return tx.run(query, map).consume();
			});
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new SupplierDao().findOne("1"));
		Supplier s = new Supplier("100000");
		new SupplierDao().addSupplier(s);
	}
}
