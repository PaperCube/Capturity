package studio.papercube.capturity.ui.swing;

public class CapUnits {
	public enum CaptureIntervalUnit{
		sec("秒"),min("分");
		
		String name;
		private CaptureIntervalUnit(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	public enum CaptureAutoSleepUnit {
		sec("秒"),min("分"),hours("时");
		
		String name;
		private CaptureAutoSleepUnit(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
}
