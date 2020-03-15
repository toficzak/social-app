package krzysztof.social.app.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import krzysztof.social.app.post.Post;
import krzysztof.social.app.post.PostDto;
import krzysztof.social.app.post.PostMapper;
import krzysztof.social.app.post.PostRepository;

@RestController
@RequestMapping("users")
public class UserRestController {

  private UserRepository userRepository;
  private PostRepository postRepository;

  public UserRestController(UserRepository userRepository, PostRepository postRepository) {
    super();
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @PostMapping
  public User add() {
    User user = new User();
    userRepository.save(user);
    return user;
  }

  @GetMapping("latestPost")
  public PostDto latestPost(@RequestParam(name = "userId") Long userId) {
    User user = userRepository.findById(userId).get();
    return PostMapper.map(user.getLatestPost());
  }

  @GetMapping("latestPostJpa")
  public PostDto latestPostJpa(@RequestParam(name = "userId") Long userId) {
    Post post = postRepository.findTopByAuthorIdOrderByIdDesc(userId).get();
    return PostMapper.map(post);
  }

  @GetMapping("latestPostJpaAuthor")
  public PostDto latestPostJpaAuthor(@RequestParam(name = "userId") Long userId) {
    // wrong -> two selects, one with join
    User user = userRepository.findById(userId).get();
    Post post = postRepository.findTopByAuthorOrderByIdDesc(user).get();
    return PostMapper.map(post);
  }
}
