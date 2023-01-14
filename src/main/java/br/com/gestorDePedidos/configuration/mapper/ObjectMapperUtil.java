package br.com.gestorDePedidos.configuration.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public interface ObjectMapperUtil {

    static ObjectMapper mapper = new ObjectMapper();

    static <T> T convertTo(Object value,Class<T> clazz){
        mapper.registerModule(new JavaTimeModule());
        return (T) mapper.convertValue(value, clazz);
    }

    @SneakyThrows
    static <T> T convertMsgTo(String value, Class<T> clazz){
        mapper.registerModule(new JavaTimeModule());
        return (T) mapper.readValue(value, clazz);
    }
}
