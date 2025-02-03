package tw.com.services.kagumachi.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.services.kagumachi.model.IncomeStatement;
import tw.com.services.kagumachi.repository.OrderDetailRepository;
import tw.com.services.kagumachi.repository.OrderRepository;

@Service
public class IncomeStatementService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Double calculateTotalRevenueForYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return orderRepository.findTotalRevenueByDateRange(startDate, endDate);
    }
    
    public Double calculateTotalCostForYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return orderDetailRepository.findTotalCostByDateRange(startDate, endDate);
    }

    public IncomeStatement generateIncomeStatementForYear(int year) {
        Double totalRevenue = calculateTotalRevenueForYear(year);
        Double totalCost = calculateTotalCostForYear(year);

        IncomeStatement incomeStatement = new IncomeStatement();
        incomeStatement.setStartDate(LocalDate.of(year, 1, 1));
        incomeStatement.setEndDate(LocalDate.of(year, 12, 31));
        incomeStatement.setTotalRevenue(totalRevenue);
        incomeStatement.setTotalCost(totalCost);
        incomeStatement.setNonOperatingIncomeAndExpense(0.0); // 預設為 0
        incomeStatement.setTax(0.0); // 預設為 0

        return incomeStatement;
    }
}
