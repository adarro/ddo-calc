# Things that should be done in a proper issue tracker

- Add a "New Issue" button to the top right of the page
- Scala MongoDB
    - Official (Java / Kotlin Scala) [MongoDB driver](https://www.mongodb.com/docs/drivers/java/sync/current/) seems to only support Scala 2.
    - [ReactiveMongo](http://reactivemongo.org/) seems to be the best option with Scala 2/3 support.
- Monix
    - In the Monix, Zio, and Cats Effect comparison, Monix may have bowed out.
      So all that beautifil [Quill](https://getquill.io) code may be in vain.
    - Need to migrate to [Zio-protoquill](https://github.com/zio/zio-protoquill) (Scala 3)
