package org.abuhuraira.app.common.dataAdapters

sealed class CharityDataModel {
    class ViewModel(
            val content: String?,
            val title: String,
            val backgroundImage: String?,
            val donationURL: String): CharityDataModel()
}