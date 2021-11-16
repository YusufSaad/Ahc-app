package org.abuhuraira.app.scenes.listCharities

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahcstores.sources.dependencies.HasDependencies
import org.abuhuraira.app.R
import org.abuhuraira.app.common.activities.BaseFragment
import org.abuhuraira.app.common.dataAdapters.CharityDataModel
import org.abuhuraira.app.scenes.listCharities.common.CharitiesDataViewDelegate
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesBusinessLogic
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesDisplayable
import org.abuhuraira.app.scenes.listCharities.common.ListCharitiesModels
import org.abuhuraira.app.scenes.listCharities.controls.ListCharitiesAdapter
import kotlinx.android.synthetic.main.fragment_list_charities.*
import java.lang.ref.WeakReference

class ListCharitiesFragment : BaseFragment(), ListCharitiesDisplayable, HasDependencies, CharitiesDataViewDelegate {

    val interactor: ListCharitiesBusinessLogic by lazy {
        ListCharitiesInteractor(
                presenter = ListCharitiesPresenter(
                        fragment = WeakReference(this)
                ),
                charitiesWorker = dependencies.resolveCharitiesWorker()
        )
    }

    private val router: ListCharitiesRouter by lazy {
        ListCharitiesRouter(
                fragment = WeakReference(this)
        )
    }


    private val listCharitiesAdapter by lazy {
        ListCharitiesAdapter(delegate = WeakReference(this))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_charities, container, false)
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
        setTitle(getString(R.string.list_charities_title))

        recyclerView.adapter = listCharitiesAdapter
    }

    private fun loadData() {
        showSpinner(message = getString(R.string.loading))
        interactor.fetchCharities(
                ListCharitiesModels.Request()
        )
    }

    private fun loadUI() {
        // TODO: Configure UI
    }

    private fun loadListeners() {
        // TODO: Configure button listeners
    }

    override fun displayFetchedCharities(viewModel: ListCharitiesModels.ViewModel) {
        hideSpinner()
        listCharitiesAdapter.reloadData(viewModels = viewModel.charities)
    }

    override fun servicesDidSelect(model: CharityDataModel.ViewModel, adapter: RecyclerView.Adapter<*>) {
        router.present(model.donationURL)
    }

}