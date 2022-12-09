package com.gildedrose;

public class BackStagePasses extends Item {

    public BackStagePasses(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    public void updateQuality() {
        if (quality < 50) {
            this.quality = getQuality() + 1;
        }

        if (sellByDate < 11) {
            if (quality < 50) {
                this.quality = getQuality() + 1;
            }

        }
        if (sellByDate < 6) {
            if (quality < 50) {
                this.quality = getQuality() + 1;
            }
        }

        this.sellByDate = sellByDate - 1;

        if (sellByDate < 0) {
            this.quality = 0;
        }
    }

}
