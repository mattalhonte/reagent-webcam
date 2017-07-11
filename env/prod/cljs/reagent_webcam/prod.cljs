(ns reagent-webcam.prod
  (:require
    [reagent-webcam.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
