package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping(value = "/members/new")
	public String createForm() {
		return "members/createMemberForm"; // "members/createMemberForm"라는 뷰(View)를 반환
	}

	@PostMapping(value = "/members/new")
	public String create(MemberForm form) {

		Member member = new Member();
		member.setName(form.getName());

		memberService.join(member); // 회원 가입 처리

		return "redirect:/"; // 루트 경로("/")로 리다이렉션하여 회원 가입 후 홈 화면으로 이동
	}

	@GetMapping(value = "/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers(); // 회원 목록 조회
		model.addAttribute("members", members); // 조회한 회원 목록을 모델에 추가
		return "members/memberList"; // "members/memberList"라는 뷰(View)를 반환
	}

}
