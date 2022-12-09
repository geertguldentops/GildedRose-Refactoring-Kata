package com.gildedrose;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Item {

    protected final String name;
    protected int sellByDate;
    protected int quality;

    public static Item of(String name, int sellByDate, int quality) {
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

    public abstract void updateQuality();

    protected void updateQuality(int changeRate) {
        this.quality = min(max(quality + changeRate, 0), 50);
    }

    protected void updateSellByDate() {
        this.sellByDate = sellByDate - 1;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + getSellByDate() + ", " + this.getQuality();
    }

}
