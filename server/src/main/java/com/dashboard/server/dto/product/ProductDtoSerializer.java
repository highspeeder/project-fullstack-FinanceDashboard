package com.dashboard.server.dto.product;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ProductDtoSerializer extends JsonSerializer<ProductDto> {

    @Override
    public void serialize(ProductDto productDto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", productDto.getId());
        jsonGenerator.writeNumberField("price", productDto.getPrice());
        jsonGenerator.writeNumberField("expense", productDto.getExpense());
        jsonGenerator.writeStringField("createdAt", productDto.getCreatedAt().toString());
        jsonGenerator.writeStringField("updatedAt", productDto.getUpdatedAt().toString());
        jsonGenerator.writeEndObject();
    }
}
