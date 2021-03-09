package com.construction.chatrecyclerapp;

public class ChatModelData {
    boolean isSender,isImage;
    String message;

    public ChatModelData(boolean isSender, boolean isImage, String message) {
        this.isSender = isSender;
        this.message = message;
        this.isImage = isImage;
    }

    public boolean isSender() {
        return isSender;
    }

    public boolean isImage() {
        return isImage;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ChatModelData{" +
                "isSender=" + isSender +
                ", isImage=" + isImage +
                ", message='" + message + '\'' +
                '}';
    }
}

