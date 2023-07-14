package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	public void 회원가입() throws Exception{
		//Given	// 사전 조건 설정
		Member member = new Member();
		member.setName("hello");
		
		//When	// 테스트하려는 동작 또는 이벤트를 나타내는 부분
		Long saveId = memberService.join(member);
		
		//Then	// 테스트 결과와 기대되는 결과를 검증하는 부분
		Member findMember = memberRepository.findById(saveId).get();
		assertEquals(member.getName(), findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() throws Exception{
		//Given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spting");
		
		//When
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class,
				() -> memberService.join(member2));//예외가 발생해야함
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}

}
