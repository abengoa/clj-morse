(ns clj-morse.core
	(:require [clojure.string :as cljstr]))


(def char2morse-table {\a ".-" \b "-..." \c "-.-." \d "-.." \e "." \f "..-." \g "--." \h "...." \i ".." \j ".---" \k "-.-" \l ".-.." \m "--" \n "-." \o "---" \p ".--." \q "--.-" \r ".-." \s "..." \t "-" \u "..-" \v "...-" \w ".--" \x "-..-" \y "-.--" \z "--.." \1 ".----" \2 "..---" \3 "...--" \4 "....-" \5 "....." \6 "-...." \7 "--..." \8 "---.." \9 "----." \0 "-----" \. ".-.-.-" \, "--..--" \? "..--.." \' ".----." \! "-.-.--" \/ "-..-." \( "-.--." \) "-.--.-" \& ".-..." \: "---..." \; "-.-.-." \= "-...-" \+ ".-.-." \- "-....-" \_ "..--.-" \" ".-..-." \$ "...-..-" \@ ".--.-." \space " "})

(def morse2char-table (assoc (reduce (fn [t [k v]] (into t {v k})) {} char2morse-table) "   " \space))


(defn char2morse 
	[c]
	(get char2morse-table c "?"))

(defn morse2char 
	[c]
	(get morse2char-table c \?))

(defn string-to-morse 
	[s]
	(apply str 
		(interleave 
			(map char2morse (cljstr/lower-case s))
			(repeat " "))))

(defn morse-to-string 
	[s]
	(apply str (flatten (map (fn [l] (map morse2char (map (partial apply str) (remove 
					#(= % '(\space)) l)))) 
					(partition-by #(= '(\space \space \space) %) 
						(partition-by #(= \space %) s))))))



(morse-to-string (string-to-morse "Just type letters, numbers and punctuation into the top box and press the \"Translate\" button. The program will place the Morse code in the bottom box, inserting a \"?\" if the character cannot be translated. If you are learning Morse code it is recommended that you use the \"toggle\" button to turn of the dots and dashes output as reading this can slow your learning down."))
