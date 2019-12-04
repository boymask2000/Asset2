package beans;

public class MoreInfoAsset {
	private long id;
	private long assetId;
	private String building;
	private String room;
	private String tenant;
	private String equipdescr;
	private String manifacturer;
	private String techspec;
	private String qta;
	private String frequency;
	private String time;

	public long getId() {
		return id;
	}

	public long getAssetId() {
		return assetId;
	}

	public String getBuilding() {
		return building;
	}

	public String getRoom() {
		return room;
	}

	public String getTenant() {
		return tenant;
	}

	public String getEquipdescr() {
		return equipdescr;
	}

	public String getManifacturer() {
		return manifacturer;
	}

	public String getTechspec() {
		return techspec;
	}

	public String getQta() {
		return qta;
	}

	public String getFrequency() {
		return frequency;
	}

	public String getTime() {
		return time;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public void setEquipdescr(String equipdescr) {
		this.equipdescr = equipdescr;
	}

	public void setManifacturer(String manifacturer) {
		this.manifacturer = manifacturer;
	}

	public void setTechspec(String techspec) {
		this.techspec = techspec;
	}

	public void setQta(String qta) {
		this.qta = qta;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
