(ns tev-frontend.core
  (:require [reagent.core :as r]
            [reagent.dom :as d]
            [tev-frontend.config :as config]
            [tev-frontend.mastodon :as mastodon]

            [tev-frontend.components.feed :as cfeed]
            [tev-frontend.components.about :as cabout]
            [tev-frontend.components.clojure-func :as cclojure]))

(def cfg (atom nil))
(def mdata (r/atom nil))

(defn render []
  (d/render [cfeed/feed mdata] (.getElementById js/document "feed"))
  (d/render [cabout/about mdata cfg] (.getElementById js/document "about"))
  (d/render [cclojure/clojure-func cfg] (.getElementById js/document "clojure-var")))

(defn load-favicon []
  (let [favicon (.createElement js/document "link")]
    (.setAttribute favicon "rel" "shortcut icon")
    (.setAttribute favicon "href" (:image @mdata))
    (.. js/document (querySelector "head") (appendChild favicon))))

(defn load-title []
  (let [title (.createElement js/document "title")]
    (.setHTML title (:title @mdata))
    (.. js/document (querySelector "head") (appendChild title))))

(defn ^:export init! []
  (add-watch cfg :cwatch (fn [_ _ _ new-state]
                           (mastodon/load-mastodon! (:mastodon new-state) mdata)))
  (add-watch mdata :mwatch (fn [_ _ _ _]
                             (load-title)
                             (load-favicon)
                             (render)))
  (config/load-config! cfg))
