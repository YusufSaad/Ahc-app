package com.example.coreandroid.sources.dependencies


/**
 * Created by ahmedsaad on 2017-11-30.
 * Copyright © 2017. All rights reserved.
 */
 interface HasDependencies {

    /// Container for dependency instance factories
    val dependencies: Dependency
        get() {
            return DependencyInjector.dependencies
        }
}

/// Used to pass around dependency container
/// which can be reassigned to another container
class DependencyInjector {
    companion object {
        var dependencies: Dependency = CoreDependency()
    }
}