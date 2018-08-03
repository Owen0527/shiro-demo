package com.jc.springboot.shirodemo.shiro;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * SpringBeanTest
 *
 * @author OwenZhu
 * @date 18/7/31
 */
@Component
@Slf4j
public class SpringBeanTest implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, DisposableBean {

    public SpringBeanTest() {
        log.info("========SpringBeanTest New====={}");
    }

    @PostConstruct
    public void init() {
        log.info("========SpringBeanTest init====={}");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("========setBeanFactory====={}");
    }

    @Override
    public void setBeanName(String s) {
        log.info("========setBeanName====={}");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("========setApplicationContext=====");
    }

    @Override
    public void destroy() throws Exception {
        log.info("=======destroy=========");
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        log.info("=======preDestroy=========");
    }
}
