package AAD.UD02E02.MateoAyarra1;

public class Vulnerabilidad {
	private String name, seq, type, status, desc;

	public String getName() {
		return name;
	}

	public String getSeq() {
		return seq;
	}

	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return name+"|"+seq+"|"+type+"|"+status+"|"+desc;
	}
	
	
	
}
