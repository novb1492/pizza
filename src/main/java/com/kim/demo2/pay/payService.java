package com.kim.demo2.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kim.demo2.utillService;

import Daos.buketDao;

@Service
public class payService {
	private static final Logger logger = LoggerFactory.getLogger(payService.class);

	@Autowired
	private buketDao buketDao;
	
	public JSONObject getPayInfor(tryBuyDto tryBuyDto,String email) {
		logger.info("getPayInfor");
		System.out.println(tryBuyDto.getMobile1()+tryBuyDto.getMobile2()+tryBuyDto.getMobile3());
		if(utillService.checkNull(tryBuyDto.getMobile1())||utillService.checkNull(tryBuyDto.getMobile2())||utillService.checkNull(tryBuyDto.getMobile3())) {
			System.out.println("ss");
			throw utillService.makeRuntimeEX("핸드폰 번호를 확인해주세요", "getPayInfor");
		}
		List<Map<String, Object>>maps=buketDao.findByEmail(email);
		if(maps.isEmpty()) {
			throw utillService.makeRuntimeEX("장바구니가 비었습니다", "getPayInfor");
		}
		
		return utillService.makeJson(true, "??");
	}
	

}
