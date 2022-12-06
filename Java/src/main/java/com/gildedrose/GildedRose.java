package com.gildedrose;

import java.util.Collection;
import java.util.Collections;

class GildedRose {

    private final Collection<Item> items;

    public GildedRose(Collection<Item> items) {
        this.items = items;
    }

    public Collection<Item> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    // TODO: Do not rename method (reason to rename: it also updates sellByDate, better refactor --> encapsulate sellByDate in Item so it becomes an implementation detail)
    public void updateQuality() {
        for (Item item : items) {
            if (!item.isSulfuras()) {
                if (item.isNormal()) {
                    item.decreaseQuality(1);

                    item.decreaseSellByDate();

                    if (item.getSellByDate() < 0) {
                        item.decreaseQuality(1);
                    }
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
                }

                if (item.getSellByDate() < 0) {
                    if (item.isBackStagePasses()) {
                        item.dropQualityToZero();
                    }
                }
            }
        }
    }

}