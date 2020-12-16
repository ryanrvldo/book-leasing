package com.lawencon.bookleasing.util;

import java.time.Instant;

/**
 * @author Rian Rivaldo Rumapea
 */
public class ReceiptNumberUtil {

	public static String getReceiptNumber() {
		return String.format("TRX%d", Instant.now().toEpochMilli());
	}

}
