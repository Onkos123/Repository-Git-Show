package br.keneitec.dio.apresentarrepositoriogithub.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import br.keneitec.dio.apresentarrepositoriogithub.R
import br.keneitec.dio.apresentarrepositoriogithub.core.createDialog
import br.keneitec.dio.apresentarrepositoriogithub.core.createProgressDialog
import br.keneitec.dio.apresentarrepositoriogithub.core.hideSoftKeyboard
import br.keneitec.dio.apresentarrepositoriogithub.databinding.ActivityMainBinding
import br.keneitec.dio.apresentarrepositoriogithub.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy{RepoListAdapter()}
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rvRepos.adapter = adapter



        viewModel.repos.observe(this){
            when(it){
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        binding.root.hideSoftKeyboard()
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}