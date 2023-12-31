package com.app.androidtesting.roomDB.userlistVM

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel: ViewModel() {

    val _userListData =  MutableLiveData<List<UserProfileDetailsData>>()
    val userListData : MutableLiveData<List<UserProfileDetailsData>> get() = _userListData

    fun loadUserListData(){
        _userListData.value = mutableListOf(
                UserProfileDetailsData(tvName = "George Bluth", tvEmailID = "george.bluth@reqres.in", tvImageURl = "https://reqres.in/img/faces/1-image.jpg", profile = "Android Developer", yexp = "10"),
                UserProfileDetailsData(tvName ="Janet Weaver", tvEmailID ="janet.weaver@reqres.in", tvImageURl ="https://reqres.in/img/faces/2-image.jpg", profile ="IOS Developer", yexp ="9"),
                UserProfileDetailsData(tvName ="Emma Wong", tvEmailID ="emma.wong@reqres.in", tvImageURl ="https://reqres.in/img/faces/3-image.jpg", profile ="MERN Developer", yexp ="12"),
                UserProfileDetailsData(tvName ="Eve Holt", tvEmailID ="eve.holt@reqres.in", tvImageURl ="https://reqres.in/img/faces/4-image.jpg", profile ="DevOps", yexp ="5"),
                UserProfileDetailsData(tvName ="Tracey Ramos", tvEmailID ="tracey.ramos@reqres.in", tvImageURl ="https://reqres.in/img/faces/6-image.jpg", profile ="AWS Solution Arch.", yexp ="5"),
                UserProfileDetailsData(tvName ="Lindsay Ferguson", tvEmailID ="lindsay.ferguson@reqres.in",tvImageURl = "https://reqres.in/img/faces/8-image.jpg", profile ="TA / TLA", yexp ="15")
        )
    }
}