package com.app.androidtesting.roomDB

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.androidtesting.databinding.UsersListViewBinding
import com.app.androidtesting.roomDB.userlistVM.UserListAdapter
import com.app.androidtesting.roomDB.userlistVM.UserProfileDetailsData
import com.app.androidtesting.roomDB.userlistVM.UserListViewModel
import kotlinx.coroutines.launch

class UserListActivity : AppCompatActivity() {

    lateinit var binding: UsersListViewBinding
    val viewModel : UserListViewModel by viewModels()

    var userList = mutableListOf<UserProfileDetailsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UsersListViewBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        binding.toolbarTitle.text = "Users List Page"


        val recyclerViewNews = binding.userListRecyclerView
       // val largeNews = DummyData.getDummyData(this)
        val userListAdapter = UserListAdapter(userList)

        recyclerViewNews.adapter = userListAdapter
        recyclerViewNews.layoutManager = LinearLayoutManager(this)
        recyclerViewNews.setHasFixedSize(true)


        val appDatabase = AppDatabase.getInstance(applicationContext)
        val userDao = appDatabase.userDao()

        viewModel.userListData.observe(this) {
           // userList.addAll(it)

        }

        lifecycleScope.launch {
            var userListx = userDao.getAllUsersList()
            userListx.forEach {
                userList.add(it)
            }
           // userList = userListx
            userListAdapter.notifyDataSetChanged()
        }

        viewModel.userListData.observe(this) { it ->
            lifecycleScope.launch {
                it.forEach { v->
                    userDao.insertUser(v)
                }
            }
        }



        viewModel.loadUserListData()

    }



}