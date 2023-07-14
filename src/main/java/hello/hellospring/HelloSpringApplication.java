package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 애플리케이션의 주요 구성 요소를 설정하는데 사용
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		// SpringBoot 애플리케이션을 실행하는 메서드
	}

}

// Spring Boot 애플리케이션을 실행하기 위한 기본 설정
// @SpringBootApplication 어노테이션을 통해 스프링 구성 요소를 자동으로 구성하고, 
// 'main'메서드에서 'SpringApplication.run()'을 호출하여 애플리케이션을 실행