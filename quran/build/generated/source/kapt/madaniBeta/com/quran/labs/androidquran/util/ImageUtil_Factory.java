// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.util;

import dagger.internal.Factory;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class ImageUtil_Factory implements Factory<ImageUtil> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public ImageUtil_Factory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public ImageUtil get() {
    return new ImageUtil(okHttpClientProvider.get());
  }

  public static ImageUtil_Factory create(Provider<OkHttpClient> okHttpClientProvider) {
    return new ImageUtil_Factory(okHttpClientProvider);
  }
}
