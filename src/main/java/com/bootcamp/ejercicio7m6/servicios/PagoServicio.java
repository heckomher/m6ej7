package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Pago;
import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.modelos.PagoDTO;
import com.bootcamp.ejercicio7m6.repos.IPagoRepositorio;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PagoServicio {
    private final IPagoRepositorio pagoRepositorio;
    private final IUsuarioRepositorio IUsuarioRepositorio;

    public PagoServicio(final IPagoRepositorio pagoRepositorio, IUsuarioRepositorio IUsuarioRepositorio) {
        this.pagoRepositorio = pagoRepositorio;
        this.IUsuarioRepositorio = IUsuarioRepositorio;
    }

    public List<PagoDTO> findAll() {
        final List<Pago> pagos = pagoRepositorio.findAll(Sort.by("idPago"));
        return pagos.stream()
                .map(pago -> mapToDTO(pago, new PagoDTO()))
                .toList();
    }

    public Long create(final PagoDTO pagoDTO) {
        final Pago pago = new Pago();
        mapToEntity(pagoDTO, pago);

        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Usuario us = IUsuarioRepositorio.findByNombreUsuario(currentPrincipalName);
        pago.setOperador(us);
         */


        final Pago savedPago = pagoRepositorio.save(pago);
        Usuario usuario = IUsuarioRepositorio.findById(pagoDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
            IUsuarioRepositorio.save(usuario);
        return savedPago.getIdPago();
    }

    public void update(final Long idPago, final PagoDTO pagoDTO) {
        final Pago pago = pagoRepositorio.findById(idPago)
                .orElseThrow(NotFoundException::new);
        pagoRepositorio.save(pago);
    }

    public void delete(final Long idPago) {
        pagoRepositorio.deleteById(idPago);
    }

    private PagoDTO mapToDTO(final Pago pago, final PagoDTO pagoDTO) {
        pagoDTO.setIdPago(pago.getIdPago());
        pagoDTO.setMonto(pago.getMonto());
        pagoDTO.setFechaPago(pago.getFechaPago());
        pagoDTO.setUsuario(pago.getUsuario().getIdUsuario());
        return pagoDTO;
    }

    private Pago mapToEntity(final PagoDTO pagoDTO,
                             final Pago pago) {
        pago.setMonto(pagoDTO.getMonto());
        pago.setFechaPago(pagoDTO.getFechaPago());
        final Usuario usuario = pagoDTO.getUsuario() == null ? null : IUsuarioRepositorio.findById(pagoDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
        pago.setUsuario(usuario);
        return pago;
    }



}
