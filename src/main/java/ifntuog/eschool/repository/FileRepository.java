package ifntuog.eschool.repository;

import ifntuog.eschool.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
