package pe.edu.upeu.lib.multas.client;

import pe.edu.upeu.lib.multas.dto.NotificacionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-lib-notificaciones")
public interface NotificacionClient { @PostMapping("/notificaciones") Object crear(@RequestBody NotificacionRequest request); }

