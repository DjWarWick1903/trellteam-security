package ro.trellteam.security.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.security.domain.Role;
import ro.trellteam.security.exceptions.TrellGenericException;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl {
    private final RoleRepository roleRepository;

    /**
     * Method used to return all roles.
     * @return List
     */
    public List<Role> list() {
        log.info("RoleService--list--IN");
        final List<Role> roles = roleRepository.findAll();
        log.info("RoleService--list--roles: {}", roles);
        log.info("RoleService--list--OUT");
        return roles;
    }

    /**
     * Method used to return a role starting from it's name.
     * @param name
     * @return Role
     */
    public Role findByName(final String name) {
        log.info("RoleService--findByName--name: {}", name);
        Role role = null;
        try {
            role = roleRepository.findByName(name);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("TRELL_ERR_2");
        }
        return role;
    }

    /**
     *
     * @param id
     * @return
     */
    public Role findById(final Long id) {
        log.info("RoleService--findById--id: {}", id);
        Role role = null;
        try {
            role = roleRepository.findById(id).get();
            if(role == null) throw new Exception("Not found");
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("TRELL_ERR_2");
        }
        log.info("RoleService--findById--role: {}", role);
        log.info("RoleService--findById--OUT");
        return role;
    }

    /**
     * Method used to save a role into the database.
     * @param role
     * @return Role
     */
    public Role save(Role role) {
        log.info("RoleService--save--IN");
        role = roleRepository.save(role);
        log.info("RoleService--save--OUT");
        return role;
    }

    /**
     * Method used to delete a role starting from it's name.
     * @param name
     */
    public void deleteRoleByName(final String name) {
        log.info("RoleService--deleteRoleByName--IN");
        log.info("RoleService--deleteRoleByName--name: {}", name);
        roleRepository.deleteByName(name);
        log.info("RoleService--deleteRoleByName--OUT");
    }

    /**
     * Method used to delete a role from the database starting from it's ID.
     * @param id
     */
    public void deleteRoleById(Long id) {
        log.info("RoleService--deleteRoleById--IN");
        log.info("RoleService--deleteRoleById--id: {}", id);
        roleRepository.deleteById(id);
        log.info("RoleService--deleteRoleById--OUT");
    }
}
