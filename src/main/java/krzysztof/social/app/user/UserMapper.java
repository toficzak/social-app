package krzysztof.social.app.user;

import java.util.stream.Collectors;
import krzysztof.social.app.post.PostMapper;

public class UserMapper {

  public static UserDto map(User user) {
    UserDto dto = UserMapper.mapMicro(user);
    if (user.history != null && !user.history.isEmpty()) {
      dto.history = user.history.stream().map(PostMapper::map).collect(Collectors.toList());
    }
    if (user.latestPost != null) {
      dto.latestPost = PostMapper.map(user.latestPost);
    }
    return dto;
  }

  public static UserDto mapMicro(User user) {
    UserDto dto = new UserDto();
    dto.id = user.getId();
    return dto;
  }
}
