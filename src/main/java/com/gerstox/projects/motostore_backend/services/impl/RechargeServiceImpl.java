package com.gerstox.projects.motostore_backend.services.impl;

import com.gerstox.projects.motostore_backend.entities.Recharge;
import com.gerstox.projects.motostore_backend.entities.Streaming;
import com.gerstox.projects.motostore_backend.repositories.RechargeRepository;
import com.gerstox.projects.motostore_backend.services.RechargeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RechargeServiceImpl implements RechargeService {

  @Autowired private RechargeRepository rechargeRepository;

  @Transactional(readOnly = true)
  @Override
  public Page<Recharge> findAll(Integer page, Integer elements) {
    Pageable pageRequest = PageRequest.of(page, elements);
    return rechargeRepository.findAll(pageRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Recharge> findById(Integer id) {
    return rechargeRepository.findById(id);
  }

  @Transactional
  @Override
  public Recharge save(Recharge recharge) {
    return rechargeRepository.save(recharge);
  }

  @Transactional
  @Override
  public Optional<Recharge> update(Integer id, Recharge recharge) {
    Optional<Recharge> rechargeOptional = rechargeRepository.findById(id);

    if (rechargeOptional.isEmpty()) {
      System.out.println("ERROR: El servicio no existe.");
    }

    Recharge rechargeDB = rechargeOptional.orElseThrow();
    rechargeDB =
        Recharge.builder()
            .provider(recharge.getProvider())
            .name(recharge.getName())
            .description(recharge.getDescription())
            .image(recharge.getImage())
            .build();

    return Optional.of(rechargeRepository.save(rechargeDB));
  }

  @Transactional
  @Override
  public Optional<Recharge> delete(Integer id) {
    Optional<Recharge> rechargeOptional = rechargeRepository.findById(id);

    rechargeOptional.ifPresent(rechargeDB -> {
      rechargeRepository.delete(rechargeDB);
    });

    return rechargeOptional;
  }

  @Transactional
  @Override
  public Boolean existsById(Integer id) {
    return rechargeRepository.existsById(id);
  }
}
