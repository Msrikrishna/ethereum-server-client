pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC20/extensions/ERC20Burnable.sol";

contract SriTokenFixedBurnable is ERC20Burnable {
    constructor() ERC20("SriBurnable", "SRIB") {
        _mint(msg.sender, 10000 * (10 ** uint256(decimals())));    //The owner of the contract gets this much coin after deployment
    }

    function mintMinerReward() public {
        _mint(block.coinbase, 1000* (10 ** uint256(decimals())));    //
    }

    //User can burn his tokens through a public function
    function burnMyTokens(uint256 amount) public {
        burn(amount);
    }
}

