package br.com.dio.barbershopapi.service.query.impl;

import br.com.dio.barbershopapi.entity.ClientEntity;
import br.com.dio.barbershopapi.exception.NotFoundException;
import br.com.dio.barbershopapi.exception.PhoneInUseException;
import br.com.dio.barbershopapi.repository.IClientRepository;
import br.com.dio.barbershopapi.service.query.IClientQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {
    private final IClientRepository repository;


    @Override
    public ClientEntity findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Não foi encontrado o cliente de id" + id)
        );
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if (repository.existsByPhone(phone)) {
            var message = "O telefone " + phone + " já está em uso!";
            throw new PhoneInUseException(message);
        }

    }

    @Override
    public void verifyPhone(long id, String phone) {
        var optional = repository.findByPhone(phone);

        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            var message = "O telefone " + phone + " já está em uso!";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if (repository.existsByEmail(email)) {
            var message = "O e-mail " + email + " já está em uso!";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(long id, String email) {
        var optional = repository.findByEmail(email);

        if (optional.isPresent() && !Objects.equals(optional.get().getEmail(), email)) {
            var message = "O e-mail " + email + " já está em uso!";
            throw new PhoneInUseException(message);
        }
    }
}
