package br.com.billcode.domain;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.billcode.R;

public class HistoricalSelectionBaseAdapter extends BaseAdapter {

	private final Context context;
	private List<Period> list;

	public HistoricalSelectionBaseAdapter(final Context context, final List<Period> list) {
		this.context = context;

		if (list != null) {
			this.list = list;
		} else {
			this.list = new ArrayList<Period>();
			this.list.add(Period.CURRENT);
			this.list.add(Period.LAST);
			this.list.add(Period.ALL);
		}
	}

	public int getCount() {
		return list.size();
	}

	@Override
	public Period getItem(final int arg0) {
		return list.get(arg0);
	}

	public long getItemId(final int location) {
		return list.get(location).getId();
	}

	public View getView(final int location, final View arg1, final ViewGroup arg2) {
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(br.com.billcode.R.layout.historical_selection_detail, null);

		final TextView textView = (TextView) view.findViewById(R.id.detail_period);
		textView.setText(context.getString((list.get(location).getLabelId())));

		return view;
	}
}
