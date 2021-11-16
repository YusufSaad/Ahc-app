package com.example.coreandroid;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by ahmedsaad on 2017-11-20.
 * Copyright © 2017. All rights reserved.
 */

public class RobolectricTestRunnerWithResources extends RobolectricTestRunner {

    @Override
    protected Config buildGlobalConfig() {
        return Config.Builder.defaults()
                .setPackageName("com.example.coreandroid")
                .setManifest("android/build/intermediates/manifests/full/debug/AndroidManifest.xml")
                .setResourceDir("../../../res/merged/debug") // relative to manifest
                .setAssetDir("../../../assets/debug") // relative to manifest
                .build();
    }

    public RobolectricTestRunnerWithResources(Class<?> testClass) throws InitializationError {
        super(testClass);
    }
}
