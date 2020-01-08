package util;

import java.util.List;

import beans.AssetAlca;
import beans.FamigliaAsset;
import beans.MoreInfoAsset;
import database.dao.AssetAlcaDAO;
import database.dao.FamigliaAssetDAO;
import database.dao.MoreInfoAssetDAO;

public class TestAssetLoader {
	private static String famiglie[] = { "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12",
			"F13", "F14", "F15" };
	private static String tenant[] = { "TEN1", "TEN2", "TEN3", "TEN4", "TEN5", "TEN6", "TEN7", "TEN8", "TEN9",
			"TEN10" };
	private static String building[] = { "BLD1", "BLD2", "BLD3", "BLD4" };

	private final static int MAXNUM = 10000;

	public static void main(String s[]) {

		for (int i = 0; i < MAXNUM; i++) {
			String rmp = "KEY_" + i;
			String famiglia = getRandom(famiglie);
			String tnt = getRandom(tenant);
			String bld = getRandom(building);

			System.out.println(rmp + " " + famiglia + " " + tnt + " " + bld);

			AssetAlca asset = new AssetAlca();
			asset.setRpieIdIndividual(rmp);
			asset.setFacSystem(famiglia);
			AssetAlcaDAO asDao = new AssetAlcaDAO();
			asDao.insert(asset);
			
			FamigliaAssetDAO famDao = new FamigliaAssetDAO();
			FamigliaAsset f = new FamigliaAsset();
			f.setFamiglia(asset.getFacSystem());
			famDao.insert(f);
			
			
			asset = asDao.searchByRPIE(asset);
			if (asset != null) {
				long id = asset.getId();
				MoreInfoAsset info = new MoreInfoAsset();
				info.setAssetId(id);
				info.setBuilding(bld);
				info.setTenant(tnt);
				info.setRoom(getRoom());
				MoreInfoAssetDAO iDao = new MoreInfoAssetDAO();
				iDao.insert(info);
			}

		}

	}

	private static String getRoom() {
		int index = (int) (Math.random() * 50);
		return "Room_" + index;
	}

	private static String getRandom(String[] d) {
		int num = d.length;
		int index = (int) (Math.random() * num);
		return d[index];
	}

}
