package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Parameter;

public interface ParameterMapper {
	final String TABELLA = "test1.parameters";

	final String SELECT_BY_NAME = "SELECT * FROM " + TABELLA + " WHERE NAME = #{name}";

	final String UPDATE = "UPDATE " + TABELLA + " SET " + //
			"description_it = #{description_it}," + //
			"description_us = #{description_us}," + //
			"value = #{value} " + //
			"WHERE name = #{name}";

	@Select(SELECT_BY_NAME)
	public List<Parameter> selectParameter(String name);

	@Update(UPDATE)
	public void updateParameter(Parameter part);
}
