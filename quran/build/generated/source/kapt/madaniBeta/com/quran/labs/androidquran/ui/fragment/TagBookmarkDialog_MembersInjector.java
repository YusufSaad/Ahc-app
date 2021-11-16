// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.ui.fragment;

import com.quran.labs.androidquran.data.QuranInfo;
import com.quran.labs.androidquran.presenter.bookmark.TagBookmarkPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TagBookmarkDialog_MembersInjector implements MembersInjector<TagBookmarkDialog> {
  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<TagBookmarkPresenter> mTagBookmarkPresenterProvider;

  public TagBookmarkDialog_MembersInjector(
      Provider<QuranInfo> quranInfoProvider,
      Provider<TagBookmarkPresenter> mTagBookmarkPresenterProvider) {
    this.quranInfoProvider = quranInfoProvider;
    this.mTagBookmarkPresenterProvider = mTagBookmarkPresenterProvider;
  }

  public static MembersInjector<TagBookmarkDialog> create(
      Provider<QuranInfo> quranInfoProvider,
      Provider<TagBookmarkPresenter> mTagBookmarkPresenterProvider) {
    return new TagBookmarkDialog_MembersInjector(quranInfoProvider, mTagBookmarkPresenterProvider);
  }

  @Override
  public void injectMembers(TagBookmarkDialog instance) {
    injectQuranInfo(instance, quranInfoProvider.get());
    injectMTagBookmarkPresenter(instance, mTagBookmarkPresenterProvider.get());
  }

  public static void injectQuranInfo(TagBookmarkDialog instance, QuranInfo quranInfo) {
    instance.quranInfo = quranInfo;
  }

  public static void injectMTagBookmarkPresenter(
      TagBookmarkDialog instance, TagBookmarkPresenter mTagBookmarkPresenter) {
    instance.mTagBookmarkPresenter = mTagBookmarkPresenter;
  }
}
