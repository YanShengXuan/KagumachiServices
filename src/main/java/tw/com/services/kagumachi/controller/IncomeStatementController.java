package tw.com.services.kagumachi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.services.kagumachi.model.IncomeStatement;
import tw.com.services.kagumachi.service.IncomeStatementService;

@RestController
@RequestMapping("/api/incomestatement")
public class IncomeStatementController {

    @Autowired
    private IncomeStatementService incomeStatementService;

    @GetMapping("/year/{year}")
    public IncomeStatement getIncomeStatementForYear(@PathVariable int year) {
        return incomeStatementService.generateIncomeStatementForYear(year);
    }
}
