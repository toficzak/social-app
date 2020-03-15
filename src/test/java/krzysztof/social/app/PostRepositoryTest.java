package krzysztof.social.app;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import krzysztof.social.app.post.Post;
import krzysztof.social.app.post.PostRepository;
import krzysztof.social.app.user.User;
import krzysztof.social.app.user.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class PostRepositoryTest {

  @Autowired
  PostRepository postRepository;

  @Autowired
  UserRepository userRepository;

  @Test
  public void myTest() throws Exception {

    List<Long> byAuthorIdResults = new ArrayList<>();
    List<Long> byAuthorUserProvidedResults = new ArrayList<>();
    List<Long> byAuthorUserNotProvidedResults = new ArrayList<>();
    List<Long> byCustomQueryResults = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      System.out.println("Case: " + i);
      User author = userRepository.save(new User());
      postRepository.save(new Post("testMessage", author));

      long startTime = System.nanoTime();
      postRepository.findTopByAuthorIdOrderByIdDesc(author.getId());
      long endTime = System.nanoTime();

      long duration = (endTime - startTime);
      byAuthorIdResults.add(duration);

      startTime = System.nanoTime();
      postRepository.findTopByAuthorOrderByIdDesc(author);
      endTime = System.nanoTime();

      duration = (endTime - startTime);
      byAuthorUserNotProvidedResults.add(duration);

      startTime = System.nanoTime();
      postRepository.findTopByAuthorOrderByIdDesc(userRepository.findById(author.getId()).get());
      endTime = System.nanoTime();

      duration = (endTime - startTime);
      byAuthorUserProvidedResults.add(duration);


      startTime = System.nanoTime();
      postRepository.findWithCustomQueryOrderByIdDesc(author.id);
      endTime = System.nanoTime();

      duration = (endTime - startTime);
      byCustomQueryResults.add(duration);
    }

    System.err.println("By author id: "
        + byAuthorIdResults.stream().mapToLong(Long::longValue).average().getAsDouble() + "\n\n"
        + "By author (User provided): "
        + byAuthorUserProvidedResults.stream().mapToLong(Long::longValue).average().getAsDouble()
        + "\n\n" + "By author (User not provided): "
        + byAuthorUserNotProvidedResults.stream().mapToLong(Long::longValue).average().getAsDouble()
        + "\n\n" + "By custom query: "
        + byCustomQueryResults.stream().mapToLong(Long::longValue).average().getAsDouble());

  }



}
