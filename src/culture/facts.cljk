(ns culture.facts
  "Regional-culture catalog for Madrid -- local dishes, protected
  products, beverages, festivals and heritage sites, piggybacked
  onto this municipality compliance repo per ADR-2607171400
  (cloud-itonami-municipality-culture-catalog, in com-junkawasaki/root),
  sibling namespace to `ordinance.facts` (ADR-2607141700).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "municipality-slug -> vector of culture entries."
  {"madrid"
   [{:culture/id "madrid.dish.cocido-madrileno"
     :culture/name "Cocido madrileño"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Traditional chickpea-based stew associated with the Madrid region, most popular during the winter."
     :culture/url "https://en.wikipedia.org/wiki/Cocido_madrile%C3%B1o"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "madrid.dish.callos-a-la-madrilena"
     :culture/name "Callos"
     :culture/name-local "Callos a la madrileña"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Tripe stew common across Spain and considered traditional to Madrid, where it is known as callos a la madrileña."
     :culture/url "https://en.wikipedia.org/wiki/Callos"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "madrid.dish.bocadillo-de-calamares"
     :culture/name "Squid sandwich"
     :culture/name-local "Bocadillo de calamares"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Fried-squid sandwich, a Spanish specialty very popular in Madrid, found in most bars of the capital, particularly around the Plaza Mayor."
     :culture/url "https://en.wikipedia.org/wiki/Bocadillo_de_calamares"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "madrid.dish.churros"
     :culture/name "Churros"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :dish
     :culture/summary "Fried choux-pastry dough from Spanish and Portuguese cuisine, commonly eaten dipped in coffee or hot chocolate; a Spanish national staple rather than a Madrid-specific dish."
     :culture/url "https://en.wikipedia.org/wiki/Churro"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "madrid.beverage.vinos-de-madrid"
     :culture/name "Vinos de Madrid"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :beverage
     :culture/summary "Spanish wine region in the southern part of Madrid, holding Denominación de Origen status acquired in 1990."
     :culture/url "https://en.wikipedia.org/wiki/Vinos_de_Madrid"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "madrid.festival.fiestas-de-san-isidro"
     :culture/name "San Isidro festival"
     :culture/name-local "Fiestas de San Isidro"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :festival
     :culture/summary "Feast day (15 May) of Isidore the Laborer, Catholic patron saint of farmers and of Madrid, celebrated in the city."
     :culture/url "https://en.wikipedia.org/wiki/Isidore_the_Laborer"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "madrid.heritage.royal-palace-of-madrid"
     :culture/name "Royal Palace of Madrid"
     :culture/name-local "Palacio Real de Madrid"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :heritage
     :culture/summary "Official residence of the Spanish royal family in the western part of central Madrid, the largest palace in Western Europe by floor area."
     :culture/url "https://en.wikipedia.org/wiki/Royal_Palace_of_Madrid"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "madrid.heritage.buen-retiro-park"
     :culture/name "Buen Retiro Park"
     :culture/name-local "Parque del Buen Retiro"
     :culture/municipality "madrid"
     :culture/country "ESP"
     :culture/kind :heritage
     :culture/summary "One of Madrid's largest urban parks; since 2021 part of a combined UNESCO World Heritage Site together with the Paseo del Prado."
     :culture/url "https://en.wikipedia.org/wiki/Buen_Retiro_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-esp-madrid culture catalog "
                 "(ADR-2607171400): " (count (get catalog "madrid"))
                 " Madrid entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [muni kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis muni)))
