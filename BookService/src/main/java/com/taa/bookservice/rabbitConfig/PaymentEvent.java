package com.taa.bookservice.rabbitConfig;

public class PaymentEvent {
    private Long bookId;
    private boolean success;

    public PaymentEvent() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
