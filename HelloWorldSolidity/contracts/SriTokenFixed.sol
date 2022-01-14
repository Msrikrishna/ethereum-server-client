pragma solidity ^0.8.0;

import 'openzeppelin-solidity/contracts/token/ERC20/ERC20.sol';


contract SriTokenFixed is ERC20 {
    constructor() ERC20("SriFixedSupply", "SRIF") {
        _mint(msg.sender, 10000 * (10 ** uint256(decimals())));    //The owner of the contract gets this much coin after deployment
    }


}

