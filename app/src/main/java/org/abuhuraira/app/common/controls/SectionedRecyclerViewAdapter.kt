package org.abuhuraira.app.common.controls

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by ahmedsaad on 2018-01-23.
 * Copyright © 2017. All rights reserved.
 */

abstract class SectionedRecyclerViewAdapter<SectionVH : RecyclerView.ViewHolder, ChildVH : RecyclerView.ViewHolder> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val LOWER_16BIT_MASK = 0x0000ffff
        private val SECTION_VIEW_HOLDER = 0x00010000
        private val CHILD_VIEW_HOLDER = 0x00020000
    }

    abstract val groupCount: Int
    abstract fun getChildCount(group: Int): Int

    abstract fun onCreateSectionViewHolder(parent: ViewGroup, viewType: Int): SectionVH
    abstract fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): ChildVH

    abstract fun onBindSectionViewHolder(holder: SectionVH, position: Int, payloads: List<Any>?)
    abstract fun onBindChildViewHolder(holder: ChildVH, belongingGroup: Int, position: Int, payloads: List<Any>?)

    fun getSectionViewHolderType(position: Int): Int {
        return 0
    }

    fun getChildViewHolderType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (isSectionViewType(viewType)) {
            onCreateSectionViewHolder(parent, viewType and SECTION_VIEW_HOLDER.inv())
        } else {
            onCreateChildViewHolder(parent, viewType and CHILD_VIEW_HOLDER.inv())
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        val itemViewType = holder.itemViewType
        if (isSectionViewType(itemViewType)) {
            onBindSectionViewHolder(holder as SectionVH, getSectionPositionFromAbsPosition(position), payloads)
        } else {
            val belongingGroup = getBelongingGroup(position)
            val childPosition = getChildPositionFromAbsPosition(position)
            onBindChildViewHolder(holder as ChildVH, belongingGroup, childPosition, payloads)
        }
    }

    private fun getBelongingGroup(position: Int): Int {
        var offset = 0
        val groupCount = groupCount

        for (i in 0 until groupCount) {
            val firstPosition = i + offset
            val lastPosition = firstPosition + getChildCount(i)

            if (position in (firstPosition + 1)..lastPosition) {
                return i
            }

            offset += getChildCount(i)
        }

        return RecyclerView.NO_POSITION
    }

    private fun getChildPositionFromAbsPosition(position: Int): Int {
        var offset = 0
        val groupCount = groupCount

        for (i in 0 until groupCount) {
            val firstPosition = i + offset
            val lastPosition = firstPosition + getChildCount(i)

            if (position in (firstPosition + 1)..lastPosition) {
                return position - firstPosition - 1
            }

            offset += getChildCount(i)
        }

        return RecyclerView.NO_POSITION
    }


    private fun getSectionPositionFromAbsPosition(position: Int): Int {

        var offset = 0
        val groupCount = groupCount

        for (i in 0 until groupCount) {
            if (position == i + offset) {
                return i
            }

            offset += getChildCount(i)
        }

        return RecyclerView.NO_POSITION
    }

    fun getFirstAbsPositionOf(group: Int): Int {
        var offset = 0
        val groupCount = groupCount

        for (i in 0 until groupCount) {
            if (group == i) {
                return i + offset
            }

            offset += getChildCount(i)
        }

        return RecyclerView.NO_POSITION
    }

    fun isSectionViewType(itemViewType: Int): Boolean {
        return itemViewType and SECTION_VIEW_HOLDER == SECTION_VIEW_HOLDER
    }

    override fun getItemCount(): Int {
        var totalChildItems = 0

        for (i in 0 until groupCount) {
            totalChildItems += getChildCount(i)
        }

        return totalChildItems + groupCount
    }


    protected fun isFirstInSection(group: Int, position: Int): Boolean {
        return position == 0
    }

    protected fun isLastInSection(group: Int, position: Int): Boolean {
        return getChildCount(group) == position + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (isSection(position)) {
            getSectionViewHolderType(position) and LOWER_16BIT_MASK or SECTION_VIEW_HOLDER
        } else getChildViewHolderType(position) and LOWER_16BIT_MASK or CHILD_VIEW_HOLDER

    }

    private fun isSection(position: Int): Boolean {
        var offset = 0
        val groupCount = groupCount

        for (i in 0 until groupCount) {
            if (position == i + offset) {
                return true
            }

            offset += getChildCount(i)
        }

        return false
    }
}