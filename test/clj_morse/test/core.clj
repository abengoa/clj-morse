(ns clj-morse.test.core
  (:use [clj-morse.core])
  (:use [clojure.test]))

(def teststring "abcdefghijklmnopqrstuvwxyz ,l./?\"'=+-_()&$@;:")

(deftest morse-test
  (is true (= teststring (morse-to-string (string-to-morse teststring)))))
