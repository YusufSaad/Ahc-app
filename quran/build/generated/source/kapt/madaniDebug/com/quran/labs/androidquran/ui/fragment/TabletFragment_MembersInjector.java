// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.ui.fragment;

import com.quran.labs.androidquran.data.QuranInfo;
import com.quran.labs.androidquran.presenter.quran.QuranPagePresenter;
import com.quran.labs.androidquran.presenter.quran.ayahtracker.AyahTrackerPresenter;
import com.quran.labs.androidquran.presenter.translation.TranslationPresenter;
import com.quran.labs.androidquran.ui.helpers.AyahSelectedListener;
import com.quran.labs.androidquran.util.QuranScreenInfo;
import com.quran.labs.androidquran.util.QuranSettings;
import com.quran.page.common.draw.ImageDrawHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import java.util.Set;
import javax.inject.Provider;

public final class TabletFragment_MembersInjector implements MembersInjector<TabletFragment> {
  private final Provider<QuranSettings> quranSettingsProvider;

  private final Provider<AyahTrackerPresenter> ayahTrackerPresenterProvider;

  private final Provider<QuranPagePresenter> quranPagePresenterProvider;

  private final Provider<TranslationPresenter> translationPresenterProvider;

  private final Provider<AyahSelectedListener> ayahSelectedListenerProvider;

  private final Provider<QuranScreenInfo> quranScreenInfoProvider;

  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<Set<ImageDrawHelper>> imageDrawHelpersProvider;

  public TabletFragment_MembersInjector(
      Provider<QuranSettings> quranSettingsProvider,
      Provider<AyahTrackerPresenter> ayahTrackerPresenterProvider,
      Provider<QuranPagePresenter> quranPagePresenterProvider,
      Provider<TranslationPresenter> translationPresenterProvider,
      Provider<AyahSelectedListener> ayahSelectedListenerProvider,
      Provider<QuranScreenInfo> quranScreenInfoProvider,
      Provider<QuranInfo> quranInfoProvider,
      Provider<Set<ImageDrawHelper>> imageDrawHelpersProvider) {
    this.quranSettingsProvider = quranSettingsProvider;
    this.ayahTrackerPresenterProvider = ayahTrackerPresenterProvider;
    this.quranPagePresenterProvider = quranPagePresenterProvider;
    this.translationPresenterProvider = translationPresenterProvider;
    this.ayahSelectedListenerProvider = ayahSelectedListenerProvider;
    this.quranScreenInfoProvider = quranScreenInfoProvider;
    this.quranInfoProvider = quranInfoProvider;
    this.imageDrawHelpersProvider = imageDrawHelpersProvider;
  }

  public static MembersInjector<TabletFragment> create(
      Provider<QuranSettings> quranSettingsProvider,
      Provider<AyahTrackerPresenter> ayahTrackerPresenterProvider,
      Provider<QuranPagePresenter> quranPagePresenterProvider,
      Provider<TranslationPresenter> translationPresenterProvider,
      Provider<AyahSelectedListener> ayahSelectedListenerProvider,
      Provider<QuranScreenInfo> quranScreenInfoProvider,
      Provider<QuranInfo> quranInfoProvider,
      Provider<Set<ImageDrawHelper>> imageDrawHelpersProvider) {
    return new TabletFragment_MembersInjector(
        quranSettingsProvider,
        ayahTrackerPresenterProvider,
        quranPagePresenterProvider,
        translationPresenterProvider,
        ayahSelectedListenerProvider,
        quranScreenInfoProvider,
        quranInfoProvider,
        imageDrawHelpersProvider);
  }

  @Override
  public void injectMembers(TabletFragment instance) {
    injectQuranSettings(instance, quranSettingsProvider.get());
    injectAyahTrackerPresenter(instance, ayahTrackerPresenterProvider.get());
    injectQuranPagePresenter(instance, DoubleCheck.lazy(quranPagePresenterProvider));
    injectTranslationPresenter(instance, DoubleCheck.lazy(translationPresenterProvider));
    injectAyahSelectedListener(instance, ayahSelectedListenerProvider.get());
    injectQuranScreenInfo(instance, quranScreenInfoProvider.get());
    injectQuranInfo(instance, quranInfoProvider.get());
    injectImageDrawHelpers(instance, imageDrawHelpersProvider.get());
  }

  public static void injectQuranSettings(TabletFragment instance, QuranSettings quranSettings) {
    instance.quranSettings = quranSettings;
  }

  public static void injectAyahTrackerPresenter(
      TabletFragment instance, AyahTrackerPresenter ayahTrackerPresenter) {
    instance.ayahTrackerPresenter = ayahTrackerPresenter;
  }

  public static void injectQuranPagePresenter(
      TabletFragment instance, Lazy<QuranPagePresenter> quranPagePresenter) {
    instance.quranPagePresenter = quranPagePresenter;
  }

  public static void injectTranslationPresenter(
      TabletFragment instance, Lazy<TranslationPresenter> translationPresenter) {
    instance.translationPresenter = translationPresenter;
  }

  public static void injectAyahSelectedListener(
      TabletFragment instance, AyahSelectedListener ayahSelectedListener) {
    instance.ayahSelectedListener = ayahSelectedListener;
  }

  public static void injectQuranScreenInfo(
      TabletFragment instance, QuranScreenInfo quranScreenInfo) {
    instance.quranScreenInfo = quranScreenInfo;
  }

  public static void injectQuranInfo(TabletFragment instance, QuranInfo quranInfo) {
    instance.quranInfo = quranInfo;
  }

  public static void injectImageDrawHelpers(
      TabletFragment instance, Set<ImageDrawHelper> imageDrawHelpers) {
    instance.imageDrawHelpers = imageDrawHelpers;
  }
}
