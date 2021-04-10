package com.example.demo.helper;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.example.demo.service.HelloService;

import org.springframework.stereotype.Component;

import  lombok.Getter;

@Component
@Getter
public class ReferenceHolder {
    //@SofaReference(interfaceType = HelloService.class, binding = @SofaReferenceBinding(bindingType = "bolt", directUrl = "127.0.0.1:12201"))
    @SofaReference(interfaceType =HelloService.class, binding =@SofaReferenceBinding(bindingType ="rest",directUrl ="127.0.0.1:8341"))
    private HelloService helloService;
    
}
