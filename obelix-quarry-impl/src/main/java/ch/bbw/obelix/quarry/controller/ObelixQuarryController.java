package ch.bbw.obelix.quarry.controller;

import ch.bbw.obelix.quarry.api.QuarryApi;
import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import ch.bbw.obelix.quarry.entity.MenhirEntity;
import ch.bbw.obelix.quarry.repository.MenhirRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.StandardException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ObelixQuarryController implements QuarryApi {

	private static final Log logger = LogFactory.getLog(ObelixQuarryController.class);

	private final MenhirRepository menhirRepository;

	@Override
	public List<MenhirDto> getAllMenhirs() {
		logger.info("ObelixQuarryController.getAllMenhirs()");
		return menhirRepository.findAll()
				.stream().map(MenhirEntity::toDto).toList();
	}

	@Override
	public MenhirDto getMenhirById(UUID menhirId) {
		logger.info("ObelixQuarryController.getMenhirById(UUID)");
		return menhirRepository.findById(menhirId)
				.map(MenhirEntity::toDto)
				.orElseThrow(() -> new UnknownMenhirException("unknown menhir with id " + menhirId));
	}

	@Override
	public void deleteById(UUID menhirId) {
		logger.info("ObelixQuarryController.deleteById(UUID)");
		menhirRepository.deleteById(menhirId);
	}

	@StandardException
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public static class UnknownMenhirException extends RuntimeException {}
}
