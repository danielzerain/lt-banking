package com.ltbanking.account.model;

import jakarta.persistence.*;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "account_banking")
public class AccountBankingEntity extends AutitableEntity {
  @Id
  @GeneratedValue
  @JdbcTypeCode(Types.VARCHAR)
  private UUID id;

  @JdbcTypeCode(Types.VARCHAR)
  private UUID idUserBanking;
  private String accountNumber;
  private Double balance;
  private LocalDateTime creationDate;

}
