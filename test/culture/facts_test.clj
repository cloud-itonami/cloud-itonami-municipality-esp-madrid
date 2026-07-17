(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest madrid-has-culture-basis
  (let [sb (facts/spec-basis "madrid")]
    (is (= 8 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "madrid" (:culture/municipality %)) sb))
    (is (every? #(= "ESP" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "barcelona")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["madrid" "barcelona"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["barcelona"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 4 (count (facts/by-kind "madrid" :dish))))
  (is (= ["madrid.beverage.vinos-de-madrid"]
         (mapv :culture/id (facts/by-kind "madrid" :beverage))))
  (is (empty? (facts/by-kind "madrid" :craft)))
  (is (empty? (facts/by-kind "barcelona" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
