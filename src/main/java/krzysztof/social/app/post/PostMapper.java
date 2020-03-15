package krzysztof.social.app.post;

import krzysztof.social.app.user.UserMapper;

public class PostMapper {

  public static PostDto map(Post post) {
    PostDto dto = new PostDto();
    dto.message = post.message;
    dto.author = UserMapper.mapMicro(post.author);
    return dto;
  }

}
