package com.nortexdev.lab7.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="students")
@Getter
@Setter
@NoArgsConstructor
public class Student {
	@Id
	@GeneratedValue()
	@Column
	private Integer id;

	@Column
	private Integer indexNumber;

	@Column
	private String name;

	@Column
	private String lastName;

	@Column
	private String email;

	@CreationTimestamp
	@Column
	private Date createdAt;

	@UpdateTimestamp
	@Column
	private Date updatedAt;
}
