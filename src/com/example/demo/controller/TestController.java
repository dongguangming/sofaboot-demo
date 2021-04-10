package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;

import com.example.demo.helper.ReferenceHolder;
import com.example.demo.service.HelloService;

/**
 * @author dgm
 * @describe ""
 * @date 2021年4月10日
 */
@RestController
@RequestMapping("/sofa/")
public class TestController {

    //@SofaReference(interfaceType = HelloSyncService.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
    @SofaReference(interfaceType =HelloService.class, binding =@SofaReferenceBinding(bindingType ="rest",directUrl ="127.0.0.1:8341"))
    private HelloService helloService;

    @Autowired
    ReferenceHolder referenceHolder;

    @RequestMapping("/test")
    public String test() {
        System.err.println(helloService);
       return helloService.saySync(" hello sofastack");
    }

    @RequestMapping("/test2")
    public String test2() {
        System.err.println(referenceHolder.getHelloService());
        return referenceHolder.getHelloService().saySync(" hello sofastack 2222");
    }

}