package personalAccounting;

import java.time.LocalDate;

public class DateSystem {
	public static DateSystem instance = new DateSystem();
	public static DateSystem getDateSystem() { return instance; }
	
	public LocalDate Today;
	
	public DateSystem() {
		this.Today = LocalDate.now();
	}
	
	public String getTodayDate() { return this.Today.toString(); };
}

