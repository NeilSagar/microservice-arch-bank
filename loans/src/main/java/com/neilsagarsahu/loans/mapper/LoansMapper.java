package com.neilsagarsahu.loans.mapper;

import com.neilsagarsahu.loans.dto.LoansDto;
import com.neilsagarsahu.loans.entity.Loans;

public class LoansMapper {
    public static LoansDto mapToLoansDto(Loans loans,LoansDto loansDto) {
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        return loansDto;
    }
    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        return loans;
    }
}
