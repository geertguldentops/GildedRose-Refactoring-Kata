package com.gildedrose;

public class ConjuredItem extends Item {

    public ConjuredItem(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    protected int changeRate() {
        return -2;
    }

}
