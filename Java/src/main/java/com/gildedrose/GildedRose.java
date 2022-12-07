package com.gildedrose;

import java.util.Collection;
import java.util.Collections;

class GildedRose {

    private final Collection<Item> items;

    public GildedRose(Collection<Item> items) {
        this.items = items;
    }

    /* default */Collection<Item> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public void updateQuality() {
        items.forEach(Item::updateQuality);
    }

}