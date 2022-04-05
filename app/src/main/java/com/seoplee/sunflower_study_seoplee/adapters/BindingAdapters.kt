package com.seoplee.sunflower_study_seoplee.adapters

import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.seoplee.sunflower_study_seoplee.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .thumbnail(0.1f)
            .into(view)
    }
}

@BindingAdapter("calendarToString")
fun bindCalendarToString(view: TextView, date: Calendar) {
    val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    view.text = dateFormat.format(date.time)
}

@BindingAdapter("wateringText")
fun bindWateringText(view: TextView, wateringInterval: Int) {
    val resources = view.context.resources
    val quantityString = resources.getQuantityString(
        R.plurals.watering_needs_suffix,
        wateringInterval,
        wateringInterval
    )
    view.text = quantityString
}


@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}
