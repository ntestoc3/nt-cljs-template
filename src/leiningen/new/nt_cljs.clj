(ns leiningen.new.nt-cljs
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files
                                             sanitize sanitize-ns project-name
                                             ]]
            [leiningen.core.main :as main]))

(def render (renderer "nt-cljs"))

(defn nt-cljs
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :project-ns (sanitize-ns name)}
        files [[".gitignore" (render ".gitignore" data)]
               ["LICENSE" (render "LICENSE" data)]
               ["README.md" (render "README.md" data)]
               ["package.json" (render "package.json" data)]
               ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
               ["public/css/main.css" (render "public/css/main.css" data)]
               ["public/index.html" (render "public/index.html" data)]
               ["public/js/app.js" (render "public/js/app.js" data)]
               ["src/css/main.scss" (render "src/css/main.scss" data)]
               ["src/hooks/sass.clj" (render "src/hooks/sass.clj" data)]
               ["src/{{sanitized}}/core.cljs" (render "src/ntcljs/core.cljs" data)]
               ]]
    (main/info "Generating fresh 'lein new' nt-cljs project.")
    (apply ->files data files)))
