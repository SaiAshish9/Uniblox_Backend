package com.sai.Uniblox.transformer;

import com.sai.Uniblox.dto.OrderRequestDTO;
import com.sai.Uniblox.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order orderDTOToOrder(OrderRequestDTO userDTO);
}

