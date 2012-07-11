package br.com.billcode.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.billcode.R;

public class HistoricalResultBaseAdapter extends BaseAdapter {

	private final Context context;
	java.text.DateFormat dateFormat;
	private final List<HistoricalResultVO> result = new ArrayList<HistoricalResultVO>();
	java.text.DateFormat timeFormat;

	public HistoricalResultBaseAdapter(final Context context, final Period period) {
		this.context = context;
		dateFormat = DateFormat.getDateFormat(context);
		timeFormat = DateFormat.getTimeFormat(context);
		final String[] projection = new String[] { Cracha.Checkpoint._ID, Cracha.Checkpoint.TYPE };
		final Uri uri = Uri.withAppendedPath(Cracha.Checkpoint.CONTENT_URI, period.name());

		final Cursor cursor = this.context.getContentResolver().query(uri, projection, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				result.add(new HistoricalResultVO(cursor.getLong(0), cursor.getString(1)));
			} while (cursor.moveToNext());
		}
		cursor.close();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.size();
	}

	@Override
	public Object getItem(final int location) {
		return result.get(location);
	}

	@Override
	public long getItemId(final int location) {
		return result.get(location).getTimeInMilis();
	}

	@Override
	public View getView(final int location, final View convertView, final ViewGroup group) {
		final HistoricalResultVO vo = result.get(location);
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(R.layout.historical_result_detail, null);

		final TextView date = (TextView) view.findViewById(R.id.historical_result_date);
		final TextView type = (TextView) view.findViewById(R.id.historical_result_type);

		final Date thisDate = new Date(vo.getTimeInMilis());
		date.setText(dateFormat.format(thisDate) + " " + timeFormat.format(thisDate));
		type.setText(vo.getType());

		return view;
	}

}
