// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.model.bookmark;

import com.quran.labs.androidquran.database.BookmarksDBAdapter;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RecentPageModel_Factory implements Factory<RecentPageModel> {
  private final Provider<BookmarksDBAdapter> adapterProvider;

  public RecentPageModel_Factory(Provider<BookmarksDBAdapter> adapterProvider) {
    this.adapterProvider = adapterProvider;
  }

  @Override
  public RecentPageModel get() {
    return new RecentPageModel(adapterProvider.get());
  }

  public static RecentPageModel_Factory create(Provider<BookmarksDBAdapter> adapterProvider) {
    return new RecentPageModel_Factory(adapterProvider);
  }
}
