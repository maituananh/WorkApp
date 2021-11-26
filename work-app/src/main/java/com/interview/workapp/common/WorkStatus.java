package com.interview.workapp.common;

public enum WorkStatus {
    PLANNING("PLANNING"),
    DOING("DOING"),
    COMPLETE("COMPLETE");

    private final String text;

    /**
     * @param text
     */
    WorkStatus(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
