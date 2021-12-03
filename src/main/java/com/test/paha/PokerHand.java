package com.test.paha;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private int handValueId;
    private byte strongCardId;
    private final List<Card> cards;
    private final Map<Byte, Integer> countOfSameValue = new TreeMap<>();

    public PokerHand(String cards) {
        this.cards = Arrays.stream(cards.split(" ", 5))
                .map(x -> new Card(x.charAt(0), x.charAt(1)))
                .collect(Collectors.toList());
        this.cards.sort(Comparator.comparingInt(Card::getValue));
        strongCardId = this.cards.get(this.cards.size()-1).getValue();
        initCountOfSameValue();
        initHandValue();
    }

    private void initHandValue() {
        for (int i = 0; i < HandValues.values().length; i++) {
            switch (i) {
                case 0:
                    if (checkRoyalFlush()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 1:
                    if (checkStraightFlush()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 2:
                    if (checkFourOfAKind()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 3:
                    if (checkFullHouse()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 4:
                    if (checkFlush()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 5:
                    if (checkStraight()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 6:
                    if (checkThreeOfAKind()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 7:
                    if (checkTwoPairs()) {
                        handValueId = i;
                        return;
                    }
                    break;
                case 8:
                    if (checkPair()) {
                        handValueId = i;
                        return;
                    }
                    break;
                default:
                    handValueId = 9;
            }
        }
    }

    private boolean checkRoyalFlush() {
        return cards.get(0).getValue() == 10 && checkStraightFlush();
    }

    private boolean checkStraightFlush() {
        return checkFlush() && checkStraight();
    }

    private boolean checkFourOfAKind() {
        return checkSameCardCount(4);
    }

    private boolean checkFullHouse() {
        return checkPair() && checkThreeOfAKind();
    }

    private boolean checkFlush() {
        char suit = cards.get(0).getSuit();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() != suit) return false;
        }
        return true;
    }

    private boolean checkStraight() {
        byte value = cards.get(0).getValue();
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getValue() != i + value) return false;
        }
        return true;
    }

    private boolean checkThreeOfAKind() {
        return checkSameCardCount(3);
    }

    private boolean checkTwoPairs() {
        if (!checkPair()) return false;
        for (byte b : countOfSameValue.keySet()) {
            if (b != strongCardId && countOfSameValue.get(b) == 2) {
                strongCardId = (byte) Integer.max(b, strongCardId);
                return true;
            }
        }
        return false;
    }

    private boolean checkPair() {
        return checkSameCardCount(2);
    }

    private void initCountOfSameValue() {
        for (Card card : cards) {
            byte cardId = card.getValue();
            if (countOfSameValue.containsKey(cardId)) {
                countOfSameValue.put(cardId, countOfSameValue.get(cardId) + 1);
            } else countOfSameValue.put(cardId, 1);
        }
    }

    private boolean checkSameCardCount(int count) {
        if (countOfSameValue.containsValue(count)) {
            for (byte b : countOfSameValue.keySet()) {
                if (countOfSameValue.get(b) == count) strongCardId = b;
            }
            return true;
        } else return false;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Map<Byte, Integer> getCountOfSameValue() {
        return countOfSameValue;
    }

    public int getStrongCardId() {
        return strongCardId;
    }

    public int getHandValueId() {
        return handValueId;
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "handValueId=" + handValueId +
                ", " + HandValues.values()[handValueId].getName() +
                ", strongCardId=" + strongCardId +
                ", cards=" + cards.toString() +
                ", countOfSameValue=" + countOfSameValue +
                '}';
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        int result = handValueId - pokerHand.getHandValueId();
        return result != 0 ? result : strongCardId - pokerHand.getStrongCardId();
    }

    public enum HandValues {
        RoyalFlush("Royal flush"),
        StraightFlush("Straight flush"),
        FourOfAKind("Four of a kind"),
        FullHouse("Full house"),
        Flush("Flush"),
        Straight("Straight"),
        ThreeOfAKind("Three of a kind"),
        TwoPairs("Two pairs"),
        Pair("Pair"),
        HighCard("HighCard");

        private final String name;

        HandValues(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Card {
        private final byte value;
        private final char suit;

        public Card(char value, char suit) {
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
                default: this.value = Byte.parseByte(String.valueOf(value));
            }
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
            return "" + value + suit;
        }
    }
}
