package org.inkwang.two.model;

public class Word {
	
	private int id;
	private String subject;
	private String paragraph;
	private String words;
	private String tag;
	
	public Word(String subject, String paragraph) {
		this.setSubject(subject);
		this.setParagraph(paragraph);
	}
	
	public Word(int id, String subject, String paragraph, String words, String tag) {
		this.setId(id);
		this.setSubject(subject);
		this.setParagraph(paragraph);
		this.setWords(words);
		this.setTag(tag);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
	}
	
	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
	public String getParagraph() {
		return paragraph;
	}
	
	public void setWords(String words) {
		this.words = words;
	}
	public String getWords() {
		return words;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTag() {
		return tag;
	}
	
	public String toString() {
		return subject + " (" + paragraph + ")";
	}
	
}
