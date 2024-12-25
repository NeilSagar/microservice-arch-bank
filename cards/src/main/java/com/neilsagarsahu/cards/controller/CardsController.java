package com.neilsagarsahu.cards.controller;

import com.neilsagarsahu.cards.constants.CardsConstants;
import com.neilsagarsahu.cards.dto.CardsDto;
import com.neilsagarsahu.cards.dto.ResponseDto;
import com.neilsagarsahu.cards.service.ICardsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardsController {
    @Autowired
    private ICardsService cardsService;

    @PostMapping("/create/{mobileNumber}")
    public ResponseEntity<ResponseDto> createCards(
            @PathVariable
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber){
        cardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(
                CardsConstants.STATUS_201, CardsConstants.MESSAGE_201
        ));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCards(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber){
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteCardDetails(@PathVariable
                                                         @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = cardsService.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }
}
