package br.com.billcode;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import br.com.billcode.domain.Cracha;
import br.com.billcode.domain.HistoricalResultBaseAdapter;
import br.com.billcode.domain.Period;

public class HistoricalResult extends ListActivity {
	private Period period;

	// TODO Remover cógido duplicado
	private void confirmDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(getString(R.string.confirm_delete)).setCancelable(false).setPositiveButton(
				getString(R.string.yes), new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog, final int id) {
						final Uri uri = Uri.withAppendedPath(Cracha.Checkpoint.CONTENT_URI, period.name());
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
	protected void onCreate(final Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		period = (Period) getIntent().getSerializableExtra(Period.class.getName());
		setListAdapter(new HistoricalResultBaseAdapter(this, period));
		if (getListAdapter().getCount() == 0) {
			final Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.empty_result),
					Toast.LENGTH_SHORT);
			toast.show();
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		final MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.historical_result_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.historical_clean:
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
