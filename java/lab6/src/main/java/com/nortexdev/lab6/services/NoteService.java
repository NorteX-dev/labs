package com.nortexdev.lab6.services;

import com.nortexdev.lab6.models.Note;

import java.util.List;

public interface NoteService {
	List<Note> getNotes();
	Note createNote(Note note);
}
