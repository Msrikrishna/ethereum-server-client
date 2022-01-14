pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/token/ERC20/extensions/ERC20Capped.sol";

contract SriTokenCapped is ERC20Capped{
    //Setting initial cap amount
    constructor(uint256 initialSupply) ERC20("SriCapped", "SRIC") ERC20Capped(100000 * (10 ** uint256(decimals()))){
        ERC20._mint(msg.sender, initialSupply);
    }

    function mintMinerReward() public {
        _mint(block.coinbase, 1000* (10 ** uint256(decimals())));    //
    }

}
