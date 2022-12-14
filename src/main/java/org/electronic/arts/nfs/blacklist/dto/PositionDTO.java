package org.electronic.arts.nfs.blacklist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionDTO {

	@JsonProperty("name")
	private String name;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("speciality")
	private String speciality;

	@JsonProperty("currentlyPosition")
	private Integer position;

	@Override
	public String toString() {
		return "PositionDTO name: " + name + ", alias: " + alias + ", speciality: " + speciality + ", position: "
				+ position;
	}

}
