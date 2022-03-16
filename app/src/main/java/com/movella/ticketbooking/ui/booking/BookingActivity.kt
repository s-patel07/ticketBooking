package com.movella.ticketbooking.ui.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import com.movella.ticketbooking.R
import com.movella.ticketbooking.data.local.entity.Seat
import com.movella.ticketbooking.databinding.ActivityBookingBinding
import com.movella.ticketbooking.di.component.ActivityComponent
import com.movella.ticketbooking.ui.base.BaseActivity
import com.movella.ticketbooking.util.common.Constants
import java.util.*

class BookingActivity : BaseActivity<BookingViewModel>(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener, ISeatListener {

    private lateinit var adapter: SeatAdapter

    private var day = 0
    private var month: Int = 0
    private var year: Int = 0
    private var hour: Int = 0
    private var minute: Int = 0
    private var myDay = 0
    private var myMonth: Int = 0
    private var myYear: Int = 0
    private var myHour: Int = 0
    private var myMinute: Int = 0

    private lateinit var tvDate: AppCompatTextView
    private lateinit var tvTime: AppCompatTextView
    private lateinit var etReminder: TextInputEditText


    lateinit var binding: ActivityBookingBinding

    override fun provideLayoutId(): View {
        binding = ActivityBookingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewModel.insertBusSeats()
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.seatData.observe(this, {
            adapter = SeatAdapter(it, this)
            binding.recycler.layoutManager = GridLayoutManager(this, 2)
            binding.recycler.adapter = adapter
        })
    }

    private fun showBookingDetails(seat: Seat, position: Int) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_booking, null)
        tvDate = view.findViewById(R.id.tv_date)
        tvTime = view.findViewById(R.id.tv_time)
        etReminder = view.findViewById(R.id.rt_remindHour)
        val btnConfirm = view.findViewById<Button>(R.id.btnConfirm)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        tvDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this, this, year, month, day)
            datePickerDialog.show()
        }

        btnConfirm.setOnClickListener {
            seat.let {
                if (it.seatStatus == Constants.AVAIL.SEAT_AVAILABLE.value) {
                    it.seatStatus = Constants.AVAIL.SEAT_NOT_AVAILABLE.value
                    it.dateOfBooking = "$myYear/$myMonth/$myDay"
                    it.timeOfBooking = "$myHour:$myMinute"
                    it.remindBefore = etReminder.text.toString()

                    // updating seat status
                    viewModel.updateBusSeat(seat)
                    adapter.notifyItemChanged(position)
                }

                // Creating calendar instance for notification
                val calendar = Calendar.getInstance().apply {
                    set(myYear, myMonth, myDay, myHour, myMinute - it.remindBefore.toInt(), 0)
                }
                viewModel.setReminder(it, calendar)
            }
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            seat.let {
                if (it.seatStatus == "2") {
                    it.seatStatus = "1"
                    viewModel.updateBusSeat(seat)
                    adapter.notifyItemChanged(position)
                }
            }
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)

        dialog.show()
    }


    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        myDay = day
        myYear = year
        myMonth = month
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(
            this, this, hour, minute,
            DateFormat.is24HourFormat(this)
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        myHour = hour
        myMinute = minute
        tvDate.text = "Booking Date : $myYear/$myMonth/$myDay"
        tvTime.text = "Booking Time $myHour:$myMinute"
    }

    override fun onClickSeat(seat: Seat, position: Int) {
        if (seat.seatStatus == Constants.AVAIL.SEAT_NOT_AVAILABLE.value) {
            Toast.makeText(this, getString(R.string.text_seat_not_available), Toast.LENGTH_SHORT)
                .show()
        } else {
            seat.let {
                if (it.seatStatus == Constants.AVAIL.SEAT_AVAILABLE.value) {
                    it.seatStatus = Constants.AVAIL.SEAT_SELECTED.value
                } else if (it.seatStatus == Constants.AVAIL.SEAT_SELECTED.value) {
                    it.seatStatus = Constants.AVAIL.SEAT_AVAILABLE.value
                }
                viewModel.updateBusSeat(seat)
                adapter.notifyItemChanged(position)
                showBookingDetails(seat, position)
            }
        }
    }


}