// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.data;

import com.quran.data.source.PageProvider;
import com.quran.labs.androidquran.util.QuranSettings;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
import javax.inject.Provider;

public final class QuranDataModule_ProvideQuranPageProviderFactory
    implements Factory<PageProvider> {
  private final Provider<Map<String, PageProvider>> providersProvider;

  private final Provider<QuranSettings> quranSettingsProvider;

  public QuranDataModule_ProvideQuranPageProviderFactory(
      Provider<Map<String, PageProvider>> providersProvider,
      Provider<QuranSettings> quranSettingsProvider) {
    this.providersProvider = providersProvider;
    this.quranSettingsProvider = quranSettingsProvider;
  }

  @Override
  public PageProvider get() {
    return Preconditions.checkNotNull(
        QuranDataModule.provideQuranPageProvider(
            providersProvider.get(), quranSettingsProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static QuranDataModule_ProvideQuranPageProviderFactory create(
      Provider<Map<String, PageProvider>> providersProvider,
      Provider<QuranSettings> quranSettingsProvider) {
    return new QuranDataModule_ProvideQuranPageProviderFactory(
        providersProvider, quranSettingsProvider);
  }

  public static PageProvider proxyProvideQuranPageProvider(
      Map<String, PageProvider> providers, QuranSettings quranSettings) {
    return Preconditions.checkNotNull(
        QuranDataModule.provideQuranPageProvider(providers, quranSettings),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
