package com.example.geoinformer.utility;

public enum LoggerMessage {

    STATUS_SUCCESS("Success!"),
    STATUS_FAILED("Failed!"),
    REPLY_NULL("null!"),
    REPLY_EMPTY("empty!"),
    REPLY_BAD_INPUT("bad input!"),
    SOURCE_NOMINATIM("Nominatim: "),
    SOURCE_DATABASE("Database: ");

    private String text;

    private LoggerMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
