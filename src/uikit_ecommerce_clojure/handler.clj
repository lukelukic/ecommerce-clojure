(ns uikit-ecommerce-clojure.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ui.layout :as layout]))


(defroutes app-routes
  (GET "/" [] (layout/layout "Naslov"))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
