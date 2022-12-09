package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        if (quality < 50) {
            this.quality = getQuality() + 1;
        }

        this.sellByDate = sellByDate - 1;

        if (sellByDate < 0) {
            if (quality < 50) {
                this.quality = getQuality() + 1;
            }
        }
    }

}
