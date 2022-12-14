package org.electronic.arts.nfs.blacklist.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "position_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_position_table_pk", schema = "position_table")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "alias")
	private String alias;
	
	@Column(name = "speciality")
	private String speciality;
	
	@Column(name = "currently_position")
	private Integer currentlyPosition;
	
	@Column(name = "registration_date")
	private Date registration;

	@Override
	public String toString() {
		return "Position Entity id: " + id + ", name: " + name + ", alias: " + alias + ", speciality: " + speciality
				+ ", currentlyPosition: " + currentlyPosition + ", registration: " + registration;
	}
}
