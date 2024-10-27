package com.example.mercearia.services;

import br.com.cafe.security.dto.SignupRequestDTO;
import br.com.cafe.security.entities.Foto;
import br.com.cafe.security.entities.User;
import br.com.cafe.security.repositories.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    public  Foto cadastrarFoto(MultipartFile foto, User user) throws IOException {
        Foto fotoUsuario = new Foto();
        fotoUsuario.setDados(foto.getBytes());
        fotoUsuario.setTipo(foto.getContentType());
        fotoUsuario.setNome(foto.getOriginalFilename());
        fotoUsuario.setUser(user);
        fotoUsuario.setUrl(addImageUri(user));
        return fotoRepository.save(fotoUsuario);
    }

    private String addImageUri(User user) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/{id}/foto")
                .buildAndExpand(user.getId()).toUri();
        return uri.toString();
    }

    @Transactional(readOnly = true)
    public byte[] buscarFotoPorIdUsuario(Integer idUsuario) throws Exception {
        Foto foto = fotoRepository.buscarFotoPorIdUsuario(idUsuario);
        if (foto == null) {
            throw new Exception("Foto não encontrada para o usuário de id: " + idUsuario);
        }
        return foto.getDados();
    }

}
