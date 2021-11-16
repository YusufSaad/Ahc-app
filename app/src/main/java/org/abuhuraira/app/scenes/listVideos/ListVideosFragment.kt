package org.abuhuraira.app.scenes.listVideos

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.ahcstores.sources.dependencies.HasDependencies
import org.abuhuraira.app.R
import org.abuhuraira.app.common.activities.BaseFragment
import org.abuhuraira.app.common.dataAdapters.VideoDataModel
import org.abuhuraira.app.scenes.listVideos.common.VideosDataViewDelegate
import org.abuhuraira.app.scenes.listVideos.common.ListVideosBusinessLogic
import org.abuhuraira.app.scenes.listVideos.common.ListVideosDisplayable
import org.abuhuraira.app.scenes.listVideos.common.ListVideosModels
import org.abuhuraira.app.scenes.listVideos.controls.ListVideosAdapter
import kotlinx.android.synthetic.main.fragment_list_videos.*
import org.abuhuraira.app.common.controls.SearchAnimationToolbar
import org.abuhuraira.app.common.extensions.closeKeyboard
import java.lang.ref.WeakReference

class ListVideosFragment : BaseFragment(), ListVideosDisplayable, HasDependencies, VideosDataViewDelegate {

    val interactor: ListVideosBusinessLogic by lazy {
        ListVideosInteractor(
                presenter = ListVideosPresenter(
                        fragment = WeakReference(this)
                ),
                videosWorker = dependencies.resolveVideosWorker()
        )
    }

    private val router: ListVideosRouter by lazy {
        ListVideosRouter(
                fragment = WeakReference(this)
        )
    }


    private val listVideosAdapter by lazy {
        ListVideosAdapter(delegate = WeakReference(this))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configure()
        loadListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
    }

    override fun onResume() {
        super.onResume()
        loadData()
        loadUI()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        loadData()
        loadUI()
    }

    private fun configure() {
        setHasOptionsMenu(true)
        setTitle(getString(R.string.list_videos_title))
        setHasOptionsMenu(true)

        searchAnimationToolbar.setSearchTextColor(R.color.greyish_brown)
        searchAnimationToolbar.setSearchHintColor(R.color.greyish)

        recyclerView.adapter = listVideosAdapter
    }

    private fun loadData() {
        showSpinner(message = getString(R.string.loading))
        interactor.fetchVideos(
                ListVideosModels.Request()
        )
    }

    private fun loadUI() {
        // TODO: Configure UI
    }

    private fun loadListeners() {
        searchAnimationToolbar.setOnSearchQueryChangedListener(object: SearchAnimationToolbar.OnSearchQueryChangedListener{
            override fun onSearchCollapsed() {

            }

            override fun onSearchQueryChanged(query: String) {
                interactor.searchVideos(
                        request = ListVideosModels.SearchRequest(
                                query = query
                        )
                )
            }

            override fun onSearchExpanded() {

            }

            override fun onSearchSubmitted(query: String) {
                interactor.searchVideos(
                        request = ListVideosModels.SearchRequest(
                                query = query
                        )
                )

                activity?.closeKeyboard()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item?.itemId


        if (id == R.id.action_search) {
            searchAnimationToolbar.onSearchIconClick()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun displayFetchedVideos(viewModel: ListVideosModels.ViewModel) {
        hideSpinner()
        listVideosAdapter.reloadData(viewModels = viewModel.videos)
    }

    override fun videosDidSelect(model: VideoDataModel.ViewModel, adapter: RecyclerView.Adapter<*>) {
        router.showVideo(model.id)
    }

}