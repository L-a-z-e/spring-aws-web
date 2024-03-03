package com.laze.springawsweb.config.auth;

import com.laze.springawsweb.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 활성화
public class SecurityConfig {

    private final CustomOauth2UserService customOauth2UserService;

    @Bean
    @ConditionalOnProperty(name = "spring.h2.console.enabled",havingValue = "true")
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }


    /**
     * disalbe - csrf(), headers().frameoptions, - h2-console 사용 위해 disabled
     * authorizeRequests URL별 권한 관리 설정 옵션 시작점
     * antMatchers - 권한 관리 대상 지정, URL, HTTP 메서드별 관리 가능, permitAll() 전체 권한, /api/v1/** -> USER 권한만 허용
     * anyRequest - 설정된 값 이외 URL, authenticated() -> 나머지 URL도 모두 인증된 사용자들에게만 허용(로그인)
     * logout().logoutSuccessUrl("/") 로그아웃 성공시 주소
     * oauth2Login - oauth2 로그인 기능 설정 진입점
     * userInfoEndpoint - oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
     * userService - 소셜 로그인 성공시 후속조치를 진행할 UserService 인터페이스의 구현체 등록, 리소스 서버(소셜서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
     */
    @Bean
    // Spring Security 6.2 에서는 WebSecurityConfigurerAdapter 사용 불가 deprecated  -> SecurityFilterChain 등으로 변경 공식 Doc 참고해서 수정 필요
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/","/login","/index", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher("/h2-console/**")
                        ).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/api/v1/**").hasAuthority("ROLE_" + Role.USER.name())
                .anyRequest().authenticated()
        )
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
                ))
                .oauth2Login(oauth2Login -> oauth2Login.userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(customOauth2UserService)))
                .build();

        return http.build();
    }


}
