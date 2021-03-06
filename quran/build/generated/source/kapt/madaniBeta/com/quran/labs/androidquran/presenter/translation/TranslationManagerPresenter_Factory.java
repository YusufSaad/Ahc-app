// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.presenter.translation;

import android.content.Context;
import com.quran.labs.androidquran.database.TranslationsDBAdapter;
import com.quran.labs.androidquran.util.QuranFileUtils;
import com.quran.labs.androidquran.util.QuranSettings;
import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class TranslationManagerPresenter_Factory
    implements Factory<TranslationManagerPresenter> {
  private final Provider<Context> appContextProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<QuranSettings> quranSettingsProvider;

  private final Provider<TranslationsDBAdapter> dbAdapterProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  public TranslationManagerPresenter_Factory(
      Provider<Context> appContextProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<TranslationsDBAdapter> dbAdapterProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    this.appContextProvider = appContextProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.quranSettingsProvider = quranSettingsProvider;
    this.dbAdapterProvider = dbAdapterProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
  }

  @Override
  public TranslationManagerPresenter get() {
    return new TranslationManagerPresenter(
        appContextProvider.get(),
        okHttpClientProvider.get(),
        quranSettingsProvider.get(),
        dbAdapterProvider.get(),
        quranFileUtilsProvider.get());
  }

  public static TranslationManagerPresenter_Factory create(
      Provider<Context> appContextProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<TranslationsDBAdapter> dbAdapterProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    return new TranslationManagerPresenter_Factory(
        appContextProvider,
        okHttpClientProvider,
        quranSettingsProvider,
        dbAdapterProvider,
        quranFileUtilsProvider);
  }

  public static TranslationManagerPresenter newTranslationManagerPresenter(
      Context appContext,
      OkHttpClient okHttpClient,
      QuranSettings quranSettings,
      TranslationsDBAdapter dbAdapter,
      QuranFileUtils quranFileUtils) {
    return new TranslationManagerPresenter(
        appContext, okHttpClient, quranSettings, dbAdapter, quranFileUtils);
  }
}
