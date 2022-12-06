package com.gildedrose;

public class Item {

    private final String name;

    private int sellByDate;
    private int quality;

    public static Item of(String name, int sellByDate, int quality) {
        return new Item(name, sellByDate, quality);
    }

    public Item(String name, int sellByDate, int quality) {
        this.name = name;
        this.sellByDate = sellByDate;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public boolean isNormal() {
        return !isAgedBrie() && !isBackStagePasses() && !isSulfuras();
    }

    public boolean isBackStagePasses() {
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    public boolean isAgedBrie() {
        return name.equals("Aged Brie");
    }

    public boolean isSulfuras() {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    public int getSellByDate() {
        return sellByDate;
    }

    public void decreaseSellByDate() {
        this.sellByDate = getSellByDate() - 1;
    }

    public int getQuality() {
        return quality;
    }

    public void decreaseQuality(int degradationRate) {
        if (quality > 0) {
            this.quality = getQuality() - degradationRate;
        }
    }

    public void increaseQuality(int improvementRate) {
        if (quality < 50) {
            this.quality = getQuality() + improvementRate;
        }
    }

    public void dropQualityToZero() {
        this.quality = 0;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getSellByDate() + ", " + this.getQuality();
    }

}
