package com.dicoding.courseschedule.ui.add

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.data.DataRepository
import java.lang.reflect.InvocationTargetException

class AddCourseViewModelFactory (private val repository: DataRepository?): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(DataRepository::class.java).newInstance(repository)
            }catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        }


    companion object {
        fun AddFactory(activity:Activity): AddCourseViewModelFactory{
            val context = activity.applicationContext ?: throw IllegalArgumentException("Belum terpasang ke aplikasi")
            return AddCourseViewModelFactory(DataRepository.getInstance(context))
        }
    }
}