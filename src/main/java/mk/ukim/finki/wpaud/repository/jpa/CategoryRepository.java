package mk.ukim.finki.wpaud.repository.jpa;

import mk.ukim.finki.wpaud.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByNameLike(String text);//so Like ne sakame celsna sovpadlivost.

    void deleteByName(String name);
}
