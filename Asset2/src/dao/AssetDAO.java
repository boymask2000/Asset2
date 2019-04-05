package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import beans.Asset;

public class AssetDAO {
	   private SqlSessionFactory sqlSessionFactory = null;
	   
	    public AssetDAO(SqlSessionFactory sqlSessionFactory){
	        this.sqlSessionFactory = sqlSessionFactory;
	    }
	 
	    /**
	     * Returns the list of all Person instances from the database.
	     * @return the list of all Person instances from the database.
	     */
	  
	    public  List<Asset> selectAll(){
	        List<Asset> list = null;
	        SqlSession session = sqlSessionFactory.openSession();
	 
	        try {
	            list = session.selectList("mapper.asset.selectAll");
	        } finally {
	            session.close();
	        }
	    
	        return list;
	 
	    }
}
