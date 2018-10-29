package com.devtee.weather.service.resource

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.PluralsRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue

interface ResourceService {
    var context: Context
    fun getString(resId: Int): String
    fun getString(resId: Int, vararg formatArgs: Any): String
    fun getQuantityString(@PluralsRes resId: Int, quantity: Int): String
    fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any): String
    fun getColor(@ColorRes resId: Int): Int
    fun getDimensionPixelOffset(@DimenRes resId: Int): Int
    fun gerAttrDimensionPixelSize(resId: Int): Int
}

class ResourceServiceImpl(override var context: Context) : ResourceService {
    override fun getString(@StringRes resId: Int) = context.getString(resId) ?: ""
    override fun getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, *formatArgs)
        ?: ""

    override fun getQuantityString(@PluralsRes resId: Int, quantity: Int) =
        context.resources.getQuantityString(resId, quantity)
            ?: ""

    override fun getQuantityString(@PluralsRes resId: Int, quantity: Int, vararg formatArgs: Any) =
        context.resources.getQuantityString(resId, quantity, *formatArgs)
            ?: ""

    override fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(context, resId)
    override fun getDimensionPixelOffset(@DimenRes resId: Int) = context.resources.getDimensionPixelOffset(resId)

    override fun gerAttrDimensionPixelSize(resId: Int): Int {
        val tv = TypedValue()
        if (context.theme.resolveAttribute(resId, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return 0
    }
}