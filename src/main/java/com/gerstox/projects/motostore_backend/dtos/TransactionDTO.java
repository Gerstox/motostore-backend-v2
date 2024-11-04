package com.gerstox.projects.motostore_backend.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

  @NotNull private Long clientId;
  @NotNull private Long serviceId;
  @NotNull private Double amount;
}
