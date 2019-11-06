package database.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import beans.Parameter;
import database.MyBatisConnectionFactory;
import database.mapper.ParameterMapper;
import restservice.beans.MsgType;

public class ParameterDAO {
	public static final String FREQUENZA_CONTROLLO_FISICO = "FREC_CTR_FIS";
	public static final String DIRECTORY_PDF_INTERVENTI_MENSILI = "DIR_INTER_MENSILI";
	public static final String LAST_ANNO_MESE_PDF_INTERVENTI_MENSILI = "DAT_INTER_MENSILI";

	public Parameter selectParameter(String name) {
		try {
			List<Parameter> list = null;
			try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

				ParameterMapper mapper = session.getMapper(ParameterMapper.class);

				list = mapper.selectParameter(name);
			}
			if (list == null || list.size() == 0)
				return null;

			return list.get(0);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	public void update(Parameter u) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ParameterMapper mapper = session.getMapper(ParameterMapper.class);

			mapper.updateParameter(u);
			session.commit();
		}
	}

	public static void initDatabase() {

		initFREQUENZA_CONTROLLO_FISICO();

		initDIRECTORY_PDF_INTERVENTI_MENSILI();

		initLAST_ANNO_MESE_PDF_INTERVENTI_MENSILI();

	}

	private static void initLAST_ANNO_MESE_PDF_INTERVENTI_MENSILI() {
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(LAST_ANNO_MESE_PDF_INTERVENTI_MENSILI);
		if (fcf == null) {
			Parameter p = new Parameter();
			p.setName(LAST_ANNO_MESE_PDF_INTERVENTI_MENSILI);
			p.setValue("000000");
			dao.insert(p);

		}

	}

	private static void initDIRECTORY_PDF_INTERVENTI_MENSILI() {
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(DIRECTORY_PDF_INTERVENTI_MENSILI);
		if (fcf == null) {
			String lastPos = "c:/temp";

			AuditDAO.generateSystemMessage(
					"ATTENZIONE: Non trovato parametro di sistema " + DIRECTORY_PDF_INTERVENTI_MENSILI
							+ "(DIRECTORY_PDF_INTERVENTI_MENSILI)" + ". Si definisce valore di default",
					MsgType.WARNING);
			Parameter p = new Parameter();
			p.setName(DIRECTORY_PDF_INTERVENTI_MENSILI);

			try {
				Path pf = Files.createTempDirectory("pdf");
				File f = pf.toFile();
				String fullPath = f.getAbsolutePath();
				p.setValue(fullPath);
				dao.insert(p);
				AuditDAO.generateSystemMessage("ATTENZIONE: Impostato valore tmp di sistema [" + fullPath + "]",
						MsgType.WARNING);

			} catch (IOException e) {
				AuditDAO.generateSystemMessage("ATTENZIONE: Impossibile impostare valore di default(" + e.getMessage()
						+ "). Si assume " + lastPos + "]", MsgType.WARNING);
				String fullPath = lastPos;
				p.setValue(fullPath);
				dao.insert(p);
				AuditDAO.generateSystemMessage("ATTENZIONE: Impostato valore [" + fullPath + "]", MsgType.WARNING);
				e.printStackTrace();
			}
			dao.insert(p);
		}

	}

	private static void initFREQUENZA_CONTROLLO_FISICO() {
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(FREQUENZA_CONTROLLO_FISICO);
		if (fcf == null) {
			AuditDAO.generateSystemMessage("ATTENZIONE: Non trovato parametro di sistema " + FREQUENZA_CONTROLLO_FISICO
					+ "(FREQUENZA_CONTROLLO_FISICO)" + ". Si definisce valore di default 10", MsgType.WARNING);
			Parameter p = new Parameter();
			p.setName(FREQUENZA_CONTROLLO_FISICO);
			p.setValue("10");
			dao.insert(p);
		}

	}

	public static void checkValues() {
		checkValuesFCF();
		checkValuesDir();
	}

	private static void checkValuesDir() {
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(DIRECTORY_PDF_INTERVENTI_MENSILI);
		String sVal = fcf.getValue();

		File fDir = new File(sVal);

		if (!fDir.exists()) {

			AuditDAO.generateSystemMessage("ATTENZIONE: Parametro di sistema " + DIRECTORY_PDF_INTERVENTI_MENSILI
					+ "(DIRECTORY_PDF_INTERVENTI_MENSILI) [" + sVal + "] non esiste", MsgType.WARNING);
			return;

		}
		if (!fDir.isDirectory()) {

			AuditDAO.generateSystemMessage("ATTENZIONE: Parametro di sistema " + DIRECTORY_PDF_INTERVENTI_MENSILI
					+ "(DIRECTORY_PDF_INTERVENTI_MENSILI) [" + sVal + "] non è una directory", MsgType.WARNING);
			return;
		}
		if (!fDir.canWrite()) {

			AuditDAO.generateSystemMessage("ATTENZIONE: Parametro di sistema " + DIRECTORY_PDF_INTERVENTI_MENSILI
					+ "(DIRECTORY_PDF_INTERVENTI_MENSILI) [" + sVal + "] non è scrivibile", MsgType.WARNING);
			return;
		}

	}

	private static void checkValuesFCF() {
		ParameterDAO dao = new ParameterDAO();
		Parameter fcf = dao.selectParameter(FREQUENZA_CONTROLLO_FISICO);
		String sVal = fcf.getValue();
		int val = 0;
		try {
			val = Integer.parseInt(sVal);
		} catch (NumberFormatException e) {
			AuditDAO.generateSystemMessage("ATTENZIONE: Parametro di sistema " + FREQUENZA_CONTROLLO_FISICO
					+ "(FREQUENZA_CONTROLLO_FISICO) deve essere numerico", MsgType.WARNING);
			return;

		}
		if (val <= 0) {
			AuditDAO.generateSystemMessage("ATTENZIONE: Parametro di sistema " + FREQUENZA_CONTROLLO_FISICO
					+ "(FREQUENZA_CONTROLLO_FISICO) deve essere un numero positivo", MsgType.WARNING);

		}

	}

	public void insert(Parameter p) {
		try (SqlSession session = MyBatisConnectionFactory.getSqlSessionFactory().openSession();) {

			ParameterMapper mapper = session.getMapper(ParameterMapper.class);

			mapper.insertParameter(p);
			session.commit();

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
