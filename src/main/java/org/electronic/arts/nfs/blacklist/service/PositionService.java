package org.electronic.arts.nfs.blacklist.service;

import org.electronic.arts.nfs.blacklist.dto.PositionDTO;
import org.electronic.arts.nfs.blacklist.dto.response.BlacklistResponse;
import org.electronic.arts.nfs.blacklist.dto.response.PositionResponse;

public interface PositionService {

	BlacklistResponse getBlacklist(Integer reverse);
	
	void insertBlacklist(PositionDTO position);
	
	PositionResponse getPosition(String position);
	
	PositionResponse updateByPositionBlacklist(PositionDTO position);
	
	void deleteByPosition(String position);
}
