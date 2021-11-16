package com.quran.labs.androidquran.pageselect;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u001c\u0010\t\u001a\u0018\u0012\t\u0012\u00070\u000b\u00a2\u0006\u0002\b\f\u0012\t\u0012\u00070\r\u00a2\u0006\u0002\b\f0\n\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0002H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0002X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\t\u001a\u0018\u0012\t\u0012\u00070\u000b\u00a2\u0006\u0002\b\f\u0012\t\u0012\u00070\r\u00a2\u0006\u0002\b\f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/quran/labs/androidquran/pageselect/PageSelectPresenter;", "Lcom/quran/labs/androidquran/presenter/Presenter;", "Lcom/quran/labs/androidquran/pageselect/PageSelectActivity;", "imageUtil", "Lcom/quran/labs/androidquran/util/ImageUtil;", "quranFileUtils", "Lcom/quran/labs/androidquran/util/QuranFileUtils;", "mainThreadScheduler", "Lio/reactivex/Scheduler;", "pageTypes", "", "", "Lkotlin/jvm/JvmSuppressWildcards;", "Lcom/quran/data/source/PageProvider;", "(Lcom/quran/labs/androidquran/util/ImageUtil;Lcom/quran/labs/androidquran/util/QuranFileUtils;Lio/reactivex/Scheduler;Ljava/util/Map;)V", "baseUrl", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "currentView", "downloadingSet", "", "bind", "", "what", "generateData", "unbind", "app_madaniDebug"})
@dagger.Reusable()
public final class PageSelectPresenter implements com.quran.labs.androidquran.presenter.Presenter<com.quran.labs.androidquran.pageselect.PageSelectActivity> {
    private final java.lang.String baseUrl = "https://android.quran.com/data/pagetypes";
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final java.util.Set<java.lang.String> downloadingSet = null;
    private com.quran.labs.androidquran.pageselect.PageSelectActivity currentView;
    private final com.quran.labs.androidquran.util.ImageUtil imageUtil = null;
    private final com.quran.labs.androidquran.util.QuranFileUtils quranFileUtils = null;
    private final io.reactivex.Scheduler mainThreadScheduler = null;
    private final java.util.Map<java.lang.String, com.quran.data.source.PageProvider> pageTypes = null;
    
    private final void generateData() {
    }
    
    @java.lang.Override()
    public void bind(@org.jetbrains.annotations.NotNull()
    com.quran.labs.androidquran.pageselect.PageSelectActivity what) {
    }
    
    @java.lang.Override()
    public void unbind(@org.jetbrains.annotations.Nullable()
    com.quran.labs.androidquran.pageselect.PageSelectActivity what) {
    }
    
    @javax.inject.Inject()
    public PageSelectPresenter(@org.jetbrains.annotations.NotNull()
    com.quran.labs.androidquran.util.ImageUtil imageUtil, @org.jetbrains.annotations.NotNull()
    com.quran.labs.androidquran.util.QuranFileUtils quranFileUtils, @org.jetbrains.annotations.NotNull()
    io.reactivex.Scheduler mainThreadScheduler, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, com.quran.data.source.PageProvider> pageTypes) {
        super();
    }
}