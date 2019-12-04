package managed;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import beans.AssetAlca;
import beans.Check;
import beans.CheckAsset;
import beans.ChecklistIntervento;
import beans.FamigliaAsset;
import beans.Intervento;
import beans.MoreInfoAsset;
import common.InfoCalAsset;
import common.TimeUtil;
import common.TipoSchedulazione;
import database.dao.AssetAlcaDAO;
import database.dao.ChecklistInterventiDAO;
import database.dao.ChecksAssetDAO;
import database.dao.ChecksDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.InterventiDAO;
import database.dao.MoreInfoAssetDAO;

public class GeneraCalAnnuale {
	private Date dataInizio;
	private Date dataFine;
	private String datePattern;

	private List<InfoCalAsset> calAssets = new ArrayList<InfoCalAsset>();

	@PostConstruct
	public void init() {
		Calendar cal = TimeUtil.getCalendar(new Date());

		cal.set(Calendar.DAY_OF_YEAR, 1);
		dataInizio = cal.getTime();

		cal.set(Calendar.DAY_OF_YEAR, 365);
		dataFine = cal.getTime();

	}

	public String build() {

		MoreInfoAssetDAO dao = new MoreInfoAssetDAO();
		List<MoreInfoAsset> lista = dao.getCalendarioAnnuale();

		for (MoreInfoAsset infoAs : lista) {

			InfoCalAsset info = new InfoCalAsset();
			copyMoreInfo(infoAs, info);

			String freq = calculateFrequency(infoAs.getId());

			info.setFrequency(freq);

			calculateMesi(info, infoAs.getId());
			calAssets.add(info);
		}

		return null;
	}

	private void calculateMesi(InfoCalAsset info, long assetId) {
		AssetAlcaDAO daoA = new AssetAlcaDAO();
		AssetAlca asset = daoA.searchById(assetId);

		InterventiDAO dao = new InterventiDAO();
		List<Intervento> ints = dao.getInterventiForAsset(asset.getId());

		ChecklistInterventiDAO cliDao = new ChecklistInterventiDAO();

		Map<String, Set<String>> mesi = new HashMap<String, Set<String>>();

		for (Intervento inter : ints) {

			String mese = TimeUtil.getMese(inter.getData_pianificata());
			Set<String> s = mesi.get(mese);
			if (s == null)
				s = new HashSet<String>();

			List<ChecklistIntervento> clis = cliDao.getChecksForInterventoId(inter.getId());
			for (ChecklistIntervento cli : clis) {
				int codFreq = cli.getCodFrequenza();
				TipoSchedulazione tipo = TipoSchedulazione.getTipoFrequenza(codFreq);
				String sigla = tipo.getSiglaLegenda();
				s.add(sigla);
				mesi.put(mese, s);

			}
		}

		String out = "";
		for (Map.Entry<String, Set<String>> e : mesi.entrySet()) {
			String val = "";
			for (String d : e.getValue())
				val += " " + d;
			String smese = e.getKey();
			switch (smese) {
			case "01":
				info.setGennaio(info.getGennaio() + " " + val);
				break;
			case "02":
				info.setFebbraio(info.getFebbraio() + " " + val);
				break;
			case "03":
				info.setMarzo(info.getMarzo() + " " + val);
				break;
			case "04":
				info.setAprile(info.getAprile() + " " + val);
				break;
			case "05":
				info.setMaggio(info.getMaggio() + " " + val);
				break;
			case "06":
				info.setGiugno(info.getGiugno() + " " + val);
				break;
			case "07":
				info.setLuglio(info.getLuglio() + " " + val);
				break;
			case "08":
				info.setAgosto(info.getAgosto() + " " + val);
				break;
			case "09":
				info.setSettembre(info.getSettembre() + " " + val);
				break;
			case "10":
				info.setOttobre(info.getOttobre() + " " + val);
				break;
			case "11":
				info.setNovembre(info.getNovembre() + " " + val);
				break;
			case "12":
				info.setDicembre(info.getDicembre() + " " + val);
				break;

			default:
				break;
			}
		}
	}

	private String calculateFrequency(long assetId) {
		AssetAlcaDAO dao = new AssetAlcaDAO();
		AssetAlca asset = dao.searchById(assetId);

		FamigliaAssetDAO fad = new FamigliaAssetDAO();
		FamigliaAsset f = fad.searchByName(asset.getFacSystem());
		long famId = f.getId();

		ChecksDAO cDao = new ChecksDAO();
		List<Check> checks = cDao.getChecksByFamilyId(famId);

		Set<String> freqs = new HashSet<String>();
		for (Check ck : checks) {
			freqs.add(ck.getFrequenza());
		}

		ChecksAssetDAO caDao = new ChecksAssetDAO();
		List<CheckAsset> checkAs = caDao.getChecksByAssetId(assetId);

		for (CheckAsset ck : checkAs) {
			freqs.add(ck.getFrequenza());
		}

		String out = "";
		for (String s : freqs) {
			if (out.length() > 0)
				out += ", ";
			out += s;
		}
		return out;
	}

	private void copyMoreInfo(MoreInfoAsset i, InfoCalAsset info) {
		info.setMainTenant(i.getTenant());
		info.setRoom(i.getRoom());
		info.setBuilding(i.getBuilding());
		info.setEquipmentDescription(i.getEquipdescr());
		info.setFrequency(i.getFrequency());
		info.setManifacturer(i.getManifacturer());
		info.setQta(i.getQta());
		info.setTechnicalSpecification(i.getTechspec());
		info.setTime(i.getTime());

	}

	public List<InfoCalAsset> getCalAssets() {
		return calAssets;
	}

	public void setCalAssets(List<InfoCalAsset> calAssets) {
		this.calAssets = calAssets;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getDatePattern() {
		return TimeUtil.getDatePattern();
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

}
