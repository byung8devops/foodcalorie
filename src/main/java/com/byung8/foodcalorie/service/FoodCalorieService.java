package com.byung8.foodcalorie.service;

import com.byung8.common.domain.Result;
import com.byung8.common.exception.Byung8Exception;
import com.byung8.foodcalorie.domain.FoodCalorieBase;

public interface FoodCalorieService {
	Result registerFoodCalorie(FoodCalorieBase foodCalorie, String txid) throws Byung8Exception;
	Result getFoodCaloire(int foodId, String txid) throws Byung8Exception;
	Result getFoodCalorieList(FoodCalorieBase foodCalorie, String txid) throws Byung8Exception;
}
