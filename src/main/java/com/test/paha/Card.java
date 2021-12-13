package com.test.paha;

public class Card {
    private final byte value;
    private final char suit;

    private final char valueChar;

    public Card(char value, char suit) {
        this.valueChar = value;
        switch (value) {
            case 'T':
                this.value = 10;
                break;
            case 'J':
                this.value = 11;
                break;
            case 'Q':
                this.value = 12;
                break;
            case 'K':
                this.value = 13;
                break;
            case 'A':
                this.value = 14;
                break;
            default:
                if (value >= '2' && value <= '9')
                    this.value = Byte.parseByte(String.valueOf(value));
                else throw new IllegalArgumentException("Wrong parameter: " + value);
        }
        if (suit != 'D' && suit != 'S' && suit != 'C' && suit != 'H') throw new IllegalArgumentException("Wrong parameter: " + suit);
        this.suit = suit;
    }

    public byte getValue() {
        return value;
    }

    public char getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "" + valueChar + suit;
    }
}
