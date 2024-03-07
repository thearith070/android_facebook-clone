package thearithtech.me.facebook_clone

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun setImage(url: String, iv: ImageView) {
    if (url.isEmpty()) return
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.img_placeholder)
        .into(iv)
}