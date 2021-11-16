package com.example.coreandroid

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by ahmedsaad on 2017-11-17.
 * Copyright © 2017. All rights reserved.
 */
@RunWith(RobolectricTestRunner::class)
@Config(manifest = "../${Config.DEFAULT_MANIFEST_NAME}")
class FakeTests {
    @Test
    fun fakeTest() {
        Assert.fail()
    }
}