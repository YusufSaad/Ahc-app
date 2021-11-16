// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.module.application;

import com.quran.labs.androidquran.util.QuranSettings;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideQuranSettingsFactory implements Factory<QuranSettings> {
  private final ApplicationModule module;

  public ApplicationModule_ProvideQuranSettingsFactory(ApplicationModule module) {
    this.module = module;
  }

  @Override
  public QuranSettings get() {
    return Preconditions.checkNotNull(
        module.provideQuranSettings(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static ApplicationModule_ProvideQuranSettingsFactory create(ApplicationModule module) {
    return new ApplicationModule_ProvideQuranSettingsFactory(module);
  }

  public static QuranSettings proxyProvideQuranSettings(ApplicationModule instance) {
    return Preconditions.checkNotNull(
        instance.provideQuranSettings(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
