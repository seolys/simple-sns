package seolnavy.sns.fixture;

import seolnavy.sns.model.entity.UserEntity;

public class UserEntityFixture {

	public static UserEntity get(final String userName, final String password) {
		final UserEntity result = new UserEntity();
		result.setId(1);
		result.setUserName(userName);
		result.setPassword(password);
		return result;
	}

}
