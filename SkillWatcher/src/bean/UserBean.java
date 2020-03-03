package bean;

public class UserBean {

	private int id;
	private String name;
	private String mailAddress;
	private String password;
	private int authority;

	public UserBean() {
		this.id = 0;
		this.name = "";
		this.mailAddress = "";
		this.password = "";
		this.authority = 0;
	}

	public UserBean(int id,String name, String mailAddress, String password, int authority) {
		this.id = id;
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
		this.authority = authority;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}



}
