package com.gildedrose;

public class Sulfuras extends Item {

    public Sulfuras(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    protected void updateSellByDate() {
        // Sulfuras never changes!
    }

    @Override
    protected void updateQuality(int changeRate) {
        // Sulfuras never changes!
    }

    @Override
    protected int changeRate() {
        return 0;
    }

}
