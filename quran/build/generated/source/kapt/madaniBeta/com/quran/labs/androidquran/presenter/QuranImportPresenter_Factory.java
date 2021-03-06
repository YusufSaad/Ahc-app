// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.presenter;

import android.content.Context;
import com.quran.labs.androidquran.model.bookmark.BookmarkImportExportModel;
import com.quran.labs.androidquran.model.bookmark.BookmarkModel;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class QuranImportPresenter_Factory implements Factory<QuranImportPresenter> {
  private final Provider<Context> appContextProvider;

  private final Provider<BookmarkImportExportModel> modelProvider;

  private final Provider<BookmarkModel> bookmarkModelProvider;

  public QuranImportPresenter_Factory(
      Provider<Context> appContextProvider,
      Provider<BookmarkImportExportModel> modelProvider,
      Provider<BookmarkModel> bookmarkModelProvider) {
    this.appContextProvider = appContextProvider;
    this.modelProvider = modelProvider;
    this.bookmarkModelProvider = bookmarkModelProvider;
  }

  @Override
  public QuranImportPresenter get() {
    return new QuranImportPresenter(
        appContextProvider.get(), modelProvider.get(), bookmarkModelProvider.get());
  }

  public static QuranImportPresenter_Factory create(
      Provider<Context> appContextProvider,
      Provider<BookmarkImportExportModel> modelProvider,
      Provider<BookmarkModel> bookmarkModelProvider) {
    return new QuranImportPresenter_Factory(
        appContextProvider, modelProvider, bookmarkModelProvider);
  }

  public static QuranImportPresenter newQuranImportPresenter(
      Context appContext, BookmarkImportExportModel model, BookmarkModel bookmarkModel) {
    return new QuranImportPresenter(appContext, model, bookmarkModel);
  }
}
