(ns tev-frontend.components.clojure-func)

(defn clojure-func
  "Displays random clojure.core-function"
  [acfg]
  (let [rand-var (-> 'cljs.core
                     (ns-publics)
                     keys
                     to-array
                     rand-nth
                     name)]
    [:p (:clojure-text @acfg) [:a {:href (str "http://cljs.github.io/api/cljs.core/" rand-var)
                                   :target "_blank"}
                               [:em rand-var]]]))
