package com.kim.demo2.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import Daos.buketDao;

@Service
public class payService {
	private static final Logger logger = LoggerFactory.getLogger(payService.class);

	@Autowired
	private buketDao buketDao;
	
	public void TotalPriceAndUser(String email,Model model) {
		logger.info("TotalPriceAndUser");
		List<Map<String, Object>>maps=buketDao.findByEmail(email);
		int totalPrice=0;
		for(Map<String, Object>map:maps) {
			int count=Integer.parseInt(map.get("CCOUNT").toString());
			totalPrice=totalPrice+count*20000;
		}
		model.addAttribute("totalPrice", totalPrice);
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("addr", "서울시");
		map.put("name", "이름자리");
		map.put("phone", "010-1111-1111");
		model.addAttribute("user", map);
		
	}
}
