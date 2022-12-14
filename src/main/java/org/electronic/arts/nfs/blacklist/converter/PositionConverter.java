package org.electronic.arts.nfs.blacklist.converter;

import org.electronic.arts.nfs.blacklist.dto.PositionDTO;
import org.electronic.arts.nfs.blacklist.entity.PositionEntity;
import org.springframework.stereotype.Component;

@Component
public class PositionConverter {

	public PositionDTO entityToDTO(PositionEntity entity) {
		return PositionDTO.builder().name(entity.getName()).alias(entity.getAlias()).speciality(entity.getSpeciality()).position(entity.getCurrentlyPosition()).build();		
	}
	
	public PositionEntity dtoToEntity(PositionDTO dto) {
		return PositionEntity.builder().name(dto.getName()).alias(dto.getAlias()).speciality(dto.getSpeciality()).currentlyPosition(dto.getPosition()).build();
	}
}
