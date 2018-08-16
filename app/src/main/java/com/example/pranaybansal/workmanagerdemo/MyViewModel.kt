package com.example.pranaybansal.workmanagerdemo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkStatus

class MyViewModel : ViewModel() {
    var mWorkManger: WorkManager
    lateinit var mWorkStatus:LiveData<List<WorkStatus>>
    val NOTIFICATION_WORKER = "notification_worker"

    init {
        mWorkManger = WorkManager.getInstance()
        mWorkStatus = mWorkManger.getStatusesByTag(NOTIFICATION_WORKER)
    }

    fun startWork(inputString : String){

        //input data to pass to the worker class
        val data = Data.Builder()
                .putString("title","running the work manager")
                .putString("msg",inputString)
                .build()

        val request = OneTimeWorkRequest.Builder(InputWorker::class.java).setInputData(data).addTag(NOTIFICATION_WORKER).build();

        mWorkManger.enqueue(request)
    }
}