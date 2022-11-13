package ro.trellteam.security.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.security.domain.Role;
import ro.trellteam.security.dto.RoleDto;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role dtoToDomain(RoleDto roleDto);
    RoleDto domainToDto(Role role);
}
