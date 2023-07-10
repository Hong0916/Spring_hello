package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hello.hellospring.domain.Member;

/*
 * 동시성 문제는 고려하지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository{	//MemberRepository에 선언된 메서드를 구현
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	@Override
	public Optional<Member> findById(Long id){
		return Optional.ofNullable(store.get(id));
	}
	
	@Override
	public List<Member> findAll(){
		return new ArrayList<>(store.values());
	}
	
	@Override
	public Optional<Member> findByName(String name){
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}
	
	public void clearStore() {
		store.clear();
	}
	

}
