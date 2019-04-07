package database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import beans.Utente;

public interface UtentiMapper {
	final String SELECT_ALL = "SELECT * FROM test1.utenti";
	final String DELETE = "DELETE FROM test1.utenti WHERE username = #{username}";
	final String SELECT_BY_ID = "SELECT * FROM utenti WHERE ID = #{id}";
	
	final String SELECT_ADMINS = "SELECT * FROM utenti where admin='Y'";
	
	final String UPDATE_PASSWORD = "UPDATE test1.utenti SET"
			+ " password = #{password}"
			+ " WHERE username = #{username}";
	
	
	final String UPDATE = "UPDATE test1.utenti SET"
		
	
			+ " email = #{email},"
			+ " tipo = #{tipo},"
			+ " descrizione = #{descrizione}"
			
			+ " WHERE username = #{username}";
	

	final String SEARCH = "SELECT * FROM test1.utenti WHERE "
	+ " username = #{username} AND"
	+ " password = #{password}";
	

	final String INSERT = "INSERT INTO test1.utenti (username ," 
						+ " password ," 
			+" email ," 
			+" descrizione ," 
			+" tipo" 
			+" ) "
			+ ""
			+ "VALUES (#{username}, #{password}, #{email},"+
	" #{descrizione}, #{tipo} )";
	
	@Select(SELECT_ALL)
	@Results(value = {

		@Result(property="username", column="username"),
		@Result(property="password", column="password"),
		@Result(property="email", column="email"),
		@Result(property="tipo", column="tipo"),
		@Result(property="descrizione", column="descrizione"),

	})
	public List<Utente> selectAll();
	
	@Update(UPDATE)
	public void update(Utente contact);
	
	@Update(UPDATE_PASSWORD)
	public void updatePassword(Utente contact);
	
	@Insert(INSERT)
	public void insert(Utente contact);
	
	@Select(SEARCH)
	public Utente search(Utente contact);
	
	@Select(DELETE)
	public Utente delete(Utente contact);
	
	@Select(SELECT_ADMINS)
	@Results(value = {
			@Result(property="id", column="ID"),
			@Result(property="user", column="user"),
			@Result(property="password", column="password"),
			@Result(property="email", column="email"),
			@Result(property="admin", column="admin"),
			@Result(property="note", column="note")
		})
	public List<Utente>  getAdmins();
}
