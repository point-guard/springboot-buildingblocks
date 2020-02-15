package com.stack.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // @RequestMapping(method = RequestMethod.GET, path = "/helloworld")
    @GetMapping("/helloworld")
    public String helloWorld() {
        return "Hello World 1";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWolrdBean() {
        return new UserDetails("Max", "G", "here");
    }
}