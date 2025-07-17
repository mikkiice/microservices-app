package com.example.post_service.Feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bad-words-service")
public interface BadWordsClient {

    @GetMapping("/bad-words/exist")
    boolean exist(@RequestParam("word") String word);
}
