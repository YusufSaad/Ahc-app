// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.module.activity;

import com.quran.labs.androidquran.util.QuranScreenInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class PagerActivityModule_ProvideImageWidthFactory implements Factory<String> {
  private final PagerActivityModule module;

  private final Provider<QuranScreenInfo> screenInfoProvider;

  public PagerActivityModule_ProvideImageWidthFactory(
      PagerActivityModule module, Provider<QuranScreenInfo> screenInfoProvider) {
    this.module = module;
    this.screenInfoProvider = screenInfoProvider;
  }

  @Override
  public String get() {
    return Preconditions.checkNotNull(
        module.provideImageWidth(screenInfoProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static PagerActivityModule_ProvideImageWidthFactory create(
      PagerActivityModule module, Provider<QuranScreenInfo> screenInfoProvider) {
    return new PagerActivityModule_ProvideImageWidthFactory(module, screenInfoProvider);
  }

  public static String proxyProvideImageWidth(
      PagerActivityModule instance, QuranScreenInfo screenInfo) {
    return Preconditions.checkNotNull(
        instance.provideImageWidth(screenInfo),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
