package com.gildedrose;

public class BackStagePasses extends Item {

    public BackStagePasses(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    protected void updateQualityBeforeSellByDate() {
        updateQuality(1);

        if (getSellByDate() < 11) {
            updateQuality(1);
        }
        if (getSellByDate() < 6) {
            updateQuality(1);
        }
    }

    @Override
    protected void updateQualityAfterSellByDate() {
        if (getSellByDate() < 0) {
            updateQuality(-getQuality());
        }
    }

    @Override
    protected int changeRate() {
        return 1;
    }

}
