package com.test.paha;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final List<Card> cardList;
    private final Map<Byte, Integer> countOfSameValue = new TreeMap<>();
    private int handValueId;
    private byte strongCardId;
    private byte kickerId;

    public PokerHand(String cards) {
        this.cardList = Arrays.stream(cards.split(" ", 5))
                .map(x -> new Card(x.charAt(0), x.charAt(1)))
                .collect(Collectors.toList());
        cardList.sort(Comparator.comparingInt(Card::getValue));
        strongCardId = cardList.get(cardList.size() - 1).getValue();
        initCountOfSameValue();
        initHandValue();
    }

    void initHandValue() {
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

    boolean checkRoyalFlush() {
        return cardList.get(0).getValue() == 10 && checkStraightFlush();
    }

    boolean checkStraightFlush() {
        return checkFlush() && checkStraight();
    }

    boolean checkFourOfAKind() {
        return checkSameCardCount(4);
    }

    boolean checkFullHouse() {
        return checkPair() && checkThreeOfAKind();
    }

    boolean checkFlush() {
        char suit = cardList.get(0).getSuit();
        for (int i = 1; i < cardList.size(); i++) {
            if (cardList.get(i).getSuit() != suit) return false;
        }
        return true;
    }

    boolean checkStraight() {
        byte value = cardList.get(0).getValue();
        for (int i = 1; i < cardList.size(); i++) {
            if (cardList.get(i).getValue() != i + value) return false;
        }
        return true;
    }

    boolean checkThreeOfAKind() {
        return checkSameCardCount(3);
    }

    boolean checkTwoPairs() {
        if (!checkPair()) return false;
        for (byte b : countOfSameValue.keySet()) {
            if (b != strongCardId && countOfSameValue.get(b) == 2) {
                kickerId = (byte) Integer.min(b, strongCardId);
                strongCardId = (byte) Integer.max(b, strongCardId);
                return true;
            }
        }
        return false;
    }

    boolean checkPair() {
        return checkSameCardCount(2);
    }

    void initCountOfSameValue() {
        for (Card card : cardList) {
            byte cardId = card.getValue();
            if (countOfSameValue.containsKey(cardId)) {
                countOfSameValue.put(cardId, countOfSameValue.get(cardId) + 1);
            } else countOfSameValue.put(cardId, 1);
        }
    }

    private boolean checkSameCardCount(int count) {
        if (countOfSameValue.containsValue(count)) {
            for (byte b : countOfSameValue.keySet()) {
                if (countOfSameValue.get(b) == count) {
                    strongCardId = b;
                    TreeMap<Byte, Integer> map = new TreeMap<>(countOfSameValue);
                    map.remove(b);
                    kickerId = map.lastKey();
                }
            }
            return true;
        } else return false;
    }

    public int getKickerIfTwoSamePairs() {
        for (Map.Entry<Byte, Integer> entry : countOfSameValue.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return 0;
    }

    public int getHandValueId() {
        return handValueId;
    }

    public byte getStrongCardId() {
        return strongCardId;
    }

    public byte getKickerId() {
        return kickerId;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public Map<Byte, Integer> getCountOfSameValue() {
        return countOfSameValue;
    }

    @Override
    public String toString() {
        return "PokerHand{" +
                "handValueId=" + handValueId +
                ", " + HandValues.values()[handValueId].getName() +
                ", strongCardId=" + strongCardId +
                ", kickerId=" + kickerId +
                ", cards=" + getCardList().toString() +
                ", countOfSameValue=" + getCountOfSameValue() +
                '}';
    }

    @Override
    public int compareTo(PokerHand pokerHand) {
        int result = handValueId - pokerHand.getHandValueId();
        result = result != 0 ? result : pokerHand.getStrongCardId() - strongCardId;
        result = result != 0 ? result : pokerHand.getKickerId() - kickerId;
        return result != 0 ? result : handValueId != 7 ? result : pokerHand.getKickerIfTwoSamePairs() - getKickerIfTwoSamePairs();
    }

    public enum HandValues {
        ROYAL_FLUSH("Royal flush"),
        STRAIGHT_FlUSH("Straight flush"),
        FOUR_OF_A_KIND("Four of a kind"),
        FULL_HOUSE("Full house"),
        FLUSH("Flush"),
        STRAIGHT("Straight"),
        THREE_OF_A_KIND("Three of a kind"),
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
                default:
                    this.value = Byte.parseByte(String.valueOf(value));
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
