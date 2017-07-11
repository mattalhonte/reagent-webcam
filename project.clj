(defproject reagent-webcam "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.9.671" :scope "provided"]
                 [reagent "0.7.0"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-figwheel "0.5.11"]
            [lein-npm "0.6.2"]]

  :npm {:dependencies [[react "15.5.4"]
                       [react-dom "15.5.4"]
                       [webpack "1.13.1"]
                       [react-webcam "0.2.0"]]

        :package {:scripts {
                            :watch "webpack -d --watch"
                            :build "webpack -p"
                            }}}

  :min-lein-version "2.5.0"

  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["public"]

  :figwheel {:http-server-root "."
             :nrepl-port 7002
             :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
             :css-dirs ["public/css"]}

  :main "src/js/main.js"

  
  :cljsbuild {:builds {:app
                       {:source-paths ["src" "env/dev/cljs"]
                        :compiler
                        {:main "reagent-webcam.dev"
                         :output-to "public/js/app.js"
                         :output-dir "public/js/out"
                         :asset-path   "js/out"
                         :source-map true
                         :foreign-libs [{:file "public/js/bundle.js"
                                         :provides ["cljsjs.react" "cljsjs.react.dom" "webpack.bundle"]}]
                         :optimizations :none
                         :pretty-print  true}
                        :figwheel
                        {:on-jsload "reagent-webcam.core/mount-root"
                         :open-urls ["http://localhost:3449/index.html"]}}
                       :release
                       {:source-paths ["src" "env/prod/cljs"]
                        :compiler
                        {:output-to "public/js/app.js"
                         :output-dir "public/js/release"
                         :asset-path   "js/out"
                         :foreign-libs [{:file "public/js/bundle.js"
                                         :provides ["cljsjs.react" "cljsjs.react.dom" "webpack.bundle"]}]
                         :optimizations :advanced
                         :pretty-print false}}
                       :server
                       {:source-paths ["src/node" "src/cljs"]
                        :compiler {:target :nodejs
                                   :output-to "main.js"
                                   :output-dir "target"
                                   :main server.core
                                   :optimizations :none}}
                       }}

  :aliases {"package" ["do" "clean" ["cljsbuild" "once" "release"]]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.4"]
                                  [figwheel-sidecar "0.5.11"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.2"]]}})
