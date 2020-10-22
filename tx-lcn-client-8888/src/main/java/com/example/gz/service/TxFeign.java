package com.example.gz.service;

import com.common.entity.TxLcA;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("tx-lcn-client-8889")
public interface TxFeign {

    @PostMapping("/gz/tx-lc-b/add")
    boolean add(@RequestBody TxLcA txLcA);
}
