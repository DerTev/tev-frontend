(ns tev-frontend.config
  (:require [cljs.core.async :as async]
            [cljs-http.client :as http]
            [shadow.json :as json]
            [clojure.string :as cstr]))

(defn- err-status?
  "Returns if `status` implies an error."
  [status]
  (let [status-str (str status)]
    (or (cstr/starts-with? status-str "4")
        (cstr/starts-with? status-str "5"))))

(defn load-config!
  "`atom`: Atom, where the data is saved."
  [atom]
  (async/go (reset! atom (let [resp (-> "/config.json"
                                        http/get
                                        async/<!)]
                           (if (err-status? (:status resp))
                             {}
                             (:body resp))))))