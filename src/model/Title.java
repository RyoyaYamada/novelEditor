package model;

import java.util.UUID;

public class Title extends Section {

	private String uuid;

	public Title() {
		this("", new Part("part1"));
	}

	public Title(String title) {
		super(title);
	}

	public Title(String title, Part childPart) {
		super(title, childPart);
		createUUID();
	}

	public Title(String title, Section childSection) {
		super(title, childSection);
		createUUID();
	}

	private void createUUID() {
		uuid = UUID.randomUUID().toString();
	}

	public String getUUID() {
		return uuid;
	}

}
