package com.gerstox.projects.motostore_backend.services;

import com.gerstox.projects.motostore_backend.entities.Streaming;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface StreamingService {

  public Page<Streaming> findAll(Integer page, Integer elements);

  public Optional<Streaming> findById(Long id);

  public Streaming save(Streaming streaming);

  public Optional<Streaming> update(Long id, Streaming streaming);

  public Optional<Streaming> delete(Long id);

  public Boolean existsById(Long id);
}
