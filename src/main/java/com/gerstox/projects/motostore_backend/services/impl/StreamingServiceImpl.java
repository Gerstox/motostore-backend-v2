package com.gerstox.projects.motostore_backend.services.impl;

import com.gerstox.projects.motostore_backend.entities.Streaming;
import com.gerstox.projects.motostore_backend.repositories.StreamingRepository;
import com.gerstox.projects.motostore_backend.services.StreamingService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StreamingServiceImpl implements StreamingService {

  @Autowired private StreamingRepository streamingRepository;

  @Transactional(readOnly = true)
  @Override
  public Page<Streaming> findAll(Integer page, Integer elements) {
    Pageable pageRequest = PageRequest.of(page, elements);
    return streamingRepository.findAll(pageRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Streaming> findById(Integer id) {
    return streamingRepository.findById(id);
  }

  @Transactional
  @Override
  public Streaming save(Streaming streaming) {
    streaming.setStatus(true);
    return streamingRepository.save(streaming);
  }

  @Transactional
  @Override
  public Optional<Streaming> update(Integer id, Streaming streaming) {
    Optional<Streaming> streamingOptional = streamingRepository.findById(id);

    if (streamingOptional.isEmpty()) {
      System.out.println("ERROR: El servicio no existe.");
    }

    Streaming streamingDB = streamingOptional.orElseThrow();
    streamingDB =
        Streaming.builder()
            .name(streaming.getName())
            .image(streaming.getImage())
            .description(streaming.getDescription())
            .duration(streaming.getDuration())
            .price(streaming.getPrice())
            .build();

    return Optional.of(streamingRepository.save(streamingDB));
  }

  @Transactional
  @Override
  public Optional<Streaming> delete(Integer id) {
    Optional<Streaming> streamingOptional = streamingRepository.findById(id);

    streamingOptional.ifPresent(streamingDB -> {
      streamingRepository.delete(streamingDB);
    });

    return streamingOptional;
  }

  @Transactional
  @Override
  public Boolean existsById(Integer id) {
    return streamingRepository.existsById(id);
  }
}
