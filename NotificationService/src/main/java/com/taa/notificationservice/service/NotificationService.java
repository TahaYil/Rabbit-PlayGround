package com.taa.notificationservice.service;

import com.taa.notificationservice.config.PaymentEvent;
import com.taa.notificationservice.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitConfig.PAYMENT_QUEUE_NAME)
    public void paymentNotification(PaymentEvent event) throws InterruptedException {
        Thread.sleep(2000);
        logger.info("Payment Notification Received , bookId: " + event.getBookId()+"success :"+event.isSuccess());
    }
}
