package tw.com.services.kagumachi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.dto.FinanceDto;
import tw.com.services.kagumachi.model.Finance;
import tw.com.services.kagumachi.repository.FinanceRepository;


@RestController
@RequestMapping("/finance")
public class FinanceController {
	
	@Autowired
	FinanceRepository financeRepository;
	
	@GetMapping("/all")
	public List<Finance> getAll(){
		return financeRepository.findAll();
	}
	
	@PostMapping("/between")
	public List<Finance> getBetween(@RequestBody FinanceDto item){
		return financeRepository.findByDateBetween(item.getStartDate(), item.getEndDate());
	}
	
	@PostMapping("/item")
	public List<Finance> getItem(@RequestBody FinanceDto data){
		return financeRepository.findByDateBetweenAndItem(data.getStartDate(), data.getEndDate(), 
				data.getItem());
	}
	
	@PostMapping("/create")
	public void createFinance(@RequestBody FinanceDto data){
		Finance finance = new Finance();
		finance.setDate(data.getDate());
		finance.setItem(data.getItem());
		finance.setDetails(data.getDetails());
		finance.setMoney(data.getMoney());
		financeRepository.save(finance);
	}
	
	@PostMapping("/revise")
	public void createRrevise(@RequestBody FinanceDto data){
		Finance finance = new Finance();
		finance.setFinanceid(data.getFinanceid());
		finance.setDate(data.getDate());
		finance.setItem(data.getItem());
		finance.setDetails(data.getDetails());
		finance.setMoney(data.getMoney());
		financeRepository.save(finance);
	}
}
