package mapper;

import bean.Product;
import database.Result;

public class ProductMapper implements Mapper<Product> {
	@Override
	public Product toBean(Result result) {
		Product product = new Product();

		product.setId((int) result.get("id"));
		product.setName((String) result.get("name"));
		product.setPrice((int) result.get("price"));
		product.setAmount((int) result.get("amount"));

		return product;
	}
}
