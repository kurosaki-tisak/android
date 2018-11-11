package devtee.com.photos.services

import android.content.Context

interface ResourceService {
    var context: Context
}

class ResourceServiceImpl(override var context: Context) : ResourceService