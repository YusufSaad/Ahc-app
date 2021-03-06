// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.ui;

import com.quran.labs.androidquran.presenter.translation.TranslationManagerPresenter;
import com.quran.labs.androidquran.util.QuranFileUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TranslationManagerActivity_MembersInjector
    implements MembersInjector<TranslationManagerActivity> {
  private final Provider<TranslationManagerPresenter> presenterProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  public TranslationManagerActivity_MembersInjector(
      Provider<TranslationManagerPresenter> presenterProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    this.presenterProvider = presenterProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
  }

  public static MembersInjector<TranslationManagerActivity> create(
      Provider<TranslationManagerPresenter> presenterProvider,
      Provider<QuranFileUtils> quranFileUtilsProvider) {
    return new TranslationManagerActivity_MembersInjector(
        presenterProvider, quranFileUtilsProvider);
  }

  @Override
  public void injectMembers(TranslationManagerActivity instance) {
    injectPresenter(instance, presenterProvider.get());
    injectQuranFileUtils(instance, quranFileUtilsProvider.get());
  }

  public static void injectPresenter(
      TranslationManagerActivity instance, TranslationManagerPresenter presenter) {
    instance.presenter = presenter;
  }

  public static void injectQuranFileUtils(
      TranslationManagerActivity instance, QuranFileUtils quranFileUtils) {
    instance.quranFileUtils = quranFileUtils;
  }
}
