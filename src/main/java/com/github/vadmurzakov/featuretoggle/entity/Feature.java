package com.github.vadmurzakov.featuretoggle.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
	@Id
	private String name;
	private boolean active;
}
