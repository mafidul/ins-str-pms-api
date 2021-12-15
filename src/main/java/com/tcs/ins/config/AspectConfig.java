package com.tcs.ins.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AspectConfig {

	@Pointcut("execution( * com.tcs.ins.pms.service.PMSService.*(..))")
	public void monitor() {
	}

	@Bean
	PerformanceMonitorInterceptor performanceMonitorInterceptor() {
		return new PerformanceMonitorInterceptor(false);
	}

	@Bean
	Advisor performanceMonitorAdvisor(PerformanceMonitorInterceptor performanceMonitorInterceptor) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("com.tcs.ins.config.AspectConfig.monitor()");
		return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor);
	}
}