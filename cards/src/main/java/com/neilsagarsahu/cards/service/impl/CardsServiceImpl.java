package com.neilsagarsahu.cards.service.impl;

import com.neilsagarsahu.cards.constants.CardsConstants;
import com.neilsagarsahu.cards.dto.CardsDto;
import com.neilsagarsahu.cards.entity.Cards;
import com.neilsagarsahu.cards.exception.CardsAlreadyExistsException;
import com.neilsagarsahu.cards.exception.ResourceNotFoundException;
import com.neilsagarsahu.cards.mapper.CardsMapper;
import com.neilsagarsahu.cards.repository.CardsRepository;
import com.neilsagarsahu.cards.service.ICardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardsServiceImpl implements ICardsService {

    @Autowired
    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of the Customer
     **/
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardsAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        Cards createdCard = createNewCard(mobileNumber);
        cardsRepository.save(createdCard);
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isEmpty()){
            throw new ResourceNotFoundException("Cards","Mobile Number", mobileNumber);
        }
        return CardsMapper.mapToCardsDto(optionalCards.get(),new CardsDto());
    }

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return  true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
