package com.example.androidhomework.location

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.androidhomework.R
import com.example.androidhomework.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_common.*
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class LocationDelegate(
    private val onItemClicked: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<Location.GotLocation, Location, LocationDelegate.LocationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): LocationHolder {
        return LocationHolder(
            parent.inflate(R.layout.item_common),
            onItemClicked
        )
    }

    override fun isForViewType(
        item: Location,
        items: MutableList<Location>,
        position: Int
    ): Boolean {
        return item is Location.GotLocation
    }

    override fun onBindViewHolder(
        item: Location.GotLocation,
        holder: LocationHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class LocationHolder(
        override val containerView: View,
        onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private val formatter = org.threeten.bp.format.DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")
            .withZone(org.threeten.bp.ZoneId.systemDefault())

        init {
            containerView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind(location: Location.GotLocation) {
            common_animalName.text = """
                Lat = ${location.lat}
                Lng = ${location.lng}
                Alt = ${location.alt}
                Speed = ${location.speed}
            """.trimIndent()

            common_animalType.text = formatter.format(location.createdAt)
            Glide.with(itemView)
                .load("https://cdn.pixabay.com/photo/2017/08/17/13/40/logo-2651379_960_720.png")
                .error(R.drawable.ic_sentiment)
                .placeholder(R.drawable.ic_location_on)
                .into(common_imageView)


        }
    }
}

