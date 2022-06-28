package ru.pycak.todolistapp.user.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.pycak.todolistapp.user.entity.Role;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class RoleDAOImpl implements RoleDAO {

    private final EntityManager entityManager;

    @Override
    public Role get(int roleId) {
        return entityManager.find(Role.class, roleId);
    }

    @Override
    public Role getRoleUser() {
        int roleUserId = 0;
        return entityManager.find(Role.class, roleUserId);
    }
}
