package tk.aizydorczyk.wykop.restclient


import play.api.libs.functional.syntax._
import play.api.libs.json._

private[restclient] object Converters {
  implicit val voterReads: Reads[Voter] = (
    (JsPath \ "author").read[String] and
      (JsPath \ "author_group").read[Int] and
      (JsPath \ "author_sex").read[String] and
      (JsPath \ "date").read[String]
    ) (Voter.apply _)

  implicit val commentReads: Reads[Comment] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "author").read[String] and
      (JsPath \ "author_group").read[Int] and
      (JsPath \ "author_sex").read[String] and
      (JsPath \ "date").read[String] and
      (JsPath \ "body").read[String] and
      (JsPath \ "entry_id").read[String] and
      (JsPath \ "blocked").read[Boolean] and
      (JsPath \ "deleted").read[Boolean] and
      (JsPath \ "vote_count").read[Int] and
      (JsPath \ "user_vote").read[Int] and
      (JsPath \ "user_favorite").read[Boolean] and
      (JsPath \ "voters").read[Seq[Voter]].orElse(Reads.pure(Nil)) and
      (JsPath \ "app").readNullable[String] and
      (JsPath \ "violation_url").read[String]
    ) (Comment.apply _)

  implicit val entryReads: Reads[Entry] = (
    (JsPath \ "id").read[Long] and
      (JsPath \ "author").read[String] and
      (JsPath \ "author_group").read[Int] and
      (JsPath \ "author_sex").read[String] and
      (JsPath \ "date").read[String] and
      (JsPath \ "body").read[String] and
      (JsPath \ "url").read[String] and
      (JsPath \ "comments").read[Seq[Comment]].orElse(Reads.pure(Nil)) and
      (JsPath \ "blocked").read[Boolean] and
      (JsPath \ "vote_count").read[Int] and
      (JsPath \ "user_vote").read[Int] and
      (JsPath \ "user_favorite").read[Boolean] and
      (JsPath \ "voters").read[Seq[Voter]].orElse(Reads.pure(Nil)) and
      (JsPath \ "deleted").read[Boolean] and
      (JsPath \ "app").readNullable[String] and
      (JsPath \ "comment_count").read[Int]
    ) (Entry.apply _)
}
