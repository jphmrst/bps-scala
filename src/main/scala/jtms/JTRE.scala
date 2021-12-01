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

package org.maraist.tms.jtms
import scala.collection.mutable.{ListBuffer, HashSet, HashMap}

class JTRE {
  // (defstruct (jtre (:PRINT-FUNCTION jtre-printer))
  //   title                   ; Pretty name
  //   jtms                    ; Pointer to its JTMS
  //   (dbclass-table nil)       ; Table of dbclasses
  //   (datum-counter 0)       ; Unique ID for asserts
  //   (rule-counter 0)        ; Unique ID for rules
  //   (debugging nil)         ; If non-NIL, show basic operations
  //   (queue nil)             ; Rule queue
  //   (rules-run 0))          ; Statistic

  // (defun jtre-printer (j st ignore)
  //   (format st "<JTRE: ~A>" (jtre-title j)))

  // (defvar *JTRE* nil)

  // (defmacro With-Jtre (jtre &rest forms)
  //   `(let ((*JTRE* ,jtre)) ,@ forms))

  // (defun In-Jtre (jtre) (setq *JTRE* jtre))

  // (defmacro debugging-jtre (msg &rest args)
  //   `(when (jtre-debugging *JTRE*) (format t ,msg  ,@args)))

  // (defun create-jtre (title &key debugging)
  //  (let ((j (make-jtre
  //       :TITLE title
  //       :JTMS (create-jtms (list :JTMS-OF title)
  //                          :NODE-STRING 'view-node)
  //       :DBCLASS-TABLE (make-hash-table :TEST #'eq)
  //       :DEBUGGING debugging)))
  //    (change-jtms (jtre-jtms j)
  //            :ENQUEUE-PROCEDURE
  //            #'(lambda (rule) (enqueue rule j)))
  //    j))

  // (defun change-jtre (jtre &key (debugging :NADA))
  //   (unless (eq debugging :NADA)
  //      (setf (jtre-debugging jtre) debugging)))
  // 
  // ;;;; Running JTRE

  //
  // ;;;; Making statements

  // ;; From jdata.lisp
  // (defun assert! (fact just &optional (*JTRE* *JTRE*) &aux datum node)
  //   (setq datum (referent fact t)
  //         node (datum-tms-node datum))
  //   (unless (listp just) (setq just (list just)))
  //   (debugging-jtre "~%    Asserting ~A via ~A." fact just)
  //   (justify-node (car just) node
  //            (mapcar #'(lambda (f) (datum-tms-node (referent f t)))
  //                    (cdr just)))
  //   datum)

  // ;; From jdata.lisp
  // (defun quiet-assert! (fact just &optional (*JTRE* *JTRE*))
  //   (without-contradiction-check (jtre-jtms *JTRE*) (assert! fact just)))

  // ;; From jdata.lisp
  // (defun assume! (fact reason &optional (*JTRE* *JTRE*) &aux datum node)
  //   (setq datum (referent fact t)
  //    node (datum-tms-node datum))
  //   (cond    ((not (datum-assumption? datum))
  //     (setf (datum-assumption? datum) reason)
  //     (debugging-jtre "~%    Assuming ~A via ~A." fact reason)
  //     (assume-node node))
  //    ((eq reason (datum-assumption? datum)))
  //    (t (error
  //        "Fact ~A assumed because of ~A assumed again because of ~A"
  //        (show-datum datum) (datum-assumption? datum) reason)))
  //   datum)

  // ;; From jdata.lisp
  // (defun retract! (fact &optional (just 'user) (quiet? nil)
  //                  (*JTRE* *JTRE*) &aux datum node)
  //   (setq datum (referent fact t)
  //    node (datum-tms-node datum))
  //   (cond ((not (tms-node-assumption? node))
  //     (unless quiet?
  //       (format t "~%~A isn't an assumption."
  //               (show-datum datum))))
  //    ((not (in-node? node))
  //     (unless quiet?
  //       (format T
  //         "~%The assumption ~A is not currently in."
  //         fact)))
  //    ((eq just (datum-assumption? datum))
  //     (debugging-jtre "~%    Retracting ~A via ~A."
  //                     fact just)
  //     (setf (datum-assumption? datum) nil)
  //     (retract-assumption node))
  //    ((not quiet?)
  //     (format t "~%~A not source of assumption for ~A"
  //             just fact)))
  //   node)

  // ;; From jdata.lisp
  // (defun uassert! (fact &optional (just 'user))
  //   (assert! fact just) ;; Do internal operation
  //   (run-rules *JTRE*))        ;; Run the rules

  // ;; From jdata.lisp
  // (defun uassume! (fact reason) ;; Similar to UASSERT!
  //   (assume! fact reason *JTRE*)
  //   (run-rules *JTRE*))

  // ;; From jdata.lisp
  // (defun run-forms (forms &optional (*JTRE* *JTRE*))
  //   (dolist (form forms) (eval form) (run-rules *JTRE*)))

  // ;; From jdata.lisp
  // (defun run (&optional (*JTRE* *JTRE*)) ;; Toplevel driver function
  //     (format T "~%>>")
  //     (do ((form (read) (read)))
  //         ((member form '(quit stop exit abort)) nil)
  //         (format t "~%~A" (eval form))
  //         (run-rules)
  //         (format t "~%>>")))

  // ;; From jdata.lisp
  // (defun show (&optional (*JTRE* *JTRE*) (stream *standard-output*))
  //   (show-data *JTRE* stream) (show-rules *JTRE* stream))

  // ;; From jdata.lisp
  // (defun referent (fact &optional (virtual? nil)
  //                  (*JTRE* *JTRE*))
  //   (if virtual? (insert fact) (referent1 fact)))

  // ;; From jdata.lisp
  // (defun contradiction (fact &optional (*JTRE* *JTRE*))
  //   (make-contradiction (datum-tms-node (referent fact t))))
  // 
  // ;;;; Interface and display of data

  // ;; From jdata.lisp
  // (defun in? (fact &optional (*JTRE* *JTRE*) &aux r)
  //   (when (setq r (referent fact))
  //    (in-node? (datum-tms-node r))))

  // ;; From jdata.lisp
  // (defun out? (fact &optional (*JTRE* *JTRE*) &aux r)
  //   (or (not (setq r (referent fact))) ; a non-existent fact is out
  //       (out-node? (datum-tms-node r))))
  //
  // ;; From jdata.lisp
  // (defun why? (fact &optional (*JTRE* *JTRE*) &aux r)
  //   (when (setq r (referent fact))
  //    (why-node (datum-tms-node r))))

  // ;; From jdata.lisp
  // (defun assumptions-of (fact &optional (*JTRE* *JTRE*))
  //   (mapcar #'view-node
  //      (assumptions-of-node
  //       (datum-tms-node (referent fact *jtre* t)))))

  // ;; From jdata.lisp
  // (defun fetch (pattern &optional (*JTRE* *JTRE*) &aux bindings unifiers)
  //   (dolist (candidate (get-candidates pattern) unifiers)
  //     (setq bindings (unify pattern (datum-lisp-form candidate)))
  //     (unless (eq bindings :FAIL)
  //       (push (sublis bindings pattern) unifiers))))

  // ;;;; More display-intensive procedures

  // ;; From jdata.lisp
  // (defun wfs (fact &optional (*JTRE* *JTRE*))
  //   ;; Displays well-founded support for a fact
  //   (cond ((out? fact) (format t "~% ~A is OUT." fact))
  //    (t (do ((queue (list (get-tms-node fact))
  //                   (nconc (cdr queue) new-antes))
  //            (so-far (list (get-tms-node fact)))
  //            (new-antes nil nil))
  //           ((null queue) (format t "~%--------") fact)
  //         (why-node (car queue))
  //         (unless (or (out-node? (car queue))
  //                     (tms-node-assumption? (car queue)))
  //           ;; Go down the support
  //           (dolist (ante (just-antecedents
  //                          (tms-node-support (car queue))))
  //             (unless (member ante so-far)
  //               (push ante so-far)
  //               (push ante new-antes))))))))

  // ;; From jdata.lisp
  // (defun say-datum-belief (pr &optional (*jtre* *jtre*)
  //                        (indent ""))
  //   (format t "~%~A~A: ~A" indent pr
  //      (if (in-node? (get-tms-node pr *jtre*))
  //          "IN" "OUT")))

  // ;; From jdata.lisp
  // (defun show-justifications (fact &optional (*jtre* *jtre*))
  //   (format t "~% ~A::" fact)
  //   (let* ((node (get-tms-node fact *jtre*))
  //     (justs (tms-node-justs node)))
  //     (unless justs
  //        (format t " No justifications.")
  //        (return-from show-justifications node))
  //     (dolist (j justs)
  //        (format t "~% ~A" (just-informant j))
  //        (cond ((just-antecedents j)
  //               (format t ", on:")
  //               (dolist (ante (just-antecedents j))
  //                       (say-datum-belief
  //                        (view-node ante) *jtre* "  "))
  //               (format t "."))
  //              (t (format t "."))))))

  // ;; From jdata.lisp
  // (defun show-data (&optional (*JTRE* *JTRE*)
  //                        (stream *standard-output*))
  //   (format stream
  //      "~%~D facts total." (jtre-datum-counter *JTRE*))
  //   (map-dbclass
  //    #'(lambda (dbclass)
  //        (dolist (datum (dbclass-facts dbclass))
  //           (format stream "~%~A: ~A" (show-datum datum)
  //                   (if (in-node? (datum-tms-node datum))
  //                       "IN" "OUT"))))))

  // ;;;; Database system

  // ;; From jdata.lisp
  // (defun get-dbclass (fact &optional (*JTRE* *JTRE*)
  //                     &aux dbclass)
  //   (cond ((null fact) (error "~% NIL can't be a dbclass."))
  //    ((listp fact) (get-dbclass (car fact) *JTRE*))
  //    ((variable? fact)
  //     (cond ((boundp fact)
  //            (get-dbclass (symbol-value fact) *JTRE*))
  //           (t (error "~%Dbclass unbound: ~A" fact))))
  //    ((symbolp fact)
  //     (cond ((setq dbclass
  //                  (gethash fact
  //                           (jtre-dbclass-table *JTRE*)))
  //            dbclass)
  //           (t (setq dbclass
  //                    (make-dbclass :NAME fact :FACTS nil
  //                                :RULES nil :JTRE *JTRE*))
  //              (setf (gethash fact
  //                     (jtre-dbclass-table *JTRE*))
  //                    dbclass)
  //              dbclass)))
  //    (t (error "Bad dbclass type: ~A" fact))))

  // ;; From jdata.lisp
  // (defun map-dbclass (proc &optional (*JTRE* *JTRE*))
  //   (maphash #'(lambda (name dbclass) (declare (ignore name))
  //           (funcall proc dbclass))
  //       (jtre-dbclass-table *JTRE*)))

  // ;; From jdata.lisp
  // (defun get-tms-node (fact &optional (*JTRE* *JTRE*))
  //   (datum-tms-node (referent fact t)))

  // ;; From jdata.lisp
  // (defun get-datum (num &optional (*JTRE* *JTRE*))
  //   (map-dbclass
  //    #'(lambda (dbclass)
  //        (dolist (datum (dbclass-facts dbclass))
  //           (when (= (datum-id datum) num)
  //                 (return-from GET-DATUM datum))))))

  // ;; From jdata.lisp
  // (defun get-just (num &optional (*JTRE* *JTRE*))
  //   (dolist (just (jtms-justs (jtre-jtms *JTRE*)))
  //     (when (= (just-index just) num)
  //      (return-from GET-just just))))
}