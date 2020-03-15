package krzysztof.social.app.user;

import java.util.List;
import krzysztof.social.app.post.PostDto;

public class UserDto {

  public Long id;
  public PostDto latestPost;
  public List<PostDto> history;
}
