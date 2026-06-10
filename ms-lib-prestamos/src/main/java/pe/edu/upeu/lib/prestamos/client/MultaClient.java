package pe.edu.upeu.lib.prestamos.client;

import pe.edu.upeu.lib.prestamos.dto.MultaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-lib-multas")
public interface MultaClient {
    @PostMapping("/multas") Object crear(@RequestBody MultaRequest request);
}

