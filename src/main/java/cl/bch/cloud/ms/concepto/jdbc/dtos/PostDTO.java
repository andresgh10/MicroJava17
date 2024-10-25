package cl.bch.cloud.ms.concepto.jdbc.dtos;

public record PostDTO(Integer userId,
        Integer id,
        String title,
        String body) {
}