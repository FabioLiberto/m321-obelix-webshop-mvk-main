package ch.bbw.obelix.quarry.api;

import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.UUID;

public interface QuarryApi {

	@GetExchange("/api/menhirs")
	List<MenhirDto> getAllMenhirs();

	@GetExchange("/api/menhirs/{menhirId}")
	MenhirDto getMenhirById(@PathVariable UUID menhirId);

	/**
	 * Note that this should only be called by Asterix himself.
	 * Hopefully, no customer will ever find this endpoint...
	 */
	@DeleteExchange("/api/quarry/{menhirId}")
	void deleteById(@PathVariable UUID menhirId);
}
