package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import DatabaseHelper.DictionaryDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private EditText edt_search;
    private ListView lv_result;
    private Button btn_saveInternal;
    private Button btn_saveExternal;
    private Button btn_copyExternal;

    private SQLiteDatabase database;

    private SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "dictionary_prefs";
    private static final String KEY_LAST_SEARCH = "last_search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edt_search = findViewById(R.id.edt_search);
        lv_result = findViewById(R.id.lv_result);
        btn_saveInternal = findViewById(R.id.btn_save_internal);
        btn_saveExternal = findViewById(R.id.btn_save_external);
        btn_copyExternal = findViewById(R.id.btn_copy_external);

        // Initialize the SQLite database
        SQLiteOpenHelper dbHelper = new DictionaryDatabaseHelper(this);
        database = dbHelper.getReadableDatabase();

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Restore the last search text from shared preferences
        String lastSearch = sharedPreferences.getString(KEY_LAST_SEARCH, "");
        edt_search.setText(lastSearch);

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();
                DictionaryDatabaseHelper.SearchWord(database, KEY_LAST_SEARCH, sharedPreferences, lv_result, MainActivity.this, searchText);
            }
        });

        btn_saveExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DictionaryDatabaseHelper.SaveDictToExternalStorage(MainActivity.this, database);
            }
        });

        btn_saveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DictionaryDatabaseHelper.SaveDictToInternalStorage(MainActivity.this, database);
            }
        });

        btn_copyExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DictionaryDatabaseHelper.CopyDictToExternalStorage(MainActivity.this);
            }
        });

        lv_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String SelectedItem = (String) parent.getItemAtPosition(position);
                edt_search.setText(SelectedItem);
            }
        });
    }
}