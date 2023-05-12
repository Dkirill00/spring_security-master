package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRolesList();
    Role getRoleById(Long id);

    Role getRoleByRole(String role);

    Role findRoleByAuthority(String authority);

}