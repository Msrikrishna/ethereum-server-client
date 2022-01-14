# ethereum-server-client
A sample Ethereum blockchain run on Ganache with client calls from Web3j Java 

![Image](https://github.com/Msrikrishna/ethereum-server-client/blob/05ec900c7517f7ab35352b8c215a53dfcd5ef326/blobs/web3jEVM.png)





## Tech Stack

**Client:** Java Web3j

**Server:** Local Ethereum Node using Ganache


![Image](https://github.com/Msrikrishna/ethereum-server-client/blob/ca264e4dd9a2f7c4c9218853567f41c33c9578e5/blobs/UsageDiagram.png)




## Installation

Install Ganache

```bash
Download Ganache GUI tool at https://trufflesuite.com/ganache/

```
![Ganach GUI sample image](https://github.com/Msrikrishna/ethereum-server-client/blob/ca264e4dd9a2f7c4c9218853567f41c33c9578e5/blobs/GanacheGUI.png)

Install Truffle (Development tool for Smart Contracts)    

```bash
npm install -g truffle
```
## Run Locally

Clone the project

```bash
  git clone https://github.com/Msrikrishna/ethereum-server-client.git
```

Go to the project directory

```bash
  cd ethereum-server-client
```

Run a local blockchain using Ganache GUI (Create a one click blockchain). This should run a blockchain
at http://127.0.0.1:7545



Deploy smart contract to blockchain using truffle

```bash
  cd HelloWorldSolidity
  truffle compile
  truffle migrate --network development
```

Find and update the smart contract blockchain address to Java client

```bash
 On the Ganache GUI under Contracts, add HelloWorldSolidity/truffle-config.js. This will allow us to
 view the contract address on the blockchain

 Now update CONTRACT_ADDRESS under Web3jJavaClient/src/main/java/EthereumNodeCaller.java with the address 
 of the "Counter" contract
```
## Running Tests

To run client side tests, run the following command

```bash
  cd Web3jJavaClient
  mvn test
```
