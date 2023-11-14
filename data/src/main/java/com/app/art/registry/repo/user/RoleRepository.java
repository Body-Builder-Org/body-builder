package com.app.art.registry.repo.user;

import com.app.art.registry.model.user.Role;
import com.app.art.registry.projection.user.RoleLightView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, BigInteger> {

    @Query("from Role r left join fetch r.permissions where r.name = ?1")
    Role findRoleByNameWithPermissions(String name);

    @Query("from Role r left join fetch r.permissions where r.id = ?1")
    Optional<Role> findById(BigInteger id);

    @Query("select r.id as id, r.name as name from Role r where r.id = ?1")
    RoleLightView getRoleByIdLight(BigInteger id);

}