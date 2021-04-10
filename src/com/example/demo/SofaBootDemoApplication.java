package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

import com.example.demo.helper.ReferenceHolder;
import com.example.demo.service.HelloService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

//@ImportResource({ "classpath*:rpc-starter-example.xml" })
/**
 * https://help.aliyun.com/document_detail/149866.html
 * https://my.oschina.net/u/4263785/blog/3291496
 */
@SpringBootApplication(scanBasePackages = { "com.example.demo" })
public class SofaBootDemoApplication {
	private static final Logger logger = LoggerFactory
			.getLogger(SofaBootDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(
				SofaBootDemoApplication.class);
		ApplicationContext applicationContext = springApplication.run(args);

		HelloService helloServiceReference = (HelloService) applicationContext
				.getBean("helloServiceImpl");

		System.out.println(helloServiceReference.saySync("sync"));
		/*
		 * HelloService bolt = (HelloService) applicationContext
		 * .getBean("helloReferenceBolt"); HelloService rest = (HelloService)
		 * applicationContext .getBean("helloReferenceRest");
		 * System.out.println(personBolt.saySync("bolt"));
		 * System.out.println(personRest.saySync("rest"));
		 */

		int count = applicationContext.getBeanDefinitionCount();
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		System.out.println("共有" + count + "个bean");
		List<String> beanList = Arrays.asList(beanNames);
		beanList.stream().forEach(
				beanName -> {
				System.out.println(beanName + "========="
							+ applicationContext.getBean(beanName).getClass());
				});

		Environment environment = applicationContext.getEnvironment();
		if (logger.isInfoEnabled()) {
			printMsg("SofaRpc Application (myclient-app) started on  "
					+ environment.getProperty("server.port") + "  port.");
		}

		// 调用 SOFARpc 服务
		ReferenceHolder referenceHolder = applicationContext
				.getBean(ReferenceHolder.class);
		final HelloService helloSyncService = referenceHolder.getHelloService();
		System.err.println(helloSyncService);

		final boolean[] first = { true };
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (first[0]) {
					try {
						String response = helloSyncService.saySync("hello rpc");
						printMsg("Response from myserver-app: " + response);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							TimeUnit.SECONDS.sleep(3);
							first[0] = false;
						} catch (InterruptedException e) {
						}
					}
				}
			}
		}).start();
	}

	private static void printMsg(String msg) {
		System.out.println(msg);
		if (logger.isInfoEnabled()) {
			logger.info(msg);
		}
	}
}
