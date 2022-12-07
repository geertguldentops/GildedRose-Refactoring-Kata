package com.gildedrose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class GildedRose {

    private final List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = new ArrayList<>(items);
    }

    public Collection<Item> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item.isNormal()) {
                var normalItem = new NormalItem(item.getName(), item.getSellByDate(), item.getQuality());
                normalItem.updateQuality();

                items.set(i, normalItem);
            } else if (item.isAgedBrie()) {
                var agedBrie = new AgedBrie(item.getName(), item.getSellByDate(), item.getQuality());
                agedBrie.updateQuality();

                items.set(i, agedBrie);
            } else if (item.isBackStagePasses()) {
                var backStagePasses = new BackStagePasses(item.getName(), item.getSellByDate(), item.getQuality());
                backStagePasses.updateQuality();

                items.set(i, backStagePasses);
            } else if (item.isSulfuras()) {
                var sulfuras = new Sulfuras(item.getName(), item.getSellByDate(), item.getQuality());
                sulfuras.updateQuality();

                items.set(i, sulfuras);
            }
        }
    }

}