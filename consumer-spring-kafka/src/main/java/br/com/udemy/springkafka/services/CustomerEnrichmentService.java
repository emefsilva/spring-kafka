package br.com.udemy.springkafka.services;

import br.com.udemy.springkafka.avro.PeopleEvent;
import br.com.udemy.springkafka.domain.CustomerSummary;
import br.com.udemy.springkafka.domain.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerEnrichmentService {

    private final CustomerProfileService customerProfileService;
    private final TransactionService transactionService;
    private final AddressService addressService;
    private final DollarService dollarService;
    private final InvestmentService investmentService;
    private final AccountCategoryService accountCategoryService;
    private final FinancialCalculationService financialCalculationService;

    public CustomerEnrichmentService(CustomerProfileService customerProfileService, TransactionService transactionService, AddressService addressService, DollarService dollarService, InvestmentService investmentService, AccountCategoryService accountCategoryService, FinancialCalculationService financialCalculationService) {
        this.customerProfileService = customerProfileService;
        this.transactionService = transactionService;
        this.addressService = addressService;
        this.dollarService = dollarService;
        this.investmentService = investmentService;
        this.accountCategoryService = accountCategoryService;
        this.financialCalculationService = financialCalculationService;
    }

    public CustomerSummary process(PeopleEvent event) {

        var profile = customerProfileService.findByCpf(event.getCpf().toString());
        List<Transaction> transactions =  transactionService.findByCpf(event.getCpf().toString());
        var totalDeposits = financialCalculationService.calculateDeposits(transactions);
        var totalWithdraws = financialCalculationService.calculateWithdraws(transactions);
        var netBalance = financialCalculationService.calculateNetBalance(totalDeposits, totalWithdraws);
        var address =  addressService.findAddressByCep(event.getCep().toString());
        var dollarRate = dollarService.getCurrentDollarRate();
        var investmentBRL =  investmentService.convertDollarToReal(BigDecimal.valueOf(event.getInvestmentDollar()),
                dollarRate);
        var accountCategory = accountCategoryService.classify(netBalance, investmentBRL);

        return CustomerSummary.builder()
                .name(event.getName().toString())
                .cpf(event.getCpf().toString())

                .agency(profile.getAgency())
                .account(profile.getAccount())
                .typeAccount(profile.getTypeAccount())
                .segment(profile.getSegment())

                .address(address)

                .investmentDollar(BigDecimal.valueOf(event.getInvestmentDollar()))

                .totalDeposits(totalDeposits)
                .totalWithdraws(totalWithdraws)
                .netBalance(netBalance)
                .dollarRate(dollarRate)
                .investmentBRL(investmentBRL)
                .accountCategory(accountCategory)
                .processedAt(LocalDateTime.now())
                .build();
    }
}
