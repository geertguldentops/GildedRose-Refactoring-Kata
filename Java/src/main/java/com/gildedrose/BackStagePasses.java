package com.gildedrose;

public class BackStagePasses extends Item {

    public BackStagePasses(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        increaseQuality(1);

        if (getSellByDate() < 11) {
            increaseQuality(1);

        }
        if (getSellByDate() < 6) {
            increaseQuality(1);
        }

        decreaseSellByDate();

        if (getSellByDate() < 0) {
            dropQualityToZero();
        }
    }

}
