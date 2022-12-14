package org.electronic.arts.nfs.blacklist.dto.response;

import java.util.List;

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
public class BlacklistResponse {

	@JsonProperty("Blacklist")
	private List<PositionDTO> positions;
}
