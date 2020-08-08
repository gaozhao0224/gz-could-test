package com.example.hystrix.test.service;

import org.springframework.stereotype.Service;

public interface ProviderHystrixService {
    String provider_ok(String id);

    String provider_timeOut(String id, Integer timeOut);

    String errorTimeAwait(Integer num);
}
