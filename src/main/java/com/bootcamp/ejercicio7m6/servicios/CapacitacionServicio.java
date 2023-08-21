package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Capacitacion;
import com.bootcamp.ejercicio7m6.modelos.CapacitacionDTO;
import com.bootcamp.ejercicio7m6.repos.ICapacitacionRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CapacitacionServicio {

    private final ICapacitacionRepositorio ICapacitacionRepositorio;

    public CapacitacionServicio(final ICapacitacionRepositorio ICapacitacionRepositorio) {
        this.ICapacitacionRepositorio = ICapacitacionRepositorio;
    }

    public List<CapacitacionDTO> findAll() {
        final List<Capacitacion> capacitacions = ICapacitacionRepositorio.findAll(Sort.by("numCapacitacion"));
        return capacitacions.stream()
                .map(capacitacion -> mapToDTO(capacitacion, new CapacitacionDTO()))
                .toList();
    }

    public CapacitacionDTO get(final Integer numCapacitacion) {
        return ICapacitacionRepositorio.findById(numCapacitacion)
                .map(capacitacion -> mapToDTO(capacitacion, new CapacitacionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CapacitacionDTO capacitacionDTO) {
        final Capacitacion capacitacion = new Capacitacion();
        mapToEntity(capacitacionDTO, capacitacion);
        return ICapacitacionRepositorio.save(capacitacion).getNumCapacitacion();
    }

    public void update(final Integer numCapacitacion, final CapacitacionDTO capacitacionDTO) {
        final Capacitacion capacitacion = ICapacitacionRepositorio.findById(numCapacitacion)
                .orElseThrow(NotFoundException::new);
        mapToEntity(capacitacionDTO, capacitacion);
        ICapacitacionRepositorio.save(capacitacion);
    }

    public void delete(final Integer numCapacitacion) {
        ICapacitacionRepositorio.deleteById(numCapacitacion);
    }

    private CapacitacionDTO mapToDTO(final Capacitacion capacitacion,
            final CapacitacionDTO capacitacionDTO) {
        capacitacionDTO.setNumCapacitacion(capacitacion.getNumCapacitacion());
        capacitacionDTO.setCantidadAsistentes(capacitacion.getCantidadAsistentes());
        capacitacionDTO.setDetalle(capacitacion.getDetalle());
        capacitacionDTO.setDiaSemana(capacitacion.getDiaSemana());
        capacitacionDTO.setDuracion(capacitacion.getDuracion());
        capacitacionDTO.setHora(capacitacion.getHora());
        capacitacionDTO.setLugar(capacitacion.getLugar());
        capacitacionDTO.setNombre(capacitacion.getNombre());
        return capacitacionDTO;
    }

    private Capacitacion mapToEntity(final CapacitacionDTO capacitacionDTO,
            final Capacitacion capacitacion) {
        capacitacion.setCantidadAsistentes(capacitacionDTO.getCantidadAsistentes());
        capacitacion.setDetalle(capacitacionDTO.getDetalle());
        capacitacion.setDiaSemana(capacitacionDTO.getDiaSemana());
        capacitacion.setDuracion(capacitacionDTO.getDuracion());
        capacitacion.setHora(capacitacionDTO.getHora());
        capacitacion.setLugar(capacitacionDTO.getLugar());
        capacitacion.setNombre(capacitacionDTO.getNombre());
        return capacitacion;
    }

}
