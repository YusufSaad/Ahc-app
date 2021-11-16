// Generated by Dagger (https://google.github.io/dagger).
package com.quran.labs.androidquran.model.quran;

import com.quran.labs.androidquran.data.AyahInfoDatabaseProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CoordinatesModel_Factory implements Factory<CoordinatesModel> {
  private final Provider<AyahInfoDatabaseProvider> ayahInfoDatabaseProvider;

  public CoordinatesModel_Factory(Provider<AyahInfoDatabaseProvider> ayahInfoDatabaseProvider) {
    this.ayahInfoDatabaseProvider = ayahInfoDatabaseProvider;
  }

  @Override
  public CoordinatesModel get() {
    return new CoordinatesModel(ayahInfoDatabaseProvider.get());
  }

  public static CoordinatesModel_Factory create(
      Provider<AyahInfoDatabaseProvider> ayahInfoDatabaseProvider) {
    return new CoordinatesModel_Factory(ayahInfoDatabaseProvider);
  }

  public static CoordinatesModel newCoordinatesModel(
      AyahInfoDatabaseProvider ayahInfoDatabaseProvider) {
    return new CoordinatesModel(ayahInfoDatabaseProvider);
  }
}
