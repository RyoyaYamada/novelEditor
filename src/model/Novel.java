package model;

public class Novel extends Section {

	public Novel() {
		
	}

	public Novel(String title) {
		super(title);
	}

	public Novel(String title, Part childPart) {
		this(title);
		addPart(childPart);
	}

	public Novel(String title, Section childSection) {
		this(title);
		addSection(childSection);
	}

}
