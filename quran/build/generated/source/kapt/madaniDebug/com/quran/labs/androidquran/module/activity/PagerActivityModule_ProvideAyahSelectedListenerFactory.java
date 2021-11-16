// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.module.activity;

import com.quran.labs.androidquran.ui.helpers.AyahSelectedListener;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class PagerActivityModule_ProvideAyahSelectedListenerFactory
    implements Factory<AyahSelectedListener> {
  private final PagerActivityModule module;

  public PagerActivityModule_ProvideAyahSelectedListenerFactory(PagerActivityModule module) {
    this.module = module;
  }

  @Override
  public AyahSelectedListener get() {
    return Preconditions.checkNotNull(
        module.provideAyahSelectedListener(),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static PagerActivityModule_ProvideAyahSelectedListenerFactory create(
      PagerActivityModule module) {
    return new PagerActivityModule_ProvideAyahSelectedListenerFactory(module);
  }

  public static AyahSelectedListener proxyProvideAyahSelectedListener(
      PagerActivityModule instance) {
    return Preconditions.checkNotNull(
        instance.provideAyahSelectedListener(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
