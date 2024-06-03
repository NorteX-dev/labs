package com.nortexdev.lab6.services;

import com.nortexdev.lab6.models.Note;
import com.nortexdev.lab6.repositories.NoteRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImp implements NoteService {
	private final NoteRepo noteRepo;

	public NoteServiceImp(NoteRepo noteRepo) {
		this.noteRepo = noteRepo;
	}

	@Override
	public List<Note> getNotes() {
		return noteRepo.findByOrderByTimestampDesc();
	}

	@Override
	public Note createNote(Note note) {
		note.setTimestamp(new Date());
		return noteRepo.save(note);
	}
}
