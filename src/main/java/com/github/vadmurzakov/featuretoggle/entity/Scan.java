package com.github.vadmurzakov.featuretoggle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "scans")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Scan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Lob
	private byte[] object;
	private String extension;
}
