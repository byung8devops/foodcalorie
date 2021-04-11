package com.byung8.foodcalorie.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.byung8.foodcalorie.domain.FoodCalorieBase;
@Mapper
public interface FoodCalorieBaseMapper {
	int registerFoodCalorieBase(FoodCalorieBase foodCalorieBase) throws SQLException;
	FoodCalorieBase findFoodCalorieById(int id) throws SQLException;
	List<FoodCalorieBase> findFoodCalorieList(FoodCalorieBase foodCalorieBase) throws SQLException;
	List<FoodCalorieBase> findFoodCalorieAllList() throws SQLException;
}
