// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.util;

import com.quran.labs.androidquran.data.QuranInfo;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ShareUtil_Factory implements Factory<ShareUtil> {
  private final Provider<QuranInfo> quranInfoProvider;

  public ShareUtil_Factory(Provider<QuranInfo> quranInfoProvider) {
    this.quranInfoProvider = quranInfoProvider;
  }

  @Override
  public ShareUtil get() {
    return new ShareUtil(quranInfoProvider.get());
  }

  public static ShareUtil_Factory create(Provider<QuranInfo> quranInfoProvider) {
    return new ShareUtil_Factory(quranInfoProvider);
  }

  public static ShareUtil newShareUtil(QuranInfo quranInfo) {
    return new ShareUtil(quranInfo);
  }
}
