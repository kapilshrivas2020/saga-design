package com.apidev;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import jakarta.jms.ConnectionFactory;

@SpringBootApplication
public class SagaDesignApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(SagaDesignApplication.class, args);
	}

	@Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://192.168.1.210:9876");
        return  activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubDomain(true);  // enable for Pub Sub to topic. Not Required for Queue.
        return jmsTemplate;
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		//BrokerService broker = new BrokerService();
		
	}
}
