package spittr.role.domain;

public class Spitter extends DomainObject {

	public Spitter() {

	}

	public Spitter(Long id, String userName, String name, String password) {
		super(id);
		this.setUserName(userName);
		this.setName(name);
		this.setPassword(password);
	}

	private String userName;

	private String name;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
