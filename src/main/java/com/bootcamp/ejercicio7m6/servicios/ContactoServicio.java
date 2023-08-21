package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Contacto;
import com.bootcamp.ejercicio7m6.modelos.ContactoDTO;
import com.bootcamp.ejercicio7m6.repos.IContactoRepositorio;
import com.bootcamp.ejercicio7m6.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ContactoServicio {

    private final IContactoRepositorio IContactoRepositorio;

    public ContactoServicio(final IContactoRepositorio IContactoRepositorio) {
        this.IContactoRepositorio = IContactoRepositorio;
    }

    public List<ContactoDTO> findAll() {
        final List<Contacto> contactos = IContactoRepositorio.findAll(Sort.by("idContacto"));
        return contactos.stream()
                .map(contacto -> mapToDTO(contacto, new ContactoDTO()))
                .toList();
    }

    public ContactoDTO get(final Integer idContacto) {
        return IContactoRepositorio.findById(idContacto)
                .map(contacto -> mapToDTO(contacto, new ContactoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ContactoDTO contactoDTO) {
        final Contacto contacto = new Contacto();
        mapToEntity(contactoDTO, contacto);
        return IContactoRepositorio.save(contacto).getIdContacto();
    }

    public void update(final Integer idContacto, final ContactoDTO contactoDTO) {
        final Contacto contacto = IContactoRepositorio.findById(idContacto)
                .orElseThrow(NotFoundException::new);
        mapToEntity(contactoDTO, contacto);
        IContactoRepositorio.save(contacto);
    }

    public void delete(final Integer idContacto) {
        IContactoRepositorio.deleteById(idContacto);
    }

    private ContactoDTO mapToDTO(final Contacto contacto, final ContactoDTO contactoDTO) {
        contactoDTO.setIdContacto(contacto.getIdContacto());
        contactoDTO.setCorreo(contacto.getCorreo());
        contactoDTO.setMensaje(contacto.getMensaje());
        contactoDTO.setNombre(contacto.getNombre());
        return contactoDTO;
    }

    private Contacto mapToEntity(final ContactoDTO contactoDTO, final Contacto contacto) {
        contacto.setCorreo(contactoDTO.getCorreo());
        contacto.setMensaje(contactoDTO.getMensaje());
        contacto.setNombre(contactoDTO.getNombre());
        return contacto;
    }

}
