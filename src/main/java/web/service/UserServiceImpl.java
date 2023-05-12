package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void saveUser(User user, String role) {
        user.setPassword(user.getPassword());
        user.setName(user.getName());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.findRoleByAuthority(role));
        user.setRoles(roleSet);
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(User user, String role) {
        user.setPassword(user.getPassword());
        user.setName(user.getName());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.findRoleByAuthority(role));
        user.setRoles(roleSet);
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
