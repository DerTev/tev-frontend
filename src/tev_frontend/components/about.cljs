(ns tev-frontend.components.about
  (:require [clojure.string :as cstr]))

(defn about [amdata acfg]
  (let [mdata @amdata
        cfg @acfg]
    [:div [:h1 (str (:greeting cfg) ", I'm " (:title mdata) "!")]
     (for [element (cstr/split (:bio cfg) "\\n")]
       [:p {:dangerouslySetInnerHTML {:__html element}}])]))
