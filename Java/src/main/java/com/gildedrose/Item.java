package com.gildedrose;

public class Item {

    private String name;

    public int sellByDate;

    public int quality;

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

    public int getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.sellByDate + ", " + this.quality;
    }

}
