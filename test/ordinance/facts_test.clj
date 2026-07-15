(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest madrid-has-spec-basis
  (let [sb (facts/spec-basis "madrid")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://transparencia.madrid.es/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "barcelona")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["madrid" "barcelona"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["barcelona"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["madrid.ordenanza-transparencia-2016"]
         (mapv :ordinance/id (facts/by-topic "madrid" :transparency))))
  (is (empty? (facts/by-topic "madrid" :labor)))
  (is (empty? (facts/by-topic "barcelona" :mobility))))
