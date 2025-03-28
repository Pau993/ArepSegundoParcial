package com.example.parcial.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ProxyController {

    private final List<String> backendUrls;
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    public ProxyController(@Value("${BACKEND_URLS:}") String backendUrlsEnv) {
        if (backendUrlsEnv.isEmpty()) {
            throw new IllegalStateException("No se configuraron las URLs de los backends en la variable de entorno BACKEND_URLS.");
        }
        this.backendUrls = List.of(backendUrlsEnv.split(","));
    }

    @GetMapping("/proxy/lucas")
    public Object forwardRequest(@RequestParam int n) {
        String backendUrl = getNextBackendUrl();
        RestTemplate restTemplate = new RestTemplate();
        String url = backendUrl + "/hello?value=" + n;
        return restTemplate.getForObject(url, Object.class);
    }

    private String getNextBackendUrl() {
        int index = currentIndex.getAndUpdate(i -> (i + 1) % backendUrls.size());
        return backendUrls.get(index);
    }
}