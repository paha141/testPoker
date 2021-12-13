package com.test.paha;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final List<Card> cardList;
    private final Map<Byte, Integer> countOfSameValue = new TreeMap<>();
    private int handValueId;
    private byte strongCardId;
    private byte kickerId;
    private final HandValues handValue;

    public PokerHand(String cards) {
        String[] cardArray = cards.split(" ");
        if (cardArray.length != 5) throw new IllegalArgumentException("The number of cards must be 5");

        this.cardList = Arrays.stream(cardArray)
                .map(x -> new Card(x.charAt(0), x.charAt(1)))
                .sorted(Comparator.comparingInt(Card::getValue))
                .collect(Collectors.toList());

        strongCardId = cardList.get(cardList.size() - 1).getValue();
        initCountOfSameValue();
        initHandValue();
        handValue = HandValues.values()[handValueId];
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
                    if (checkFourOfKind()) {
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
                    if (checkThreeOfKind()) {
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

    private void initCountOfSameValue() {
        for (Card card : cardList) {
            byte cardId = card.getValue();
            if (countOfSameValue.containsKey(cardId)) {
                countOfSameValue.put(cardId, countOfSameValue.get(cardId) + 1);
            } else countOfSameValue.put(cardId, 1);
        }
    }

    private boolean checkRoyalFlush() {
        return cardList.get(0).getValue() == 10 && checkStraightFlush();
    }

    private boolean checkStraightFlush() {
        return checkFlush() && checkStraight();
    }

    private boolean checkFourOfKind() {
        return checkSameCardCount(4);
    }

    private boolean checkFullHouse() {
        return checkPair() && checkThreeOfKind();
    }

    private boolean checkFlush() {
        char suit = cardList.get(0).getSuit();
        for (int i = 1; i < cardList.size(); i++) {
            if (cardList.get(i).getSuit() != suit) return false;
        }
        return true;
    }

    private boolean checkStraight() {
        byte value = cardList.get(0).getValue();
        for (int i = 1; i < cardList.size(); i++) {
            if (cardList.get(i).getValue() != i + value) return false;
        }
        return true;
    }

    private boolean checkThreeOfKind() {
        return checkSameCardCount(3);
    }

    private boolean checkTwoPairs() {
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

    private boolean checkPair() {
        return checkSameCardCount(2);
    }

    private boolean checkSameCardCount(int count) {
        if (countOfSameValue.containsValue(count)) {
            for (byte b : countOfSameValue.keySet()) {
                if (countOfSameValue.get(b) == count) {
                    strongCardId = b;
                    TreeSet<Byte> set = new TreeSet<>(countOfSameValue.keySet());
                    set.remove(b);
                    kickerId = set.last();
                    break;
                }
            }
            return true;
        } else return false;
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

    public HandValues getHandValue() {
        return handValue;
    }


    @Override
    public int compareTo(PokerHand pokerHand) {
        int result = handValueId - pokerHand.getHandValueId() ;
        result = result != 0 ? result : pokerHand.getStrongCardId() - strongCardId;
        result = result != 0 ? result : pokerHand.getKickerId() - kickerId;
        if (result == 0 && handValueId >= 4 && handValueId != 5) {
            List<Byte> otherCards = getSingleCardList(pokerHand.getCountOfSameValue());
            result = compareSingleCards(otherCards);
        }
        return result;
    }

    private int compareSingleCards(List<Byte> otherCards) {
        List<Byte> cards = getSingleCardList(countOfSameValue);

        int result = 0;
        for (int i = 0; i < cards.size(); i++) {
            result = otherCards.get(i) - cards.get(i);
            if (result != 0) break;
        }

        return result;
    }


    @Override
    public String toString() {
        return "PokerHand{" +
                "cardList=" + cardList +
                ", countOfSameValue=" + countOfSameValue +
                ", handValueId=" + handValueId +
                ", strongCardId=" + strongCardId +
                ", kickerId=" + kickerId +
                ", handValue=" + handValue.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return cardList.equals(pokerHand.getCardList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardList);
    }

     private static List<Byte> getSingleCardList(Map<Byte, Integer> countOfSameValue) {
        return countOfSameValue.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
