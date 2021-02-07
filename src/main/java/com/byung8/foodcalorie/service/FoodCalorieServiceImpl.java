package com.byung8.foodcalorie.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.byung8.common.domain.Result;
import com.byung8.common.exception.Byung8Exception;
import com.byung8.foodcalorie.domain.FoodCalorieBase;
import com.byung8.foodcalorie.mapper.FoodCalorieBaseMapper;

import lombok.extern.slf4j.Slf4j;

@Service("foodCalorieService")
@Slf4j
public class FoodCalorieServiceImpl implements FoodCalorieService {

	@Autowired
	FoodCalorieBaseMapper foodCalorieBaseMapper;

	@Override
	public Result registerFoodCalorie(FoodCalorieBase foodCalorie, String txid) throws Byung8Exception {
		Result result = null;
		try {
			int cnt = foodCalorieBaseMapper.registerFoodCalorieBase(foodCalorie);
			boolean res = cnt > 0 ? true : false;
			result = new Result(txid, Result.OK).putValue("result", res);
		} catch(SQLException e) {
			log.error("registerFoodCalorie", e);
			throw new Byung8Exception(e);
		}
		return result;
	}
	
	@Override
	public Result getFoodCalorieList(FoodCalorieBase foodCalorie, String txid) throws Byung8Exception {
		Result result = null;
		try {
			List<FoodCalorieBase> list = foodCalorieBaseMapper.findFoodCalorieList(foodCalorie);
			result = new Result(txid, Result.OK).putValue("foodCalories", list);
		} catch(SQLException e) {
			log.error("foodCalories", e);
			throw new Byung8Exception(e);
		}
		return result;
	}

	@Override
	public Result getFoodCaloire(int foodId, String txid) throws Byung8Exception {
		Result result = null;
		try {
			FoodCalorieBase foodCalorie = foodCalorieBaseMapper.findFoodCalorieById(foodId);
			if (log.isInfoEnabled()) {
				log.info("foodId:"+foodId+" {"+foodCalorie.getFoodName()+"}");
			}
			result = new Result(txid, Result.OK).putValue("foodCalorie", foodCalorie);
		} catch(SQLException e) {
			log.error("getFoodCaloire", e);
			throw new Byung8Exception(e);
		}
		return result;
	}

	@Override
	public Result getFoodCalorieAllList(String txid) throws Byung8Exception {
		Result result = null;
		try {
			List<FoodCalorieBase> list = foodCalorieBaseMapper.findFoodCalorieAllList();
			if (log.isInfoEnabled()) {
				log.info("foodAll");
			}
			result = new Result(txid, Result.OK).putValue("findFoodCalorieAllList", list);
		} catch(SQLException e) {
			log.error("getFoodCaloire", e);
			throw new Byung8Exception(e);
		}
		return result;
	}


}
