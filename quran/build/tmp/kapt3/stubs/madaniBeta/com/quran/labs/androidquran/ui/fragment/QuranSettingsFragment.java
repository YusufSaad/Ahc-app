package com.quran.labs.androidquran.ui.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0006H\u0016R4\u0010\u0004\u001a\u0018\u0012\t\u0012\u00070\u0006\u00a2\u0006\u0002\b\u0007\u0012\t\u0012\u00070\b\u00a2\u0006\u0002\b\u00070\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u001d"}, d2 = {"Lcom/quran/labs/androidquran/ui/fragment/QuranSettingsFragment;", "Landroid/preference/PreferenceFragment;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "()V", "pageTypes", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "Lcom/quran/data/source/PageProvider;", "getPageTypes", "()Ljava/util/Map;", "setPageTypes", "(Ljava/util/Map;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onPreferenceTreeClick", "", "preferenceScreen", "Landroid/preference/PreferenceScreen;", "preference", "Landroid/preference/Preference;", "onResume", "onSharedPreferenceChanged", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "app_madaniBeta"})
public final class QuranSettingsFragment extends android.preference.PreferenceFragment implements android.content.SharedPreferences.OnSharedPreferenceChangeListener {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public java.util.Map<java.lang.String, com.quran.data.source.PageProvider> pageTypes;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, com.quran.data.source.PageProvider> getPageTypes() {
        return null;
    }
    
    public final void setPageTypes(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.quran.data.source.PageProvider> p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onSharedPreferenceChanged(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences sharedPreferences, @org.jetbrains.annotations.NotNull()
    java.lang.String key) {
    }
    
    @java.lang.Override()
    public boolean onPreferenceTreeClick(@org.jetbrains.annotations.NotNull()
    android.preference.PreferenceScreen preferenceScreen, @org.jetbrains.annotations.NotNull()
    android.preference.Preference preference) {
        return false;
    }
    
    public QuranSettingsFragment() {
        super();
    }
}