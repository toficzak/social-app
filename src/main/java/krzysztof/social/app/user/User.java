package krzysztof.social.app.user;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import krzysztof.social.app.BaseEntity;
import krzysztof.social.app.post.Post;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

  // TODO Cannot get those!!!
  @OneToOne
  // (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
  Post latestPost;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author",
      orphanRemoval = true)
  List<Post> history = new ArrayList<>();

  public Post getLatestPost() {
    // if (latestPost == null) {
    // throw new RuntimeException("No latest.");
    // }
    return this.latestPost;
  }

  List<Post> retrieveHistory() {
    if (this.history == null || this.history.isEmpty()) {
      throw new RuntimeException("No history.");
    }
    return this.history;
  }

  public Post addPost(Post post) {
    this.history.add(post);
    this.latestPost = post;
    return post;
  }
}
