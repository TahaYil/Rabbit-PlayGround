package com.taa.bookservice.rabbitConfig;

public class BookEvent {

    private Long  bookId;
    private Long userId;
    private Float price;

    public BookEvent() {
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public Float getPrice() {
        return price;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
