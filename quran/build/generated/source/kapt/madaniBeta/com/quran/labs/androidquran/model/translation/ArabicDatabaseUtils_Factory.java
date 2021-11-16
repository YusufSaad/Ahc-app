// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.model.translation;

import android.content.Context;
import com.quran.labs.androidquran.data.QuranInfo;
import com.quran.labs.androidquran.util.QuranFileUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ArabicDatabaseUtils_Factory implements Factory<ArabicDatabaseUtils> {
  private final Provider<Context> contextProvider;

  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  public ArabicDatabaseUtils_Factory(
      Provider<Context> contextProvider,
      Provider<QuranInfo> quranInfoProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    this.contextProvider = contextProvider;
    this.quranInfoProvider = quranInfoProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
  }

  @Override
  public ArabicDatabaseUtils get() {
    return new ArabicDatabaseUtils(
        contextProvider.get(), quranInfoProvider.get(), quranFileUtilsProvider.get());
  }

  public static ArabicDatabaseUtils_Factory create(
      Provider<Context> contextProvider,
      Provider<QuranInfo> quranInfoProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    return new ArabicDatabaseUtils_Factory(
        contextProvider, quranInfoProvider, quranFileUtilsProvider);
  }

  public static ArabicDatabaseUtils newArabicDatabaseUtils(
      Context context, QuranInfo quranInfo, QuranFileUtils quranFileUtils) {
    return new ArabicDatabaseUtils(context, quranInfo, quranFileUtils);
  }
}
