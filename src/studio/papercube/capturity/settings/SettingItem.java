package studio.papercube.capturity.settings;

public enum SettingItem {

	autoSleepThreshold("5000"),
	SavePartially(false),
	SavePercent(100),
	PasswordBase(0),
	Password("CapturityLogin"),
	OutputPath,
	Interval,
	NotificationSilent(false),
	AlwaysRunInBackground(true);
	
	public String defaultValue;
	public String name;
	
	private SettingItem(){
		this.defaultValue = "";
		name = name();
	}
	
	private <T> SettingItem(T defaultval){
		this.defaultValue = String.valueOf(defaultval);
		name = name();
	}
	
	
}
