// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.presenter.bookmark;

import com.quran.labs.androidquran.model.bookmark.BookmarkModel;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AddTagDialogPresenter_Factory implements Factory<AddTagDialogPresenter> {
  private final Provider<BookmarkModel> bookmarkModelProvider;

  public AddTagDialogPresenter_Factory(Provider<BookmarkModel> bookmarkModelProvider) {
    this.bookmarkModelProvider = bookmarkModelProvider;
  }

  @Override
  public AddTagDialogPresenter get() {
    return new AddTagDialogPresenter(bookmarkModelProvider.get());
  }

  public static AddTagDialogPresenter_Factory create(
      Provider<BookmarkModel> bookmarkModelProvider) {
    return new AddTagDialogPresenter_Factory(bookmarkModelProvider);
  }
}
