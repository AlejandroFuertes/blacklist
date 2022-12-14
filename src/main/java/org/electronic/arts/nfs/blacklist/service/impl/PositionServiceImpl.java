package org.electronic.arts.nfs.blacklist.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.electronic.arts.nfs.blacklist.converter.PositionConverter;
import org.electronic.arts.nfs.blacklist.dto.PositionDTO;
import org.electronic.arts.nfs.blacklist.dto.response.BlacklistResponse;
import org.electronic.arts.nfs.blacklist.dto.response.PositionResponse;
import org.electronic.arts.nfs.blacklist.entity.PositionEntity;
import org.electronic.arts.nfs.blacklist.exception.DatabaseException;
import org.electronic.arts.nfs.blacklist.exception.RequestException;
import org.electronic.arts.nfs.blacklist.repository.PositionRepository;
import org.electronic.arts.nfs.blacklist.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PositionServiceImpl implements PositionService {

	private static final Logger log = LogManager.getLogger(PositionServiceImpl.class);
	private static final PositionConverter converter = new PositionConverter();
	private static final String ERROR_NOT_FOUND = "No se ha encontrado datos para esta peticion.";

	@Autowired
	private PositionRepository positionRepository;

	@Override
	public BlacklistResponse getBlacklist(Integer reverse) {
		log.info("Se ingreso a la capa de servicio.");

		BlacklistResponse response = new BlacklistResponse();
		List<PositionEntity> listEntity = positionRepository.findAll();
		List<PositionDTO> positions = getBlacklistDto(listEntity);

		if (reverse == null) {
			positions.sort(Comparator.comparing(PositionDTO::getPosition));
		} else if (reverse.intValue() == 1) {
			positions.sort(Comparator.comparing(PositionDTO::getPosition).reversed());
		} else {
			positions.sort(Comparator.comparing(PositionDTO::getPosition));
		}

		response.setPositions(positions);
		return response;
	}

	@Override
	public void insertBlacklist(PositionDTO position) {

		log.info("Se ingreso a la capa de servicio.");
		validateFields(position);
		log.info("Campos validados.");
		PositionEntity positionEntity = converter.dtoToEntity(position);
		positionRepository.insertInTheBlacklist(positionEntity.getName(), positionEntity.getAlias(),
				positionEntity.getSpeciality(), positionEntity.getCurrentlyPosition().intValue());
		log.info("Success Insert!!");
	}

	public PositionResponse getPosition(String pos) {
		int position = Integer.parseInt(pos);
		validateCurrentlyPosition(position);

		var positionEntity = positionRepository.findByCurrentlyPosition(position)
				.orElseThrow(() -> new DatabaseException(HttpStatus.NOT_FOUND, ERROR_NOT_FOUND));
		log.info("Resultado de la consulta: " + positionEntity.toString());
		PositionResponse response = new PositionResponse();
		PositionDTO positionDTO = converter.entityToDTO(positionEntity);
		response.setPositionDTO(positionDTO);
		return response;

	}

	public PositionResponse updateByPositionBlacklist(PositionDTO position) {
		validateFields(position);
		var positionEntity = positionRepository.findByCurrentlyPosition(position.getPosition())
				.orElseThrow(() -> new DatabaseException(HttpStatus.NOT_FOUND, ERROR_NOT_FOUND));
		log.info("Resultado de la consulta: " + positionEntity.toString());

		positionEntity.setName(position.getName());
		positionEntity.setAlias(position.getAlias());
		positionEntity.setSpeciality(position.getSpeciality());
		positionEntity.setCurrentlyPosition(position.getPosition());

		positionRepository.save(positionEntity);

		PositionResponse response = new PositionResponse();
		response.setPositionDTO(converter.entityToDTO(positionEntity));
		return response;
	}

	public void deleteByPosition(String pos) {
		log.info("Se ingreso a la capa de servicio");
		int position = Integer.parseInt(pos);
		validateCurrentlyPosition(position);

		var positionEntity = positionRepository.findByCurrentlyPosition(position)
				.orElseThrow(() -> new DatabaseException(HttpStatus.NOT_FOUND, ERROR_NOT_FOUND));
		log.info("Resultado de la consulta: " + positionEntity.toString());
		positionRepository.delete(positionEntity);

	}

	private List<PositionDTO> getBlacklistDto(List<PositionEntity> listEntity) {
		List<PositionDTO> listPosition = new ArrayList<>();

		for (PositionEntity positionEntity : listEntity) {
			listPosition.add(converter.entityToDTO(positionEntity));
		}
		return listPosition;
	}

	private void validateFields(PositionDTO position) {
		validateName(position.getName());
		validateAlias(position.getAlias());
		validateSpeciality(position.getSpeciality());
		validateCurrentlyPosition(position.getPosition());
	}

	private void validateName(String name) {
		if (name == null || name.isEmpty()) {
			throw new RequestException("400", HttpStatus.BAD_REQUEST, "Se requiere un nombre");
		}
	}

	private void validateAlias(String alias) {
		if (alias == null || alias.isEmpty()) {
			throw new RequestException("400", HttpStatus.BAD_REQUEST, "Se requiere un alias");
		}
	}

	private void validateSpeciality(String speciality) {
		if (speciality == null || speciality.isEmpty()) {
			throw new RequestException("400", HttpStatus.BAD_REQUEST, "Se requiere una especialidad");
		}
	}

	private void validateCurrentlyPosition(Integer position) {
		if (position < 1 || position > 15 || position == null) {
			throw new RequestException("400", HttpStatus.BAD_REQUEST, "Se requiere una posicion entre 1 y 15.");
		}
	}

}
