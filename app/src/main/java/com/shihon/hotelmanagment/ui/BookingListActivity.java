package com.shihon.hotelmanagment.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shihon.hotelmanagment.R;
import com.shihon.hotelmanagment.adapters.BookingAdapter;
import com.shihon.hotelmanagment.database.AppDatabase;
import com.shihon.hotelmanagment.database.BookingEntity;

import java.util.List;

public class BookingListActivity extends AppCompatActivity {

    RecyclerView rvBookings;
    BookingAdapter adapter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        rvBookings = findViewById(R.id.rvBookings);
        rvBookings.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        List<BookingEntity> bookings = db.bookingDao().getAllBookings();
        adapter = new BookingAdapter(bookings);
        rvBookings.setAdapter(adapter);



        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s); // Adapter implements Filterable
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

    }


}
