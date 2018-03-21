package tk.aizydorczyk.wykop.restclient

import java.time.LocalDateTime

case class Entry
(
  id: Long,
  author: String,
  author_group: Int,
  author_sex: String,
  date: String,
  body: String,
  url: String,
  comments: Seq[Comment],
  blocked: Boolean,
  vote_count: Int,
  user_vote: Int,
  user_favorite: Boolean,
  voters: Seq[Voter],
  deleted: Boolean,
  app: Option[String],
  comment_count: Int
)
