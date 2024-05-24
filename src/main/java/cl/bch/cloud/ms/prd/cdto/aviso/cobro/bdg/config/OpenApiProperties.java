package cl.bch.cloud.ms.prd.cdto.aviso.cobro.bdg.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger.info")
public record OpenApiProperties(
    String version,
    String name,
    String description,
    OpenApiContactProperties contact) {
}
