package dao;

import java.util.List;
import java.util.Map;

public interface IBaseDAO<T> {
	// tạo bản ghi mới từ object
	public int create(T object);

	// tạo bản ghi mới từ map
	public int create(Map<String, Object> data);

	// lấy tất cả bản ghi
	public List<T> all();
	
	public List<T> get();

	// tìm bản ghi theo id
	public T find(Object id);
	
	// xóa bản ghi thep id
	public int delete(Object id);
	
	// cập nhât bản ghi theo id kèm theo dữ liệu cập nhật
	public int update(Object id, Map<String, Object> data);
	
	// cập nhật bản ghi thẹo object (objetc đã có id trong đó)
	public int update(T object);
}
