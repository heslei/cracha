package br.com.billcode.domain;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Cracha {

	public static class Checkpoint implements BaseColumns {

		public static final String CHECKPOINT = "checkpoint";
		/**
		 * The content:// style URL for this table
		 */
		public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/checkpoint");

		public static final String TYPE = "type";

		private Checkpoint() {
		}
	}

	public static final String AUTHORITY = "br.com.billcode.provider.crachaprovider";

	// This class cannot be instantiated
	private Cracha() {
	}
}
