package com.gildedrose;

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
        @ValueSource(strings = {TEST_ITEM_NAME, AGED_BRIE, BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, SULFURAS_HAND_OF_RAGNAROS})
        void item_quality_is_never_negative_when_sell_by_day_positive(String itemName) {
            var app = new GildedRose(new Item[]{new Item(itemName, 7, 0)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getQuality)
                    .allSatisfy(quality -> assertThat(quality).isNotNegative());
        }

        @ParameterizedTest(name = "Item with name [{0}] should never have a quality higher than 50")
        @ValueSource(strings = {TEST_ITEM_NAME, AGED_BRIE, BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, SULFURAS_HAND_OF_RAGNAROS})
        void item_quality_is_never_higher_than_50(String itemName) {
            var app = new GildedRose(new Item[]{new Item(itemName, 7, 50)});

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
        @ValueSource(strings = {TEST_ITEM_NAME, ANOTHER_TEST_ITEM_NAME})
        void item_sell_by_date_and_quality_go_down_by_1_each_day_when_sell_by_date_is_positive(String itemName) {
            var app = new GildedRose(new Item[]{new Item(itemName, 7, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(itemName, 6, 4)
                    );
        }

        @ParameterizedTest
        @ValueSource(strings = {TEST_ITEM_NAME, ANOTHER_TEST_ITEM_NAME})
        void item_sell_by_date_and_quality_go_down_by_1_each_day_when_sell_by_date_now(String itemName) {
            var app = new GildedRose(new Item[]{new Item(itemName, 0, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(itemName, -1, 3)
                    );
        }

        @ParameterizedTest
        @ValueSource(strings = {TEST_ITEM_NAME, ANOTHER_TEST_ITEM_NAME})
        void item_degrades_in_quality_twice_as_fast_when_sell_by_date_has_passed(String itemName) {
            var app = new GildedRose(new Item[]{new Item(itemName, -1, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(itemName, -2, 3)
                    );
        }

        @ParameterizedTest
        @ValueSource(strings = {TEST_ITEM_NAME, ANOTHER_TEST_ITEM_NAME})
        void item_quality_is_never_negative_even_when_sell_by_day_has_passed(String itemName) {
            var app = new GildedRose(new Item[]{new Item(itemName, -1, 1)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(itemName, -2, 0)
                    );
        }

    }

    @Nested
    class AgedBrie {

        @Test
        void aged_brie_increases_in_quality_when_sell_by_date_positive() {
            var app = new GildedRose(new Item[]{new Item(AGED_BRIE, 7, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(AGED_BRIE, 6, 6)
                    );
        }

        // TODO: Bug or feature? Not explicitly mentioned as a requirement
        @Test
        void aged_brie_increases_in_quality_twice_as_fast_when_sell_by_date_now() {
            var app = new GildedRose(new Item[]{new Item(AGED_BRIE, 0, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(AGED_BRIE, -1, 7)
                    );
        }

        // TODO: Bug or feature? Not explicitly mentioned as a requirement
        @Test
        void aged_brie_increases_in_quality_twice_as_fast_when_sell_by_date_has_passed() {
            var app = new GildedRose(new Item[]{new Item(AGED_BRIE, -1, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(AGED_BRIE, -2, 7)
                    );
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -2})
        void aged_brie_quality_is_never_higher_than_50_even_when_sell_by_date_is_now_or_has_passed(int sellByDate) {
            var app = new GildedRose(new Item[]{new Item(AGED_BRIE, sellByDate, 49)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(AGED_BRIE, sellByDate - 1, 50)
                    );
        }

    }

    @Nested
    class Sulfuras {

        @ParameterizedTest
        @ValueSource(ints = {15, 1, 0, -1, -8})
        void sulfuras_does_not_change_quality(int sellByDate) {
            var app = new GildedRose(new Item[]{new Item(SULFURAS_HAND_OF_RAGNAROS, sellByDate, 80)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getQuality)
                    .containsOnly(
                            tuple(SULFURAS_HAND_OF_RAGNAROS, 80)
                    );

        }

        @Test
        void sulfuras_is_never_sold() {
            var app = new GildedRose(new Item[]{new Item(SULFURAS_HAND_OF_RAGNAROS, 25, 80)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate)
                    .containsOnly(
                            tuple(SULFURAS_HAND_OF_RAGNAROS, 25)
                    );

        }

    }

    @Nested
    class BackstagePasses {

        @ParameterizedTest
        @ValueSource(ints = {40, 12, 11})
        void backstage_passes_increases_in_quality_by_1_when_sell_by_date_larger_than_10(int sellByDate) {
            var app = new GildedRose(new Item[]{new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate - 1, 6)
                    );
        }

        @ParameterizedTest
        @ValueSource(ints = {10, 9, 8, 7, 6})
        void backstage_passes_increases_in_quality_by_2_when_sell_by_date_between_10_and_6_inclusive(int sellByDate) {
            var app = new GildedRose(new Item[]{new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate - 1, 7)
                    );
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 4, 3, 2, 1})
        void backstage_passes_increases_in_quality_by_3_when_sell_by_date_between_5_and_1_inclusive(int sellByDate) {
            var app = new GildedRose(new Item[]{new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate - 1, 8)
                    );
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -2})
        void backstage_passes_drop_to_0_quality_when_sell_by_date_now_or_has_passed(int sellByDate) {
            var app = new GildedRose(new Item[]{new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate, 5)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate - 1, 0)
                    );
        }

        @ParameterizedTest
        @ValueSource(ints = {11, 10, 7, 5, 4, 1})
        void backstage_passes_quality_is_never_higher_than_50(int sellByDate) {
            var app = new GildedRose(new Item[]{new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate, 49)});

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(1)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, sellByDate - 1, 50)
                    );
        }

    }

    @Nested
    class MultipleItems {

        @Test
        void update_quality_of_multiple_items_at_the_same_time() {
            var app = new GildedRose(new Item[]{
                    new Item(TEST_ITEM_NAME, 7, 5),
                    new Item(ANOTHER_TEST_ITEM_NAME, -1, 3)
            });

            app.updateQuality();

            assertThat(app.items)
                    .hasSize(2)
                    .extracting(Item::getName, Item::getSellByDate, Item::getQuality)
                    .containsOnly(
                            tuple(TEST_ITEM_NAME, 6, 4),
                            tuple(ANOTHER_TEST_ITEM_NAME, -2, 1)
                    );
        }

    }

}
