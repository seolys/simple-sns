package seolnavy.sns.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seolnavy.sns.exception.SnsApplicationException;
import seolnavy.sns.model.User;
import seolnavy.sns.model.entity.UserEntity;
import seolnavy.sns.repository.UserEntityRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserEntityRepository userEntityRepository;

	public User join(final String userName, final String password) {
		// 회원가입하려는 userName으로 회원가입된 user가 있는지 체크
		final var userEntity = userEntityRepository.findByUserName(userName);

		// 회원가입 진행 = user를 등록
		userEntityRepository.save(new UserEntity());

		return new User();
	}

	public String login(final String userName, final String password) {
		// 회원가입 여부 체크
		final var userEntity = userEntityRepository.findByUserName(userName).orElseThrow(() -> new SnsApplicationException());

		// 비밀번호 체크
		if (userEntity.getPassword().equals(password)) {
			throw new SnsApplicationException();
		}

		// 토큰 생성

		return "";
	}

}
