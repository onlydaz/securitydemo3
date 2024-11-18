package ltweb.repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    public Role getRoleByName(@Param("name") String name);
}

