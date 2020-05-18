package com.sumniy.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    // @Target : 이 어노테이션이 생성될 수 있는 위치를 지정. PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다. 이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있다.
    // @interface : 이 파일을 어노테이션 클래스로 지정한다. LoginUser라는 이름을 가진 어노테이션이 생성되었다.
    // @Retention : 해당 어노테이션이 언제까지 유지할지 알려준다.
    //  - Source : 어노테이션을 주석처럼 사용하고 싶을 때 사용. 소스코드에만 존재하며 컴파일 시 사라진다.
    //  - Class : 컴파일한 .class 파일에서는 유지되지만 런타임 시 클래스를 메모리로 읽어오면 사라진다.
    //  - Runtime : 클래스를 메모리에 읽어왔을 때까지 유지한다. 코드에서 이 정보를 바탕으로 특정 로직 실행 가능.
}
