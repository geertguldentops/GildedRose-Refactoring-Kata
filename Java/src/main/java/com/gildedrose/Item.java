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

    public void setSellByDate(int sellByDate) {
        this.sellByDate = sellByDate;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void decreaseQuality(int degradationRate) {
        if (getQuality() > 0) {
            setQuality(getQuality() - degradationRate);
        }
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getSellByDate() + ", " + this.getQuality();
    }

}
