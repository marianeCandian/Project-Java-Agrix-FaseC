package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FarmService.
 */
@Service
public class FarmService {

  private FarmRepository farmRepository;

  /**
   * FarmService constructor.
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Atualiza farm.
   */
  public Optional<Farm> updateFarm(Long id, Farm farm) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isPresent()) {
      Farm farmFromDb = optionalFarm.get();
      farmFromDb.setName(farm.getName());
      farmFromDb.setSize(farm.getSize());

      Farm updatedFarm = farmRepository.save(farmFromDb);
      return Optional.of(updatedFarm);
    }
    return optionalFarm;
  }

  /**
   * Remove farm.
   */
  public Optional<Farm> removeFarmById(Long id) {

    Optional<Farm> farmOptional = farmRepository.findById(id);

    if (farmOptional.isPresent()) {

      farmRepository.deleteById(id);

    }
    return farmOptional;
  }

  public Optional<Farm> getFarmById(Long id) {
    return farmRepository.findById(id);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

}
