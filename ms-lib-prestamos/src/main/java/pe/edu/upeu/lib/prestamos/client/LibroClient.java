package pe.edu.upeu.lib.prestamos.client;

import pe.edu.upeu.lib.prestamos.dto.LibroDTO;
import pe.edu.upeu.lib.prestamos.dto.StockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-lib-libros")
public interface LibroClient {
    @GetMapping("/libros/{id}") LibroDTO obtener(@PathVariable Long id);
    @PutMapping("/libros/{id}/stock/descontar") LibroDTO descontar(@PathVariable Long id, @RequestBody StockRequest request);
    @PutMapping("/libros/{id}/stock/aumentar") LibroDTO aumentar(@PathVariable Long id, @RequestBody StockRequest request);
}

