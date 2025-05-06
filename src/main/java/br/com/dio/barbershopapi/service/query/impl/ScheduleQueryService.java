package br.com.dio.barbershopapi.service.query.impl;

import br.com.dio.barbershopapi.entity.ScheduleEntity;
import br.com.dio.barbershopapi.exception.NotFoundException;
import br.com.dio.barbershopapi.exception.ScheduleInUseException;
import br.com.dio.barbershopapi.repository.IScheduleRepository;
import br.com.dio.barbershopapi.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private  final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Agendamento não encontrado!")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThenEqualOrderByStartAtEndAt(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if (repository.existsStartAtAndEndAt(startAt, endAt)) {
            var message = "Já existe um cliente agendado no horário solicitado!";
            throw new ScheduleInUseException(message);
        }
    }
}
