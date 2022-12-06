package com.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class GildedRoseTest {

	@Nested
	class AgedBrie {

		@Test
		void aged_brie_increases_in_quality() {
			GildedRose app = new GildedRose(new Item[] { new Item("Aged Brie", 7, 5) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getSellIn, Item::getQuality)
					.containsOnly(
							tuple("Aged Brie", 6, 6)
					);
		}

	}

}
