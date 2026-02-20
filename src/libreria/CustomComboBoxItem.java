package libreria;

public class CustomComboBoxItem {
	private int value;
	private String label;
	
	public CustomComboBoxItem(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public int getValue() { return value; }
	public String getLabel() { return label; }
	
	@Override
	public String toString() {
		return getLabel();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CustomComboBoxItem) {
			return ((CustomComboBoxItem) obj).getValue() == this.value;
		}
		return false;
	}
}
