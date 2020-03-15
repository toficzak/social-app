package krzysztof.social.app.post;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import krzysztof.social.app.user.User;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

  Optional<Post> findTopByAuthorOrderByIdDesc(User author);

  Optional<Post> findTopByAuthorIdOrderByIdDesc(Long authorId);

  @Query("from Post where author_id = ?1 order by id desc")
  Optional<Post> findWithCustomQueryOrderByIdDesc(Long authorId);

}
