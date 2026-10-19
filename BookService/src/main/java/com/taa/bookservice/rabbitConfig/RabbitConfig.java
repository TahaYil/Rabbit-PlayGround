package com.taa.bookservice.rabbitConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String BOOK_QUEUE_NAME="book.queue";
    public static final String BOOK_EXCHANGE_NAME="book.exchange";
    public static final String BOOK_ROUTING_KEY="book.created";

    private final String PAYMENT_QUEUE_NAME="payment.queue";
    private final String PAYMENT_EXCHANGE_NAME="payment.exchange";
    private final String PAYMENT_ROUTING_KEY="payment.done";

    @Bean
    public Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE_NAME, true);
    }
    @Bean
    public Exchange paymentExchange() {
        return new TopicExchange(PAYMENT_EXCHANGE_NAME);
    }
    @Bean
    public Binding paymentBinding(Queue paymentQueue, Exchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue()).to(paymentExchange()).with(PAYMENT_ROUTING_KEY).noargs();
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
