// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.presenter.bookmark;

import android.content.Context;
import com.quran.labs.androidquran.data.QuranInfo;
import com.quran.labs.androidquran.model.bookmark.BookmarkModel;
import com.quran.labs.androidquran.model.translation.ArabicDatabaseUtils;
import com.quran.labs.androidquran.ui.helpers.QuranRowFactory;
import com.quran.labs.androidquran.util.QuranSettings;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BookmarkPresenter_Factory implements Factory<BookmarkPresenter> {
  private final Provider<Context> appContextProvider;

  private final Provider<BookmarkModel> bookmarkModelProvider;

  private final Provider<QuranSettings> quranSettingsProvider;

  private final Provider<ArabicDatabaseUtils> arabicDatabaseUtilsProvider;

  private final Provider<QuranRowFactory> quranRowFactoryProvider;

  private final Provider<QuranInfo> quranInfoProvider;

  public BookmarkPresenter_Factory(
      Provider<Context> appContextProvider,
      Provider<BookmarkModel> bookmarkModelProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<ArabicDatabaseUtils> arabicDatabaseUtilsProvider,
      Provider<QuranRowFactory> quranRowFactoryProvider,
      Provider<QuranInfo> quranInfoProvider) {
    this.appContextProvider = appContextProvider;
    this.bookmarkModelProvider = bookmarkModelProvider;
    this.quranSettingsProvider = quranSettingsProvider;
    this.arabicDatabaseUtilsProvider = arabicDatabaseUtilsProvider;
    this.quranRowFactoryProvider = quranRowFactoryProvider;
    this.quranInfoProvider = quranInfoProvider;
  }

  @Override
  public BookmarkPresenter get() {
    return new BookmarkPresenter(
        appContextProvider.get(),
        bookmarkModelProvider.get(),
        quranSettingsProvider.get(),
        arabicDatabaseUtilsProvider.get(),
        quranRowFactoryProvider.get(),
        quranInfoProvider.get());
  }

  public static BookmarkPresenter_Factory create(
      Provider<Context> appContextProvider,
      Provider<BookmarkModel> bookmarkModelProvider,
      Provider<QuranSettings> quranSettingsProvider,
      Provider<ArabicDatabaseUtils> arabicDatabaseUtilsProvider,
      Provider<QuranRowFactory> quranRowFactoryProvider,
      Provider<QuranInfo> quranInfoProvider) {
    return new BookmarkPresenter_Factory(
        appContextProvider,
        bookmarkModelProvider,
        quranSettingsProvider,
        arabicDatabaseUtilsProvider,
        quranRowFactoryProvider,
        quranInfoProvider);
  }

  public static BookmarkPresenter newBookmarkPresenter(
      Context appContext,
      BookmarkModel bookmarkModel,
      QuranSettings quranSettings,
      ArabicDatabaseUtils arabicDatabaseUtils,
      QuranRowFactory quranRowFactory,
      QuranInfo quranInfo) {
    return new BookmarkPresenter(
        appContext, bookmarkModel, quranSettings, arabicDatabaseUtils, quranRowFactory, quranInfo);
  }
}
