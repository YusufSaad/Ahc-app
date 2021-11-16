// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.model.translation;

import android.content.Context;
import com.quran.labs.androidquran.util.QuranFileUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TranslationModel_Factory implements Factory<TranslationModel> {
  private final Provider<Context> appContextProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  public TranslationModel_Factory(
      Provider<Context> appContextProvider, Provider<QuranFileUtils> quranFileUtilsProvider) {
    this.appContextProvider = appContextProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
  }

  @Override
  public TranslationModel get() {
    return new TranslationModel(appContextProvider.get(), quranFileUtilsProvider.get());
  }

  public static TranslationModel_Factory create(
      Provider<Context> appContextProvider, Provider<QuranFileUtils> quranFileUtilsProvider) {
    return new TranslationModel_Factory(appContextProvider, quranFileUtilsProvider);
  }

  public static TranslationModel newTranslationModel(
      Context appContext, QuranFileUtils quranFileUtils) {
    return new TranslationModel(appContext, quranFileUtils);
  }
}