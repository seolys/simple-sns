package seolnavy.sns.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import seolnavy.sns.exception.SnsApplicationException;
import seolnavy.sns.fixture.UserEntityFixture;
import seolnavy.sns.repository.UserEntityRepository;

@SpringBootTest
class UserServiceTest {

	@Autowired private UserService userService;

	@MockBean
	private UserEntityRepository userEntityRepository;

	@Test
	void 회원가입이_정상적으로_동작하는_경우() {
		final String userName = "userName";
		final String password = "password";

		// mocking
		when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());
		when(userEntityRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));

		assertDoesNotThrow(() -> userService.join(userName, password));
	}

	@Test
	void 회원가입시_uaserName으로_회원가입한_유저가_이미_있는경우() {
		final String userName = "userName";
		final String password = "password";

		// mocking
		when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));
		when(userEntityRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(userName, password)));

		assertThatThrownBy(() -> userService.join(userName, password))
				.isInstanceOf(SnsApplicationException.class);
	}

	@Test
	void 로그인이_정상적으로_동작하는_경우() {
		final String userName = "userName";
		final String password = "password";

		final var fixture = UserEntityFixture.get(userName, password);

		// mocking
		when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));

		assertDoesNotThrow(() -> userService.login(userName, password));
	}

	@Test
	void 로그인시_userName으로_회원가입한_유저가_없는_경우() {
		final String userName = "userName";
		final String password = "password";

		final var fixture = UserEntityFixture.get(userName, password);

		// mocking
		when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> userService.login(userName, password))
				.isInstanceOf(SnsApplicationException.class);
	}

	@Test
	void 로그인시_password가_틀린_경우() {
		final String userName = "userName";
		final String password = "password";
		final String wrongPassword = "password";

		final var fixture = UserEntityFixture.get(userName, password);

		// mocking
		when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> userService.login(userName, wrongPassword))
				.isInstanceOf(SnsApplicationException.class);
	}

}