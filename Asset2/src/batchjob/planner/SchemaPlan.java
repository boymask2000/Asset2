package batchjob.planner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SchemaPlan {
	private Map<String, Set<Long>> map = new HashMap<String, Set<Long>>();

	public void set(long assetId, String data) {
		Set<Long> s = map.get(data);
		if (s == null) {
			map.put(data, new HashSet<Long>());
			s = map.get(data);
		}
		s.add(assetId);
	}

	public boolean hasDataAsset(String data, long assetId) {
		Set<Long> s = map.get(data);
		if (s == null)
			return false;

		if (s.contains(assetId))
			return true;
		return false;
	}

}
