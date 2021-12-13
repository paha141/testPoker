package com.test.paha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PokerHand> hands = new ArrayList<>();
        hands.add(new PokerHand("JS JH 5C 3S 3D"));
        hands.add(new PokerHand("QC JC JC AC TC"));
        hands.add(new PokerHand("JD JC AC 3S 3D"));
        hands.add(new PokerHand("JD 9C AC 3S 5D"));

        Collections.sort(hands);
        hands.forEach(System.out::println);
    }
}
