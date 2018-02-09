# Coin Mixer 
A Coin mixer is a way to maintain your privacy on the crypto coin network.
Coin mixer archcitecture comprises of 3 components:
1. Mapper
2. Mixer
3. Doler

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
2. Add tax for the mixing service.
3. Re-design architecture - more efficient and fault tolerant