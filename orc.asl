// Agent orc in project orcTest.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!move.

!checkTarget.

!setTarget.

ctr(0).

/* Plans */

+!setTarget : id(I) & ctr(C) & C > 4 <- target(200 + I*10, 200 + I*10); -ctr(C); +ctr(-50).

+!setTarget : id(I) & ctr(C) & C < 0 <- -ctr(C); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X <= 250 & Y <= 250 & ctr(C) <- target(375, 125); -ctr(C); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X >= 250 & Y <= 250 & ctr(C) <- target(125, 375); -ctr(C); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X <= 250 & Y >= 250 & ctr(C) <- target(375, 375); -ctr(C); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X >= 250 & Y >= 250 & ctr(C) <- target(125, 125); -ctr(C); +ctr(C+1).
+!setTarget <- !setTarget.

+!move <- follow_target(1); !move.


+!checkTarget : id(I) & pos(I, X, Y) & target(I, X, Y) <- !setTarget !checkTarget.

+!checkTarget <- !checkTarget.
