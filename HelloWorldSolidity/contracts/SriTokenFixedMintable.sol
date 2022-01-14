pragma solidity ^0.8.0;

import 'openzeppelin-solidity/contracts/token/ERC20/ERC20.sol';


contract SriTokenFixedMintable is ERC20 {
    constructor() ERC20("SriFixedSupply", "SRIM") {
        _mint(msg.sender, 10000 * (10 ** uint256(decimals())));    //The owner of the contract gets this much coin after deployment
    }

    //Function to mint coin to the miners address. Hence this contract has unlimited supply
    function mintMinerReward() public {
        _mint(block.coinbase, 10000 * (10 ** uint256(decimals())));
    }

}

