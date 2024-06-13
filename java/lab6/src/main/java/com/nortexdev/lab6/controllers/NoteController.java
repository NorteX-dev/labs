package com.nortexdev.lab6.controllers;

import com.nortexdev.lab6.enums.NoteImportance;
import com.nortexdev.lab6.models.Note;
import com.nortexdev.lab6.services.NoteService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {
	private final NoteService noteService;

	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}

	@GetMapping("/notes")
	public String getAllRoute(Model model) {
		List<Note> notes = noteService.getNotes();
		model.addAttribute("notes", notes);
		model.addAttribute("importances", NoteImportance.values());
		return "notes";
	}

	@PostMapping(path = "/notes")
	public String createNewRoute(@ModelAttribute Note note, HttpServletResponse httpServletResponse) {
		noteService.createNote(note);
		httpServletResponse.setHeader("Location", "/notes");
		httpServletResponse.setStatus(302);
		return "redirect:/notes";
	}
}
