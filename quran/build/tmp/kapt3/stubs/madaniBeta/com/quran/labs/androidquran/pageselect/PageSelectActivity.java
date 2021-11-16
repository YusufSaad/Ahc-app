package com.quran.labs.androidquran.pageselect;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0014J\b\u0010\u001c\u001a\u00020\u0014H\u0014J\u0014\u0010\u001d\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/quran/labs/androidquran/pageselect/PageSelectActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "adapter", "Lcom/quran/labs/androidquran/pageselect/PageSelectAdapter;", "presenter", "Lcom/quran/labs/androidquran/pageselect/PageSelectPresenter;", "getPresenter", "()Lcom/quran/labs/androidquran/pageselect/PageSelectPresenter;", "setPresenter", "(Lcom/quran/labs/androidquran/pageselect/PageSelectPresenter;)V", "quranSettings", "Lcom/quran/labs/androidquran/util/QuranSettings;", "getQuranSettings", "()Lcom/quran/labs/androidquran/util/QuranSettings;", "setQuranSettings", "(Lcom/quran/labs/androidquran/util/QuranSettings;)V", "viewPager", "Landroid/support/v4/view/ViewPager;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPageTypeSelected", "type", "", "onPause", "onResume", "onUpdatedData", "data", "", "Lcom/quran/labs/androidquran/pageselect/PageTypeItem;", "app_madaniBeta"})
public final class PageSelectActivity extends android.support.v7.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.quran.labs.androidquran.pageselect.PageSelectPresenter presenter;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.quran.labs.androidquran.util.QuranSettings quranSettings;
    private com.quran.labs.androidquran.pageselect.PageSelectAdapter adapter;
    private android.support.v4.view.ViewPager viewPager;
    
    @org.jetbrains.annotations.NotNull()
    public final com.quran.labs.androidquran.pageselect.PageSelectPresenter getPresenter() {
        return null;
    }
    
    public final void setPresenter(@org.jetbrains.annotations.NotNull()
    com.quran.labs.androidquran.pageselect.PageSelectPresenter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.quran.labs.androidquran.util.QuranSettings getQuranSettings() {
        return null;
    }
    
    public final void setQuranSettings(@org.jetbrains.annotations.NotNull()
    com.quran.labs.androidquran.util.QuranSettings p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    public final void onUpdatedData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.quran.labs.androidquran.pageselect.PageTypeItem> data) {
    }
    
    private final void onPageTypeSelected(java.lang.String type) {
    }
    
    public PageSelectActivity() {
        super();
    }
}