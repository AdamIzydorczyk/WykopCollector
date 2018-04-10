package tk.aizydorczyk.wykop.restclient

import java.time.LocalDateTime

case class Comment
(
  id: Long,
  author: String,
  author_group: Int,
  author_sex: String,
  date: String,
  body: String,
  entry_id: String,
  blocked: Boolean,
  deleted: Boolean,
  vote_count: Int,
  user_vote: Int,
  user_favorite: Boolean,
  voters: Seq[Voter],
  app: Option[String],
  violation_url: String,
)
