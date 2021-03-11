//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()//设置不需要认证的页面
//                .antMatchers("/**").hasRole("USER")//设置需要认证的页面
//                .anyRequest()//其他请求
//                .authenticated()//需要认证
//                .and()
//                .formLogin()//登录页
////                .loginPage("/login")
////                .loginProcessingUrl("/login")
//                .successForwardUrl("/Course/show")//登录成功之后访问的页面
//                .failureForwardUrl("/")//登录失败访问之后的页面
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .permitAll()
//                .and()
//                .csrf().disable();
//    }
//
//
//
//
//    /*@Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }*/
//}
