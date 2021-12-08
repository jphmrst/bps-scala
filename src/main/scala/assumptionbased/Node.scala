// Copyright (c) 1986-1993, Kenneth D. Forbus, Northwestern University
// and Johan de Kleer, the Xerox Corporation.
// Copyright (C) 2021 John Maraist.
// All rights reserved.
//
// See the LICENSE.txt and README-forbus-dekleer.txt files distributed
// with this work for a paragraph stating scope of permission and
// disclaimer of warranty, and for additional information regarding
// copyright ownership.  The above copyright notice and that paragraph
// must be included in any separate copy of this file.
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
// implied, for NON-COMMERCIAL use.  See the License for the specific
// language governing permissions and limitations under the License.

package org.maraist.truthmaintenancesystems.assumptionbased
import scala.util.control.NonLocalReturns.*
import scala.collection.mutable.{ListBuffer, HashSet, HashMap, Queue}

// Assumption-based truth maintenance system, translated from F/dK
// version 61 of 7/21/92.

class Node[D, I](
  val title: String
) {

  // ; From atms.lisp
  // (defstruct (tms-node (:PRINT-FUNCTION print-tms-node))
  //   (index 0)                                             ;; Unique name.
  //   (datum nil)                   ; Pointer to IE data structures.
  //   (label nil)                   ; minimal envs believed under
  //   (justs nil)                   ; providers of support
  //   (consequences nil)            ; provides support for.
  //   (contradictory? nil)          ; flag marking it as contradictory.
  //   (assumption? nil)             ; flag marking it as n assumption.
  //   (rules nil)                   ; run when label non-empty.
  //   (atms nil))
  //
  // (defun print-tms-node (node stream ignore)
  //   (declare (ignore ignore))
  //   (if (tms-node-assumption? node)
  //       (format stream "A-~D" (tms-node-index node))
  //       (format stream "#<NODE: ~A>" (node-string node))))
}
