package com.lullaby.springlibrary.external.sample;

import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "hackerNewsClient", url = "https://hacker-news.firebaseio.com", configuration = HackerNewsClient.Configuration.class)
public interface HackerNewsClient {

    @GetMapping("/v0/topstories.json")
    List<Long> topStories();


    class Configuration {

        @Bean
        public RequestInterceptor requestInterceptor(
                // @Value("${some.key}") String key
        ) {
            return requestTemplate -> {
                requestTemplate.header("Content-Type", "application/json");
            };
        }

        @Bean
        public Retryer retryer() {
            return new Retryer.Default(1000, 1000 * 10, 3);
        }


        @Bean
        public ErrorDecoder errorDecoder() {
            return new ErrorDecoder.Default();
        }
        
    }

}
