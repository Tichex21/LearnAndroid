package com.sachinshah.practical

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachinshah.practical.adapter.DbUserAdapter
import com.sachinshah.practical.adapter.QuotesAdapter2
import com.sachinshah.practical.databinding.ActivityDatabaseCrudBinding
import com.sachinshah.practical.databinding.ActivityListBinding
import com.sachinshah.practical.model.ResultModel
import com.sachinshah.practical.room.entity.UserModel
import com.sachinshah.practical.viewmodels.DbCrudViewModel
import com.sachinshah.practical.viewmodels.QuotesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DatabaseCrudActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityDatabaseCrudBinding

    val dbCrudViewModel: DbCrudViewModel by viewModels()
    lateinit var dbUserAdapter: DbUserAdapter

    var isUpdate: Boolean = false
    var userModel : UserModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_database_crud)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dbUserAdapter = DbUserAdapter(
            update = { userModel, i ->
                isUpdate = true
                this.userModel=userModel
                mBinding.btnAdd.text="Update"
                mBinding.edUserName.setText(userModel.firstName)
            },
            delete = { userModel, i ->
                dbCrudViewModel.deleteUser(userModel)
            }
        )
        with(mBinding.recyclerView) {
            layoutManager =
                LinearLayoutManager(this@DatabaseCrudActivity, LinearLayoutManager.VERTICAL, false)
            adapter = dbUserAdapter
        }
        setupObservers()

        mBinding.btnAdd.setOnClickListener {
            if (isUpdate) {

                userModel?.copy(firstName =   mBinding.edUserName.text.toString())?.let { it1 ->
                    dbCrudViewModel.updateUser(
                        it1
                    )
                }
                isUpdate=false
                mBinding.edUserName.setText("")
                mBinding.btnAdd.text="Add"
            } else {
                dbCrudViewModel.addUser(
                    UserModel(
                        firstName = mBinding.edUserName.text.toString(),
                        lastName = "Default"
                    )
                )
            }
        }
    }

    fun setupObservers() {
        dbCrudViewModel.getAllUserLiveData.observe(this@DatabaseCrudActivity, Observer {
            dbUserAdapter.submitList(it)

        })
    }

}