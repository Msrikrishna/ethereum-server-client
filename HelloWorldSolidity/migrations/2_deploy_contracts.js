var Counter = artifacts.require("Counter");
module.exports = function(deployer) {
    deployer.deploy(Counter);
    // Additional contracts can be deployed here
};