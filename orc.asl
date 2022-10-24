// Agent orc in project orcTest.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!move.

/* Plans */

//+!move : id(I) & pos(I, X, Y) & Y > 250 <- move(0,  1); !move.
//+!move : id(I) & pos(I, X, Y) & Y < 250 <- move(0, -1); !move.

+!move : true <- target(250, 250); follow_target(1) !move.

+!move <- !move.
