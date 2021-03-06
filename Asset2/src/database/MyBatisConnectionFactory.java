package database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import database.mapper.AssetAlcaMapper;
import database.mapper.AuditMapper;
import database.mapper.CalendarioMapper;
import database.mapper.ChecklistInterventoMapper;
import database.mapper.ChecklistMapper;
import database.mapper.ChecksMapper;
import database.mapper.ChecksassetMapper;
import database.mapper.DocInterventiMapper;
import database.mapper.FamigliaAssetMapper;
import database.mapper.FotoInterventiMapper;
import database.mapper.FrequenzeAlcaMapper;
import database.mapper.InterventiMapper;
import database.mapper.IspezioniMapper;
import database.mapper.LocationMapper;
import database.mapper.ManualiFamigliaMapper;
import database.mapper.ManualiMapper;
import database.mapper.MoreInfoAssetMapper;
import database.mapper.NormativeMapper;
import database.mapper.ParameterMapper;
import database.mapper.RitardiMapper;
import database.mapper.SafetyMapper;
import database.mapper.TimeLineMapper;
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
	
				sqlSessionFactory.getConfiguration().addMapper(AssetAlcaMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(CalendarioMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ChecklistInterventoMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ChecklistMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ChecksMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ChecksassetMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(DocInterventiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(FamigliaAssetMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(FotoInterventiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(FrequenzeAlcaMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(InterventiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(LocationMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ManualiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(MoreInfoAssetMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(NormativeMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(RitardiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(SafetyMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(UtentiMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(AuditMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(TimeLineMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ManualiFamigliaMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(ParameterMapper.class);
				sqlSessionFactory.getConfiguration().addMapper(IspezioniMapper.class);
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
