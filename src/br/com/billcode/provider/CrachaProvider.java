package br.com.billcode.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import br.com.billcode.domain.Cracha;
import br.com.billcode.domain.Period;

public class CrachaProvider extends ContentProvider {

	private static final String CURRENT_WHERE = Cracha.Checkpoint.CHECKPOINT
			+ " >= strftime('%Y-%m-%d %H:%M:%S','now','start of month')";
	private static final String LAST_WHERE = Cracha.Checkpoint.CHECKPOINT
			+ " >= strftime('%Y-%m-%d %H:%M:%S','now','start of month','-1 month') AND " + Cracha.Checkpoint.CHECKPOINT
			+ " < strftime('%Y-%m-%d %H:%M:%S','now','start of month') ";

	private HistoricalDataHelper historicalDataHelper;

	@Override
	public int delete(final Uri uri, final String selection, final String[] selectionArgs) {
		final SQLiteDatabase db = historicalDataHelper.getWritableDatabase();
		final String string = uri.getLastPathSegment();

		final Period period = Period.valueOf(string);
		// Esta comparação se faz necessária para que sempre retorne a
		// quantidade de registros apagados. Isso porque este numero só eh
		// gerado se houver uma clausula where no delete.
		String where = Cracha.Checkpoint._ID + " = " + Cracha.Checkpoint._ID;
		switch (period) {
		case CURRENT:
			where = CURRENT_WHERE;
			break;
		case LAST:
			where = LAST_WHERE;
			break;

		default:
			break;
		}

		final int result = db.delete(HistoricalDataHelper.DICTIONARY_TABLE_HISTORICAL, where, null);
		db.close();

		return result;
	}

	@Override
	public String getType(final Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(final Uri uri, final ContentValues values) {
		final SQLiteDatabase db = historicalDataHelper.getWritableDatabase();
		final long rowId = db.insert(HistoricalDataHelper.DICTIONARY_TABLE_HISTORICAL, null, values);

		db.close();
		return Uri.withAppendedPath(uri, Long.toString(rowId));
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		historicalDataHelper = new HistoricalDataHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs,
			final String sortOrder) {
		final SQLiteDatabase db = historicalDataHelper.getWritableDatabase();

		final String string = uri.getLastPathSegment();

		final Period period = Period.valueOf(string);
		String where = null;
		switch (period) {
		case CURRENT:
			where = CURRENT_WHERE;
			break;
		case LAST:
			where = LAST_WHERE;
			break;

		default:
			break;
		}
		return db.query(HistoricalDataHelper.DICTIONARY_TABLE_HISTORICAL, projection, where, null, null, null,
				Cracha.Checkpoint._ID + " DESC");
	}

	@Override
	public int update(final Uri uri, final ContentValues values, final String selection, final String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
