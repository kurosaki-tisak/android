package devtee.com.photos.common

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImageToImageView(view: ImageView, imageUrl: String?) {

    Picasso.with(view.context)
            .load(imageUrl)
            .into(view)
}

@BindingAdapter("show")
fun showOrGoneView(view: View, show: Boolean) {
    when (show) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}