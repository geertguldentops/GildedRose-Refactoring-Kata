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

            Item newItem = Item.of(item.getName(), item.getSellByDate(), item.getQuality());
            newItem.updateQuality();

            items.set(i, newItem);
        }
    }

}