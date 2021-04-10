package com.example.demo.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.example.demo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * 参考官方 https://help.aliyun.com/document_detail/149877.html?spm=a2c4g.11186623.6.571.5257393bvQn85q
 */
@Service
@SofaService(interfaceType = HelloService.class, bindings = {
        @SofaServiceBinding(bindingType = "bolt"),
        @SofaServiceBinding(bindingType ="rest")//使用 rest 协议，接口需要要加 path。
}, enableSofaService = true)
/**
 * @author dgm
 * @describe "扩展开发@sofaservice"
 * @date 2021年4月10日
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String saySync(String string) {
        return string;
    }
}