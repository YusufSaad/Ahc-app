// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.presenter.translation;

import com.quran.labs.androidquran.data.QuranInfo;
import com.quran.labs.androidquran.database.TranslationsDBAdapter;
import com.quran.labs.androidquran.model.translation.TranslationModel;
import com.quran.labs.androidquran.util.QuranSettings;
import com.quran.labs.androidquran.util.ShareUtil;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TranslationPresenter_Factory implements Factory<TranslationPresenter> {
  private final Provider<TranslationModel> translationModelProvider;

  private final Provider<QuranSettings> quranSettingsProvider;

  private final Provider<TranslationsDBAdapter> translationsAdapterProvider;

  private final Provider<ShareUtil> shareUtilProvider;

  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<Integer[]> pagesProvider;

  public TranslationPresenter_Factory(
      Provider<TranslationModel> translationModelProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<TranslationsDBAdapter> translationsAdapterProvider,
      Provider<ShareUtil> shareUtilProvider,
      Provider<QuranInfo> quranInfoProvider,
      Provider<Integer[]> pagesProvider) {
    this.translationModelProvider = translationModelProvider;
    this.quranSettingsProvider = quranSettingsProvider;
    this.translationsAdapterProvider = translationsAdapterProvider;
    this.shareUtilProvider = shareUtilProvider;
    this.quranInfoProvider = quranInfoProvider;
    this.pagesProvider = pagesProvider;
  }

  @Override
  public TranslationPresenter get() {
    return new TranslationPresenter(
        translationModelProvider.get(),
        quranSettingsProvider.get(),
        translationsAdapterProvider.get(),
        shareUtilProvider.get(),
        quranInfoProvider.get(),
        pagesProvider.get());
  }

  public static TranslationPresenter_Factory create(
      Provider<TranslationModel> translationModelProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<TranslationsDBAdapter> translationsAdapterProvider,
      Provider<ShareUtil> shareUtilProvider,
      Provider<QuranInfo> quranInfoProvider,
      Provider<Integer[]> pagesProvider) {
    return new TranslationPresenter_Factory(
        translationModelProvider,
        quranSettingsProvider,
        translationsAdapterProvider,
        shareUtilProvider,
        quranInfoProvider,
        pagesProvider);
  }

  public static TranslationPresenter newTranslationPresenter(
      TranslationModel translationModel,
      QuranSettings quranSettings,
      TranslationsDBAdapter translationsAdapter,
      ShareUtil shareUtil,
      QuranInfo quranInfo,
      Integer... pages) {
    return new TranslationPresenter(
        translationModel, quranSettings, translationsAdapter, shareUtil, quranInfo, pages);
  }
}
