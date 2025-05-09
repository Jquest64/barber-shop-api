package br.com.dio.barbershopapi.mapper;

import br.com.dio.barbershopapi.controller.request.SaveClientRequest;
import br.com.dio.barbershopapi.controller.response.SaveClientResponse;
import br.com.dio.barbershopapi.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);
}
