package pe.edu.upeu.lib.usuarios.service;

import pe.edu.upeu.lib.usuarios.dto.UsuarioDTO;
import pe.edu.upeu.lib.usuarios.entity.Usuario;
import pe.edu.upeu.lib.usuarios.exception.RecursoNoEncontradoException;
import pe.edu.upeu.lib.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    public UsuarioService(UsuarioRepository repository) { this.repository = repository; }
    public List<UsuarioDTO> listar() { return repository.findAll().stream().map(this::toDto).toList(); }
    public UsuarioDTO obtener(Long id) { return toDto(buscar(id)); }
    public UsuarioDTO crear(UsuarioDTO dto) { return toDto(repository.save(toEntity(dto, new Usuario()))); }
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) { return toDto(repository.save(toEntity(dto, buscar(id)))); }
    public void eliminar(Long id) { repository.delete(buscar(id)); }
    private Usuario buscar(Long id) { return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado: " + id)); }
    private UsuarioDTO toDto(Usuario u) { return new UsuarioDTO(u.getIdUsuario(), u.getNombre(), u.getApellido(), u.getCorreo(), u.getPassword(), u.getTelefono(), u.getDireccion(), u.getTipoUsuario(), u.getEstado(), u.getFechaRegistro()); }
    private Usuario toEntity(UsuarioDTO dto, Usuario u) { u.setNombre(dto.nombre()); u.setApellido(dto.apellido()); u.setCorreo(dto.correo()); u.setPassword(dto.password()); u.setTelefono(dto.telefono()); u.setDireccion(dto.direccion()); u.setTipoUsuario(dto.tipoUsuario()); u.setEstado(dto.estado()); u.setFechaRegistro(dto.fechaRegistro()); return u; }
}
