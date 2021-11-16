package org.abuhuraira.app.common.controls

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import kotlinx.android.synthetic.main.view_search_toolbar.view.*
import org.abuhuraira.app.R
import org.abuhuraira.app.common.extensions.DebouncedTextWatcher

class SearchAnimationToolbar(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
        FrameLayout(context, attrs, defStyleAttr), DebouncedTextWatcher {

    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    interface OnSearchQueryChangedListener {
        fun onSearchCollapsed()
        fun onSearchQueryChanged(query: String)
        fun onSearchExpanded()
        fun onSearchSubmitted(query: String)
    }

    init {
        inflateAndBindViews()
        bindAttributes(attrs)
    }


    private var txtSearch: EditText? = null
    private var searchMenuItem: MenuItem? = null
    private var onSearchQueryChangedListener: OnSearchQueryChangedListener? = null
    private var currentQuery = ""


    private fun bindAttributes(attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchAnimationToolbar)

        val toolbarTitle = typedArray.getString(R.styleable.SearchAnimationToolbar_title)
        val toolbarTitleColor = typedArray.getColor(R.styleable.SearchAnimationToolbar_titleTextColor, Color.WHITE)
        val toolbarSubtitle = typedArray.getString(R.styleable.SearchAnimationToolbar_subtitle)
        val toolbarSubtitleColor = typedArray.getColor(R.styleable.SearchAnimationToolbar_subtitleTextColor, Color.WHITE)
        val searchHint = typedArray.getString(R.styleable.SearchAnimationToolbar_searchHint)
        val searchBackgroundColor = typedArray.getColor(R.styleable.SearchAnimationToolbar_searchBackgroundColor, Color.WHITE)

        typedArray.recycle()

        toolbar.title = toolbarTitle
        toolbar.setTitleTextColor(toolbarTitleColor)

        if (!TextUtils.isEmpty(toolbarSubtitle)) {
            toolbar.subtitle = toolbarSubtitle
            toolbar.setSubtitleTextColor(toolbarSubtitleColor)
        }

        if (!TextUtils.isEmpty(searchHint)) {
            txtSearch?.hint = searchHint
        }

        searchToolbar.setBackgroundColor(searchBackgroundColor)

    }

    fun setSearchTextColor(color: Int) {
        txtSearch?.setTextColor(color)
    }

    fun setSearchHintColor(color: Int) {
        txtSearch?.setHintTextColor(color)
    }

    private fun inflateAndBindViews() {
        View.inflate(context, R.layout.view_search_toolbar, this)

        searchToolbar.inflateMenu(R.menu.menu_search_animation)
        searchToolbar.setNavigationOnClickListener { collapse() }

        searchMenuItem = searchToolbar.menu.findItem(R.id.action_search)

        searchMenuItem?.setOnActionExpandListener(object: MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                collapse()
                return true
            }
        })

        val searchView = searchMenuItem?.actionView as? SearchView

        // Enable/Disable Submit button in the keyboard
        searchView?.isSubmitButtonEnabled = false

        // Change search close button image
        val closeButton = searchView?.findViewById(R.id.search_close_btn) as? ImageView
        closeButton?.setImageResource(R.drawable.ic_clear_black_24dp)


        // set hint and the text colors
        txtSearch = searchView?.findViewById(android.support.v7.appcompat.R.id.search_src_text) as? EditText
        txtSearch?.addTextChangedListener(this)
        txtSearch?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                notifySearchSubmitted(txtSearch?.text.toString())
                true
            } else {
                false
            }
        }

    }

    override fun onDetachedFromWindow() {
        txtSearch?.removeTextChangedListener(this)
        super.onDetachedFromWindow()
    }

    fun setSupportActionBar(act: AppCompatActivity) {
        act.setSupportActionBar(toolbar)
    }

    fun onSearchIconClick(): Boolean {
        expand()
        searchMenuItem?.expandActionView()
        return true
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun circleReveal(isShow: Boolean) {

        var width = toolbar.width
        width -= (resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2)

        val cx = width
        val cy = toolbar.height / 2

        val anim = if (isShow)
             ViewAnimationUtils.createCircularReveal(searchToolbar, cx, cy, 0F, width.toFloat())
        else
             ViewAnimationUtils.createCircularReveal(searchToolbar, cx, cy, width.toFloat(), 0F)

        anim.duration = 250L

        // make the view invisible when the animation is done
        anim.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (!isShow) {
                    super.onAnimationEnd(animation)
                    searchToolbar.visibility = View.INVISIBLE
                }
            }
        })

        // make the view visible and start the animation
        if (isShow) {
            searchToolbar.visibility = View.VISIBLE
            notifySearchExpanded()
        } else {
            notifySearchCollapsed()
        }

        // start the animation
        anim.start()
    }

    fun setSearchHint(hint: String) {
        txtSearch?.hint = hint
    }

    fun onBackPressed(): Boolean {
        val isInSearchMode = searchToolbar.visibility == View.VISIBLE
        if (!isInSearchMode) {
            return false
        }

        collapse()
        searchMenuItem?.collapseActionView()
        return true
    }


    fun setTitle(title: String) {
        toolbar.title = title
        toolbar.invalidate()
    }

    fun setTitleTextColor(color: Int) {
        toolbar.setTitleTextColor(color)
    }

    fun getToolbar(): Toolbar {
        return toolbar
    }

    fun getSearchToolbar(): Toolbar {
        return searchToolbar
    }

    fun setOnSearchQueryChangedListener(onSearchQueryChangedListener: OnSearchQueryChangedListener) {
        this.onSearchQueryChangedListener = onSearchQueryChangedListener
    }

    private fun notifySearchCollapsed() {
        this.onSearchQueryChangedListener?.onSearchCollapsed()
    }

    private fun notifySearchExpanded() {
        this.onSearchQueryChangedListener?.onSearchExpanded()

    }

    private fun notifySearchQueryChanged(q: String) {
        this.onSearchQueryChangedListener?.onSearchQueryChanged(q)
    }

    private fun notifySearchSubmitted(q: String) {
        this.onSearchQueryChangedListener?.onSearchSubmitted(q)
    }

    override fun debouncedAfterTextChanged(s: Editable?) {
        super.debouncedAfterTextChanged(s)

        if (!currentQuery.equals(s.toString(), ignoreCase = true)) {
            currentQuery = s.toString()
            notifySearchQueryChanged(currentQuery)
        }
    }

    private fun collapse() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circleReveal(false)
        } else
            searchToolbar.visibility = View.GONE
    }

    private fun expand() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circleReveal(true)
        } else {
            searchToolbar.visibility = View.VISIBLE
        }
    }

    /**
     * Expands the toolbar (Without animation) and update the search with a given query
     */
    fun expandAndSearch(query: String) {
        searchToolbar.visibility = View.VISIBLE
        searchMenuItem?.expandActionView()
        txtSearch?.setText(query)
    }
}