package com.taa.bookservice.service;

import com.taa.bookservice.rabbitConfig.BookEvent;
import com.taa.bookservice.rabbitConfig.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final RabbitTemplate rabbitTemplate;
    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void createBook(){
        BookEvent bookEvent = new BookEvent();
        bookEvent.setBookId(999L);
        bookEvent.setPrice(170F);
        bookEvent.setUserId(44L);

        logger.info(bookEvent.toString());
        rabbitTemplate.convertAndSend(RabbitConfig.BOOK_EXCHANGE_NAME,RabbitConfig.BOOK_ROUTING_KEY,bookEvent);

    }
}
