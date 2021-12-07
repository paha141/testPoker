package com.test.paha;

import org.junit.Assert;
import org.junit.Test;

public class PokerHandTest extends Assert {

    @Test
    public void testInitHandValue() {
        PokerHand pokerHand = new PokerHand("TC JC QC KC AC");
        Assert.assertEquals(PokerHand.HandValues.ROYAL_FLUSH, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("TC JC 8C 7C 9C");
        Assert.assertEquals(PokerHand.HandValues.STRAIGHT_FlUSH, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("2D 2H 2C KC 2S");
        Assert.assertEquals(PokerHand.HandValues.FOUR_OF_A_KIND, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("TD JH JS TC JC");
        Assert.assertEquals(PokerHand.HandValues.FULL_HOUSE, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("9S JS 7S KS 2S");
        Assert.assertEquals(PokerHand.HandValues.FLUSH, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("9D JC QH KS TS");
        Assert.assertEquals(PokerHand.HandValues.STRAIGHT, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("KD KH KC 2C TS");
        Assert.assertEquals(PokerHand.HandValues.THREE_OF_A_KIND, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("KD KH TC 2C TS");
        Assert.assertEquals(PokerHand.HandValues.TWO_PAIRS, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("KD KH 5C 2C TS");
        Assert.assertEquals(PokerHand.HandValues.PAIR, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);

        pokerHand = new PokerHand("KD 7H TC 2C 9S");
        Assert.assertEquals(PokerHand.HandValues.HIGH_CARD, PokerHand.HandValues.values()[pokerHand.getHandValueId()]);
    }

    @Test
    public void testCheckRoyalFlush() {
        PokerHand pokerHand = new PokerHand("TC JC QC KC AC");
        PokerHand pokerHand1 = new PokerHand("2C JC QC KC AC");
        PokerHand pokerHand2 = new PokerHand("TC JC QH KC AH");

        Assert.assertTrue(pokerHand.checkRoyalFlush());
        Assert.assertFalse(pokerHand1.checkRoyalFlush());
        Assert.assertFalse(pokerHand2.checkRoyalFlush());
    }

    @Test
    public void testCheckStraightFlush() {
        PokerHand pokerHand = new PokerHand("TC JC QC KC AC");
        PokerHand pokerHand1 = new PokerHand("9C JC QC KC TC");
        PokerHand pokerHand2 = new PokerHand("TC JC QH KC AH");
        PokerHand pokerHand3 = new PokerHand("9C JC 2C KC TC");

        Assert.assertTrue(pokerHand.checkStraightFlush());
        Assert.assertTrue(pokerHand1.checkStraightFlush());
        Assert.assertFalse(pokerHand2.checkStraightFlush());
        Assert.assertFalse(pokerHand3.checkStraightFlush());
    }

    @Test
    public void testCheckFourOfAKind() {
        PokerHand pokerHand = new PokerHand("TD JH JS KC JC");
        PokerHand pokerHand1 = new PokerHand("2D 2H 2C KC 2S");
        PokerHand pokerHand2 = new PokerHand("TC JC QC KC AC");
        PokerHand pokerHand3 = new PokerHand("KD KH KC 2C KS");

        Assert.assertFalse(pokerHand.checkFourOfAKind());
        Assert.assertFalse(pokerHand2.checkFourOfAKind());

        Assert.assertTrue(pokerHand1.checkFourOfAKind());
        Assert.assertEquals(pokerHand1.getKickerId(), 13);
        Assert.assertEquals(pokerHand1.getStrongCardId(), 2);

        Assert.assertTrue(pokerHand3.checkFourOfAKind());
        Assert.assertEquals(pokerHand3.getKickerId(), 2);
        Assert.assertEquals(pokerHand3.getStrongCardId(), 13);
    }

    @Test
    public void testCheckFullHouse() {
        PokerHand pokerHand = new PokerHand("2D 2H 2C KC 2S");
        PokerHand pokerHand1 = new PokerHand("TD JH JS TC JC");
        PokerHand pokerHand2 = new PokerHand("TC JC QC KC AC");
        PokerHand pokerHand3 = new PokerHand("KD 2H KC 2C 2S");

        Assert.assertFalse(pokerHand.checkFullHouse());
        Assert.assertFalse(pokerHand2.checkFullHouse());

        Assert.assertTrue(pokerHand1.checkFullHouse());
        Assert.assertEquals(pokerHand1.getKickerId(), 10);
        Assert.assertEquals(pokerHand1.getStrongCardId(), 11);

        Assert.assertTrue(pokerHand3.checkFullHouse());
        Assert.assertEquals(pokerHand3.getKickerId(), 13);
        Assert.assertEquals(pokerHand3.getStrongCardId(), 2);
    }

    @Test
    public void testCheckFlush() {
        PokerHand pokerHand = new PokerHand("TC JC QC KC 9C");
        PokerHand pokerHand1 = new PokerHand("9S JS 7S KS 2S");
        PokerHand pokerHand2 = new PokerHand("TC JC QH KC AH");
        PokerHand pokerHand3 = new PokerHand("KD KH KC 2C KS");

        Assert.assertTrue(pokerHand.checkFlush());
        Assert.assertTrue(pokerHand1.checkFlush());
        Assert.assertFalse(pokerHand2.checkFlush());
        Assert.assertFalse(pokerHand3.checkFlush());
    }

    @Test
    public void testCheckStraight() {
        PokerHand pokerHand = new PokerHand("TC JC QC KC 9C");
        PokerHand pokerHand1 = new PokerHand("9D JC QH KS TS");
        PokerHand pokerHand2 = new PokerHand("TC JC 2H KC AH");
        PokerHand pokerHand3 = new PokerHand("KD KH KC 2C KS");

        Assert.assertTrue(pokerHand.checkStraight());
        Assert.assertTrue(pokerHand1.checkStraight());
        Assert.assertFalse(pokerHand2.checkStraight());
        Assert.assertFalse(pokerHand3.checkStraight());
    }

    @Test
    public void testCheckThreeOfAKind() {
        PokerHand pokerHand = new PokerHand("2D 2H 2C KC 2S");
        PokerHand pokerHand1 = new PokerHand("TD AH JS TC TH");
        PokerHand pokerHand2 = new PokerHand("2D 6H 2C KC 6S");
        PokerHand pokerHand3 = new PokerHand("KD KH KC 2C TS");

        Assert.assertFalse(pokerHand.checkThreeOfAKind());
        Assert.assertFalse(pokerHand2.checkThreeOfAKind());

        Assert.assertTrue(pokerHand1.checkThreeOfAKind());
        Assert.assertEquals(pokerHand1.getKickerId(), 14);
        Assert.assertEquals(pokerHand1.getStrongCardId(), 10);

        Assert.assertTrue(pokerHand3.checkThreeOfAKind());
        Assert.assertEquals(pokerHand3.getKickerId(), 10);
        Assert.assertEquals(pokerHand3.getStrongCardId(), 13);
    }

    @Test
    public void testCheckTwoPairs() {
        PokerHand pokerHand = new PokerHand("2D 2H 2C KC 2S");
        PokerHand pokerHand1 = new PokerHand("2D 6H 2C KC 6S");
        PokerHand pokerHand2 = new PokerHand("2D AH 2C KC 6S");
        PokerHand pokerHand3 = new PokerHand("KD KH TC 2C TS");

        Assert.assertFalse(pokerHand.checkTwoPairs());
        Assert.assertFalse(pokerHand2.checkTwoPairs());

        Assert.assertTrue(pokerHand1.checkTwoPairs());
        Assert.assertEquals(pokerHand1.getKickerId(), 2);
        Assert.assertEquals(pokerHand1.getStrongCardId(), 6);

        Assert.assertTrue(pokerHand3.checkTwoPairs());
        Assert.assertEquals(pokerHand3.getKickerId(), 10);
        Assert.assertEquals(pokerHand3.getStrongCardId(), 13);

    }

    @Test
    public void testCheckPair() {
        PokerHand pokerHand = new PokerHand("2D 3H 7C KC AS");
        PokerHand pokerHand1 = new PokerHand("AD AH JS 5C TH");
        PokerHand pokerHand2 = new PokerHand("2D 3H 4C 5C 6S");
        PokerHand pokerHand3 = new PokerHand("KD KH 6C 2C AS");

        Assert.assertFalse(pokerHand.checkPair());
        Assert.assertFalse(pokerHand2.checkPair());

        Assert.assertTrue(pokerHand1.checkPair());
        Assert.assertEquals(pokerHand1.getKickerId(), 11);
        Assert.assertEquals(pokerHand1.getStrongCardId(), 14);

        Assert.assertTrue(pokerHand3.checkPair());
        Assert.assertEquals(pokerHand3.getKickerId(), 14);
        Assert.assertEquals(pokerHand3.getStrongCardId(), 13);
    }

    @Test
    public void testCompareTo() {
        PokerHand first = new PokerHand("2D 3H 7C KC 3S");
        PokerHand second = new PokerHand("TC JC QC KC AC");
        assert first.compareTo(second) < 0;
        assert second.compareTo(first) > 0;

        //Royal flush
        first = new PokerHand("TC JC QC KC AC");
        second = new PokerHand("TH JH QH KH AH");
        assert first.compareTo(second) == 0;

        //Straight flush
        first = new PokerHand("TC JC QC KC 9C");
        second = new PokerHand("5H 6H 7H 8H 9H");
        assert first.compareTo(second) > 0;

        //Four of a kind
        first = new PokerHand("5C 5H 5D 4C 5S");
        second = new PokerHand("8C 8H 8D 5C 8S");
        assert first.compareTo(second) < 0;

        first = new PokerHand("5C 5H 5D AC 5S");
        second = new PokerHand("5C 5H 5D 4C 5S");
        assert first.compareTo(second) > 0;

        //Full house
        first = new PokerHand("5C 5H 5D 4C 4S");
        second = new PokerHand("8C 8H 8D 4C 4S");
        assert first.compareTo(second) < 0;

        first = new PokerHand("5C 5H 5D AC AS");
        second = new PokerHand("5C 5H 5D 4C 4S");
        assert first.compareTo(second) > 0;

        //Flush
        first = new PokerHand("TC JC 4C KC 9C");
        second = new PokerHand("5H 6H 2H 8H 9H");
        assert first.compareTo(second) > 0;

        first = new PokerHand("5C 6C 7C 8C 9C");
        second = new PokerHand("5H 6H 7H 8H 9H");
        assert first.compareTo(second) == 0;

        first = new PokerHand("TC JC 4C KC 9C");
        second = new PokerHand("TH JH 4H KH 8H");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC JC 4C KC 9C");
        second = new PokerHand("TH JH 2H KH 9H");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC JC 4C AC 9C");
        second = new PokerHand("TH JH 4H KH 9H");
        assert first.compareTo(second) > 0;

        //Straight
        first = new PokerHand("TC JH QC KD 9C");
        second = new PokerHand("5H 6C 7D 8H 9H");
        assert first.compareTo(second) > 0;

        //Three of a kind
        first = new PokerHand("TC JH JC JD AC");
        second = new PokerHand("TC JH JC JD KC");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC JH JC JD AC");
        second = new PokerHand("QC JH JC JD AC");
        assert first.compareTo(second) < 0;

        first = new PokerHand("TC JH JC JD AC");
        second = new PokerHand("TC 5H 5C 5D AC");
        assert first.compareTo(second) > 0;

        //Two Pairs
        first = new PokerHand("TC JH JC TD AC");
        second = new PokerHand("TC JH JC TD 5C");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC 9H 9C TD 5C");
        second = new PokerHand("8C JH JC 8D 5C");
        assert first.compareTo(second) < 0;

        first = new PokerHand("TC 9H 9C TD 5C");
        second = new PokerHand("TC 8H 8C TD 5C");
        assert first.compareTo(second) > 0;

        //Pairs
        first = new PokerHand("TC 9H 9C AD 5C");
        second = new PokerHand("TC JH JC 2D 5C");
        assert first.compareTo(second) < 0;

        first = new PokerHand("TC 9H 9C AD 5C");
        second = new PokerHand("TC 9H 9C 2D 5C");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC 9H 9C AD 5C");
        second = new PokerHand("8C 9H 9C AD 5C");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC 9H 9C AD 5C");
        second = new PokerHand("TC 9H 9C AD 4C");
        assert first.compareTo(second) > 0;

        //HighCard
        first = new PokerHand("TC 9H 2C AD 5C");
        second = new PokerHand("TC 9H 2C 8D 5C");
        assert first.compareTo(second) > 0;

        first = new PokerHand("KC 9H 2C AD 5C");
        second = new PokerHand("TC 9H 2C AD 5C");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC 9H 2C AD 5C");
        second = new PokerHand("TC 8H 2C AD 5C");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC 9H 2C AD 5C");
        second = new PokerHand("TC 9H 2C AD 3C");
        assert first.compareTo(second) > 0;

        first = new PokerHand("TC 9H 3C AD 5C");
        second = new PokerHand("TC 9H 2C AD 5C");
        assert first.compareTo(second) > 0;
    }
}