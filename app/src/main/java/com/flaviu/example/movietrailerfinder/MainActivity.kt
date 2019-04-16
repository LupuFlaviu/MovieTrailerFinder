package com.flaviu.example.movietrailerfinder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onCancel
import com.flaviu.example.movietrailerfinder.databinding.ActivityMainBinding
import com.flaviu.example.movietrailerfinder.ui.main.model.MainViewModel
import com.flaviu.example.movietrailerfinder.utils.rx.Optional
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private val disposable = CompositeDisposable()
    private var retryButtonClicked = PublishSubject.create<Optional<Unit>>()

    private val destinationChangedListener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (destination.id == R.id.empty_dest) {
            showSelectionDialog()
        }
    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_title)
        navController = findNavController(R.id.fragment_container)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        disposable.add(
            viewModel.getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { error ->
                    Timber.e(error, error.localizedMessage)
                    showErrorDialog(error)
                }
                .repeatWhen { handler ->
                    handler.flatMap {
                        Timber.d("Retrying to retrieve movie list")
                        progressBar.visibility = View.VISIBLE
                        return@flatMap retryButtonClicked.hide()
                    }
                }
                .retryWhen { handler ->
                    handler.flatMap {
                        Timber.d("Retrying to retrieve movie list")
                        progressBar.visibility = View.VISIBLE
                        return@flatMap retryButtonClicked.hide()
                    }
                }
                .subscribe({
                    if (it.isSuccess) {
                        viewModel.movieList.postValue(it.getOrNull())
                        showSelectionDialog()
                    } else {
                        showErrorDialog(it.exceptionOrNull())
                    }
                }, { Timber.e(it, it.localizedMessage) })
        )
    }

    private fun showSelectionDialog() {
        binding.progressBar.visibility = View.GONE
        MaterialDialog(this).show {
            message(R.string.select_view_type)
            positiveButton(R.string.button_list, click = {
                navigate(R.id.action_empty_dest_to_movie_list_dest)
            })
            negativeButton(R.string.button_image, click = {
                navigate(R.id.action_empty_dest_to_gallery_dest)
            })
            onCancel { finish() }
        }
        navController.removeOnDestinationChangedListener(destinationChangedListener)
    }

    private fun showErrorDialog(error: Throwable?) {
        binding.progressBar.visibility = View.GONE
        MaterialDialog(this).show {
            message(
                text = if (error is UnknownHostException || error is SocketTimeoutException) {
                    resources.getString(R.string.error_connection)
                } else {
                    error!!.localizedMessage
                }
            )
            positiveButton(R.string.button_retry, click = {
                retryButtonClicked.onNext(Optional.create(null))
            })
            onCancel { finish() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    private fun navigate(actionId: Int) {
        binding.progressBar.visibility = View.GONE
        navController.apply {
            navigate(actionId)
            addOnDestinationChangedListener(destinationChangedListener)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
