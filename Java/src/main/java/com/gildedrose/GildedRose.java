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

    // TODO: Do not rename method (reason to rename: it also updates sellByDate, better refactor --> encapsulate sellByDate in Item so it becomes an implementation detail)
    public void updateQuality() {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item.isNormal()) {
                NormalItem normalItem = new NormalItem(item.getName(), item.getSellByDate(), item.getQuality());
                normalItem.updateQuality();

                items.set(i, normalItem);
            } else if (item.isAgedBrie()) {
                item.increaseQuality(1);

                item.decreaseSellByDate();

                if (item.getSellByDate() < 0) {
                    item.increaseQuality(1);
                }
            } else if (item.isBackStagePasses()) {
                item.increaseQuality(1);

                if (item.getSellByDate() < 11) {
                    item.increaseQuality(1);

                }
                if (item.getSellByDate() < 6) {
                    item.increaseQuality(1);
                }

                item.decreaseSellByDate();

                if (item.getSellByDate() < 0) {
                    item.dropQualityToZero();
                }
            } else if (item.isSulfuras()) {
                // Do Nothing
            }
        }
    }

}