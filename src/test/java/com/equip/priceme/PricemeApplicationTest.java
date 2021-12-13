package com.equip.priceme;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class PricemeApplicationTest {

    @Inject
    private EmbeddedApplication application;

    @Test
    void isApplicationUp() {
        assertEquals(application.isRunning(), true);
    }

}