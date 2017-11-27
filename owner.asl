/* Initial goals */

!get(beer). // initial goal: get a beer
!check_bored. // initial goal: verify whether I am getting bored

@g
+!get(beer)
	: true
	<- .send(beerbot, achieve, has(owner,beer)).

@h1
+has(owner,beer)
	: true
	<- !drink(beer).

@h2
-has(owner,beer)
	: true
	<- !get(beer).

// while I have beer, sip
@d1
+!drink(beer)
	: has(owner,beer)
	<- sip(beer);
	   !drink(beer).
@d2
+!drink(beer)
	: not has(owner,beer)
	<- true.

+!check_bored
	: true
	<- .random(X); .wait(X*5000+2000); // i get bored at random times
	   .send(beerbot, askOne, time(_), R); // when bored, I ask the robot about the time
	   .print(R);
	   !check_bored.

+msg(M)[source(Ag)]
	: true
	<- .print("Message from ",Ag,": ",M);
	   -msg(M).
