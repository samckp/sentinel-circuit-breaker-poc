package com.sentinel.circuitbreaker;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class BookService {

    private final RestTemplate restTemplate = new RestTemplate();

    /*public BookService(RestTemplate rest) {
        this.restTemplate = rest;
    }*/

    @SentinelResource(value = "readingList", fallback = "reliable")
    public String readingList() {
        URI uri = URI.create("http://localhost:8080/books");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }
}
