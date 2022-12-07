package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        increaseQuality(1);

        decreaseSellByDate();

        if (getSellByDate() < 0) {
            increaseQuality(1);
        }
    }

}
