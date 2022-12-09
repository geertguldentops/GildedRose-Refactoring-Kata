package com.gildedrose;

public class NormalItem extends Item {

    public NormalItem(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        updateQuality(-1);
        updateSellByDate();

        if (sellByDate < 0) {
            updateQuality(-1);
        }
    }

}
