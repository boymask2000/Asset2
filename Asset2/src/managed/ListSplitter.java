package managed;

import java.util.ArrayList;
import java.util.List;

import common.InfoCalAsset;

public class ListSplitter {

	private List<InfoCalAsset> list;

	public ListSplitter(List<InfoCalAsset> calAssets) {
		this.list = calAssets;
	}

	public void split() {
		if (list == null || list.size() < 2)
			return;

		InfoCalAsset rif = list.get(0);

		String rooms = rif.getRoom();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			InfoCalAsset info = list.get(i);
			if (areEquals(info, rif) && !compare(info.getRoom(), rif.getRoom())) {
				rooms += ", "+info.getRoom();
				list.remove(i);size--;i--;
			} else {
				// break
				rif.setRoom(rooms);
				rif = info;
				rooms = rif.getRoom();
			}

		}

	}

	private static boolean areEquals(InfoCalAsset info, InfoCalAsset rif) {
		boolean ok = //
				compare(info.getGennaio(), rif.getGennaio()) && //
						compare(info.getFebbraio(), rif.getFebbraio()) && //
						compare(info.getMarzo(), rif.getMarzo()) && //
						compare(info.getAprile(), rif.getAprile()) && //
						compare(info.getMaggio(), rif.getMaggio()) && //
						compare(info.getGiugno(), rif.getGiugno()) && //
						compare(info.getLuglio(), rif.getLuglio()) && //
						compare(info.getAgosto(), rif.getAgosto()) && //
						compare(info.getSettembre(), rif.getSettembre()) && //
						compare(info.getOttobre(), rif.getOttobre()) && //
						compare(info.getNovembre(), rif.getNovembre()) && //
						compare(info.getDicembre(), rif.getDicembre()) && //
						compare(info.getBuilding(), rif.getBuilding()) && //
						compare(info.getEquipmentDescription(), rif.getEquipmentDescription()) && //
						compare(info.getFrequency(), rif.getFrequency()) && //
						compare(info.getMainTenant(), rif.getMainTenant()) && //
						compare(info.getTechnicalSpecification(), rif.getTechnicalSpecification());
		return ok;
	}

	private static boolean compare(String s1, String s2) {
		if (s1 == null && s2 == null)
			return true;
		if (s1 == null || s2 == null)
			return false;

		return s1.equals(s2);
	}

	public static void main(String s[]) {
		List<InfoCalAsset> calAssets = new ArrayList<>();
		InfoCalAsset as1 = new InfoCalAsset();
		as1.setAgosto("A");
		as1.setRoom("200");
		InfoCalAsset as2 = new InfoCalAsset();
		as2.setAgosto("A");
		as2.setRoom("212");
		InfoCalAsset as3 = new InfoCalAsset();
		as3.setAgosto("B");
		as3.setRoom("11");

		calAssets.add(as1);
		calAssets.add(as2);
		calAssets.add(as3);

		ListSplitter sp = new ListSplitter(calAssets);
		sp.split();

		System.out.println(calAssets.size());

	}
}
