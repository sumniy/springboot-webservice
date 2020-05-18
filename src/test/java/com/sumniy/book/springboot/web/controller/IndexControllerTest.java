package com.sumniy.book.springboot.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩() {
        //when  "/" 주소로 요청이 갔을 때 반환된 오브젝트(index.mustache)를 body로 저장하고
        String body = this.restTemplate.getForObject("/", String.class);

        //then  body 안에 아까 작성한 내용이 있는지 확인한다.
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
