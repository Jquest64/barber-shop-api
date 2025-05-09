package br.com.dio.barbershopapi.controller;

import br.com.dio.barbershopapi.controller.request.SaveClientRequest;
import br.com.dio.barbershopapi.controller.response.SaveClientResponse;
import br.com.dio.barbershopapi.mapper.IClientMapper;
import br.com.dio.barbershopapi.service.IClientService;
import br.com.dio.barbershopapi.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);

        return mapper.toSaveResponse(entity);

    }

}
