(ns tev-frontend.mastodon
  (:require [shadow.cljs.modern :as smodern]
            [shadow.json :as json]
            ["rss-to-json" :refer (parse)]))

(defn load-mastodon!
  "`profile`: URL to user's profile; eg https://example.com/@user
  `atom`: Atom, where the data is saved"
  [profile atom]
  (smodern/js-await [json (parse (str profile ".rss"))]
                    (reset! atom (json/to-clj json))))