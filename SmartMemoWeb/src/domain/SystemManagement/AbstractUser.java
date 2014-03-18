package domain.SystemManagement;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String tel;
	private String pwd;
	private String nickname;
	private Integer age;
	private String sex;
	private String image;
	private String hob;
	private String prof;
	private Set realtimes = new HashSet(0);
	private Set timings = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(Integer userId) {
		this.userId = userId;
	}

	/** full constructor */
	public AbstractUser(Integer userId, String tel, String pwd,
			String nickname, Integer age, String sex, String image, String hob,
			String prof, Set realtimes, Set timings) {
		this.userId = userId;
		this.tel = tel;
		this.pwd = pwd;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.image = image;
		this.hob = hob;
		this.prof = prof;
		this.realtimes = realtimes;
		this.timings = timings;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHob() {
		return this.hob;
	}

	public void setHob(String hob) {
		this.hob = hob;
	}

	public String getProf() {
		return this.prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public Set getRealtimes() {
		return this.realtimes;
	}

	public void setRealtimes(Set realtimes) {
		this.realtimes = realtimes;
	}

	public Set getTimings() {
		return this.timings;
	}

	public void setTimings(Set timings) {
		this.timings = timings;
	}

}