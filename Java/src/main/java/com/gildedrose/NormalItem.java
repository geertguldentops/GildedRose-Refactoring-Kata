package com.gildedrose;

public class NormalItem extends Item {

    public NormalItem(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        decreaseQuality(1);

        decreaseSellByDate();

        if (getSellByDate() < 0) {
            decreaseQuality(1);
        }
    }

}
