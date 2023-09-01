package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.NotFoundError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {

  private CropRepository cropRepository;
  private FertilizerRepository fertilizerRepository;

  /**
   * Crop constructor.
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public Crop insertCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Atualiza crop.
   */
  public Optional<Crop> updateCrop(Long id, Crop crop) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);

    if (optionalCrop.isPresent()) {
      Crop cropFromDb = optionalCrop.get();
      cropFromDb.setName(crop.getName());
      cropFromDb.setPlantedArea(crop.getPlantedArea());
      cropFromDb.setFarmId(crop.getFarmId());


      Crop updatedCrop = cropRepository.save(cropFromDb);
      return Optional.of(updatedCrop);
    }
    return optionalCrop;
  }

  /**
   * Remove crop.
   */
  public Optional<Crop> removeCropById(Long id) {

    Optional<Crop> cropOptional = cropRepository.findById(id);

    if (cropOptional.isPresent()) {

      cropRepository.deleteById(id);

    }
    return cropOptional;
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Metodo para salvar um fertilizante em uma plantação.
   */
  public void setFertilizerToCrop(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new NotFoundError("Plantação não encontrada!");
    }

    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);

    if (optionalFertilizer.isEmpty()) {
      throw new NotFoundError("Fertilizante não encontrado!");
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);

    cropRepository.save(crop);
    fertilizerRepository.save(fertilizer);
  }

  /**
   * Metodo para pegar todos os fertilizantes por um cropId.
   */
  public List<Fertilizer> getFertilizerByCropId(long cropId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new NotFoundError("Plantação não encontrada!");
    }

    Crop crop = optionalCrop.get();
    return crop.getFertilizers();
  }
}
