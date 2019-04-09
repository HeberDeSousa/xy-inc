package com.xyinc.poi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyinc.poi.model.Poi;
import com.xyinc.poi.model.Referencia;
import com.xyinc.poi.service.PoiService;

@RestController
@RequestMapping("poi/v1/")
public class PoiController {

	@Autowired
	private PoiService poiService;

	@PostMapping(value = "cadastrar")
	public Poi cadastrar(@RequestBody Poi poi) {
		return poiService.salvar(poi);
	}

	@GetMapping(value = "listar")
	public List<Poi> listar() {
		return poiService.listar();
	}

	@PostMapping(value = "listarPorProximidade")
	public List<Poi> listarPorProximidade(@RequestBody Referencia referencia) {
		// Lista pelo Filtro feito do Banco
		// return poiService.listarPorProximidade(referencia.getReferenciaX(), referencia.getReferenciaY(), referencia.getDistanciaMaxima());
		
		// Lista pelo Filtro feito no Java 
		return poiService.listarPorProximidade2(referencia.getReferenciaX(), referencia.getReferenciaY(), referencia.getDistanciaMaxima());
	}

}
