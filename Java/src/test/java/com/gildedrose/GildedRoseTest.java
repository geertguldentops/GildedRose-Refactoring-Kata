package com.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class GildedRoseTest {

	@Nested
	class Invariants {

		@ParameterizedTest(name = "Item with name [{0}] should never have a negative quality")
		@ValueSource(strings = { "Test item name", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros" })
		void item_quality_is_never_negative(String itemName) {
			var app = new GildedRose(new Item[] { new Item(itemName, 7, 0) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getQuality)
					.allSatisfy(quality -> assertThat(quality).isNotNegative());
		}

	}

	@Nested
	class AgedBrie {

		@Test
		void aged_brie_increases_in_quality() {
			var app = new GildedRose(new Item[] { new Item("Aged Brie", 7, 5) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getSellIn, Item::getQuality)
					.containsOnly(
							tuple("Aged Brie", 6, 6)
					);
		}

	}

	@Nested
	class Sulfuras {

		@Test
		void sulfuras_does_not_change_quality() {
			var app = new GildedRose(new Item[] { new Item("Sulfuras, Hand of Ragnaros", 7, 5) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getQuality)
					.containsOnly(
							tuple("Sulfuras, Hand of Ragnaros", 5)
					);

		}

		@Test
		void sulfuras_is_never_sold() {
			var app = new GildedRose(new Item[] { new Item("Sulfuras, Hand of Ragnaros", 25, 4) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getSellIn)
					.containsOnly(
							tuple("Sulfuras, Hand of Ragnaros", 25)
					);

		}

	}

}
