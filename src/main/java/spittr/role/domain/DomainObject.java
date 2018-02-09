package spittr.role.domain;

public abstract class DomainObject {

	private long id;

	public DomainObject() {

	}

	public DomainObject(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
