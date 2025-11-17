package ch.bbw.obelix.webshop.controller;

import java.util.List;
import java.util.UUID;

import ch.bbw.obelix.quarry.api.dto.MenhirDto;
import ch.bbw.obelix.webshop.dto.BasketDto;
import ch.bbw.obelix.webshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ObelixWebshopController {

	private static final Log logger = LogFactory.getLog(ObelixWebshopController.class);

	private final BasketService basketService;

	@GetMapping("/api")
	public String welcome() {
		logger.info("ObelixWebshopController welcome()");
		return "Welcome to Obelix's Menhir Shop! The finest menhirs in all of Gaul! Ces Romains sont fous!";
	}

	@GetMapping("/api/menhirs")
	public List<MenhirDto> getMenhirs() {
		logger.info("ObelixWebshopController getMenhirs()");
		return basketService.getAllMenhirs();
	}

	/**
	 * Customer adds even more shinies in exchange for a beautiful menhir.
	 */
	@PutMapping("/api/basket/offer")
	public BasketDto offer(@RequestBody BasketDto.BasketItem basketItem) {
		logger.info("ObelixWebshopController offer()");
		return basketService.offer(basketItem);
	}

	/**
	 * In case the customer doesn't want to offer more and leaves.
	 */
	@DeleteMapping("/api/basket")
	public void leave() {
		logger.info("ObelixWebshopController leave()");
		basketService.leave();
	}

	/**
	 * Decide if the current basket is worthy enough for a beautiful menhir.
	 *
	 * @param menhirId the menhir to buy
	 * @throws BasketService.BadOfferException in case the basket is tiny
	 */
	@PostMapping("/api/basket/buy/{menhirId}")
	public void exchangeFor(@PathVariable UUID menhirId) {
		logger.info("ObelixWebshopController exchangeFor()");
		basketService.exchange(menhirId);
	}

}
