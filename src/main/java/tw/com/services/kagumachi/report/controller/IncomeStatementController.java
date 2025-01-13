package tw.com.services.kagumachi.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.IncomeStatement;
import tw.com.services.kagumachi.repository.IncomeStatementRepository;

@RestController
@RequestMapping("/incomestatement")
public class IncomeStatementController {
	
	@Autowired
	private IncomeStatementRepository incomeStatementRepository;
	
	@GetMapping("/method1")
	public void method1() {
		List<IncomeStatement> list = incomeStatementRepository.findByTime(null);
		for (IncomeStatement incomeStatement : list) {
			System.out.printf("%s: %s\n", incomeStatement.getTime());
		}
	}
	
	@GetMapping("/method2")
	public void method2() {
		List<IncomeStatement> list = incomeStatementRepository.findByTime(null);
		for (IncomeStatement incomeStatement : list) {
			System.out.printf("%s: %s\n", incomeStatement.getTime());
		}
	}
}
