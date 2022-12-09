package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    protected int changeRate() {
        return 1;
    }

}
