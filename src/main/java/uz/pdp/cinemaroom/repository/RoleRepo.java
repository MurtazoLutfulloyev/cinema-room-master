package uz.pdp.cinemaroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.user.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
}
