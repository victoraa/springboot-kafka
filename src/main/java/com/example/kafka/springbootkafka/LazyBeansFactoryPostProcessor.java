package com.example.kafka.springbootkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class LazyBeansFactoryPostProcessor implements BeanFactoryPostProcessor {
	private static final Logger logger = LoggerFactory.getLogger(LazyBeansFactoryPostProcessor.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		for (String name : beanFactory.getBeanDefinitionNames()) {
			// beanFactory.getBeanDefinition(name).setLazyInit(false);
			logger.info("-->" + name);
		}
//		beanFactory.getBeanDefinition("kafkaConsumerFactory").setLazyInit(false);
//		beanFactory.getBeanDefinition("kafkaListenerContainerFactoryConfigurer").setLazyInit(false);
//		beanFactory.getBean
	}
}
