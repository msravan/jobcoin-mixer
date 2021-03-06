# Coin Mixer 
A Coin mixer is a way to maintain your privacy on the crypto coin network.
How it works:
You provide  a list of new, unused addresses that you own to the mixer;
The mixer provides you with a new deposit address that it owns;
You transfer your bitcoins to that address;
The mixer will detect your transfer by watching or polling the P2P Bitcoin network;
The mixer will transfer your bitcoin from the deposit address into a big “house account” along with all the other bitcoin currently being mixed; and
Then, over some time the mixer will use the house account to dole out your bitcoin in smaller increments to the withdrawal addresses that you provided, possibly after deducting a fee.
Coin mixer archcitecture comprises of 3 components:
1. Mapper
2. Mixer
3. Doler

Architecture Diagram: 
![Architecture Diagram](https://github.com/msravan/jobcoin-mixer/blob/master/Architecute.png)

Mapper: 
1. Receives user input - inputAddress 
2. Generates a new Address and maps it with the input address and shares the map with the Mixer

Mixer:
1. Polls to the network and receives new Transactions
2. Checks the incoming transaction has a mapping with the shared map
3. If mapping exists, incoming transaction deposit amount is moved to houseAccount.

Doler:
1. Breaks a deposited amount from user into smaller random amounts.
2. Distributes them to the user input address from houseAccout.

Things still to do:
1. Currently Doler could only distribute the 1st transaction, 2nd transaction is taking very long. Commented out this function in the code.
2. Add fee for the mixing service.
3. Re-design architecture - more efficient and fault tolerant.
4. More number of test cases and edge cases.

Steps to Run this project:

1. Clone this repository and do maven clean compile install
2. Run the Application.Scala
3. Service requests to provide input address and generates a new address for every input.
4. Send job coins to the generated address using the Job Coin API.
5. You should see job coins transaction from the house account address to the input address provided in step-3.
6. If we check the transactions of the input address, it does not show the actual sender address, instead shows house account address.
Note: House account address for this project: srvn-a944322d972
