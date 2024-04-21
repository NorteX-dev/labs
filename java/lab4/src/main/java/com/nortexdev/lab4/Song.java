package com.nortexdev.lab4;

import java.io.Serializable;

public class Song implements Serializable {
	private String title;
	private int tempo;
	private String rhythm;
	private String performer;
	private String text;

	public Song() {
		this.title = "";
		this.tempo = 0;
		this.rhythm = "";
		this.performer = "";
		this.text = "";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public String getRhythm() {
		return rhythm;
	}

	public void setRhythm(String rhythm) {
		this.rhythm = rhythm;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Song{" +
			"title='" + title + '\'' +
			", tempo=" + tempo +
			", rhythm='" + rhythm + '\'' +
			", performer='" + performer + '\'' +
			", text='" + text + '\'' +
			'}';
	}
}
