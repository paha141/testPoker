package com.testPoker.paha;

public enum HandValues {
    ROYAL_FLUSH("Royal flush"),
    STRAIGHT_FlUSH("Straight flush"),
    FOUR_OF_KIND("Four of a kind"),
    FULL_HOUSE("Full house"),
    FLUSH("Flush"),
    STRAIGHT("Straight"),
    THREE_OF_KIND("Three of a kind"),
    TWO_PAIRS("Two pairs"),
    PAIR("Pair"),
    HIGH_CARD("HighCard");

    private final String name;

    HandValues(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
