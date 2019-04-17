package io.truthencode.api

import java.io._
import java.util.Date
import io.truthencode.server._
import io.truthencode.model._
import io.truthencode.model.Error
import io.truthencode.model.Weapon
import io.finch.circe._
import io.circe.generic.semiauto._
import com.twitter.concurrent.AsyncStream
import com.twitter.finagle.Service
import com.twitter.finagle.Http
import com.twitter.finagle.http.{ Request, Response }
import com.twitter.finagle.http.exp.Multipart.{ FileUpload, InMemoryFileUpload, OnDiskFileUpload }
import com.twitter.util.Future
import com.twitter.io.Buf
import io.finch._, items._
import java.io.File

object WeaponsApi {
  /**
   * Compiles all service endpoints.
   * @return Bundled compilation of all service endpoints.
   */
  def endpoints(da: DataAccessor) =
    findWeaponByName(da) :+:
      listWeapons(da)

  /**
   *
   * @return And endpoint representing a Seq[Weapon]
   */
  private def findWeaponByName(da: DataAccessor): Endpoint[Seq[Weapon]] =
    get("items" :: "weapon" :: string) { (name: String) =>
      Ok(da.Weapons_findWeaponByName(name))
    } handle {
      case e: Exception => BadRequest(e)
    }

  /**
   *
   * @return And endpoint representing a Seq[Weapon]
   */
  private def listWeapons(da: DataAccessor): Endpoint[Seq[Weapon]] =
    get("items" :: "weapon" :: params("tags") :: int) { (tags: Seq[String], limit: Int) =>
      Ok(da.Weapons_listWeapons(tags, limit))
    } handle {
      case e: Exception => BadRequest(e)
    }

  implicit private def fileUploadToFile(fileUpload: FileUpload): File = {
    fileUpload match {
      case upload: InMemoryFileUpload =>
        bytesToFile(Buf.ByteArray.Owned.extract(upload.content))
      case upload: OnDiskFileUpload =>
        upload.content
      case _ => null
    }
  }

  private def bytesToFile(input: Array[Byte]): java.io.File = {
    val file = File.createTempFile("tmpWeaponsApi", null)
    val output = new FileOutputStream(file)
    output.write(input)
    file
  }

  // This assists in params(string) application (which must be Seq[A] in parameter list) when the param is used as a List[A] elsewhere.
  implicit def seqList[A](input: Seq[A]): List[A] = input.toList
}
