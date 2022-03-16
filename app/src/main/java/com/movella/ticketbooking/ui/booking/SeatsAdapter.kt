package com.movella.ticketbooking.ui.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movella.ticketbooking.R
import com.movella.ticketbooking.data.local.entity.Seat
import com.movella.ticketbooking.databinding.ItemSeatBinding

class SeatAdapter(private val dataSet: List<Seat>, private val iSeatListener: ISeatListener) :
    RecyclerView.Adapter<SeatAdapter.ViewHolder>() {

    class ViewHolder(private val view: ItemSeatBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Seat) {
            view.tvSeat.text = item.id
            when (item.seatStatus) {
                "0" -> view.tvSeat.setBackgroundResource(R.color.orange)
                "1" -> view.tvSeat.setBackgroundResource(R.color.green)
                "2" -> view.tvSeat.setBackgroundResource(R.color.blue)
                else -> view.tvSeat.setBackgroundResource(R.color.green)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSeatBinding =
            ItemSeatBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dataModel: Seat = dataSet[position]
        viewHolder.bind(dataModel)
        viewHolder.itemView.setOnClickListener {
            iSeatListener.onClickSeat(dataSet[position], position)
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

interface ISeatListener {
    fun onClickSeat(seat: Seat, position: Int)
}