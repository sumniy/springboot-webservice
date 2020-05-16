package com.sumniy.book.springboot.web.dto;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        //given 임시로 변수에 값을 할당하고
        String name = "test";
        int amount = 1000;

        //when 이 변수들을 입력으로 HelloResponseDto 생성자를 호출
        // -> 이 생성자는 롬복 어노테이션으로 구현되었기 때문에 롬복이 제대로 기능하는지 테스트하기 위함
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then assertThat이 롬복으로 구현된 dto의 get이 제대로 동작하는지 확인하고,
        // isEqualTo를 통해 내가 기대하는 값으로 제대로 생성되었는지를 확인한다
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }


}
