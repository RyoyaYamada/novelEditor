package model;

public class Section extends IndexNode {

	public Section() {
		Part initialPart = new Part(this.getTitle() +"-1");
		addPart(initialPart);
	}

	public Section(String title) {
		super(title);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public Section(String title, Part childPart) {
		super(title);
		addPart(childPart);
	}
	
	public Section(String title, Section childSection) {
		super(title);
		addSection(childSection);
	}
	
	
	public void addSection(Section childSection) {
		this.getChildren().add(childSection);
	}
	
	public void addPart(Part childPart) {
		this.getChildren().add(childPart);
	}

}
