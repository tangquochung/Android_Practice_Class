package DatabaseHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class DictionaryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DictionaryApp.db";
    private static final int DATABASE_VERSION = 1;

    private final Context context;

    public DictionaryDatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the dictionary table
        db.execSQL("CREATE TABLE dictionary (word TEXT, definition TEXT)");

        // Populate the dictionary with sample data
        db.execSQL("INSERT INTO dictionary VALUES ('car', 'a road vehicle with an engine, four wheels, and seats for a small number of people')");
        db.execSQL("INSERT INTO dictionary VALUES ('mouse', 'a small mammal with short fur, a pointed face, and a long tail')");
        db.execSQL("INSERT INTO dictionary VALUES ('deer', 'a quite large animal with four legs that eats grass and leaves')");
        db.execSQL("INSERT INTO dictionary VALUES ('guitar', 'a musical instrument, usually made of wood, with six strings and a long neck, played with the fingers or a plectrum')");
        db.execSQL("INSERT INTO dictionary VALUES ('piano', 'a large musical instrument with a row of black and white keys that are pressed to play notes')");
        db.execSQL("INSERT INTO dictionary VALUES ('computer', 'an electronic machine that is used for storing, organizing, and finding words, numbers, and pictures, for doing calculations, and for controlling other machines')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public static void SearchWord (SQLiteDatabase database, String KEY_LAST_SEARCH, SharedPreferences sharedPreferences, ListView lv_result, Context context, String SearchingWord) {
        // Save the current search text to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LAST_SEARCH, SearchingWord);
        editor.apply();

        List<String> results = new ArrayList<>();

        // Search for the exact word in the dictionary
        Cursor cursor = database.rawQuery("SELECT definition FROM dictionary WHERE word = ?", new String[]{SearchingWord});
        if (cursor.moveToFirst()) {
            // Exact match found
            String definition = cursor.getString(0);
            results.add(definition);
        } else {
            // No exact match found, search for words containing the search text
            cursor = database.rawQuery("SELECT word FROM dictionary WHERE word LIKE ?", new String[]{"%" + SearchingWord + "%"});
            if (cursor.moveToFirst()) {
                do {
                    String word = cursor.getString(0);
                    results.add(word);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();

        // Display the search results in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, results);
        lv_result.setAdapter(adapter);
    }

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static void SaveDictToInternalStorage(Context context, SQLiteDatabase database) {
        try {
            // Create a file in internal storage
            File file = new File(context.getFilesDir(), "dictionary.txt");
            FileOutputStream outputStream = new FileOutputStream(file);

            // Retrieve dictionary data from the SQLite database
            Cursor cursor = database.rawQuery("SELECT word, definition FROM dictionary", null);
            if (cursor.moveToFirst()) {
                do {
                    String word = cursor.getString(0);
                    String definition = cursor.getString(1);

                    // Write the word and definition to the file
                    outputStream.write((word + ": " + definition + "\n").getBytes());
                } while (cursor.moveToNext());
            }

            cursor.close();
            outputStream.close();

            Toast.makeText(context, "saved dictionary to internal storage successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "saved dictionary to internal storage failed", Toast.LENGTH_SHORT).show();
        }
    }

    public static void SaveDictToExternalStorage(Context context, SQLiteDatabase database) {
        try {
            if (isExternalStorageWritable()) {
                // Create a file in external storage
                File file = new File(context.getExternalFilesDir(null), "dictionary.txt");
                FileOutputStream outputStream = new FileOutputStream(file);

                // Retrieve dictionary data from the SQLite database
                Cursor cursor = database.rawQuery("SELECT word, definition FROM dictionary", null);
                if (cursor.moveToFirst()) {
                    do {
                        String word = cursor.getString(0);
                        String definition = cursor.getString(1);

                        // Write the word and definition to the file
                        outputStream.write((word + ": " + definition + "\n").getBytes());
                    } while (cursor.moveToNext());
                }

                cursor.close();
                outputStream.close();

                Toast.makeText(context, "saved dictionary to external storage successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "External storage is not available", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "saved dictionary to external storage failed", Toast.LENGTH_SHORT).show();
        }
    }

    public static void CopyDictToExternalStorage(Context context) {
        try {
            if (isExternalStorageWritable()) {
                // Create a file in external storage
                File internalFile = new File(context.getFilesDir(), "dictionary.txt");
                File externalFile = new File(context.getExternalFilesDir(null), "dictionary_copy.txt");

                FileInputStream inputStream = new FileInputStream(internalFile);
                FileOutputStream outputStream = new FileOutputStream(externalFile);

                // Copy the contents of the internal file to the external file
                FileChannel inChannel = inputStream.getChannel();
                FileChannel outChannel = outputStream.getChannel();
                inChannel.transferTo(0, inChannel.size(), outChannel);

                inputStream.close();
                outputStream.close();

                Toast.makeText(context, "copied dictionary to external storage", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "External storage is not available", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "copied dictionary failed", Toast.LENGTH_SHORT).show();
        }
    }
}
