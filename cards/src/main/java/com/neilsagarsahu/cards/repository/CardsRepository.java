package com.neilsagarsahu.cards.repository;

import com.neilsagarsahu.cards.entity.Cards;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.smartcardio.Card;
import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
    Optional<Cards> findByMobileNumber(String mobileNumber);

    Optional<Cards> findByCardNumber(String cardNumber);
}
