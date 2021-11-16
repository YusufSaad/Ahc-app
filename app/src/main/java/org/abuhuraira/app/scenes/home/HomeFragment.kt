package org.abuhuraira.app.scenes.home

import android.content.Context
import android.os.Bundle
import android.view.*
import com.example.ahcstores.sources.dependencies.HasDependencies
import org.abuhuraira.app.R
import org.abuhuraira.app.common.activities.BaseFragment
import org.abuhuraira.app.common.controls.PlayRatingDialog
import org.abuhuraira.app.scenes.home.common.HomeBusinessLogic
import org.abuhuraira.app.scenes.home.common.HomeDisplayable
import org.abuhuraira.app.scenes.home.common.HomeModels
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.view_home_widget.view.*
import org.abuhuraira.app.scenes.home.controls.*
import java.lang.ref.WeakReference

class HomeFragment : BaseFragment(), HomeDisplayable, HasDependencies, EventDelegate, EventDataDelegate {

    val interactor: HomeBusinessLogic by lazy {
        HomeInteractor(
                presenter = HomePresenter(
                        fragment = WeakReference(this)
                ),
                prayerTimesWorker = dependencies.resolvePrayerTimesWorker(),
                eventsWorker = dependencies.resolveEventsWorker()
        )
    }

    private val router: HomeRouter by lazy {
        HomeRouter(
                fragment = WeakReference(this)
        )
    }

    private val prayerTimeDataViewAdapter by lazy {
        PrayerTimeDataViewAdapter()
    }

    private val eventDataViewAdapter by lazy {
        EventDataViewAdapter(delegate = WeakReference(this))
    }

    val playRatingDialog by lazy {
        PlayRatingDialog(this.activity as Context)
    }

    private val eventDialogFragment by lazy {
        EventDialogFragment.newInstance(delegate = WeakReference(this))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configure()
        loadListeners()
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
        setTitle(getString(R.string.main_title))

        prayerTimesWidgetView.adapter = prayerTimeDataViewAdapter
        eventsWidgetView.adapter = eventDataViewAdapter
    }

    private fun loadData() {
        interactor.fetchPrayerTimes(
                HomeModels.PrayerTimesRequest()
        )

        interactor.fetchEvents(
                HomeModels.EventsRequest()
        )
    }

    private fun loadUI() {
        // TODO: Configure UI
    }

    private fun loadListeners() {
        // TODO: Configure button listeners
    }

    override fun displayFetchedPrayerTimes(viewModel: HomeModels.PrayerTimesViewModel) {
        prayerTimesWidgetView.subTitleTextView.text = viewModel.currentDate
        prayerTimeDataViewAdapter.reloadData(viewModel.prayerTimes)
    }

    override fun displayFetchedEvents(viewModel: HomeModels.EventsViewModel) {
        eventDataViewAdapter.reloadData(viewModels = viewModel.events)
    }

    override fun onClick(viewModel: HomeModels.EventViewModel) {
        eventDialogFragment.viewModel = viewModel
        eventDialogFragment.show(childFragmentManager,
                EventDialogFragment::class.java.simpleName)
    }

    override fun viewEventDetails(url: String?) {
        if (url != null) router.present(url)
    }
}