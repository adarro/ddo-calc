/**
 * Copyright (C) 2015 Andre White (adarro@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.truthencode.ddo.web

import net.ruippeixotog.scalascraper.model.Element

/**
 * @author
 *   Andre White Represents a TreeNode like list derived from an HTML fragment.
 *
 * Generally this will be extracted from structures like lists (UL/OL) or Tables
 */
sealed abstract class HtmlTreeNode {

  /**
   * when true, indicates that this item has no leaves or branches.
   */
  val isStump: Boolean

  /**
   * a collection of leaves directly attached to this level.
   *
   * There may be more leaves nested within the branches collection.
   */
  val leaves: Option[Iterable[Leaf]]

  /**
   * a collection of Branches that may contain additional leaves or branches.
   */
  val branches: Option[Iterable[Branch]]

}

/**
 * Represents an end node with no branches
 * @param text
 *   the value of the leaf
 */
case class Leaf(text: String) extends HtmlTreeNode with UnBranchable {
  val isStump = false
}

object Leaf {
  def apply(element: Element): Leaf = {
    Leaf(element.text)
  }
}

/**
 * Represents a node that can have additional leaves and branches
 * @param branches
 *   nested branches, if any
 * @param leaves
 *   any leaves on the branch
 */
case class Branch(branches: Option[Iterable[Branch]] = None, leaves: Option[Iterable[Leaf]] = None)
  extends HtmlTreeNode {
  val isStump = false
}

/**
 * The base tree node that may contain branches or leaves
 */
case class Tree(root: HtmlTreeNode) extends HtmlTreeNode {
  val isStump = {
    root match {
      case Stump() => true
      case _ => false
    }
  }
  val leaves = root match {
    case Leaf(x) => Some(List(Leaf(x)))
    case _ => None
  }
  val branches = root match {
    case Branch(x, y) => Some(List(Branch(x, y)))
    case _ => None
  }
}

/**
 * Represents a node with no leaves or branches
 */
case class Stump() extends HtmlTreeNode with UnBranchable { val isStump = true }

/**
 * @author
 *   Andre White Represents an object with no leaves or branches.
 */
trait UnBranchable {

  /**
   * Represents no leaves by setting this val to [[None]]
   */
  val leaves = None

  /**
   * Represents no branches by setting this val to [[None]]
   */
  val branches = None
}
