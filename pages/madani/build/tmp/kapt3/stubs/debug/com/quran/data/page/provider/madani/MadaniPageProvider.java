package com.quran.data.page.provider.madani;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/quran/data/page/provider/madani/MadaniPageProvider;", "Lcom/quran/data/source/PageProvider;", "()V", "baseUrl", "", "dataSource", "Lcom/quran/data/pageinfo/common/MadaniDataSource;", "getAudioDatabasesBaseUrl", "getAudioDirectoryName", "getAyahInfoBaseUrl", "getAyahInfoDirectoryName", "getDataSource", "getDatabaseDirectoryName", "getDatabasesBaseUrl", "getImageVersion", "", "getImagesBaseUrl", "getImagesDirectoryName", "getImagesZipBaseUrl", "getPageSizeCalculator", "Lcom/quran/data/source/PageSizeCalculator;", "displaySize", "Lcom/quran/data/source/DisplaySize;", "getPatchBaseUrl", "getPreviewDescription", "getPreviewTitle", "madani_debug"})
public final class MadaniPageProvider implements com.quran.data.source.PageProvider {
    private final java.lang.String baseUrl = "https://android.quran.com/data";
    private final com.quran.data.pageinfo.common.MadaniDataSource dataSource = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.quran.data.pageinfo.common.MadaniDataSource getDataSource() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.quran.data.source.PageSizeCalculator getPageSizeCalculator(@org.jetbrains.annotations.NotNull()
    com.quran.data.source.DisplaySize displaySize) {
        return null;
    }
    
    @java.lang.Override()
    public int getImageVersion() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getImagesBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getImagesZipBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getPatchBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getAyahInfoBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getAudioDirectoryName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getDatabaseDirectoryName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getAyahInfoDirectoryName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getDatabasesBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getAudioDatabasesBaseUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String getImagesDirectoryName() {
        return null;
    }
    
    @java.lang.Override()
    public int getPreviewTitle() {
        return 0;
    }
    
    @java.lang.Override()
    public int getPreviewDescription() {
        return 0;
    }
    
    public MadaniPageProvider() {
        super();
    }
}