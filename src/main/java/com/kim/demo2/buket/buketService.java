package com.kim.demo2.buket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kim.demo2.restController;
import com.kim.demo2.utillService;

import Daos.buketDao;

@Service
public class buketService {
	
	private static final Logger logger = LoggerFactory.getLogger(buketService.class);
	
	@Autowired
	private buketDao buketDao;
	
	public JSONObject changeCount(JSONObject jsonObject) {
		logger.info("changeCount");
		System.out.println(jsonObject);
		int bid=Integer.parseInt(jsonObject.get("bid").toString());
		Map<String, Object>map=buketDao.findByBid(bid);
		logger.info("장바구니 조회결과" +map.toString());
		String dbEmail=(String)map.get("CEMAIL");
		int num=Integer.parseInt(jsonObject.get("num").toString());
		if(!dbEmail.equals("kim@kim.com")) {
			throw utillService.makeRuntimeEX("장바구니 이메일 불일치", "changeCount");
		}/*else if(num!=1||num!=1) {
			throw utillService.makeRuntimeEX("카운트 수량을 조작하였습니다", "changeCount");
		}*/
		int dbCount=Integer.parseInt(map.get("CCOUNT").toString());
		dbCount +=num;
		
		if(dbCount<=0) {
			buketDao.deleteById(bid);
			return utillService.makeJson(false, "0");
		}else {
			Map<String, Object>map2=new HashMap<String, Object>();
			map2.put("count", dbCount);
			map2.put("bid", bid);
			buketDao.updateCount(map2);
		}
		JSONObject response=new JSONObject();
		response.put("flag", true);
		response.put("count", dbCount);
		response.put("price", dbCount*20000);
		return response;
	}
	@Transactional(rollbackFor = Exception.class)
	public JSONObject deleteCart(deleteCartDto dto,String loginEmail) {
		logger.info("deleteCart");
		List<Map<String, Object>>maps=new ArrayList<Map<String,Object>>();
		List<Integer>integers=dto.getArr();
		for(int i:integers) {
			Map<String, Object>map=buketDao.findByBid(i);
			maps.add(map);
		}
		for(Map<String, Object>map:maps) {
			String dbemail=(String)map.get("CEMAIL");
			if(!dbemail.equals(loginEmail)) {
				throw utillService.makeRuntimeEX("장바구니 이메일이 일치하지 않습니다", "deleteCart");
			}
			
			buketDao.deleteById(Integer.parseInt(map.get("CNUM").toString()));
		}
		return utillService.makeJson(true,"장바구니삭제");
		
	}
}
