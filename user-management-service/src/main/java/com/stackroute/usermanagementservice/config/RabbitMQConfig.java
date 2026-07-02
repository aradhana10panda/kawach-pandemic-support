package com.stackroute.usermanagementservice.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.doctor}") private String doctorQueue;
    @Value("${rabbitmq.queue.volunteer}") private String volunteerQueue;
    @Value("${rabbitmq.exchange}") private String exchange;
    @Value("${rabbitmq.routing.key.doctor}") private String doctorRoutingKey;
    @Value("${rabbitmq.routing.key.volunteer}") private String volunteerRoutingKey;
    @Bean public Queue doctorQueue() { return new Queue(doctorQueue, true); }
    @Bean public Queue volunteerQueue() { return new Queue(volunteerQueue, true); }
    @Bean public TopicExchange exchange() { return new TopicExchange(exchange); }
    @Bean public Binding doctorBinding(Queue doctorQueue, TopicExchange exchange) { return BindingBuilder.bind(doctorQueue).to(exchange).with(doctorRoutingKey); }
    @Bean public Binding volunteerBinding(Queue volunteerQueue, TopicExchange exchange) { return BindingBuilder.bind(volunteerQueue).to(exchange).with(volunteerRoutingKey); }
    @Bean public MessageConverter jsonMessageConverter() { return new Jackson2JsonMessageConverter(); }
    @Bean public AmqpTemplate amqpTemplate(ConnectionFactory cf) { RabbitTemplate t = new RabbitTemplate(cf); t.setMessageConverter(jsonMessageConverter()); return t; }
}
