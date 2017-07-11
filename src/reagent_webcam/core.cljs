(ns reagent-webcam.core
    (:require
     [reagent.core :as reagent :refer [atom]]
     [webpack.bundle]))

;; -------------------------
;; Views

(defn home-page []
  (let [webcam-component (aget js/window "deps" "react-webcam")]  ;;Based on this: https://github.com/mozmorris/react-webcam
    [:div
     [:h2 "Reagent Webcam"]
     [:> webcam-component {:audio false   ;;Some attributes to play with.  Mess with them!
                           :height 400
                           :width 400}]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
