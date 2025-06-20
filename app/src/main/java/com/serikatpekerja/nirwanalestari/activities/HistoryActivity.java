package com.serikatpekerja.nirwanalestari.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.serikatpekerja.nirwanalestari.R;
import com.serikatpekerja.nirwanalestari.database.DatabaseHelper;
import com.serikatpekerja.nirwanalestari.models.Laporan;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ListView listHistory;
    DatabaseHelper dbHelper;
    List<Laporan> laporanList;
    ArrayList<String> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listHistory = findViewById(R.id.listHistory);
        dbHelper = new DatabaseHelper(this);

        laporanList = dbHelper.getAllLaporan();
        displayList = new ArrayList<>();

        for (Laporan laporan : laporanList) {
            displayList.add("NAMA: " + laporan.getNama() +
                    "\nNIK: " + laporan.getNik() +
                    "\nDEPT: " + laporan.getDepartemen() +
                    "\nADUAN: " + laporan.getIsi());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listHistory.setAdapter(adapter);
    }
}
