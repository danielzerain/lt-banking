package com.ltbanking.cardservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "card_banking")
public class CardEntity extends AutitableEntity{
    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;
    private UUID accountId;
    private String cardNumber;
    private String cardType;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardCVV;
    private String cardSecurityCode;

}
