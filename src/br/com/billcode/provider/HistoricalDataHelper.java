package br.com.billcode.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.billcode.domain.Cracha;

public class HistoricalDataHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "cracha";
	private static final int DATABASE_VERSION = 1;
	public static final String DICTIONARY_TABLE_HISTORICAL = "historical";
	private static final String DICTIONARY_TABLE_CREATE = "CREATE TABLE " + DICTIONARY_TABLE_HISTORICAL + "("
			+ Cracha.Checkpoint._ID + " INTEGER PRIMARY KEY, " + Cracha.Checkpoint.CHECKPOINT + " TEXT, "
			+ Cracha.Checkpoint.TYPE + " TEXT);";

	HistoricalDataHelper(final Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {
		Log.i(toString(), "Creating database " + DATABASE_NAME);
		db.execSQL(DICTIONARY_TABLE_CREATE);
		Log.i(toString(), "Database done");
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
		Log.i(toString(), "Upgrading database " + DATABASE_NAME);

		Log.w(toString(), "Upgrading database from version " + oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_HISTORICAL);
		onCreate(db);
	}

}
