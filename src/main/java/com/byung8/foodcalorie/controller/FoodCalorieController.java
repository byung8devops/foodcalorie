package com.byung8.foodcalorie.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.byung8.common.domain.IResult;
import com.byung8.common.domain.Result;
import com.byung8.common.exception.Byung8Exception;
import com.byung8.foodcalorie.domain.FoodCalorieBase;
import com.byung8.foodcalorie.service.FoodCalorieService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/calorie")
public class FoodCalorieController {

	@Autowired
	FoodCalorieService dietService;
	
	private String txId() {
		return UUID.randomUUID().toString();
	}

	@RequestMapping(value = "/food", method = RequestMethod.POST)
	public ResponseEntity<String> registerFoodCalorie(@RequestBody final FoodCalorieBase foodCalorie) {

		String txid = txId();
		try {
			Result result = dietService.registerFoodCalorie(foodCalorie, txid);
			return new ResponseEntity<String>(result.toJson(), result.status());
		} catch (Exception e) {
			log.error("Failed to register foodCalorie", e);
			if (e instanceof NullPointerException) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			} else if (e instanceof Byung8Exception) {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e.getMessage());
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			} else {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e);
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

	@RequestMapping(value = "/food/{foodId}", method = RequestMethod.GET)
	public ResponseEntity<String> getFoodCalorie(@PathVariable("foodId") final int foodId) {

		String txid = txId();
		try {
			if (log.isInfoEnabled()) {
				log.info("getFoodCalorie(foodId:"+foodId+") with txid : "+txid);
			}
			
			Result result = dietService.getFoodCaloire(foodId, txid);
			
			return new ResponseEntity<String>(result.toJson(), result.status());
		} catch (Exception e) {
			log.error("Failed to get foodCalorie", e);
			if (e instanceof NullPointerException) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			} else if (e instanceof Byung8Exception) {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e.getMessage());
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			} else {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e);
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

	@RequestMapping(value = "/{foodTypeId}/{foodName}", method = RequestMethod.GET)
	public ResponseEntity<String> getFoodCalorieList(@PathVariable("foodTypeId") final int foodTypeId
			, @PathVariable("foodName") final String foodName) {

		String txid = txId();
		try {
			FoodCalorieBase foodCalorie = new FoodCalorieBase();
			foodCalorie.setFoodTypeId(foodTypeId);
			foodCalorie.setFoodName(foodName);
			
			Result result = dietService.getFoodCalorieList(foodCalorie, txid);
			
			return new ResponseEntity<String>(result.toJson(), result.status());
		} catch (Exception e) {
			log.error("Failed to get foodCalorieList", e);
			if (e instanceof NullPointerException) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			} else if (e instanceof Byung8Exception) {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e.getMessage());
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			} else {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e);
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<String> getFoodCalorieAllList() {

		String txid = txId();
		try {
			Result result = dietService.getFoodCalorieAllList(txid);
			
			return new ResponseEntity<String>(result.toJson(), result.status());
		} catch (Exception e) {
			log.error("Failed to get foodCalorieList", e);
			if (e instanceof NullPointerException) {
				return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
			} else if (e instanceof Byung8Exception) {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e.getMessage());
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			} else {
				Result result = new Result(txid, IResult.ERROR, "").putValue("error", e);
				return new ResponseEntity<String>(result.toJson(), HttpStatus.EXPECTATION_FAILED);
			}
		}
	}
}
