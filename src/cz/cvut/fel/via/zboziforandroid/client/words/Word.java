package cz.cvut.fel.via.zboziforandroid.client.words;

public class Word {
	private int numberOfSearch;
	private String word;
	private int id;

	@Override
	public String toString() {
		return id + " " + numberOfSearch + " " + word;
	}

	public String getWord() {
		return word;
	}

}