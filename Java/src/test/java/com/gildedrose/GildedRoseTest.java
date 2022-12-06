package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class GildedRoseTest {

	private static final String TEST_ITEM_NAME = "Test item name";
	private static final String ANOTHER_TEST_ITEM_NAME = "Another test item name";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

	@Nested
	class Invariants {

		@ParameterizedTest(name = "Item with name [{0}] should never have a negative quality")
		@ValueSource(strings = { TEST_ITEM_NAME, AGED_BRIE, BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, SULFURAS_HAND_OF_RAGNAROS })
		void item_quality_is_never_negative(String itemName) {
			var app = new GildedRose(new Item[] { new Item(itemName, 7, 0) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getQuality)
					.allSatisfy(quality -> assertThat(quality).isNotNegative());
		}

		@ParameterizedTest(name = "Item with name [{0}] should never have a quality higher than 50")
		@ValueSource(strings = { TEST_ITEM_NAME, AGED_BRIE, BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, SULFURAS_HAND_OF_RAGNAROS })
		void item_quality_is_never_higher_than_50(String itemName) {
			var app = new GildedRose(new Item[] { new Item(itemName, 7, 50) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getQuality)
					.allSatisfy(quality -> assertThat(quality).isLessThanOrEqualTo(50));
		}

	}

	@Nested
	class NormalItem {

		@ParameterizedTest
		@ValueSource(strings = { TEST_ITEM_NAME, ANOTHER_TEST_ITEM_NAME })
		void item_sell_by_date_and_quality_go_down_by_1_each_day_when_sell_by_date_is_positive(String itemName) {
			var app = new GildedRose(new Item[] { new Item(itemName, 7, 5) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getSellIn, Item::getQuality)
					.containsOnly(
							tuple(itemName, 6, 4)
					);
		}

		@ParameterizedTest
		@ValueSource(strings = { TEST_ITEM_NAME, ANOTHER_TEST_ITEM_NAME })
		void item_degrades_in_quality_twice_as_fast_when_sell_by_date_has_passed(String itemName) {
			var app = new GildedRose(new Item[] { new Item(itemName, -1, 5) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getSellIn, Item::getQuality)
					.containsOnly(
							tuple(itemName, -2, 3)
					);
		}

	}

	@Nested
	class AgedBrie {

		@Test
		void aged_brie_increases_in_quality() {
			var app = new GildedRose(new Item[] { new Item(AGED_BRIE, 7, 5) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getSellIn, Item::getQuality)
					.containsOnly(
							tuple(AGED_BRIE, 6, 6)
					);
		}

	}

	@Nested
	class Sulfuras {

		@Test
		void sulfuras_does_not_change_quality() {
			var app = new GildedRose(new Item[] { new Item(SULFURAS_HAND_OF_RAGNAROS, 7, 5) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getQuality)
					.containsOnly(
							tuple(SULFURAS_HAND_OF_RAGNAROS, 5)
					);

		}

		@Test
		void sulfuras_is_never_sold() {
			var app = new GildedRose(new Item[] { new Item(SULFURAS_HAND_OF_RAGNAROS, 25, 4) });

			app.updateQuality();

			assertThat(app.items)
					.hasSize(1)
					.extracting(Item::getName, Item::getSellIn)
					.containsOnly(
							tuple(SULFURAS_HAND_OF_RAGNAROS, 25)
					);

		}

	}

}
