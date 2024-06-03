package com.nortexdev.lab6.repositories;

import com.nortexdev.lab6.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Integer> {
	List<Note> findByOrderByTimestampDesc();
}
