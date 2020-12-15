package consts;

public enum Strings {
	defaultTitle("New Title");
	
	private final String string;
	
	private Strings(final String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
}
