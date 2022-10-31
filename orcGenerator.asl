// Agent orc in project orcTest.mas2j

/* Initial beliefs and rules */

/* Initial goals */

//!createOrcs.

/* Plans */

+nbCreateOrc(X) : X >= 0 <- .create_agent(X, "orc.asl"); addOrc(X); +nbCreateOrc(X-1).
/*
+!createOrcs : nbCreateOrc(X) & X > 0 <- -nbCreateOrc(X); .create_agent(X, "orc.asl"); addOrc(X); +nbCreateOrc(X-1); !createOrcs.
+!createOrcs : nbCreateOrc(X) & X == 0 <- start.
+!createOrcs <- !createOrcs.
*/
