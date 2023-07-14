package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	@Autowired	// 생성자에 @Autowired를 사용하면 객체 생성 시전에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다.
				// 생성자가 1새만 있으면 @Autowired는 생략할 수 있다.
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/*
	 * 회원가입
	 */
	public Long join(Member member) {
		
		long start = System.currentTimeMillis();
		
		try {
			validateDuplicateMember(member);	// 중복 회원 검증
			memberRepository.save(member);
			return member.getId();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("join " + timeMs + "ms");
		}

//		validateDuplicateMember(member); // 중복 회원 검증
//		memberRepository.save(member);
//		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	/*
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		long start = System.currentTimeMillis();
		
		try {
			return memberRepository.findAll();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("findMembers " + timeMs + "ms");
		}
//		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
		
}
