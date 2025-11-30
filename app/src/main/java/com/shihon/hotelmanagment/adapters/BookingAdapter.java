package com.shihon.hotelmanagment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.database.BookingEntity;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> implements Filterable {

    private List<BookingEntity> bookingList;
    private List<BookingEntity> bookingListFull; // copy for filtering

    public BookingAdapter(List<BookingEntity> bookings) {
        bookingList = bookings;
        bookingListFull = new ArrayList<>(bookings);
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingEntity booking = bookingList.get(position);
        holder.tvGuest.setText("Guest: " + booking.guestName);
        holder.tvRoomNumber.setText("Room: " + booking.roomNumber);
        holder.tvCheckIn.setText("Check In: " + booking.checkInDate);
        holder.tvCheckOut.setText("Check Out: " + booking.checkOutDate);
        holder.tvTotalPrice.setText("Total: $" + booking.totalPrice);
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    @Override
    public Filter getFilter() {
        return bookingFilter;
    }

    private Filter bookingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<BookingEntity> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(bookingListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (BookingEntity item : bookingListFull) {
                    if (item.guestName.toLowerCase().contains(filterPattern) ||
                            item.roomNumber.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            bookingList.clear();
            bookingList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class BookingViewHolder extends RecyclerView.ViewHolder {
        TextView tvGuest, tvRoomNumber, tvCheckIn, tvCheckOut, tvTotalPrice;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGuest = itemView.findViewById(R.id.tvGuest);
            tvRoomNumber = itemView.findViewById(R.id.tvRoomNumber);
            tvCheckIn = itemView.findViewById(R.id.tvCheckIn);
            tvCheckOut = itemView.findViewById(R.id.tvCheckOut);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
        }
    }
}
