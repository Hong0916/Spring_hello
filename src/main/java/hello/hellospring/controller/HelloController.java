package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello"; // "hello"라는 뷰를 반환
	}

	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template"; // "hello-template"라는 뷰를 반환
	}

	@GetMapping("hello-string")
	@ResponseBody // mapping 쪽에서 요청이 오면 문자열을 http 응답 본문에 사용
	public String helloString(@RequestParam("name") String name) { // ReqyestParam은 주소 뒤에 들어갈 문자열을 입력
		return "hello " + name; // 문자열을 HTTP 응답 본문에 직접 변환
	}

	@GetMapping("hello-api")
	@ResponseBody // HTTP 응답 본문에 객체를 사용
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello; // 객체를 HTTP 응답 본문에 직접 반환(객체가 JSON으로 변환되어 전송됨)
	}

	// 정적 중첩 클래스로 Hello 객체 정의
	static class Hello {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
