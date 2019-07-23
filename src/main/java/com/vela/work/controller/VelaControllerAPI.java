package com.vela.work.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.vela.model.CardModel;
import com.vela.model.StatModel;
import com.vela.work.entities.Card;
import com.vela.work.entities.Stats;
import com.vela.work.repository.StatRepository;
import com.vela.work.services.CardService;

@RestController
@RequestMapping("card-scheme/")
public class VelaControllerAPI {
	
	@Autowired
	private CardService cardservice;

	@Autowired
	private StatRepository statsRepo;
	
	@Autowired 
	RestTemplate template;
	
	@Value("${urlApi}")
	private String url;

	@GetMapping(path="verify/{card_no}")
	private ModelAndView verifyCard(@PathVariable("card_no")String cardNo, ModelMap modelmap) {
		//makes an API call to get the data of the card.
		CardModel cardModel = new CardModel();
		 Card model = template.getForObject(url+cardNo, Card.class);
		 
		          if(model == null) {
		        	  //input the card into the database
		        	  Card card = new Card();
		        	  card.setCardNo(cardNo);
		        	  cardservice.addCard(card);
		        	  
		  			//shows the client or user the response detail on html file
		        	  cardModel.setPayload(card);
		        	  cardModel.setSuccess(false);
		  			return new ModelAndView("cardInfo", getModel(null, cardNo, "invalid card."));
		          }
		          else {
		        	// checks if card has been registered on stat table if not then register the card  
			       if(!isCardOnStat(cardNo)) {
			    	   Stats stat = new Stats();
			    	         stat.setRecords(1);
			    	         stat.setCardNo(cardNo);
			    	         
			    	   statsRepo.save(stat);
			       }
			       else {
			    	   // increments the card record in the stat table
			    	   statsRepo.updateStatistics(cardNo);
			       }
			       
			//shows the client or user the response detail on html file
			       cardModel.setPayload(model);
			       cardModel.setSuccess(true);
			  return new ModelAndView("cardInfo", getModel(cardModel, cardNo, "Card Exist"));

		  }
	}

	@GetMapping(path="stats", produces= MediaType.APPLICATION_JSON_VALUE)
	private ModelAndView fetchStats(@RequestParam("start")int start, @RequestParam("limit")int limit) {
		Map<String, Object> modelMap = new HashMap<>();
		
			List<Stats> stat = cardservice.getCartStats(start, limit);
			StatModel statmodel = new StatModel(true, start, limit, stat.size(), stat);
			modelMap.put("card_stat", statmodel);
			
			return new ModelAndView("showStats", modelMap);
		
	}
	
	private boolean isCardOnStat(String cardno) {
		
		Stats st = statsRepo.findByCardNo(cardno);
		
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
	
	
	private ModelMap getModel(CardModel cardmodel, String cardNo,String msg) {
		ModelMap modelmap = new ModelMap();
		modelmap.addAttribute("success", cardmodel.isSuccess());
		modelmap.addAttribute("bank", cardmodel.getPayload().getBank());
		modelmap.addAttribute("scheme", cardmodel.getPayload().getScheme());
		modelmap.addAttribute("type", cardmodel.getPayload().getType());
		modelmap.addAttribute("msg", msg);
		modelmap.addAttribute("card_no", cardNo);
		
		return modelmap;
	}

}
