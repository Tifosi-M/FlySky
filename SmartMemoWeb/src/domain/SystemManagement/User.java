package domain.SystemManagement;

import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer userId) {
		super(userId);
	}

	/** full constructor */
	public User(Integer userId, String tel, String pwd, String nickname,
			Integer age, String sex, String image, String hob, String prof,
			Set realtimes, Set timings) {
		super(userId, tel, pwd, nickname, age, sex, image, hob, prof,
				realtimes, timings);
	}

}
