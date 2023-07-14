package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
// 해당 클래스를 스프링 구성 클래스로 지정. 스프링 애플리케이션 컨텍스트가 이 클래스를 인식하여 bean 설정을 읽고 구성
public class SpringConfig {
	private final DataSource dataSource;
	private final EntityManager em;
	private final MemberRepository memberRepository;
	// 클래스 내의 필드들은 주입받을 의존성을 나타냄
	// 이 필드들은 생성자를 통해 주입되며, 생성자의 파라미터로 주입받을 bean들을 전달함

	public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
		// 생성자
		// 이 생성자를 통해 필요한 의존성들을 주입받음
		// dataSource, EntityManager, MemberRepository 인터페이스를 구현한 구체적인 구현체들을 주입받을 수 있음
		this.dataSource = dataSource;
		this.em = em;
		this.memberRepository = memberRepository;
	}

	@Bean
	// 해당 메서드가 스프링 컨테이너에 빈으로 등록되어야 함을 나타냄
	public MemberService memberService() {
	// MemberService  빈을 생성하여 반환하는 메서드
	// MemberService는 memberRepository() 메서드를 통해 주입받은 MemberRepository를 사용
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
	// MemberRepository 빈을 생성하여 반환하는 메서드
// return new MemoryMemberRepository();
// return new JdbcMemberRepository(dataSource);
// return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}

// 스프링 애플리케이션 구성 클래스로서, 필요한 의존성을 주입받아 필요한 빈들을 생성하고 구성함
// memberService() 메서드는 MemberService 빈을 생성하고, memberRepository() 메서드는 MemberRepository 빈을 생성함
// 이를 통해 스프링 컨테이너에서 이 빈들을 사용할 수 있게 됨