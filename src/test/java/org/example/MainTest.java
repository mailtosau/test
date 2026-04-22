package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void findUrls() {
        Assert.assertEquals(1, new Main().findUrls("SomeText"));
    }
}