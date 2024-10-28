package com.gerstox.projects.motostore_backend.services;

import com.gerstox.projects.motostore_backend.entities.Recharge;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface RechargeService {

  public Page<Recharge> findAll(Integer page, Integer elements);

  public Optional<Recharge> findById(Integer id);

  public Recharge save(Recharge recharge);

  public Optional<Recharge> update(Integer id, Recharge recharge);

  public Optional<Recharge> delete(Integer id);
}
