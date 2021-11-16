// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.database;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TranslationsDBHelper_Factory implements Factory<TranslationsDBHelper> {
  private final Provider<Context> contextProvider;

  public TranslationsDBHelper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TranslationsDBHelper get() {
    return new TranslationsDBHelper(contextProvider.get());
  }

  public static TranslationsDBHelper_Factory create(Provider<Context> contextProvider) {
    return new TranslationsDBHelper_Factory(contextProvider);
  }

  public static TranslationsDBHelper newTranslationsDBHelper(Context context) {
    return new TranslationsDBHelper(context);
  }
}
