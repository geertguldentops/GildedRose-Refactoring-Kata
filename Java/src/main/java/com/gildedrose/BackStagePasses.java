package com.gildedrose;

public class BackStagePasses extends Item {

    public BackStagePasses(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        updateQuality(1);

        if (sellByDate < 11) {
            updateQuality(1);
        }
        if (sellByDate < 6) {
            updateQuality(1);
        }

        updateSellByDate();

        if (sellByDate < 0) {
            updateQuality(-getQuality());
        }
    }

}
