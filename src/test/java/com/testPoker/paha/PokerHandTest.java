package com.testPoker.paha;

import org.junit.Assert;
import org.junit.Test;

public class PokerHandTest extends Assert {

   @Test
    public void testCheckRoyalFlush() {
       PokerHand pokerHand = new PokerHand("TC JC QC KC AC");
       assertEquals(HandValues.ROYAL_FLUSH, pokerHand.getHandValue());
    }

    @Test
    public void testCheckStraightFlush() {
        PokerHand pokerHand = new PokerHand("TC JC 8C 7C 9C");
        assertEquals(HandValues.STRAIGHT_FlUSH, pokerHand.getHandValue());
    }

    @Test
    public void testCheckFourOfKind() {
        PokerHand pokerHand = new PokerHand("2D 2H 2C KC 2S");
        assertEquals(HandValues.FOUR_OF_KIND, pokerHand.getHandValue());
    }

    @Test
    public void testCheckFullHouse() {
        PokerHand pokerHand = new PokerHand("TD JH JS TC JC");
        assertEquals(HandValues.FULL_HOUSE, pokerHand.getHandValue());
    }

    @Test
    public void testCheckFlush() {
        PokerHand pokerHand = new PokerHand("9S JS 7S KS 2S");
        assertEquals(HandValues.FLUSH, pokerHand.getHandValue());
    }

    @Test
    public void testCheckStraight() {
        PokerHand pokerHand = new PokerHand("9D JC QH KS TS");
        assertEquals(HandValues.STRAIGHT, pokerHand.getHandValue());
    }

    @Test
    public void testCheckThreeOfKind() {
        PokerHand pokerHand = new PokerHand("KD KH KC 2C TS");
        assertEquals(HandValues.THREE_OF_KIND, pokerHand.getHandValue());
    }

    @Test
    public void testCheckTwoPairs() {
        PokerHand pokerHand = new PokerHand("KD KH TC 2C TS");
        assertEquals(HandValues.TWO_PAIRS, pokerHand.getHandValue());
    }

    @Test
    public void testCheckPair() {
        PokerHand pokerHand = new PokerHand("KD KH 5C 2C TS");
        assertEquals(HandValues.PAIR, pokerHand.getHandValue());
    }

    @Test
    public void testCheckHighCard() {
        PokerHand pokerHand = new PokerHand("KD 7H TC 2C 9S");
        assertEquals(HandValues.HIGH_CARD, pokerHand.getHandValue());
    }

    @Test
    public void testCompareTo() {
        PokerHand first = new PokerHand("2D 3H 7C KC 3S");
        PokerHand second = new PokerHand("TC JC QC KC AC");
        Assert.assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void testCompareToRoyalFlush() {
        PokerHand first = new PokerHand("TC JC QC KC AC");
        PokerHand second = new PokerHand("TH JH QH KH AH");
        Assert.assertEquals(0, first.compareTo(second));
    }

    @Test
    public void testCompareToStraightFlush() {
        PokerHand first = new PokerHand("TC JC QC KC 9C");
        PokerHand second = new PokerHand("5H 6H 7H 8H 9H");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToFourOfKind() {
        PokerHand first = new PokerHand("5C 5H 5D 4C 5S");
        PokerHand second = new PokerHand("8C 8H 8D 5C 8S");
        Assert.assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void testCompareToFourOfKindSecondCard() {
        PokerHand first = new PokerHand("5C 5H 5D AC 5S");
        PokerHand second = new PokerHand("5C 5H 5D 4C 5S");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToFullHouseThree() {
        PokerHand first = new PokerHand("5C 5H 5D 4C 4S");
        PokerHand second = new PokerHand("8C 8H 8D 4C 4S");
        Assert.assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void testCompareToFullHousePair() {
        PokerHand first = new PokerHand("5C 5H 5D AC AS");
        PokerHand second = new PokerHand("5C 5H 5D 4C 4S");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToFlushHighCard() {
        PokerHand first = new PokerHand("TC JC 4C KC 9C");
        PokerHand second = new PokerHand("5H 6H 2H 8H 9H");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToFlushWithDifferentSuit() {
        PokerHand first = new PokerHand("5C 6C 7C 8C 9C");
        PokerHand second = new PokerHand("5H 6H 7H 8H 9H");
        Assert.assertEquals(0, first.compareTo(second));
    }

    @Test
    public void testCompareToFlushFourthCard() {
        PokerHand first = new PokerHand("TC JC 4C KC 9C");
        PokerHand second = new PokerHand("TH JH 4H KH 8H");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToFlushLowestCard() {
        PokerHand first = new PokerHand("TC JC 4C KC 9C");
        PokerHand second = new PokerHand("TH JH 2H KH 9H");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToStraight() {
        PokerHand first = new PokerHand("TC JH QC KD 9C");
        PokerHand second = new PokerHand("5H 6C 7D 8H 9H");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToThreeOfKindSecondCard() {
        PokerHand first = new PokerHand("TC JH JC JD AC");
        PokerHand second = new PokerHand("TC JH JC JD KC");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToThreeOfKindLowestCard() {
        PokerHand first = new PokerHand("TC JH JC JD AC");
        PokerHand second = new PokerHand("QC JH JC JD AC");
        Assert.assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void testCompareToThreeOfKind() {
        PokerHand first = new PokerHand("TC JH JC JD AC");
        PokerHand second = new PokerHand("TC 5H 5C 5D AC");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToTwoPairsLowestCard() {
        PokerHand first = new PokerHand("TC JH JC TD AC");
        PokerHand second = new PokerHand("TC JH JC TD 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToTwoPairsHighPair() {
        PokerHand first = new PokerHand("TC 9H 9C TD 5C");
        PokerHand second = new PokerHand("8C JH JC 8D 5C");
        Assert.assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void testCompareToTwoPairsSecondPair() {
        PokerHand first = new PokerHand("TC 9H 9C TD 5C");
        PokerHand second = new PokerHand("TC 8H 8C TD 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToPair() {
        PokerHand first = new PokerHand("TC 9H 9C AD 5C");
        PokerHand second = new PokerHand("TC JH JC 2D 5C");
        Assert.assertTrue(first.compareTo(second) > 0);
    }

    @Test
    public void testCompareToPairHighCard() {
        PokerHand first = new PokerHand("TC 9H 9C AD 5C");
        PokerHand second = new PokerHand("TC 9H 9C 2D 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToPairSecondCard() {
        PokerHand first = new PokerHand("TC 9H 9C AD 5C");
        PokerHand second = new PokerHand("8C 9H 9C AD 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToPairLowestCard() {
        PokerHand first = new PokerHand("TC 9H 9C AD 5C");
        PokerHand second = new PokerHand("TC 9H 9C AD 4C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToHighCard() {
        PokerHand first = new PokerHand("TC 9H 2C AD 5C");
        PokerHand second = new PokerHand("TC 9H 2C 8D 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToHighCardSecondCard() {
        PokerHand first = new PokerHand("KC 9H 2C AD 5C");
        PokerHand second = new PokerHand("TC 9H 2C AD 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToHighCardThirdCard() {
        PokerHand first = new PokerHand("TC 9H 2C AD 5C");
        PokerHand second = new PokerHand("TC 8H 2C AD 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToHighCardFourthCard() {
        PokerHand first = new PokerHand("TC 9H 2C AD 5C");
        PokerHand second = new PokerHand("TC 9H 2C AD 3C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }

    @Test
    public void testCompareToHighCardLowestCard() {
        PokerHand first = new PokerHand("TC 9H 3C AD 5C");
        PokerHand second = new PokerHand("TC 9H 2C AD 5C");
        Assert.assertTrue(first.compareTo(second) < 0);
    }
}