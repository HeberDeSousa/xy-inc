package com.xyinc.poi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyinc.poi.model.Poi;
import com.xyinc.poi.repository.PoiRepository;

@Service
public class PoiService {

	@Autowired
	PoiRepository poiRepository;

	public List<Poi> listar() {
		return poiRepository.findAll();
	}

	public Poi salvar(Poi poi) {
		return poiRepository.saveAndFlush(poi);
	}

	// Dependendo da situação o filtro pode ser feito no Banco de Dados ou no Java
	// Primeiro filtro no Banco de Dados
	public List<Poi> listarPorProximidade(Integer referenciaX, Integer referenciaY, Integer distancia) {
		return poiRepository.listarPorProximidade(referenciaX, referenciaY, distancia);
	}

	// Segundo filtro no Java
	public List<Poi> listarPorProximidade2(Integer referenciaX, Integer referenciaY, Integer distancia) {
		return poiRepository.findAll().stream()
				.filter(d -> (d.getCoordX() - referenciaX) * (d.getCoordX() - referenciaX)
						+ (d.getCoordY() - referenciaY) * (d.getCoordY() - referenciaY) <= distancia * distancia)
				.collect(Collectors.toList());

	}

}
