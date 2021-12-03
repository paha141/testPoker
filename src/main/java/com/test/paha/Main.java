package com.test.paha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PokerHand> hands = new ArrayList<>();
        hands.add(new PokerHand("JS JH 2C 2S 2D"));
        hands.add(new PokerHand("QC KC JH 9C TC"));
        Collections.sort(hands);
        hands.forEach(System.out::println);

    }
}
