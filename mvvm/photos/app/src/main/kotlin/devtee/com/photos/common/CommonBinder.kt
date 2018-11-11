package devtee.com.photos.common

import android.arch.lifecycle.LifecycleOwner

interface CommonBinder {
    fun bindTo(owner: LifecycleOwner)
}