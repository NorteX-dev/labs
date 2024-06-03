package com.nortexdev.lab6.models;

import com.nortexdev.lab6.enums.NoteImportance;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="notes")
@Getter
@Setter
@NoArgsConstructor
public class Note {
	@Id
	@GeneratedValue()
	@Column
	private Integer id;

	@Column
	private NoteImportance importance;

	@Column
	private String text;

	@Column
	private Date timestamp;

	@CreationTimestamp
	@Column
	private Date createdAt;

	@UpdateTimestamp
	@Column
	private Date updatedAt;

}
