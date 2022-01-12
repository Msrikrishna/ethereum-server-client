pragma solidity ^0.8.0;

contract Counter {
    uint count = 1440; //Stores the state on the block chain

    event Increment(uint value);  //Anyone can listen/ subscribe to this event
    event Decrement(uint value);

    constructor() {
       count = 0;
    }

    function getCount() view public returns(uint) {
        return count;
    }

    function increment() public {
        count += 1;
        emit Increment(count);
    }

    function decrement() public {
        count -= 1;
        emit Decrement(count);
    }

}
