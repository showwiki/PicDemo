package utils

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.picdemo.viewmodel.BaseViewModel
import kotlinx.coroutines.Deferred

/**
 * 获取viewModel
 */
fun <T : BaseViewModel> getViewModel(@NonNull owner: ViewModelStoreOwner, @NonNull modelClass: Class<T>): T {
    return ViewModelProvider(owner).get(modelClass)
}

//统一处理协程中async 协程遇到的异常
suspend fun <T> Deferred<T>.awaitEx(handle: HandleError = object : HandleError {
    override fun onError(e: Throwable) {
        //统一处理异常

    }

}): T? {
    return try {
        this.await()
    } catch (e: Throwable) {
        handle.onError(e)
        null
    }
}

interface HandleError {
    fun onError(e: Throwable)
}


fun List<*>?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}
