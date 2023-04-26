package com.example.th2.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.R;
import com.example.th2.adapter.RecycleViewAdapter;
import com.example.th2.dal.SqliteHelper;
import com.example.th2.model.Item;

import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView tvTong;
    private Button btSearch;
    private SearchView searchView;
    private EditText efrom, eto;
    private Spinner sp;
    private RecycleViewAdapter adapter;
    private SqliteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db = new SqliteHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setList(list);
        tvTong.setText("Tong Tien: " + tong(list));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Item> list = db.searchByTitle(newText);
                tvTong.setText("Tong tien: "+tong(list));
                adapter.setList(list);
                return true;
            }
        });
        efrom.setOnClickListener(this);
        eto.setOnClickListener(this);
        btSearch.setOnClickListener(this);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = sp.getItemAtPosition(position).toString();
                List<Item> list;
                if(!category.equalsIgnoreCase("All")){
                    list = db.searchByCategory(category);
                }else{
                    list = db.getAll();
                }
                adapter.setList(list);
                tvTong.setText("Tong tien: "+tong(list));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private int tong(List<Item> list){
        int t = 0;
        for (Item i : list){
            t += Integer.parseInt(i.getPrice());
        }
        return t;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycleView);
        tvTong = view.findViewById(R.id.tvTong);
        btSearch = view.findViewById(R.id.btSearch);
        searchView = view.findViewById(R.id.search);
        efrom = view.findViewById(R.id.eFrom);
        eto = view.findViewById(R.id.eTo);
        sp = view.findViewById(R.id.spCategory);
        String[] arr = getResources().getStringArray(R.array.category);
        String[] arr1 = new String[arr.length+1];
        arr1[0] = "All";
        for(int i = 0; i < arr.length; i++){
            arr1[i+1] = arr[i];
        }
        sp.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner,arr1));
    }

    @Override
    public void onClick(View v) {
        if(v == efrom){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                    String date ="";
                    if(m>8){
                        date = d + "/" + (m+1) + "/" + y;
                    }else{
                        date = d + "/0" + (m+1) + "/" + y;
                    }
                    efrom.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(v == eto){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                    String date ="";
                    if(m>8){
                        date = d + "/" + (m+1) + "/" + y;
                    }else{
                        date = d + "/0" + (m+1) + "/" + y;
                    }
                    eto.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(v == btSearch){
            String from = efrom.getText().toString();
            String to = eto.getText().toString();
            if(!from.isEmpty() && !to.isEmpty()){
                List<Item> list = db.searchByDateFromTo(from,to);
                adapter.setList(list);
                tvTong.setText("Tong tien: "+tong(list));
            }
        }
    }
}
