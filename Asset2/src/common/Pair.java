package common;

public class Pair {
	private String name;
	private Object val;
	private String type;

	public Pair(String name, Object v, String type) {
		this.name = name;
		this.val = v;
		this.type=type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
