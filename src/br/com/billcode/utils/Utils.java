package br.com.billcode.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private static final String ISO8601_MASK = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(ISO8601_MASK);
	// Mascara para o campo data, nao foi necessario gravas os segundos

	public static final String parseISO8601(final long timeInMilis) {
		return formatter.format(new Date(timeInMilis));
	}
}
