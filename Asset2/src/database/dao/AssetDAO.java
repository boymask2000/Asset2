package database.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Asset;
import beans.Calendario;
import database.MyBatisConnectionFactory;
import database.mapper.AssetMapper;
import database.mapper.CalendarioMapper;

public class AssetDAO {


	 
	    /**
	     * Returns the list of all Person instances from the database.
	     * @return the list of all Person instances from the database.
	     */
	  
	    public  List<Asset> selectAll(){
	        List<Asset> list = null;
	
	        return list;
	 
	    }
		public void insert(Asset u) {
			SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();
			try {
				AssetMapper mapper = session.getMapper(AssetMapper.class);
				mapper.insert(u);
				session.commit();
			} finally {
				session.close();
			}
		}
}
