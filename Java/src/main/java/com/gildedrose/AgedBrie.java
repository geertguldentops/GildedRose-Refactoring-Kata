package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        updateQuality(1);
        updateSellByDate();

        if (sellByDate < 0) {
            updateQuality(1);
        }
    }

}
