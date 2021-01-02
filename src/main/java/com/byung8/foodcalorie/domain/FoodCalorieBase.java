package com.byung8.foodcalorie.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodCalorieBase {
/*
	SELECT
	  a.FDCL_ID as foodId
	, a.FDCL_NM as foodName
	, a.FD_TP_ID as foodTypeId
	, (SELECT b.CD_NM FROM TB_CMM_CD b WHERE a.FD_TP_ID=b.CD_ID AND b.GRP_VAL='03') as foodTypeName
	, a.UNT_TP_ID as unitTypeId
	, (SELECT b.CD_NM FROM TB_CMM_CD b WHERE a.UNT_TP_ID=b.CD_ID AND b.GRP_VAL='04') as unitTypeName
	, a.UNT_CL_VAL as unitCalorieVal
	, a.USE_YN as useYn
	, a.REG_DT as regDt
	, a.UDP_DT as udpDt
	FROM TB_FS_FDCL a
	WHERE a.USE_YN = 'Y';
 */
	private int foodId;
	private String foodName;
	private int foodTypeId;
	private String foodTypeName;
	private int unitTypeId;
	private String unitTypeName;
	private int unitCalorieVal;
	private String useYn;
	private Date regDt;
	private Date udpDt;
}
