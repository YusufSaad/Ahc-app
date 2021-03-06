// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.presenter.translation;

import com.quran.labs.androidquran.data.QuranInfo;
import com.quran.labs.androidquran.database.TranslationsDBAdapter;
import com.quran.labs.androidquran.model.translation.TranslationModel;
import com.quran.labs.androidquran.util.QuranSettings;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InlineTranslationPresenter_Factory
    implements Factory<InlineTranslationPresenter> {
  private final Provider<TranslationModel> translationModelProvider;

  private final Provider<TranslationsDBAdapter> dbAdapterProvider;

  private final Provider<QuranSettings> quranSettingsProvider;

  private final Provider<QuranInfo> quranInfoProvider;

  public InlineTranslationPresenter_Factory(
      Provider<TranslationModel> translationModelProvider,
      Provider<TranslationsDBAdapter> dbAdapterProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<QuranInfo> quranInfoProvider) {
    this.translationModelProvider = translationModelProvider;
    this.dbAdapterProvider = dbAdapterProvider;
    this.quranSettingsProvider = quranSettingsProvider;
    this.quranInfoProvider = quranInfoProvider;
  }

  @Override
  public InlineTranslationPresenter get() {
    return new InlineTranslationPresenter(
        translationModelProvider.get(),
        dbAdapterProvider.get(),
        quranSettingsProvider.get(),
        quranInfoProvider.get());
  }

  public static InlineTranslationPresenter_Factory create(
      Provider<TranslationModel> translationModelProvider,
      Provider<TranslationsDBAdapter> dbAdapterProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<QuranInfo> quranInfoProvider) {
    return new InlineTranslationPresenter_Factory(
        translationModelProvider, dbAdapterProvider, quranSettingsProvider, quranInfoProvider);
  }

  public static InlineTranslationPresenter newInlineTranslationPresenter(
      TranslationModel translationModel,
      TranslationsDBAdapter dbAdapter,
      QuranSettings quranSettings,
      QuranInfo quranInfo) {
    return new InlineTranslationPresenter(translationModel, dbAdapter, quranSettings, quranInfo);
  }
}
