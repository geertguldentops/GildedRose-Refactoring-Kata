package com.gildedrose;

public class NormalItem extends Item {

    public NormalItem(String name, int sellByDate, int quality) {
        super(name, sellByDate, quality);
    }

    @Override
    protected int changeRate() {
        return -1;
    }

}
