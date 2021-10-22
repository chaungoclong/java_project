package mapper;

import database.Result;

public interface Mapper<T> {
	// tạo một bean từ một hàng trong dữ liệu lấy về
	public T toBean(Result result);
}
