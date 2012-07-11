package br.com.billcode.domain;

import br.com.billcode.R;

public enum Period {

	ALL(4, R.string.all), CURRENT(1, R.string.current), LAST(2, R.string.last);

	public static Period getPeriodById(final long id) {
		Period period = null;
		switch ((int) id) {
		case 1:
			period = CURRENT;
			break;
		case 2:
			period = LAST;
			break;
		default:
			period = ALL;
			break;
		}
		return period;
	}

	private long id;

	private int labelId;

	private Period(final int id, final int labelId) {
		this.id = id;
		this.labelId = labelId;

	}

	public long getId() {
		return id;
	}

	public int getLabelId() {
		return labelId;
	}

}
