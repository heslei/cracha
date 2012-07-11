package br.com.billcode;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import br.com.billcode.domain.Cracha;
import br.com.billcode.utils.Utils;

public class Main extends Activity {
	private void configureButtons() {
		final Button buttonCheckin = (Button) findViewById(R.id.buttonCheckin);
		buttonCheckin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {

				final ContentValues values = new ContentValues();
				final long timeInMilis = System.currentTimeMillis();

				values.put(Cracha.Checkpoint._ID, timeInMilis);
				values.put(Cracha.Checkpoint.CHECKPOINT, Utils.parseISO8601(timeInMilis));
				values.put(Cracha.Checkpoint.TYPE, getString(R.string.checkin));

				getContentResolver().insert(Cracha.Checkpoint.CONTENT_URI, values);
				showSuccessRecord(R.string.checkin);
			}
		});

		final Button buttonCheckout = (Button) findViewById(R.id.buttonCheckout);
		buttonCheckout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final ContentValues values = new ContentValues();
				final long timeInMilis = System.currentTimeMillis();

				values.put(Cracha.Checkpoint._ID, timeInMilis);
				values.put(Cracha.Checkpoint.CHECKPOINT, Utils.parseISO8601(timeInMilis));
				values.put(Cracha.Checkpoint.TYPE, getString(R.string.checkout));

				getContentResolver().insert(Cracha.Checkpoint.CONTENT_URI, values);
				showSuccessRecord(R.string.checkout);
			}
		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		configureButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.historical_menu:
			startActivity(new Intent(this, HistoricalSelection.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showSuccessRecord(final int type) {

		final String sType = getString(type);
		showToast(getString(R.string.sucess, sType.toLowerCase()));
	}

	private void showToast(final String message) {
		final Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}
}