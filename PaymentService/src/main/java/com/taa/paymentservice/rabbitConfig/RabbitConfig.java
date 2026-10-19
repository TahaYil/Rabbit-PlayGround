package com.taa.paymentservice.rabbitConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final  String BOOK_QUEUE_NAME="book.queue";
    private final  String BOOK_EXCHANGE_NAME="book.exchange";
    private final  String BOOK_ROUTING_KEY="book.created";

    public static final String PAYMENT_QUEUE_NAME="payment.queue";
    public static final String PAYMENT_EXCHANGE_NAME="payment.exchange";
    public static final String PAYMENT_ROUTING_KEY="payment.done";

    @Bean
    public Queue bookQueue() {
        return new Queue(BOOK_QUEUE_NAME, true);
    }
    @Bean
    public Exchange bookExchange() {
        return ExchangeBuilder.topicExchange(BOOK_EXCHANGE_NAME).durable(true).build();
    }
    @Bean
    public Binding bookBinding(Queue bookQueue, Exchange bookExchange) {
        return BindingBuilder.bind(bookQueue).to(bookExchange).with(BOOK_ROUTING_KEY).noargs();
    }











    // 1. Converter tanımı: Doğrudan JacksonJsonMessageConverter kullanıyoruz
    @Bean
    public JacksonJsonMessageConverter converter() {
        return new JacksonJsonMessageConverter();
    }

    // 2. RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    // 3. Listener Factory
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            JacksonJsonMessageConverter converter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        return factory;
    }


}
