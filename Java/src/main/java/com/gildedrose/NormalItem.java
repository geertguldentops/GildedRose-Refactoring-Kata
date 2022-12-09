package com.gildedrose;

public class NormalItem extends Item {

    public NormalItem(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        if (quality > 0) {
            this.quality = getQuality() - 1;
        }

        this.sellByDate = sellByDate - 1;

        if (sellByDate < 0) {
            if (quality > 0) {
                this.quality = getQuality() - 1;
            }
        }
    }

}
