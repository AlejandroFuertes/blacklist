package org.electronic.arts.nfs.blacklist.dto.response;

import org.electronic.arts.nfs.blacklist.dto.PositionDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionResponse {

	@JsonProperty("data")
	private PositionDTO positionDTO;
}
