package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import database.mapper.AssetAlcaMapper;
import database.mapper.AssetMapper;
import database.mapper.CalendarioMapper;
import database.mapper.ChecklistMapper;
import database.mapper.ChecksMapper;
import database.mapper.DocInterventiMapper;
import database.mapper.FrequenzeAlcaMapper;
import database.mapper.InterventiMapper;
import database.mapper.ManualiMapper;
import database.mapper.NormativeMapper;
import database.mapper.UtentiMapper;


/**
 * MyBatis Connection Factory, which reads the configuration data from a XML file.

 */
public class MyBatisConnectionFactory {

	private static SqlSessionFactory sqlSessionFactory;

	static {

		try {

			String resource = "SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);

			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	
				sqlSessionFactory.getConfiguration().addMapper(UtentiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(CalendarioMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(AssetMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ManualiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(InterventiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(DocInterventiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(NormativeMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ChecksMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ChecklistMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(AssetAlcaMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(FrequenzeAlcaMapper.class);
			}
			
		}

		catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {

		return sqlSessionFactory;
	}

}
