package com.epam.spring.homework3.mapper;

import com.epam.spring.homework3.model.DTO.RequestDTO;
import com.epam.spring.homework3.model.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    Request mapRequest(RequestDTO requestDTO);

    RequestDTO mapRequestDTO(Request request);
}
