package com.porudzbina;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "proizvod-service",
        url = "${application.config.proizvod-url}"
)
public interface ProizvodKlijent {
}
