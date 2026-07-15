(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Madrid -- the EIGHTH
  municipality-level entry (see cloud-itonami-municipality-jpn-tokyo,
  -usa-washington-dc, -gbr-london, -can-toronto, -deu-berlin, -fra-paris,
  -nld-amsterdam for the first seven) per ADR-2607141700
  (cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL transparencia.madrid.es (Ayuntamiento de
  Madrid's transparency portal, 'Huella normativa' section) URL -- never
  fabricated. An ordinance not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url/number.

  madrid.es's own PDF re-publications of these ordinances rendered as
  effectively blank pages to the Read tool (an Apache FOP font-embedding
  artifact, distinct from the JS-only or 403 failure modes seen
  elsewhere in this family). Both entries below were instead directly
  WebFetch-verified against transparencia.madrid.es's HTML pages, which
  state the ordinance's exact approval date and official gazette
  citation (BOAM/BOCM issue, page, date).")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"madrid"
   [{:ordinance/id "madrid.ordenanza-movilidad-sostenible-2018"
     :ordinance/title "Ordenanza de Movilidad Sostenible"
     :ordinance/municipality "madrid"
     :ordinance/country "ESP"
     :ordinance/kind :ordinance
     :ordinance/number "BOAM núm. 8.263, pág. 11"
     :ordinance/url "https://transparencia.madrid.es/portales/transparencia/es/Informacion-juridica/Huella-normativa/Ordenanza-de-Movilidad-Sostenible/?vgnextfmt=default&vgnextoid=77f171955c48a510VgnVCM2000001f4a900aRCRD&vgnextchannel=4099508929a56510VgnVCM1000008a4a900aRCRD"
     :ordinance/url-provenance :official-transparencia-madrid-es
     :ordinance/enacted-date "2018-10-05"
     :ordinance/last-revised-date "2018-10-23"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:mobility :environment}}
    {:ordinance/id "madrid.ordenanza-transparencia-2016"
     :ordinance/title "Ordenanza de Transparencia de la Ciudad de Madrid"
     :ordinance/municipality "madrid"
     :ordinance/country "ESP"
     :ordinance/kind :ordinance
     :ordinance/number "BOCM nº 196, pág. 107"
     :ordinance/url "https://transparencia.madrid.es/portales/transparencia/es/Informacion-juridica/Huella-normativa/Ordenanza-de-Transparencia-de-la-Ciudad-de-Madrid/?vgnextfmt=default&vgnextoid=a37eecef98d24510VgnVCM2000001f4a900aRCRD&vgnextchannel=4099508929a56510VgnVCM1000008a4a900aRCRD"
     :ordinance/url-provenance :official-transparencia-madrid-es
     :ordinance/enacted-date "2016-07-27"
     :ordinance/last-revised-date "2016-08-17"
     :ordinance/retrieved-at "2026-07-15"
     :ordinance/topic #{:transparency :information-disclosure}}]})

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
      :note (str "cloud-itonami-municipality-esp-madrid Wave 0 (ADR-2607141700): "
                 (count (get catalog "madrid")) " Madrid entries seeded with "
                 "an official transparencia.madrid.es citation. Extend "
                 "`ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
