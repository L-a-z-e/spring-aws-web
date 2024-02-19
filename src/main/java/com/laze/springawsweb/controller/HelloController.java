package com.laze.springawsweb.controller;


import org.springframework.web.bind.annotation.GetMapping;
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
}
