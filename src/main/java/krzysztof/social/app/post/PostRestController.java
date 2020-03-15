package krzysztof.social.app.post;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import krzysztof.social.app.user.User;
import krzysztof.social.app.user.UserRepository;

@RestController
@RequestMapping("posts")
public class PostRestController {

	private UserRepository userRepository;
	private PostRepository postRepository;

	public PostRestController(UserRepository userRepository,
			PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@PostMapping
	public Post add(@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "message") String message) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("No such user."));
		Post post = new Post(message, user);
		user.addPost(post);
		return postRepository.save(post);
		// return userRepository.save(user).getLatestPost();
	}
}
