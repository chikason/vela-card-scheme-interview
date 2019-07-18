package com.vela.work.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vela.model.CardModel;
import com.vela.model.StatModel;
import com.vela.work.entities.Card;
import com.vela.work.entities.Stats;
import com.vela.work.repository.StatRepository;
import com.vela.work.services.cardServiceImp;

@RestController
@RequestMapping("card-scheme/")
public class VelaControllerAPI {
	
	@Autowired
	private cardServiceImp cardservice;

	@Autowired
	private StatRepository statsRepo;

	@GetMapping(path="verify/{card_no}")
	private ModelAndView verifyCard(@PathVariable("card_no")long card_no, ModelMap modelmap) {
		CardModel cardmodel;
		
		try {
			Card card = cardservice.getCardVerified(card_no);
			
			
			//checks if card detail has been registered to the stats table
			if(card != null) {
			  if(isCardOnStat(card_no)) {
					//increments the number of view records if card has been registered
					 statsRepo.updateStatistics(card_no);
			  }
			  
			}

			
			cardmodel = new CardModel(true, card);
			//builds the model map to return to the client
			modelmap = getModel(cardmodel, card_no, "card information");
			
			//shows the client or user the response detail on html file
			return new ModelAndView("cardInfo", modelmap);
		}
		catch(Exception e) {
			cardmodel = new CardModel(false, new Card());
			//builds the model map to return to the client
			modelmap = getModel(cardmodel, card_no, "card is not valid");
			
			//shows the client or user the response detail on html file
			return new ModelAndView("cardInfo", modelmap);
		}
		
	}

	@GetMapping(path="stats", produces= MediaType.APPLICATION_JSON_VALUE)
	private ModelAndView fetchStats(@RequestParam("start")int start, @RequestParam("limit")int limit) {
		StatModel statmodel;
		Map<String, Object> modelMap = new HashMap<>();
		try {
			List<Stats> stat = cardservice.getCartStats(start, limit);
			statmodel = new StatModel(true, start, limit, stat.size(), stat);
			modelMap.put("card_stat", statmodel);
			
			return new ModelAndView("showStats", modelMap);
		}
		catch(Exception e) {
			statmodel = new StatModel(false, start, limit, 0,new ArrayList<Stats>());
			modelMap.put("card_stat", statmodel);
			
			return new ModelAndView("showStats",modelMap);
		}
		
	}
	
	private boolean isCardOnStat(long cardno) {
		Stats st;
		try {
			st = statsRepo.findByCardNo(cardno);
			
			if(st == null) {
				System.out.println("card not on stat");
				// register card detail to the stat table if it has not been registeredbfore	  
				  st = new Stats();
				  st.setCardNo(cardno);
				  st.setRecords(1);
				  statsRepo.save(st);
				  
				  return false;
			}
			else {
				 return true;
			}
			
		}
		catch(Exception e) {
			System.out.print("card not in stats 2");
			System.out.print(e.getMessage());
			return false;
		}
	}
	
	private ModelMap getModel(CardModel cardmodel, long cardno,String msg) {
		ModelMap modelmap = new ModelMap();
		modelmap.addAttribute("success", cardmodel.isSuccess());
		modelmap.addAttribute("bank", cardmodel.getPayload().getBank());
		modelmap.addAttribute("scheme", cardmodel.getPayload().getScheme());
		modelmap.addAttribute("type", cardmodel.getPayload().getType());
		modelmap.addAttribute("msg", msg);
		modelmap.addAttribute("card_no", cardno);
		
		return modelmap;
	}
}
