package com.neilsagarsahu.loans.controller;

import com.neilsagarsahu.loans.constants.LoansConstants;
import com.neilsagarsahu.loans.dto.LoansDto;
import com.neilsagarsahu.loans.dto.ResponseDto;
import com.neilsagarsahu.loans.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    @Autowired
    private ILoansService loansService;

    @PostMapping("/create/{mobileNumber}")
    public ResponseEntity<ResponseDto> createLoan(
            @PathVariable
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
            String mobileNumber) {
        System.out.println(mobileNumber);
        loansService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
                LoansConstants.STATUS_201,LoansConstants.MESSAGE_201
        ));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
            String mobileNumber) {
        LoansDto loansDto = loansService.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoansDto loansDto)  {
        boolean updated = loansService.updateLoan(loansDto);
        if(!updated) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
           LoansConstants.STATUS_200,LoansConstants.MESSAGE_200
        ));
    }

    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteLoanDetails(
            @PathVariable
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {
        boolean isDeleted = loansService.deleteLoan(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }
}
