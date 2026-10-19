package com.taa.paymentservice.service;



import com.taa.paymentservice.rabbitConfig.BookEvent;
import com.taa.paymentservice.rabbitConfig.PaymentEvent;
import com.taa.paymentservice.rabbitConfig.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PaymentService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.BOOK_QUEUE_NAME)
    public void doPayment(BookEvent bookEvent) throws InterruptedException {
        Thread.sleep(3000);
        logger.info(bookEvent.toString());

        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setBookId(bookEvent.getBookId());
        paymentEvent.setSuccess(true);
        logger.info("ÇALIŞIYOR");
        logger.info(paymentEvent.getBookId().toString());


        rabbitTemplate.convertAndSend(RabbitConfig.PAYMENT_EXCHANGE_NAME,RabbitConfig.PAYMENT_ROUTING_KEY,paymentEvent);

    }

}
