package model;

public class NewMemberVo {
	// Fields
	private String userid;
	private String passwd;
	private String passwd2;
	private String changedpwd;
	private String username;
	private String natid;
	private String email;
	private String tel;
	private String job;
	private String gender;
	private String intro;
	private String indate;
	
	// Constructor
	public NewMemberVo(String userid, String passwd, String passwd2, String username, String natid, String email,
			String tel, String job, String gender, String intro, String indate) {
		this.userid = userid;
		this.passwd = passwd;
		this.passwd = passwd2;
		this.username = username;
		this.natid = natid;
		this.email = email;
		this.tel = tel;
		this.job = job;
		this.gender = gender;
		this.intro = intro;
		this.indate = indate;
	}

	public NewMemberVo(String userid) {
		this.userid = userid;
	}
	public NewMemberVo(String userid, String passwd) {
		this.userid = userid;
		this.passwd = passwd;
	}

	public NewMemberVo(String userid, String passwd, String username, String job, String gender, String intro,
			String indate) {
		this.userid = userid;
		this.passwd = passwd;
		this.username = username;
		this.job = job;
		this.gender = gender;
		this.intro = intro;
		this.indate = indate;
	}

	public NewMemberVo(String username, String natid, String email) {
		this.username = username;
		this.natid = natid;
		this.email = email;
	}

	public NewMemberVo(String userid, String username, String natid, String tel) {
		this.userid = userid;
		this.username = username;
		this.natid = natid;
		this.tel = tel;
	}

	public NewMemberVo(String userid, String username, String natid, String tel, String passwd) {
		this.userid = userid;
		this.username = username;
		this.natid = natid;
		this.tel = tel;
		this.passwd = passwd; 
	}

	// Getter / Setter
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
	public String getChangedpwd() {
		return changedpwd;
	}
	public void setChangedpwd(String changedpwd) {
		this.changedpwd = changedpwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNatid() {
		return natid;
	}
	public void setNatid(String natid) {
		this.natid = natid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	} 
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.intro = indate;
	}
	
	// toString
	@Override
	public String toString() {
		return "NewMemberVo [userid=" + userid + ", passwd=" + passwd + ", username=" + username + ", natid=" + natid
				+ ", email=" + email + ", tel=" + tel + ", job=" + job + ", gender=" + gender + ", intro=" + intro
				+ ",indate=" + indate + "]";
	}
	
	
	
	
}
