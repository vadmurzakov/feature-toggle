package com.github.vadmurzakov.featuretoggle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Data
@Table(name = "scans")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "object")
public class Scan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Lob
	private byte[] object;
	private String extension;
}
