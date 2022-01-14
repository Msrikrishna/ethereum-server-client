var SriTokenFixed = artifacts.require("SriTokenFixed");



module.exports = function(deployer) {
    deployer.deploy(SriTokenFixed);
    deployer.deploy(SriTokenFixedBurnable);

    // Additional contracts can be deployed here
};