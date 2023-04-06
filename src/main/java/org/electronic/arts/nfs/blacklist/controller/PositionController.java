package org.electronic.arts.nfs.blacklist.controller;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.electronic.arts.nfs.blacklist.dto.PositionDTO;
import org.electronic.arts.nfs.blacklist.dto.response.BlacklistResponse;
import org.electronic.arts.nfs.blacklist.dto.response.PositionResponse;
import org.electronic.arts.nfs.blacklist.service.impl.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/blacklist", produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

	private static final Logger log = LogManager.getLogger(PositionController.class);

	@Autowired
	private PositionServiceImpl positionServiceImpl;

	@GetMapping(path = "/positions")
	public ResponseEntity<BlacklistResponse> getBlacklist(@RequestParam(name = "reverse", required = false) Integer reverse) {
		log.info("Ingreso al servicio getBlacklist.");
		return new ResponseEntity<BlacklistResponse>(positionServiceImpl.getBlacklist(reverse), HttpStatus.OK);
	}

	@PostMapping(path = "/insert")
	public ResponseEntity<String> insertInTheBlacklist(@RequestBody PositionDTO position) {
		log.info("Ingreso al servicio insertInTheBlacklist.");
		positionServiceImpl.insertBlacklist(position);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/position")
	public ResponseEntity<PositionResponse> getPosition(@RequestParam(name = "position") String pos) {
		log.info("Ingreso al servicio getPosition");
		return new ResponseEntity<PositionResponse>(positionServiceImpl.getPosition(pos), HttpStatus.OK);
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<PositionResponse> updateChallengerByPosition(@RequestBody PositionDTO position) {
		log.info("Ingreso al servicio updateChallengerByPosition");	
		return new ResponseEntity<>(positionServiceImpl.updateByPositionBlacklist(position), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteChallengerByPosition(@RequestParam(name = "position") String pos) {
		log.info("Ingreso al servicio deleteChallengerByPosition");
		positionServiceImpl.deleteByPosition(pos);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//prueba de maquina 2
	
}
