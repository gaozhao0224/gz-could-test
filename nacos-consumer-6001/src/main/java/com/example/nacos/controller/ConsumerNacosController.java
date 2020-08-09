package com.example.nacos.controller;

import com.feign.production.service.ProductionNacosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ConsumerNacosController {

    @Autowired
    private ProductionNacosService productionNacosService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productionUrl}")
    private String serverURL;
    /*
    * 使用feign
    * */
    @RequestMapping("consumerNacos/{id}")
    public Object consumerNacos(@PathVariable("id") String id){
        log.info("consumerNacos方法，下面开始调用服务production");
        Object o = productionNacosService.getProductionVal(id);
        return o;
    }
    /*
    * 使用 restTemplate 做负载均衡
    * */
    @RequestMapping("consumerNacosRest/{id}")
    public Object consumerNacosRest(@PathVariable("id") String id){
        log.info("consumerNacosRest方法，下面使用restTemplate开始调用服务production\t\t\t"+serverURL);
        return restTemplate.getForObject(serverURL+"/nacosProduction/LBProduction/"+id,String.class);
    }

}