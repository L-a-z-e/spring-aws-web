package com.laze.springawsweb.web.controller;


import com.laze.springawsweb.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controller -> JSON을 반환하는 Controller로 만들어줌
// @ResponseBody를 각 메서드마다 선언했던 것을 한 번에 사용할 수 있게 해줌
@RestController
public class HelloController {

    // HTTP Method -> Get 요청을 받을 수 있는 API
    // 이전은 @RequestMapping(method = RequestMethod.GET)
    // /helo 로 요청이 오면 "hello" 를 반환하는 기능을 하게 됨
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
    // @RequestParam 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    // name 이란 이름으로 넘긴 파라미를 메서드 파라미터 String name에 저장
}
