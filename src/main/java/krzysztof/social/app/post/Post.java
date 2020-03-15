package krzysztof.social.app.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import krzysztof.social.app.BaseEntity;
import krzysztof.social.app.user.User;

@Entity
public class Post extends BaseEntity {

  @NotBlank
  String message;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", referencedColumnName = "id")
  User author;

  public Post() {
    super();
  }

  public Post(@NotBlank String message, @NotNull User author) {
    super();
    this.message = message;
    this.author = author;
  }
}
