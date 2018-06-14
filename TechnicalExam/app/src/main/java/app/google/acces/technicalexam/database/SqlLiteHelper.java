package app.google.acces.technicalexam.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import app.google.acces.technicalexam.model.ItemModel;

/**
 * Created by cicciolina on 2/1/18.
 */

public class SqlLiteHelper extends SQLiteOpenHelper {

    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead;

    private static String DATABASE_NAME = "technicalexam.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_ITEMS = "items_tbl";

    private static final String KEY_ITEM_AUTOINC_ID = "_id";
    private static final String KEY_ITEM_NAME = "itemName";
    private static final String KEY_ITEM_DESC = "itemDesc";
    private static final String KEY_ITEM_PHOTO_PATH = "itemPhotoPath";
    private static final String DATABASE_CREATE_ITEMS = "create table if not exists "
            + TABLE_ITEMS
            + " ( "
            + KEY_ITEM_AUTOINC_ID + " integer primary key autoincrement, "
            + KEY_ITEM_NAME + " text, "
            + KEY_ITEM_DESC + " text, "
            + KEY_ITEM_PHOTO_PATH + " text "
            + " );";

    public SqlLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        dbWrite = this.getWritableDatabase();
        dbRead = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_ITEMS);

    }


    public void addEquipment(ItemModel item) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ITEM_NAME, item.getName());
        contentValues.put(KEY_ITEM_DESC, item.getDesc());
        contentValues.put(KEY_ITEM_PHOTO_PATH, item.getPhotoPath());

        dbWrite.insert(TABLE_ITEMS, null, contentValues);
    }


    public ArrayList<ItemModel> getAllEquipment() {
        ArrayList<ItemModel> items = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;

        Cursor cursor = dbWrite.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        ItemModel item = new ItemModel((cursor.getString(cursor.getColumnIndex(KEY_ITEM_NAME))),
                                (cursor.getString(cursor.getColumnIndex(KEY_ITEM_DESC))),
                                (cursor.getString(cursor.getColumnIndex(KEY_ITEM_PHOTO_PATH))));
                        items.add(item);
                    } while (cursor.moveToNext());
                }
        // return contact list
                return items;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
    }
}
