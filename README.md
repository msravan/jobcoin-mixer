# Coin Mixer 
A Coin mixer is a way to maintain your privacy on the crypto coin network.
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

#Steps to Run this project:

1. Clone this repository and do maven clean compile install
2. Run the Application.Scala
3. Service requests to provide input address and generates an address.
4. Send job coins to the generated address using the Job Coin API.
5. You should see job coins transaction from the house account address to the input address provided in step-3.
6. If we check the transactions of the input address, it does not show the actual sender address, instead shows house account address.
Note: House account address for this project: srvn-a944322d972