(ns clojure-warrior-web.seed)

(def history
  [

   {:board [[{:type :warrior
              :state :base
              :max-health 20
              :health 20}
             {:type :floor}
             {:type :sludge
              :state :base
              :max-health 12
              :health 12}
             {:type :stairs}]]
    :messages ["You enter Level 1"]
    :tick 1}

   {:board [[{:type :warrior
              :state :walk
              :max-health 20
              :health 20}
             {:type :floor}
             {:type :sludge
              :state :base
              :max-health 12
              :health 12}
             {:type :stairs}]]
    :messages ["You enter Level 1"
               "You walk forward"]
    :tick 2}

   {:board [[{:type :floor}
             {:type :warrior
              :state :base
              :max-health 20
              :health 20}
             {:type :sludge
              :state :base
              :max-health 12
              :health 12}
             {:type :stairs}]]
    :messages ["You enter Level 1"
               "You walk forward"]
    :tick 2}

   {:board [[{:type :floor}
             {:type :warrior
              :state :attack
              :max-health 20
              :health 20}
             {:type :sludge
              :state :base
              :max-health 12
              :health 10}
             {:type :stairs}]]
    :messages ["You enter Level 1"
               "You walk forward"
               "You attack and hit a sludge, dealing 2 damage"]
    :tick 2}

   {:board [[{:type :floor}
             {:type :warrior
              :state :base
              :max-health 20
              :health 20}
             {:type :sludge
              :state :base
              :max-health 12
              :health 10}
             {:type :stairs}]]
    :messages ["You enter Level 1"
               "You walk forward"
               "You attack and hit a sludge, dealing 2 damage"]
    :tick 2}

   {:board [[{:type :floor}
             {:type :warrior
              :state :base
              :max-health 20
              :health 18}
             {:type :sludge
              :state :attack
              :max-health 12
              :health 10}
             {:type :stairs}]]
    :messages ["You enter Level 1"
               "You walk forward"
               "You attack and hit a sludge, dealing 2 damage"
               "Sludge attacks; you lose 2 health"]
    :tick 2}

   {:board [[{:type :floor}
             {:type :warrior
              :state :base
              :max-health 20
              :health 18}
             {:type :sludge
              :state :base
              :max-health 12
              :health 10}
             {:type :stairs}]]
    :messages ["You enter Level 1"
               "You walk forward"
               "You attack and hit a sludge, dealing 2 damage"
               "Sludge attacks; you lose 2 health"]
    :tick 2}

])

#_[{:type :floor}
 {:type :warrior
  :state :attack
  :max-health 20
  :health 20}
 {:type :sludge
  :state :attack
  :max-health 12
  :health 10}
 {:type :thick-sludge
  :state (rand-nth [:base :attack :dead])
  :max-health 24
  :health (rand-int 24)}
 {:type :archer
  :state (rand-nth [:base :attack :dead])
  :max-health 7
  :health (rand-int 7)}
 {:type :wizard
  :state (rand-nth [:base :shoot :dead])
  :max-health 3
  :health (rand-int 3)}
 {:type :stairs}
 {:type :captive
  :state (rand-nth [:base :free :dead])
  :max-health 1
  :health (rand-int 2)}]
