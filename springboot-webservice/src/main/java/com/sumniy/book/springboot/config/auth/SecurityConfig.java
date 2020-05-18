package com.sumniy.book.springboot.config.auth;

import com.sumniy.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }

    // authorizeRequests : URL별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있음
    // antMatchers : 권한 관리 대상을 지정하는 옵션, URL,HTTP 메소드별로 관리가 가능하다
    // 위에서 "/" 등의 URL은 전체 열람권한(permitAll()) 이고, "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 설정함
    // anyRequest : 설정된 값들 이외 나머지 URL들을 나타냄, authenticated 옵션을 통해 인증된 사용자만 사용하도록(로그인한 사용자)
    // userInfoEndpoint() : OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
    // userService : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록.
    // 리소스 서버(google, naver 등등)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
}
