package com.nortexdev.lab6.services;

import com.nortexdev.lab6.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {
	List<Note> getNotes();
	Note createNote(Note note);
}
