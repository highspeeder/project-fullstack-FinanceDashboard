package com.dashboard.server.dto.product;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TransactionDtoSerializer extends JsonSerializer<TransactionDto> {

    @Override
    public void serialize(TransactionDto transactionDto, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", transactionDto.getId());
        jsonGenerator.writeNumberField("amount", transactionDto.getAmount());
        jsonGenerator.writeStringField("buyer", transactionDto.getBuyer());
        jsonGenerator.writeStringField("createdAt", transactionDto.getCreatedAt().toString());
        jsonGenerator.writeStringField("updatedAt", transactionDto.getUpdatedAt().toString());
        jsonGenerator.writeEndObject();
    }
}
