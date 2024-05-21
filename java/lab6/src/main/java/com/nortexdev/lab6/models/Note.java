package com.nortexdev.lab6.domain;

import com.nortexdev.lab6.enums.NoteImportance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="notes")
@Getter
@Setter
@NoArgsConstructor
public class Note {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;

	@Column
	private NoteImportance importance;

	@Column
	private String text;

	@Column
	private LocalDateTime timestamp;

	@CreationTimestamp
	@Column
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column
	private LocalDateTime updatdAt;
}
