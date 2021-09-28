package com.gti.rest.api.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public interface DateTools {

	static boolean detecteDateForamt(String date) {
		Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$");
		return pattern.matcher(date).matches();
	}

	static LocalDate convertDate(String date) {
		return detecteDateForamt(date) ? LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				: LocalDate.parse(date);
	}

}
