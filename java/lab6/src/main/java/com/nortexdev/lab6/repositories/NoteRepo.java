package com.nortexdev.lab6.repos;

import com.nortexdev.lab6.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Integer> {
	List<Note> findByOrderByTimestampDesc();
}
