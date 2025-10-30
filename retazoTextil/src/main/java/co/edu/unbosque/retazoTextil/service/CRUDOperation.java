package co.edu.unbosque.retazoTextil.service;

import java.util.List;

public interface CRUDOperation<T> {

	public int create(T data);

	public List<T> getAll();

	public int deleteById(Integer username);

	public int updateById(Integer username, T newData);

	public long count();

	public boolean exist(Integer username);
}
