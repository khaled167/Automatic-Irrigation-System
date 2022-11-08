package ais.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ais.models.LandPlot;

@Repository
public interface LandPlotRepository extends JpaRepository<LandPlot, Long> {

	Optional<LandPlot> findByLocationXAndLocationY(Integer locationX, Integer locationY);
	List<LandPlot> findByAgriculturalCrop(String agriculturalCrop);
}
