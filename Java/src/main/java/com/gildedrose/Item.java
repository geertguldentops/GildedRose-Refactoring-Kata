package com.gildedrose;

public abstract class Item {

    private final String name;

    private int sellByDate;
    private int quality;

    public static Item of(String name, int sellByDate, int quality) {
        return switch (name) {
            case "Aged Brie" -> new AgedBrie(name, sellByDate, quality);
            case "Backstage passes to a TAFKAL80ETC concert" -> new BackStagePasses(name, sellByDate, quality);
            case "Sulfuras, Hand of Ragnaros" -> new Sulfuras(name, sellByDate, quality);
            default -> new NormalItem(name, sellByDate, quality);
        };
    }

    public Item(String name, int sellByDate, int quality) {
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

    public void decreaseSellByDate() {
        this.sellByDate = getSellByDate() - 1;
    }

    public abstract void updateQuality();

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
