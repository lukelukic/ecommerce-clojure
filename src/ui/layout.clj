(ns ui.layout)

(use 'hiccup.core
     'hiccup.page
     '[clojure.string :as s])

(def metas (html
             [:meta {:charset "UTF-8"}]
             [:meta {:http-equiv "x-ua-compatible" :content "ie=edge"}]
             [:meta {:name "viewport" :content "width=device-width, initial-scale=1" }]))

(defn css-block [hrefs]
  (for [href hrefs] (html [:link {:rel "stylesheet" :href (if
                                                            (s/includes? href "http")
                                                            href
                                                            (str "/styles/" href))}])))

(defn script-block [srcs]
  (for [src srcs] (html [:script {:src (str "/scripts/" src)}])))

(defn head-block [title] [:head
                          metas
                          [:title title]
                          (css-block `("style.css", "http://fonts.googleapis.com/css?family=Roboto:400,500"))
                          (script-block `("uikit.js", "uikit-icons.js"))
                          ])

(defn category-menu-item [categories] (html
                                      [:li
                                       [:a {:href "/catalog"}
                                        "Catalog" [:span.uk-margin-xsmall-left {:uk-icon "icon: chevron-down; ratio: .75;"}]]
                                       [:div {:class "uk-navbar-dropdown uk-margin-remove uk-padding-remove-vertical" :uk-drop "pos: bottom-justify;delay-show: 125;delay-hide: 50;duration: 75;boundary: .tm-navbar-container;boundary-align: true;pos: bottom-justify;flip: x"}
                                        [:div.uk-container
                                         [:ul {:class "uk-navbar-dropdown-grid uk-child-width-1-5" :uk-grid "a"}
                                          (for [cat categories] [:li
                                                                 [:div.uk-margin-top.uk-margin-bottom
                                                                  [:a.uk-link-reset {:href "kategorija"}
                                                                   [:img {:class "uk-display-block uk-margin-auto uk-margin-bottom" :src (:image cat) :alt (:name cat) :width "80" :height "80"}]
                                                                   [:div.uk-text-bolder (:name cat)]
                                                                   [:ul.uk-nav.uk-nav-default
                                                                    (for [child (:children cat)] ([:li
                                                                                                   [:a {:href "/test"} (:name child)]]))]]]])]]]
                                           ]))

(defn nav [menu] (html
                    [:div {:class "uk-navbar-container tm-navbar-container" :uk-sticky "cls-active: tm-navbar-container-fixed"}
                     [:div.uk-container {:uk-navbar ""}
                      [:div.uk-navbar-left
                       [:button {:class "uk-navbar-toggle uk-hidden@m" :uk-toggle "target: #nav-offcanvas" :uk-navbar-toggle-icon ""}]
                       [:a.uk-navbar-item.uk-logo {:href "/"}
                        [:img {:src "images/logo.svg" :width "90" :height "32" :alt "Logo"}]]
                       [:nav {:class "uk-visible@m"}
                        [:ul.uk-navbar-nav (category-menu-item
                                             [{:image "/images/catalog/computers.svg"
                                               :name "Laptops & Tablets"
                                               :children ({:name "Test 1"}
                                                          {:name "Test 2"})}])
                         [:li
                          [:a {:href "stavka"} "Stavka"]]]]
                       ]]]))

(defn footer [] (html
                  [:footer
                   [:section {:class "uk-section uk-section-secondary uk-section-small uk-light"}
                    [:div.uk-container
                     [:div {:class "uk-grid-medium uk-child-width-1-1 uk-child-width-1-4@m" :style "text-align:center"}
                      [:span.text-center "Author - Luka Lukić " (.format (java.text.SimpleDateFormat. "yyyy") (new java.util.Date))]]]]]))

(defn layout [title] (html [:html {:lang "en"}
                            (head-block title)
                            (nav [{}])
                            (footer)
                           ]))