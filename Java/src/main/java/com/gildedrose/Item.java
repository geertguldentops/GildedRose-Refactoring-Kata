package com.gildedrose;

public class Item {

    private final String name;

    private int sellByDate;
    private int quality;

    public Item(String name, int sellByDate, int quality) {
        this.name = name;
        this.sellByDate = sellByDate;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public boolean isNormal() {
        return !getName().equals("Aged Brie") &&
                !getName().equals("Backstage passes to a TAFKAL80ETC concert");
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
