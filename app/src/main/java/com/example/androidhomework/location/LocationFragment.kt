package com.example.androidhomework.location

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomework.R
import com.google.android.gms.location.LocationServices
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import kotlinx.android.synthetic.main.list_fragment.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.random.Random

class LocationFragment : Fragment(R.layout.list_fragment) {

    private var locationsList = listOf<Location.GotLocation>()
    private var locationAdapter: LocationAdapter? = null
    private var datePickerDialog: Unit? = null
    private var timePickerDialog: Unit? = null

    private val KEY_LIST_SIZE = "KEY_LIST_SIZE"


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLIst()
        getLocationButton.setOnClickListener { showLocationInfo() }

        if (savedInstanceState != null) {
            locationsList = listOf()
            val state = savedInstanceState.getParcelableArray(KEY_LIST_SIZE)
            for (a in state!!.iterator()) {
                locationsList = locationsList + listOf(a as Location.GotLocation)
            }
        }
        locationAdapter?.items = locationsList
        checkIfListIsEmpty()

    }

    private fun initLIst() {
        locationAdapter = LocationAdapter { position -> changeLocationTime(position) }
        with(animalRecyclerView) {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = FlipInTopXAnimator()
            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun changeLocationTime(position: Int) {
        val currentDateTime = LocalDateTime.now()
        datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                timePickerDialog = TimePickerDialog(
                    requireContext(), { _, hour, minute ->

                        val zonedDateTime = LocalDateTime.of(year, month + 1, day, hour, minute)
                            .atZone(ZoneId.systemDefault())
                        locationsList[position].createdAt = zonedDateTime.toInstant()
                        locationAdapter?.notifyItemChanged(position)
                    },
                    currentDateTime.hour,
                    currentDateTime.minute, true
                ).show()
            },
            currentDateTime.year, currentDateTime.month.value - 1, currentDateTime.dayOfMonth
        ).show()
    }

    private fun showLocationInfo() {
        LocationServices.getFusedLocationProviderClient(requireContext())
            .lastLocation
            .addOnSuccessListener {
                it?.let {
                    val newLocation = Location.GotLocation(
                        Random.nextLong(),
                        it.latitude,
                        it.longitude,
                        it.altitude,
                        it.speed,
                        Instant.now()
                    )
                    locationsList = listOf(newLocation) + locationsList
                    checkIfListIsEmpty()
                    locationAdapter?.items = locationsList
                } ?: toast("Локация отсутствует")
            }
            .addOnCanceledListener { toast("Запрос был отменен") }
            .addOnFailureListener { toast("Не удалось получить локацию") }
    }

    private fun checkIfListIsEmpty() {
        if (locationsList.isEmpty()) {
            animalRecyclerView.isVisible = false
            emptyListTextView.isVisible = true
        } else {
            animalRecyclerView.isVisible = true
            emptyListTextView.isVisible = false
        }
    }

    fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArray(KEY_LIST_SIZE, locationsList.toTypedArray())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        locationAdapter = null
        datePickerDialog = null
        timePickerDialog = null
    }
}
