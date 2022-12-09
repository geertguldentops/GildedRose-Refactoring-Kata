package com.gildedrose;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Item {

    private final String name;
    private int sellByDate;
    private int quality;

    public static com.gildedrose.Item of(String name, int sellByDate, int quality) {
        return switch (name) {
            case "Aged Brie" -> new AgedBrie(name, sellByDate, quality);
            case "Backstage passes to a TAFKAL80ETC concert" -> new BackStagePasses(name, sellByDate, quality);
            case "Sulfuras, Hand of Ragnaros" -> new Sulfuras(name, sellByDate, quality);
            default -> new NormalItem(name, sellByDate, quality);
        };
    }

    protected Item(String name, int sellByDate, int quality) {
        this.name = name;
        this.sellByDate = sellByDate;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellByDate() {
        return sellByDate;
    }

    public int getQuality() {
        return quality;
    }

    public final void updateQuality() {
        updateQualityBeforeSellByDate();
        updateSellByDate();

        if (sellByDate < 0) {
            updateQualityAfterSellByDate();
        }
    }

    protected void updateQualityBeforeSellByDate() {
        updateQuality(changeRate());
    }

    protected void updateSellByDate() {
        this.sellByDate = sellByDate - 1;
    }

    protected void updateQualityAfterSellByDate() {
        updateQuality(changeRate());
    }

    protected void updateQuality(int changeRate) {
        this.quality = min(max(quality + changeRate, 0), 50);
    }

    protected abstract int changeRate();

    @Override
    public String toString() {
        return this.getName() + ", " + getSellByDate() + ", " + this.getQuality();
    }

}