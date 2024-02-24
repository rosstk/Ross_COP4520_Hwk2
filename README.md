# Ross_COP4520_Hwk2
Homework 2 assignment
##Problem 1
My solution was for one of N guests, the tracker, to eat the cupcakes, while the other guests asked for a cupcake if there was none on the plate.
This allowed for the the tracker to keep count of the amount of times they ate a cupcake, and let the minotaur know when they had eaten
N cupcakes. There is no communication between the guests, as the only information they share is the cupcake flag, this information is "given"
by the staff, the party still going flag, given by the staff, the maze lock, the minotaur controls this only letting one guest in at a time, and
the exit lock, only one guest is allowed to eat/ask for a cupcake at a time.
##Problem 2
My solution was the third option of having a queue. This is the most efficient as it prevents crowding and confusion, because guests wait in order
to view the vase, and any guest that wants to view the vase will have the chance to. The drawback of this, is that it prevents the guests from doing
anything else while they are in line to see the vase. My solution is correct, as I put a lock on the viewVase method, preventing multiple guests
from being in the showroom at one time. Guests are also allowed to queue as many times as they want, "leaving the party" only when they've said no
to seeing the vase twice.
