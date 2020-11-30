package component;

public class Part extends IndexNode {

	/**
	 * 本文
	 */
	private String context;
	
	public Part() {
		this("");
	}

	public Part(String title) {
		this(title, "");
	}
	
	public Part(String title, String context) {
		super(title);
		this.context = context;
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
