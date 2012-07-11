package br.com.billcode.domain;

public class HistoricalResultVO {

	private long timeInMilis;
	private String type;

	public HistoricalResultVO(final long timeInMilis, final String type) {
		this.timeInMilis = timeInMilis;
		this.type = type;
	}

	public long getTimeInMilis() {
		return timeInMilis;
	}

	public String getType() {
		return type;
	}

	public void setTimeInMilis(final long timeInMilis) {
		this.timeInMilis = timeInMilis;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
