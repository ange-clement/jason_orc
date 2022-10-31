// Agent orc in project orcTest.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!separtion.
!move.
/*
!checkTarget.

!setTarget.

!win.

ctr(0).
*/

/* Plans */

+!A : id(I) & hp(I, X) & X < 0 <- .print("i'm dead"); .kill_agent(I).

//s'éloigner jusqu'a être assez loin
+!separtion : id(I) & pos(I, X, Y) & closest(I, C) & pos(C, XC, YC) & (math.abs(X-XC) > 100 | math.abs(Y-YC) > 100) <- !exploration.
+!separtion : id(I) & pos(I, X, Y) & closest(I, C) & pos(C, XC, YC) <- target(X + (X-XC), Y + (Y-YC)) ; !separtion.
+!separtion <- !separtion.

//Prendre une position cible aléatoire jusqu'a trouver un ennemi
+!exploration : id(I) & pos(I, X, Y) & closest(I, C) & pos(C, XC, YC) & (math.abs(X-XC) < 30 | math.abs(Y-YC) < 30) <- !noticeEnemy.
+!exploration : id(I) & pos(I, X, Y) & target(I, XT, YT) & math.abs(X-XT) < 5 & math.abs(Y-YT) < 5 <- !randomTarget; !exploration.
+!exploration <- !exploration.

//Prendre une décision sur l'ennemi (fuir ou attaquer)
+!noticeEnemy : id(I) & hp(I, H) & closest(I, C) & hp(C, HC) & H >= HC <- !attack.
+!noticeEnemy : id(I) & hp(I, H) & closest(I, C) & hp(C, HC) & H < HC <- !separtion.
+!noticeEnemy <- !noticeEnemy.

//Se rappocher jusqu'a être assez proche pour taper, puis repartir en exploration
+!attack : id(I) & pos(I, X, Y) & closest(I, C) & pos(C, XC, YC) & (math.abs(X-XC) < 10 | math.abs(Y-YC) < 10) <- hit(C); !randomTarget; !exploration.
+!attack : id(I) & closest(I, C) & pos(C, XC, YC) <- target(XC, YC); !attack.
+!attack <- !attack.

+!randomTarget <- target(math.random(490)+5, math.random(490)+5).
+!move <- follow_target(1); !move.

/*
+!win <- hit(0).

+!setTarget : id(I) & ctr(C) & C > 4 <- target(200 + I*10, 200 + I*10); -ctr(C); +ctr(-1).

+!setTarget : id(I) & ctr(C) & C < 0 <- -ctr(C); .wait(1000); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X <= 250 & Y <= 250 & ctr(C) <- target(375, 125); -ctr(C); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X >= 250 & Y <= 250 & ctr(C) <- target(125, 375); -ctr(C); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X <= 250 & Y >= 250 & ctr(C) <- target(375, 375); -ctr(C); +ctr(C+1).
+!setTarget : id(I) & pos(I, X, Y) & X >= 250 & Y >= 250 & ctr(C) <- target(125, 125); -ctr(C); +ctr(C+1).
+!setTarget <- !setTarget.

+!move <- follow_target(1); !move.


+!checkTarget : id(I) & pos(I, X, Y) & target(I, X, Y) <- !setTarget; !checkTarget.

+!checkTarget <- !checkTarget.
*/
