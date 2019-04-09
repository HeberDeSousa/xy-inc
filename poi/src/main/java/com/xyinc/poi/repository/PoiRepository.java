package com.xyinc.poi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xyinc.poi.model.Poi;

public interface PoiRepository extends JpaRepository<Poi, Long> {

	@Query("SELECT p "
			+ " FROM Poi p"
			+ " WHERE "
			+ " 	(p.coordX - :referenciaX) * (p.coordX - :referenciaX) + (p.coordY - :referenciaY) * (p.coordY - :referenciaY) <= :distancia * :distancia")
	List<Poi> listarPorProximidade(@Param("referenciaX") Integer referenciaX, @Param("referenciaY") Integer referenciaY,
			@Param("distancia") Integer distancia);

}
