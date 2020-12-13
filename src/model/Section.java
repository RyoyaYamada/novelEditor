package model;

public class Section extends IndexItem {

	public Section() {
		this("", new Part("part1"));
	}

	public Section(String title) {
		super(title);
	}

	public Section(String title, Part childPart) {
		this(title);
		addPart(childPart);
	}

	public Section(String title, Section childSection) {
		this(title);
		addSection(childSection);
	}


	public void addSection(Section childSection) {
		this.getChildren().add(childSection);
	}

	public void addPart(Part childPart) {
		this.getChildren().add(childPart);
	}

}
