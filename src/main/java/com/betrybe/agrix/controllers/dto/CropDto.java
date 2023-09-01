package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * Crop dto.
 */
public record CropDto(Long id, String name, Long farmId, Double plantedArea, LocalDate plantedDate,
                      LocalDate harvestDate) {


  public Crop toCrop(Long farmId) {
    return new Crop(id, farmId, name, plantedArea, plantedDate, harvestDate, null);
  }

  public Crop toCrop() {
    return new Crop(id, farmId, name, plantedArea, plantedDate, harvestDate);
  }

}
