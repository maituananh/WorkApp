package com.interview.workapp.common;

/**
 * The enum Work status.
 */
public enum WorkStatus {
    /**
     * Planning work status.
     */
    PLANNING("PLANNING"),
    /**
     * Doing work status.
     */
    DOING("DOING"),
    /**
     * Complete work status.
     */
    COMPLETE("COMPLETE");

    private final String text;

    /**
     * @param text
     */
    WorkStatus(final String text) {
        this.text = text;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }
}
