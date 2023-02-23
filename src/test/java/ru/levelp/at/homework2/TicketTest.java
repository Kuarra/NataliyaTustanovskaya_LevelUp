package ru.levelp.at.homework2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TicketTest {

    @ParameterizedTest
    @ValueSource(ints = {111111, 222222})
    @DisplayName("Счастливый билет")
    @Tag(TagTest.POSITIVE_TAG_NAME)
    void whenTicketAmountMatchesThenTrue(int number) {
        Ticket luckyTicket = new Ticket();
        boolean result = luckyTicket.findLuckyTicket(number);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {111456, 146, 1234567})
    @DisplayName("Несчастливый билет")
    @Tag(TagTest.NEGATIVE_TAG_NAME)
    void whenTicketAmountNotMatchesThenFalse(int number) {
        Ticket luckyTicket = new Ticket();
        boolean result = luckyTicket.findLuckyTicket(number);
        assertFalse(result);
    }
}


