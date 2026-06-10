package pe.edu.upeu.lib.reservas.client;

import pe.edu.upeu.lib.reservas.dto.NotificacionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-lib-notificaciones")
public interface NotificacionClient { @PostMapping("/notificaciones") Object crear(@RequestBody NotificacionRequest request); }

