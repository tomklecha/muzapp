package com.tkdev.muzapp.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tkdev.muzapp.R
import com.tkdev.muzapp.domain.models.mocks.MockMessages
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("setVisibility")
fun setVisibility(view: View, flag: Boolean) {
    when (flag) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.GONE
    }
}

@BindingAdapter("setDate")
fun setDate(view: TextView, timestamp: String) {
    val dateFormat = DateTimeFormatter.ofPattern(MockMessages.DATE_FORMAT)
    val highlightDateTime = DateTimeFormatter.ofPattern(MockMessages.SHORT_DATE)
    val currentDate = LocalDateTime.parse(timestamp, dateFormat)
    view.text = currentDate.format(highlightDateTime)
}

@BindingAdapter("setProfilePicture")
fun setProfilePicture(image: ImageView, userId: String?) {
    // here would be mapping the picture based on userId
    val glide = Glide.with(image.context)
    glide.load(R.mipmap.ic_launcher_round).into(image)
}

@BindingAdapter("wasItRead")
fun wasMessageRead(image: ImageView, wasItRead: Boolean) {
    // here would be mapping the picture based on userId
    val glide = Glide.with(image.context)
    when (wasItRead) {
        true -> glide.load(R.drawable.ic_baseline_check_24).into(image)
        false -> glide.load(R.drawable.ic_baseline_schedule_24).into(image)
    }
}
