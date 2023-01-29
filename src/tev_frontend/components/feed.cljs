(ns tev-frontend.components.feed)

(defn feed [amdata]
  (let [data @amdata]
    [:div {:class "feed-content"}
     [:h2 {:class "clickable"
           :on-click #(.open js/window (:link data) "_blank")}
      (:description data)]
     [:div {:class "feed-posts"}
      (for [post (take 10 (:items data))]
        [:div {:class "post"
               :on-click #(.open js/window (:link post) "_blank")}
         [:div {:class ["post-content" "clickable"]
                :dangerouslySetInnerHTML {:__html (:description post)}}]])]]))
