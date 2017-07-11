(ns ^:figwheel-no-load reagent-webcam.dev
  (:require
    [reagent-webcam.core :as core]
    [devtools.core :as devtools]))


(enable-console-print!)

(devtools/install!)

(core/init!)
