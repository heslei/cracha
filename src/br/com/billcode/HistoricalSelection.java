package br.com.billcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.com.billcode.domain.Cracha;
import br.com.billcode.domain.HistoricalSelectionBaseAdapter;
import br.com.billcode.domain.Period;

public class HistoricalSelection extends Activity {

	private void confirmDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getString(R.string.confirm_delete)).setCancelable(false).setPositiveButton(
				getString(R.string.yes), new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						final Uri uri = Uri.withAppendedPath(Cracha.Checkpoint.CONTENT_URI, Period.ALL.name());
						final int result = getContentResolver().delete(uri, null, null);

						if (result > 0) {
							showToast(getString(R.string.delete_sucess));
						} else {
							showToast(getString(R.string.delete_fail));
						}
						finish();
					}
				}).setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, final int id) {
				dialog.cancel();
			}
		});
		final AlertDialog alertDialog = builder.create();
		alertDialog.show();

	}

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.historical_selection);

		final ListView listView = (ListView) findViewById(R.id.historical_selection_list);
		listView.setAdapter(new HistoricalSelectionBaseAdapter(this, null));
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				final Intent intent = new Intent(view.getContext(), HistoricalResult.class);

				intent.putExtra(Period.class.getName(), Period.getPeriodById(id));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.historical_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.historical_clean_all:
			confirmDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showToast(final String message) {
		final Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}

}
