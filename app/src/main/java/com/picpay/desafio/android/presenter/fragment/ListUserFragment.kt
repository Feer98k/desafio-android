package com.picpay.desafio.android.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presenter.adapter.UserListAdapter
import com.picpay.desafio.android.presenter.fragment.extensions.showError
import com.picpay.desafio.android.presenter.viewmodel.UserListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListUserFragment : Fragment() {


    private val viewModel: UserListViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val adapterRecyclerView by lazy {
        UserListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_user, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchUsers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingComponents(view)

    }


    private fun setListUser() {
        recyclerView.adapter = adapterRecyclerView
        if (adapterRecyclerView.usersList.isNotEmpty()) {
            whenUserListIsNotEmpty()
        } else {
            whenListUserIsEmpty()
        }
    }

    private fun whenListUserIsEmpty() {
        progressBar.visibility = View.VISIBLE
    }

    private fun whenUserListIsNotEmpty() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }


    private fun loadingComponents(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.user_list_progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(context)
        setListUser()
    }

    private fun searchUsers() {
        viewModel.getAllUsersModel().observe(this, Observer { resource ->
            resource.data?.let {
                adapterRecyclerView.refresh(it)
                setListUser()
            }
            resource.error?.let {
                showError()
            }
        })
    }
}
