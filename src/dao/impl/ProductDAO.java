package dao.impl;

import java.util.List;

import bean.Product;
import dao.IProductDAO;

public class ProductDAO extends BaseDAO<Product> implements IProductDAO {
	public ProductDAO() {
		this.insertField = new String[] { "name", "price", "amount" };
	}

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();

//		Class<?> cls = dao.getClassBean();
//
//		Product p;
//		try {
//			p = (Product) cls.newInstance();
//			p.setId(1);
//			p.setName("abc");
//			p.setPrice(123);
//			p.setAmount(100);
//			System.out.println(p);
//		} catch (InstantiationException | IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		Product p = new Product();
//		p.setName("BÁNH BAO");
//		p.setPrice(8000);
//		p.setAmount(100);
//		dao.create(p);
//		
		List<Product> ls = dao.all();
//		
		for (Product product : ls) {
			System.out.println(product.toString());
		}

	}
}
